open Parser;;
open Lexer;;
open Synt;;
open String;;


(* Declaration des variables pour l'environnement latex *)

(** Valeur mutable contenant le nombre d'indententation a realiser a la prochaine ligne.
 Cette variable est utilise en interne **)
let indentation = ref 0;;

(** Indique le flux devant etre utiliser comme sortie, (= sortie standard par defaut) **)
let tamagocc_convert_out = ref stdout;;


(** Indique la largeur pour placer une marque d'indentation (par defaut = 3) **)
let indent_width = ref 3;;

(** Indique la colonne courante dans le document (cette variable est utilisee en interne) **)
let cursor = ref 0;;

(** Indique si on nous a demander une ligne en mode lazy **)
let mustnewline = ref true;;

(** Indique si le mot en cours d'ecriture est un mot cle **)
let iscmd = ref false;;
(** ------------------------------------------------------------------- *) 

let addindent() =
  indentation := !indentation + 1
;;
let subindent() = 
	if((!indentation) > 0) then
		(indentation := !indentation - 1)
	
;;



let ecrire_hd msg =
  Printf.fprintf (!tamagocc_convert_out) "%s" (msg);
  cursor := !cursor + (String.length msg)
;;

let keyword_hd msg =
  ecrire_hd msg
;;

let indentmark() =  
  if(!iscmd) then
     Printf.fprintf (!tamagocc_convert_out) "}%s \\kw{" ("\\= ")
  else
     Printf.fprintf (!tamagocc_convert_out) "%s" ("\\= ")
;;

let indent() =
  for i=1 to (!indentation)
  do
    Printf.fprintf !tamagocc_convert_out "\\>  ";
  done;
;;

let newline() =
  if(!cursor != 0) then 
  begin
     Printf.fprintf (!tamagocc_convert_out) "\\\\\n";
    cursor:=0;
  end
;;

let forcenewline() =
  begin
    Printf.fprintf (!tamagocc_convert_out) "\\\\\n";
    cursor:=0;
  end
;;



let rec ecrire_rec msg =
  let len = (String.length msg) in
    if len > 0 then
      if !cursor >= !indent_width then
	begin
	  indentmark();
	  ecrire_hd msg;
	end
      else
	begin
          Printf.fprintf (!tamagocc_convert_out) "%s" (String.sub msg 0 1);
         (if msg.[0] != '$' then 
             cursor := !cursor + 1);
	  ecrire_rec (String.sub msg 1 (len-1));
	end
;;


let rec keyword_rec msg =
  let len = (String.length msg) in
    if len > 0 then
      if !cursor >= !indent_width then
	begin
	  indentmark();
	  keyword_hd msg;
	end
      else
	begin
          Printf.fprintf (!tamagocc_convert_out) "%s" (String.sub msg 0 1);
	  cursor := !cursor + 1; (* un keyword ne peut pas etre en mode math *)
	  keyword_rec (String.sub msg 1 (len-1));
	end
;;


let keyword msg =
  iscmd := true;
  (if(!cursor > !indent_width) then
    begin
      Printf.fprintf (!tamagocc_convert_out) "\\kw{%s} " (msg);
      cursor := !cursor + (String.length msg);
    end
  else
   begin
    (if(!cursor = 0) then indent());
    Printf.fprintf (!tamagocc_convert_out) "%s" "\\kw{";
    keyword_rec msg;
    Printf.fprintf (!tamagocc_convert_out) "%s" "} ";
   end );
  iscmd := false;
;;

let ecrire msg =
   if(!cursor > !indent_width) then
    begin
      Printf.fprintf (!tamagocc_convert_out) "%s" (msg);
      cursor := !cursor + (String.length msg);
    end
  else if (!cursor = 0) then
     begin
        indent();
        ecrire_rec msg;
     end
  else
    ecrire_rec msg
;;
    

let space i = 
  ecrire ("\\hspace{"^(string_of_int i)^"pt}")
;;
		 


let newline_add() =
  begin
    addindent();
    newline()
  end
;;

let newline_sub() =
  begin
    subindent();
    newline()
  end
;;

(** ------------------------------------------------------------------- *) 

let rec string_tamagotype r = 
   match r with 
  | Tint -> keyword "int"
  | Treal -> keyword "real"
  | Tbool -> keyword "bool"
  | Tvoid -> keyword "void"
  | Tstring -> ecrire "String"
  | Tobject(t,g) -> 
      begin
	if((List.length g) > 0) then
	  begin 
	    ecrire t;
	    ecrire "$\\langle$";
	    ecrire_generic g;
	    ecrire "$\\rangle$";
	  end
	else
	  ecrire t
      end
  | Tarray(t) -> 
        begin
            (string_tamagotype t);
            ecrire "[]"
        end
and ecrire_generic g=
  match g with
    | [] -> ()
    | t::[] -> (string_tamagotype t)
    | t::tl -> 
	begin
	  (string_tamagotype t);
	  ecrire ",";
	  ecrire_generic tl
	end
;;

let string_relop op = 
  match op with
  | Plt -> " $<$ "
  | Pleq -> " $\\leq$ "
  | Pgt -> " $>$ "
  | Pgeq -> " $\\geq$ "
  | Peq -> " $=$ "
  | Pneq -> " $\\neq$ "
;;

let string_binop op = 
  match op with
  | Padd -> " $+$ "
  | Psub -> " $-$ "
  | Ptimes -> " $\\times$ "
  | Pquo -> " $\\div$ "
  | Pmod -> " $%$ "
;;

let rec ecrire_rec_string_list sl =
 match sl with
  | [] -> ()
  | a::[] -> (ecrire a)
  | a::l -> 
    begin
      (ecrire a);
      (ecrire_rec_string_list l);
    end
;;

let rec exprs_to_tex str el =
match el with
|[] -> ()
| [b] -> (expr_to_tex b)
| a::b::sl -> 
	begin
	  expr_to_tex a;
	  ecrire str;
	  exprs_to_tex str (b::sl);
	end
and expr_to_tex e =
match e with
| Pbool(true) -> keyword "true"
| Pbool(false) -> keyword "false"
| Prel(r,e1,e2) ->
    begin
      expr_to_tex e1;
      ecrire (string_relop r);
      expr_to_tex e2;
    end
| Pinlabel(v,e1) ->
    begin
      expr_to_tex v;
      ecrire ".";
      expr_to_tex e1
    end
| Pcast(t,e) ->
    begin
      ecrire "((";
      (string_tamagotype t);
      ecrire ") ";
      expr_to_tex e;
      ecrire ")";
    end
| Pand(el) ->
	begin
	  exprs_to_tex " $\\land$ " el;
	end
| Por(el) ->
	begin
	  exprs_to_tex " $\\lor$ " el;
	end
| Pxor(el) ->
	begin
	  exprs_to_tex " \\^ " el;
	end
| Pnot(s) ->
	begin
	  ecrire "$!$";
	  expr_to_tex s;
	end
| Preturn -> keyword "@return "
| Preturnindex(e) -> 
    begin
      keyword "@return ";
      ecrire "\\[";
      expr_to_tex e;
      ecrire "\\]"
    end
| Pvar(n) -> 
	begin
	  ecrire n;
	end
| Pcall(n,el) ->
	begin
	  ecrire n;
	  ecrire "(";
	  exprs_to_tex "," el;
	  ecrire ")"
	end
| Patpre(e) ->
	begin
	  expr_to_tex e;
	  keyword "@pre";
	end
| Pproperty(n) -> ecrire ("$"^n^"$")
| Pbinop(o,e1,e2) ->
	begin
		expr_to_tex e1;
		ecrire (string_binop o);
		expr_to_tex e2;
	end
| Pint(i) -> ecrire (string_of_int i)
| Preal(r) -> ecrire (string_of_float r)
| Pstring(s) ->
	begin
	  ecrire "\"";
	  ecrire s;
	  ecrire "\""
	end
| Pimply(a,b) ->
   begin
     expr_to_tex a;
     ecrire "$\\implies$ ";
     expr_to_tex b;
   end
| Pequiv(a,b) ->
	begin
	  expr_to_tex a;
	  ecrire "$\\equiv$ ";
	  expr_to_tex b;
	end
| Pnull -> keyword "$null$"
| Pquantifierrange(q,a,t,min,max,body) ->
	(quantrange_to_tex q a t min max body)
| Pquantifierset(q,a,t,set,body) ->
	(quantset_to_tex q a t set body)
| Pquantifiercoll(q,a,t,coll,body) ->
	(quantcoll_to_tex q a t coll body)
| Planguage(s) ->
	begin
		ecrire "(";
		ecrire s;
		ecrire ")";
	end
| Pinstate(sl) ->
  begin
        ecrire "(";
	ecrire_rec_string_list sl;
	ecrire ")";
  end
    | Pvarindex(n,e) -> 
	begin
	  ecrire n;
	  ecrire "\\[";
	  expr_to_tex e;
	  ecrire "\\]"
	end
    | Ppropertyindex(n,idx) -> 
	begin
	  ecrire ("$"^n^"$");
	  ecrire "\\[";
	  expr_to_tex idx;
	  ecrire "\\]"
	end
and exprset_to_tex set =
  match set with
    | [] -> ()
    | e::[] -> 
	begin 
		expr_to_tex e; 
		ecrire " "; 
	end
    | e::sl ->
	begin
	 expr_to_tex e;
	 ecrire " ";
	 exprset_to_tex sl;
	end
and quantrange_to_tex quant a t min max body =
  begin
    ecrire "$\\";
    ecrire (quantifierop_to_string quant);
    ecrire "$ ";
    ecrire a;
    ecrire " : ";
    (string_tamagotype t);
    ecrire " \\in [";
    expr_to_tex min;
    ecrire ";";
    expr_to_tex max;
    ecrire "] ";
    ecrire "\\{";
    newline_add();
    expr_to_tex body;
    newline_sub();
    ecrire "\\}";
  end
and quantset_to_tex quant a t set body = 
  begin
    ecrire "$\\";
    ecrire (quantifierop_to_string quant);
    ecrire "$ ";
    ecrire a;
    ecrire " : ";
    (string_tamagotype t);
    ecrire " [";
    exprset_to_tex set;
    ecrire "] ";
    ecrire "\\{";
    newline_add();
    expr_to_tex body;
    newline_sub();
    ecrire "\\}";
  end
and quantcoll_to_tex quant a t coll body =
  begin
    ecrire "$\\";
    ecrire (quantifierop_to_string quant);
    ecrire "$ ";
    ecrire a;
    ecrire " : ";
    (string_tamagotype t);
    ecrire " [";
    expr_to_tex coll;
    ecrire "] ";
    ecrire "\\{";
    newline_add();
    expr_to_tex body;
    newline_sub();
    ecrire "\\}";
  end
and quantifierop_to_string q =
  match q with
    | Forall -> "forall"
    | Exist -> "exist"

;;

(* ******************************************** *)

exception TooComplexScope;;
let rec simplexpr_to_tex expr =
  match expr with
    | Pvar(s) -> ecrire s
    | Pinlabel(s,e) -> 
	begin
	  simplexpr_to_tex s;
	  ecrire ".";
	  simplexpr_to_tex e
	end
    | _ -> raise TooComplexScope
;;

let parameter_to_tex (Parameter(v,t)) =
  begin
    (string_tamagotype t);
    ecrire " ";
    ecrire v
  end

let rec parameters_to_tex pl =
  match pl with
    | [] -> ()
    | a::[] -> parameter_to_tex a
    | a::sl -> 
	begin
	  parameter_to_tex a;
	  ecrire ", ";
	  parameters_to_tex sl
	end
;;

let condition_to_tex condopt =
  match condopt with
    | NoCondition -> ()
    | Precondition(cat,msg,expr) ->
	begin
	  keyword "pre";
	  expr_to_tex expr;
	  if(String.length msg) > 0 then
	    begin
	      ecrire " ";
              keyword "fail";
	      ecrire msg;
	    end;
	    ecrire ";";
	end
    | Postcondition(cat,msg,expr) ->
		begin
	  keyword "post";
	  expr_to_tex expr;
	  if(String.length msg) > 0 then
	    begin
	      ecrire " ";
              keyword "fail";
	      ecrire msg;
	    end;
	    ecrire ";";
	end
    | Invariant(cat,msg,expr) ->
	begin
	  keyword "invariant";
	  expr_to_tex expr;
	  if (String.length msg) > 0 then
	    begin
	      ecrire " ";
              keyword "fail";
	      ecrire msg;
	    end;
	    ecrire ";";
	end
    | Condition(cat,msg,expr) ->
	begin
	  keyword "condition ";
	  expr_to_tex expr;
	  if(String.length msg) > 0 then
	    begin
	      ecrire " ";
              keyword "fail";
	      ecrire msg;
	    end;
	    ecrire ";";
	end
;;

let method_to_tex (Method(name,t,params,ident,pre,post)) =
  begin
    keyword "method";
    (string_tamagotype t);
    ecrire " ";
    ecrire name;
    ecrire "(";
    parameters_to_tex params;
    ecrire ")";
    ecrire " \\{";
    newline_add();
    begin
      match ident with
	| "" -> ()
	| _ -> 
	    begin
	      keyword "id";
	      ecrire ident;
	      ecrire ";";
	      newline();
	    end
    end;
    condition_to_tex pre;
    newline();
    condition_to_tex post;
    newline_sub();
    ecrire "\\}";
  end
;;

let string_access a =
  match a with
    | Aread -> "read"
    | Awrite -> "write"
    | Areadwrite -> "readwrite"
;;


(* function None -> ... | Some(c) -> ...  fo curryfie les fonctions*)

let extend_to_tex e= 
  match e with 
    | Refine(s,m) -> 
	begin
	  keyword "refine";
	  keyword "service";
          ecrire m;
	  ecrire ".";
	  ecrire s;
	  ecrire ";";
	end
    | Include(s,m) -> 
	begin
	  keyword "include";
          keyword "service";
	  ecrire m;
	  ecrire ".";
	  ecrire s;
	  ecrire ";";
	end
;;

let rec extends_to_tex pl = 
  match pl with
    | [] -> ()
    | a::sl -> (extend_to_tex a;newline(); extends_to_tex sl)
;;


let property_to_tex (Property(n,t,a)) =
  begin
    keyword "property";
    keyword (string_access a);
    ecrire " ";
    (string_tamagotype t);
    ecrire " "; 
    ecrire n;
    ecrire ";"
  end
;;

let rec properties_to_tex pl = 
  match pl with
    | [] -> ()
    | a::sl -> (property_to_tex a;newline(); properties_to_tex sl)
;;

let rec invariants_to_tex il =
  match il with
    | [] -> ()
    | a::sl ->
	begin
	  (condition_to_tex a);
	  newline();
	  (invariants_to_tex sl)
	end
;;

let rec methods_to_tex pl = 
  match pl with
    | [] -> ()
    | a::sl -> (method_to_tex a;newline(); methods_to_tex sl)
;;


let allow_to_tex a =
  begin
    keyword "allow";
    keyword "method";
    ecrire a;
    ecrire ";";
  end
;;  

let rec allows_to_tex ta =
  match ta with
    | [] -> ()
    | a::sl -> (allow_to_tex a; newline(); allows_to_tex sl)
;;

exception WrongNamespace;;
let rec namespace_to_tex a =
  match a with
    | EndNamespace -> raise WrongNamespace;
    | Namespace("*",EndNamespace) ->
	begin
	  ecrire "*";
	end
    | Namespace(s,EndNamespace) ->
	begin
	  ecrire s;
	end
    | Namespace(s,sa) ->
	begin
	  ecrire s;
	  ecrire ".";
	  namespace_to_tex sa;
	end
;;

let using_to_tex (Using a) =
 if(a != EndNamespace) then
    begin
        keyword "using";
 	(match a with
	 | Namespace(s,EndNamespace) ->
	  ecrire s;
	 | Namespace(s,sa) -> 
	 	begin
	     ecrire s;
	     ecrire ".";
	     namespace_to_tex sa
	     end
	 | _ -> raise WrongNamespace
      );
      ecrire ";"
    end;
;;

let usings_to_tex ta = 
  let rec aux_usings_to_tex ta =
    match ta with
      | [] -> ()
      | a::sl -> (using_to_tex a; newline(); aux_usings_to_tex sl)
  in
    match ta with
      | [] -> ()
      | _ -> 
	  begin
	    aux_usings_to_tex ta;
	  end
;;




let state_to_tex (State(s,ta)) = 
  begin
    keyword "state";
    ecrire s;
    ecrire " \\{";
    newline_add();
    allows_to_tex ta;
    newline_sub();
    ecrire "\\}"
  end
;;



let rec states_to_tex pl = 
  match pl with
    | [] -> ()
    | a::sl -> (state_to_tex a;newline(); states_to_tex sl)
;;

let transition_to_tex t = 
  match t with 
    | Transition(f,t,w) ->
	begin
	  keyword "transition";
          keyword "from";
	  ecrire f;
          ecrire " ";
	  keyword "to";
	  ecrire t;
          ecrire " ";
	  keyword "with";
	  ecrire w;
	  ecrire ";";
	end
    | TransitionCond(f,t,w,c) ->
	begin
	  keyword "transition";
	  keyword "from";
	  ecrire f;
          ecrire " ";
	  keyword "to";
	  ecrire t;
          ecrire " ";
	  keyword "with";
	  ecrire w;
          ecrire " ";
	  keyword "guard";
	  expr_to_tex c;
	  ecrire ";";
	end
;;

let rec transitions_to_tex tl =
  match tl with
    | [] -> ()
    | a::sl -> (transition_to_tex a; newline(); transitions_to_tex sl)
;;

let behavior_to_tex b =
  match b with
    | NoBehavior -> ()
    | Behavior(init,states,trans) ->
	begin
	  keyword "behavior";
	  ecrire "\\{";
	  newline_add();
	  (match init with
	     | "" -> ()
	     | _ -> 
		 begin
		   keyword "default";
		   keyword "state";
		   ecrire init;
		   ecrire ";"
		 end);
	  newline();
	  if (states != []) then
	    begin
	      keyword "states";
	      ecrire "\\{";
	      newline_add();
	      states_to_tex states;
	      newline_sub();
	      ecrire "\\}";
	      newline();
	    end;
	  if(trans != []) then
	    begin
	      keyword "transitions";
	      ecrire "\\{";
	      newline_add();
	      transitions_to_tex trans;
	      newline_sub();
	      ecrire "\\}";
	    end;
	  newline_sub();
	  ecrire "\\}";
	end
;;

let rec implements_to_tex l =
  match l with
    | [] -> ()
    | Implements(interface)::[] ->
	impl_to_tex interface
    | Implements(interface)::sl ->
	begin
	  impl_to_tex interface;
	  newline();
	  implements_to_tex sl
	end
and impl_to_tex interface =
  begin
    ecrire "\\kw{implements} \\kw{interface}";
    ecrire interface;
    ecrire ";";
  end
;;



let require_to_tex (Require(s,m,l)) =
  begin
    keyword "require";
    keyword "service";
    ecrire m;
    ecrire ".";
    ecrire s;
    ecrire " ";
    keyword "label";
    ecrire l;
    ecrire ";";
  end
;;
let rec requires_to_tex pl =
  match pl with
    | [] -> ()
    | p::sl -> (require_to_tex p; newline(); requires_to_tex sl)
;;


let provide_to_tex (Provide(s,m)) =
  begin
    keyword "provide";
    keyword "service";
    ecrire m;
    ecrire ".";
    ecrire s;
    ecrire ";";
  end
;;

let rec provides_to_tex pl =
  match pl with
    | [] -> ()
    | p::sl -> (provide_to_tex p; newline(); provides_to_tex sl)
;;


let definition_to_tex (Definition(comp,modu,label)) =
  begin
    keyword "use";
    ecrire modu;
    ecrire ".";
    ecrire comp;
    ecrire " ";
    keyword "name";
    ecrire label;
    ecrire ";";
  end
;;

let rec definitions_to_tex dl = 
  match dl with
    | [] -> ()
    | d::sl -> (definition_to_tex d; newline(); definitions_to_tex sl)
;;



let bind_to_tex (Bind(_,serv,modu,client,provider)) =
  begin
    keyword "bind";
    keyword "service";
    ecrire serv;
    ecrire " ";
    keyword "requirer";
    simplexpr_to_tex client;
    ecrire " ";
    keyword "provider";
    simplexpr_to_tex provider;
    ecrire ";";
  end
;;

let rec binds_to_tex bl =
  match bl with
    | [] -> ()
    | b::sl -> (bind_to_tex b;newline();binds_to_tex sl)
;;

let export_to_tex (Export(serv,modu,provider)) =
  begin
    keyword "export";
    keyword "service";
    ecrire modu;
    ecrire ".";
    ecrire serv;
    ecrire " ";
    keyword "by";
    expr_to_tex provider;
    ecrire ";"
  end

let rec exports_to_tex el =
  match el with
    | [] -> ()
    | e::sl -> (export_to_tex e;newline();exports_to_tex sl)
;;


let rec percolators_to_tex pl =
  match pl with
    | [] -> ()
    | AllPercolator::_ ->
	begin
	  keyword "allpercolator"
	end
    | Percolator(s)::sl -> 
	begin
	  keyword "percolator";
	  ecrire s;
	  ecrire ";";
	  newline();
	  percolators_to_tex sl
	end
;;


(* ************************************************************** *)
(*                 Partie concernant les services                 *)
(* ************************************************************** *)


let service_to_tex imports name pack extends impls props conds funcs beh =
  begin 
    keyword "service";
    ecrire name;
    ecrire " \\{";
    newline_add();
    usings_to_tex imports;
    newline();
    extends_to_tex extends;
    newline();
    implements_to_tex impls;
    newline();
    properties_to_tex props;
    newline();
    invariants_to_tex conds;
    newline();
    methods_to_tex funcs;
    newline();
    behavior_to_tex beh;
    
    newline_sub();
    ecrire "\\}"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les composants                 *)
(* ************************************************************** *)

let component_to_tex imports n m il pl cl fl beh percol prov req =
  begin 
    keyword "component";
    ecrire n;
    ecrire "\\{";
    newline_add();
    usings_to_tex imports;
    newline();
    implements_to_tex il;
    newline();
    properties_to_tex pl;
    newline();
    percolators_to_tex percol;
    newline();
    provides_to_tex prov;
    newline();
    requires_to_tex req;
    newline();
    invariants_to_tex cl;
    newline();
    methods_to_tex fl;
    behavior_to_tex beh;
    newline_sub();
    ecrire "\\}"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les assemblages                *)
(* ************************************************************** *)


let assembly_to_tex imports n m def binds =
  begin
    keyword "assembly";
    ecrire n;
    ecrire "\\}";
    newline_add();
    usings_to_tex imports;
    newline();
    definitions_to_tex def;
    newline();
    binds_to_tex binds;
    ecrire "\\}"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les composites                 *)
(* ************************************************************** *)

let composite_to_tex imports n m il pl cl fl beh perco prov req exp def bind =
  begin 
    keyword "composite";
    ecrire n;
    ecrire "\\{";
    newline_add();
    usings_to_tex imports;
    implements_to_tex il;
    newline();
    newline();
    properties_to_tex pl;
    newline();
    percolators_to_tex perco;
    newline();
    definitions_to_tex def;
    newline();
    provides_to_tex prov;
    newline();
    requires_to_tex req;
    newline();
    exports_to_tex exp;
    newline();
    binds_to_tex bind;
    newline();
    invariants_to_tex cl;
    methods_to_tex fl;
    behavior_to_tex beh;
    newline_sub();
    ecrire "\\}"
  end
;;




(* ************************************************************** *)
(*            Partie concernant la fonction publique              *)
(* ************************************************************** *)

let tamago_to_tex t output =
  begin
    tamagocc_convert_out := output;
    Printf.fprintf (!tamagocc_convert_out) "%s" ("\\begin{program}\n");
    newline();
    begin
      match t with
	| Service(imports,n,m,el,il,pl,cl,fl,beh) ->
	    service_to_tex imports n m el il pl cl fl beh
	| Component(imports,n,m,il,pl,cl,fl,beh,percol,prov,req) ->
	    component_to_tex imports n m il pl cl fl beh percol prov req
	| Assembly(imports,n,m,dl,bl) ->
	    assembly_to_tex imports n m dl bl
	| Composite(imports,n,m,il,pl,cl,fl,beh,perco,prov,req,exp,def,bind) ->
	    composite_to_tex imports n m il pl cl fl beh perco prov req exp def bind
    end;
    Printf.fprintf (!tamagocc_convert_out) "%s" ("\n\\end{program}");
  end
;;

