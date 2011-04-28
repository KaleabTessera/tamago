(* ************************************************************ *)
(* ************************************************************ *)
(* 
   This file convert the compact syntax into a xml file that 
   correspond to the CDL file.
   
   @author: Hakim Belhaouari
*)
(* ************************************************************ *)
(* ************************************************************ *)

open Parser;;
open Lexer;;
open Synt;;
open String;;
open Str;;

(* internal variable that correspond to the position of the indentation *)
let indentation = ref 0;;

(* external variable that represent the output stream for the generated code *)
let tamagocc_convert_out = ref stdout;;



(* internal variable that indicates, if we must indent the code *)
let must_indent = ref true;;

(* internal variable that indicates, if we write something
   on the current line. This is avoid empty lines *) 
let has_write = ref false;;

let space_string = ref "   ";;
(* let space_character = ref "\t";; *)

let newline_string = ref "\n";;
(* let newline_character = ref "\n";; *)

(* -------------------------------------------------------------------- *)


(* -------------------------------------------------------------------- *)

let addindent() =
  indentation := !indentation + 1
;;

let subindent() = 
  if((!indentation) > 0) then
    (indentation := !indentation - 1)
      
;;

let indent() =
  for i=1 to (!indentation)
  do
    Printf.fprintf (!tamagocc_convert_out) "%s" (!space_string);
    ignore(i);
  done;
;;

let newline() =
  begin
    if(!has_write) then
      begin
	Printf.fprintf (!tamagocc_convert_out) "%s" (!newline_string);
	must_indent := true;
	has_write := false;
      end      
  end
;;


let write msg = 
  let write_aux msg =
    if(msg = '\n') then
      newline()
    else
      begin
	if(!must_indent) then
	  begin
	    indent();
	    must_indent := false
	  end;
	Printf.fprintf (!tamagocc_convert_out) "%c" (msg);
	has_write:= true;
      end
  in
    String.iter write_aux msg;
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

(* ************************************************************** *)
(*                 Partie concernant les contracts                *)
(* ************************************************************** *)

let xml_generic name =
  (Str.global_replace (Str.regexp_string "<")
	  "&lt;" (Str.global_replace (Str.regexp_string ">")
					"&gt;" name))
;;

let rec write_each_instate sl =
 match sl with
 | [] -> ()
 | a::l -> 
   begin
     (write ("<state name=\""^a^"\" />"));
     (write_each_instate l);
   end
;;

let rec tamagotype_generiques g = 
  match g with
    | [] ->  ""
    | t::[] -> (string_tamagotype t)
    | t::lt -> (string_tamagotype t)^","^(tamagotype_generiques lt)
and  string_tamagotype r = 
  match r with 
    | Tint -> "int"
    | Treal -> "real"
    | Tbool -> "bool"
    | Tvoid -> "void"
    | Tstring -> "String"
    | Tobject(t,g) -> 
		  begin
			 if ((List.length g) > 0) then
				(t^"&lt;"^(tamagotype_generiques g)^"&gt;")
			 else
				t
		  end
    | Tarray(t) -> (string_tamagotype t)^"[]"
;;

let string_relop op = 
  match op with
    | Plt -> "lt"
    | Pleq -> "le"
    | Pgt -> "gt"
    | Pgeq -> "ge"
    | Peq -> "eq"
    | Pneq -> "ne"
;;

let string_binop op = 
  match op with
    | Padd -> "add"
    | Psub -> "sub"
    | Ptimes -> "mul"
    | Pquo -> "quo"
    | Pmod -> "mod"
;;


let rec exprs_to_xml el =
  match el with
    |[] -> ()
    | a::sl -> 
	begin
	  expr_to_xml a;
	  newline();
	  exprs_to_xml sl;
	end
and flatand el = 
  match el with
    | [] -> []
    | (Pand(e))::sl -> flatand (e@sl)
    | x::sl -> x::(flatand sl)
and flator el =
  match el with
    | [] -> []
    | (Por(e))::sl -> flator (e@sl)
    | x::sl -> x::(flator sl)
and flatxor el = 
  match el with
    | [] -> []
    | (Pxor(e))::sl -> flatxor (e@sl)
    | x::sl -> x::(flatxor sl)    
and expr_to_xml e =
  match e with
    | Pbool(true) -> write "<bool value=\"true\" />"
    | Pbool(false) -> write "<bool value=\"false\" />"
    | Prel(r,e1,e2) ->
	begin
	  write "<operator name=\"";
	  write (string_relop r);
	  write "\" >";
	  newline_add();
	  expr_to_xml e1;
	  newline();
	  expr_to_xml e2;
	  newline_sub();
	  write "</operator>"
	end
    | Pinlabel(v,e1) ->
	begin
	  write "<in>";
	  newline_add();
	  write "<scope>";
	  newline_add();
	  expr_to_xml v;
	  newline_sub();
	  write "</scope>";
	  newline();
	  write "<target>";
	  newline_add();
	  expr_to_xml e1;
	  newline_sub();
	  write "</target>";
	  newline_sub();
	  write "</in>";
	end
    | Pcast(t,e) ->
		  begin
			 write "<cast type=\"";
			 write (xml_generic (string_tamagotype t));
			 write "\" >";
			 newline_add();
			 write "<expression>";
			 newline_add();
			 expr_to_xml e;
			 newline_sub();
			 write "</expression>";
			 newline_sub();
			 write "</cast>";
		  end
    | Pand(el) ->
	begin
	  write "<operator name=\"and\" >";
	  newline_add();
	  exprs_to_xml (flatand el);
	  newline_sub();
	  write "</operator>"
	end
    | Por(el) ->
	begin
	  write "<operator name=\"or\" >";
	  newline_add();
	  exprs_to_xml (flator el);
	  newline_sub();
	  write "</operator>"
	end
    | Pxor(el) ->
	begin
	  write "<operator name=\"xor\" >";
	  newline_add();
	  exprs_to_xml (flatxor el);
	  newline_sub();
	  write "</operator>"
	end
    | Pnot(s) ->
	begin
	  write "<not>";
	  newline_add();
	  expr_to_xml s;
	  newline_sub();
	  write "</not>";
	end
    | Preturn -> write "<return />"
    | Preturnindex(e) ->
	begin
	  write "<return>";
	  newline_add();
	  write "<index>";
	  newline_add();
	  expr_to_xml e;
	  newline_sub();
	  write "</index>";
	  newline_sub();
	  write "</return>"
	end
    | Pnull -> write "<nil />"
    | Pvar(n) -> 
	begin
	  write "<variable name=\"";
	  write n;
	  write "\" />"
	end
    | Pvarindex(n,e) -> 
	begin
	  write "<variable name=\"";
	  write n;
	  write "\" >";
	  newline_add();
	  write "<index>";
	  newline_add();
	  expr_to_xml e;
	  newline_sub();
	  write "</index>";
	  newline_sub();
	  write "</variable>"
	end
    | Ppropertyindex(n,idx) -> 
	begin
	  write "<read property=\"";
	  write n;
	  write "\" >";
	  newline_add();
	  write "<index>";
	  newline_add();
	  expr_to_xml idx;
	  newline_sub();
	  write "</index>";
	  newline_sub();
	  write "</read>"
	end
    | Pcall(n,el) ->
	begin
	  write "<call method=\"";
	  write n;
	  write "\" >";
	  newline_add();
	  write "<arguments>";
	  newline_add();
	  exprs_to_xml el;
	  newline_sub();
	  write "</arguments>";
	  newline_sub();
	  write "</call>"
	end
    | Patpre(e) ->
	begin
	  write "<atpre>";
	  newline_add();
	  expr_to_xml e;
	  newline_sub();
	  write "</atpre>"
	end
    | Pproperty(n) -> write ("<read property=\""^n^"\" />")
    | Pbinop(o,e1,e2) ->
	begin
	  write "<operator name=\"";
	  write (string_binop o);
	  write "\" >";
	  newline_add();
	  expr_to_xml e1;
	  newline();
	  expr_to_xml e2;
	  newline_sub();
	  write "</operator>"
	end
    | Pint(i) -> write ("<int value=\""^(string_of_int i)^ "\" />")
    | Preal(r) -> write ("<real value=\""^(string_of_float r)^ "\" />")
    | Pstring(s) ->
	begin
	  write "<string>";
	  write s;
	  write "</string>"
	end
    | Pimply(a,b) ->
	begin
	  write "<operator name=\"impl\" >";
	  newline_add();
	  expr_to_xml a;
	  expr_to_xml b;
	  newline_sub();
	  write "</operator>"
	end
    | Pequiv(a,b) ->
	begin
	  write "<operator name=\"equiv\" >";
	  newline_add();
	  expr_to_xml a;
	  expr_to_xml b;
	  newline_sub();
	  write "</operator>"
	end
    | Pquantifierrange(q,a,t,min,max,body) ->
	(quantrange_to_xml q a t min max body)
    | Pquantifierset(q,a,t,set,body) ->
	(quantset_to_xml q a t set body)
    | Pquantifiercoll(q,a,t,coll,body) ->
	(quantcoll_to_xml q a t coll body)
    | Planguage(s) ->
	begin
	  write "<lang-expr>";
	  write s;
	  write "</lang-expr>";
	end
    | Pinstate(sl) ->
        begin
          write "<instate>";
          write_each_instate sl;
          write "</instate>";
        end
and exprset_to_xml set =
  match set with
    | [] -> ()
    | e::sl ->
	begin
	  write "<element>";
	  expr_to_xml e;
	  write "</element>";
	  newline();
	  exprset_to_xml sl;
	end
and quantrange_to_xml quant a t min max body =
  begin
    write "<quantifier variable=\"";
    write a;
    write "\" type=\"";
    write (string_tamagotype t);
    write "\" quantifier=\"";
    write (quantifierop_to_string quant);
    write "\">";
    newline_add();
    write "<range>";
    newline_add();
    write "<min>";
    newline_add();
    expr_to_xml min;
    newline_sub();
    write "</min>";
    newline();
    write "<max>";
    newline_add();
    expr_to_xml max;
    newline_sub();
    write "</max>";
    newline_sub();
    write "</range>";
    newline();
    write "<body>";
    newline_add();
    expr_to_xml body;
    newline_sub();
    write "</body>";
    newline_sub();
    write "</quantifier>";
  end
and quantset_to_xml quant a t set body = 
  begin
    write "<quantifier variable=\"";
    write a;
    write "\" type=\"";
    write (string_tamagotype t);
    write "\" quantifier=\"";
    write (quantifierop_to_string quant);
    write "\">";
    newline_add();
    write "<set>";
    newline_add();
    exprset_to_xml set;
    newline_sub();
    write "</set>";
    newline();
    write "<body>";
    newline_add();
    expr_to_xml body;
    newline_sub();
    write "</body>";
    newline_sub();
    write "</quantifier>"
  end
and quantcoll_to_xml quant a t coll body = 
  begin
    write "<quantifier variable=\"";
    write a;
    write "\" type=\"";
    write (string_tamagotype t);
    write "\" quantifier=\"";
    write (quantifierop_to_string quant);
    write "\">";
    newline_add();
    write "<collection>";
    newline_add();
    expr_to_xml coll;
    newline_sub();
    write "</collection>";
    newline();
    write "<body>";
    newline_add();
    expr_to_xml body;
    newline_sub();
    write "</body>";
    newline_sub();
    write "</quantifier>";
  end
and quantifierop_to_string q =
  match q with
    | Forall -> "forall"
    | Exist -> "exist"
;;


exception TooComplexScope;;
let rec simplexpr_to_xml expr =
  match expr with
    | Pvar(s) -> write s
    | Pinlabel(s,e) -> 
	begin
	  simplexpr_to_xml s;
	  write ".";
	  simplexpr_to_xml e
	end
    | _ -> raise TooComplexScope
;;

let parameter_to_xml (Parameter(v,t)) =
  begin
    write "<parameter name=\"";
    write v;
    write "\" type=\"";
    write (string_tamagotype t);
    write "\" />"
  end

let rec parameters_to_xml pl =
  match pl with
    | [] -> ()
    | a::[] -> parameter_to_xml a
    | a::sl -> 
	begin
	  parameter_to_xml a;
	  newline();
	  parameters_to_xml sl
	end
;;

let condition_to_xml condopt =
  match condopt with
    | NoCondition -> ()
    | Precondition(cat,msg,expr) ->
	begin
	  write "<pre ";
	  if(msg != "") then
	    begin
	      write "message=\"";
	      write msg;
	      write "\"";
	    end;
	  write " >";
	  newline_add();
	  expr_to_xml expr;
	  newline_sub();
	  write "</pre>"
	end
    | Postcondition(cat,msg,expr) ->
	begin
	  write "<post ";
	  if(msg != "") then
	    begin
	      write "message=\"";
	      write msg;
	      write "\"";
	    end;
	  write " >";
	  newline_add();
	  expr_to_xml expr;
	  newline_sub();
	  write "</post>"
	end
    | Invariant(cat,msg,expr) ->
	begin
	  write "<invariant ";
	  if(msg != "") then
	    begin
	      write "message=\"";
	      write msg;
	      write "\"";
	    end;
	  write " >";
	  newline_add();
	  expr_to_xml expr;
	  newline_sub();
	  write "</invariant>"
	end
    | Condition(cat,msg,expr) ->
	begin
	  write "<condition ";
	  if(msg != "") then
	    begin
	      write "message=\"";
	      write msg;
	      write "\"";
	    end;
	  write " >";
	  newline_add();
	  expr_to_xml expr;
	  newline_sub();
	  write "</condition>"
	end
;;

let method_to_xml (Method(name,t,params,ident,pre,post)) =
  begin
    write "<method name=\"";
    write name;
    write "\" rettype=\"";
    write (string_tamagotype t);
    begin
      match ident with
	| "" -> ()
	| _ -> 
	    begin
	      write "\" id=\"";
	      write ident;
	    end
    end;
    write "\" >";
    newline_add();
    write "<parameters>";
    newline_add();
    parameters_to_xml params;
    newline_sub();
    write "</parameters>";
    newline();
    condition_to_xml pre;
    newline();
    condition_to_xml post;
    newline_sub();
    write "</method>"	
  end
;;

let string_access a =
  match a with
    | Aread -> "r"
    | Awrite -> "w"
    | Areadwrite -> "rw"
;;


(* function None -> ... | Some(c) -> ...  fo curryfie les fonctions*)

let extend_to_xml e= 
  match e with 
    | Refine(s,m) -> 
	begin
	  write "<refine service=\"";
	  write (xml_generic s);
	  write "\" module=\"";
	  write m;
	  write "\" />";
	end
    | Include(s,m) -> 
	begin
	  write "<include service=\"";
	  write (xml_generic s);
	  write "\" module=\"";
	  write m;
	  write "\" />";
	end
;;

let rec extends_to_xml pl = 
  match pl with
    | [] -> ()
    | a::sl -> (extend_to_xml a;newline(); extends_to_xml sl)
;;


let property_to_xml (Property(n,t,a)) =
  begin
    write "<property name=\"";
    write n;
    write "\" type=\"";
    write (string_tamagotype t);
    write "\" access=\"";
    write (string_access a);
    write "\" />"
  end
;;

let rec properties_to_xml pl = 
  match pl with
    | [] -> ()
    | a::sl -> (property_to_xml a;newline(); properties_to_xml sl)
;;

let rec invariants_to_xml il =
  match il with
    | [] -> ()
    | a::sl ->
	begin
	  (condition_to_xml a);
	  newline();
	  (invariants_to_xml sl)
	end
;;

let rec methods_to_xml pl = 
  match pl with
    | [] -> ()
    | a::sl -> (method_to_xml a;newline(); methods_to_xml sl)
;;


let allow_to_xml a =
  begin
    write "<allow method=\"";
    write a;
    write "\" />";
  end
;;  

let rec allows_to_xml ta =
  match ta with
    | [] -> ()
    | a::sl -> (allow_to_xml a; newline(); allows_to_xml sl)
;;

exception WrongNamespace;;
let rec namespace_to_xml a =
  match a with
    | EndNamespace -> raise WrongNamespace;
    | Namespace("*",EndNamespace) ->
	begin
	  write "\" >";
	  newline_add();
	  write "<anyentity />";
	end
    | Namespace(s,EndNamespace) ->
	begin
	  write "\" >";
	  newline_add();
	  write "<entity value=\"";
	  write s;
	  write "\" />";
	end
    | Namespace(s,sa) ->
	begin
	  write ".";
	  write s;
	  namespace_to_xml sa;
	end
;;

let using_to_xml (Using a) =
  write "<using>";
  newline_add();
  if(a != EndNamespace) then
    begin
      write "<package value=\"";
      (match a with
	 | Namespace(_,EndNamespace) -> raise WrongNamespace
	 | Namespace(s,sa) -> 
	     write s; 
	     namespace_to_xml sa
	 | _ -> raise WrongNamespace
      );
      newline_sub();
      write "</package>"
    end;
  newline_sub();
  write "</using>"
;;

let usings_to_xml ta = 
  let rec aux_usings_to_xml ta =
    match ta with
      | [] -> ()
      | a::sl -> (using_to_xml a; newline(); aux_usings_to_xml sl)
  in
    match ta with
      | [] -> ()
      | _ -> 
	  begin
	    write "<imports>";
	    newline_add();
	    aux_usings_to_xml ta;
	    newline_sub();
	    write "</imports>";
	  end
;;




let state_to_xml (State(s,ta)) = 
  begin
    write "<state name=\"";
    write s;
    write "\" >";
    newline_add();
    allows_to_xml ta;
    newline_sub();
    write "</state>"
  end
;;



let rec states_to_xml pl = 
  match pl with
    | [] -> ()
    | a::sl -> (state_to_xml a;newline(); states_to_xml sl)
;;

let transition_to_xml t = 
  match t with 
    | Transition(f,t,w) ->
	begin
	  write "<transition from=\"";
	  write f;
	  write "\" to=\"";
	  write t;
	  write "\" with=\"";
	  write w;
	  write "\" />";
	end
    | TransitionCond(f,t,w,c) ->
	begin
	  write "<transition from=\"";
	  write f;
	  write "\" to=\"";
	  write t;
	  write "\" with=\"";
	  write w;
	  write "\">";
	  newline_add();
	  write "<condition>";
	  newline_add();
	  expr_to_xml c;
	  newline_sub();
	  write "</condition>";
	  newline_sub();
	  write "</transition>";
	end
;;

let rec transitions_to_xml tl =
  match tl with
    | [] -> ()
    | a::sl -> (transition_to_xml a; newline(); transitions_to_xml sl)
;;

let behavior_to_xml b =
  match b with
    | NoBehavior -> ()
    | Behavior(init,states,trans) ->
	begin
	  write "<behavior";
	  (match init with
	     | "" -> ()
	     | _ -> 
		 begin
		   write " default=\"";
		   write init;
		   write "\""
		 end);
	  write ">";
	  newline_add();
	  if (states != []) then
	    begin
	      write "<states>";
	      newline_add();
	      states_to_xml states;
	      newline_sub();
	      write "</states>";
	      newline();
	    end;
	  if(trans != []) then
	    begin
	      write "<transitions>";
	      newline_add();
	      transitions_to_xml trans;
	      newline_sub();
	      write "</transitions>";
	    end;
	  newline_sub();
	  write "</behavior>";
	end
;;

let rec implements_to_xml l =
  match l with
    | [] -> ()
    | Implements(interface)::[] ->
	impl_to_xml interface
    | Implements(interface)::sl ->
	begin
	  impl_to_xml interface;
	  newline();
	  implements_to_xml sl
	end
and impl_to_xml interface =
  begin
    write "<implements type=\"";
    try
      let right = String.rindex interface '.' in
      let size = (String.length interface)-right-1 in
      let sub = String.sub interface (right+1) size in
	begin
	  write (xml_generic sub);
	  write "\" module=\"";
	  write (String.sub interface 0 right);
	  write "\" />"
	end
    with
	Not_found ->
	  begin
	    write (xml_generic interface);
	    write "\" module=\"";
	    write "\" />"
	  end
  end
;;



let require_to_xml (Require(s,m,l)) =
  begin
    write "<require service=\"";
    write (xml_generic s); 
    write "\" module=\"";
    write m;
    write "\" label=\"";
    write l;
    write "\" />";
  end
;;
let rec requires_to_xml pl =
  match pl with
    | [] -> ()
    | p::sl -> (require_to_xml p; newline(); requires_to_xml sl)
;;


let provide_to_xml (Provide(s,m)) =
  begin
    write "<provide service=\"";
    write (xml_generic s);
    write "\" module=\"";
    write m;
    write "\" />";
  end
;;
let rec provides_to_xml pl =
  match pl with
    | [] -> ()
    | p::sl -> (provide_to_xml p; newline(); provides_to_xml sl)
;;


let definition_to_xml (Definition(comp,modu,label)) =
  begin
    write "<use type=\"";
    write comp;
    write "\" module=\"";
    write modu;
    write "\" label=\"";
    write label;
    write "\" />";
  end
;;

let rec definitions_to_xml dl = 
  match dl with
    | [] -> ()
    | d::sl -> (definition_to_xml d; newline(); definitions_to_xml sl)
;;



let bind_to_xml (Bind(label,serv,modu,client,provider)) =
  begin
    write "<bind service=\"";
    write (xml_generic serv);
    write "\" module=\"";
    write modu;
    write "\" requirer=\"";
    simplexpr_to_xml client;
    write "\" provider=\"";
    simplexpr_to_xml provider;
    write "\" label=\"";
    write label;
    write "\" />";
  end
;;

let rec binds_to_xml bl =
  match bl with
    | [] -> ()
    | b::sl -> (bind_to_xml b;newline();binds_to_xml sl)
;;

let export_to_xml (Export(serv,modu,provider)) =
  begin
    write "<export service=\"";
    write (xml_generic serv);
    write "\" module=\"";
    write modu;
    write "\">";
    newline_add();
    write "<provider>";
    expr_to_xml provider;
    write"</provider>";
    newline_sub();
    write "</export>";
  end

let rec exports_to_xml el =
  match el with
    | [] -> ()
    | e::sl -> (export_to_xml e;newline();exports_to_xml sl)
;;


let rec percolators_to_xml pl =
  match pl with
    | [] -> ()
    | AllPercolator::_ ->
	begin
	  write "<percolator name=\"*\" />"
	end
    | Percolator(s)::sl -> 
	begin
	  write "<percolator name=\"";
	  write s;
	  write "\" />";
	  percolators_to_xml sl
	end
;;


(* ************************************************************** *)
(*                 Partie concernant les services                 *)
(* ************************************************************** *)


let service_to_xml imports name pack extends impls props conds funcs beh =
  begin 
    write "<service name=\"";
    write (xml_generic name);
    write "\" module=\"";	 
    write pack;
    write "\" >";
    newline_add();
    usings_to_xml imports;
    newline();
    write "<extends>";
    newline_add();
    extends_to_xml extends;
    newline_sub();
    write "</extends>";
    newline();
    write "<coretypes>";
    newline_add();
    implements_to_xml impls;
    newline_sub();
    write "</coretypes>";
    newline();
    write "<properties>";
    newline_add();
    properties_to_xml props;
    newline_sub();
    write "</properties>";
    newline();
    write "<invariants>";
    newline_add();
    invariants_to_xml conds;
    newline_sub();
    write "</invariants>";
    newline();
    write "<methods>"; newline_add();
    methods_to_xml funcs;
    newline_sub(); 	
    write "</methods>";
    behavior_to_xml beh;
    newline_sub();
    write "</service>"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les composants                 *)
(* ************************************************************** *)

let component_to_xml imports n m il pl cl fl beh percol prov req =
  begin 
    write "<component name=\"";
    write (xml_generic n);
    write "\" module=\"";
    write m;
    write "\" >";
    newline_add();
    usings_to_xml imports;
    newline();
    write "<coretypes>";
    newline_add();
    implements_to_xml il;
    newline_sub();
    write "</coretypes>";
    newline();
    write "<properties>";
    newline_add();
    properties_to_xml pl;
    newline_sub();
    write "</properties>";
    newline();
    write "<percolators>";
    newline_add();
    percolators_to_xml percol;
    newline_sub();
    write "</percolators>";
    newline();
    write "<provides>"; 
    newline_add();
    provides_to_xml prov;
    newline_sub(); 
    write "</provides>";
    newline();
    write "<requires>"; 
    newline_add();
    requires_to_xml req;
    newline_sub(); 
    write "</requires>";
    newline();
    invariants_to_xml cl;
    write "<methods>"; 
    newline_add();	
    methods_to_xml fl;
    newline_sub(); 
    write "</methods>";
    behavior_to_xml beh;
    newline_sub();
    write "</component>"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les assemblages                *)
(* ************************************************************** *)


let assembly_to_xml imports n m def binds =
  begin
    write "<assembly name=\"";
    write (xml_generic n);
    write "\" module=\"";
    write m;
    write "\" >";
    newline_add();
    usings_to_xml imports;
    newline();
    write "<definitions>";
    newline_add();
    definitions_to_xml def;
    newline_sub();
    write "</definitions>";
    newline();
    write "<binds>";
    newline_add();
    binds_to_xml binds;
    newline_sub();
    write "</binds>";
    newline_sub();
    write "</assembly>"
  end
;;


(* ************************************************************** *)
(*               Partie concernant les composites                 *)
(* ************************************************************** *)

let composite_to_xml imports n m il pl cl fl beh perco prov req exp def bind =
  begin 
    write "<composite name=\"";
    write (xml_generic n);
    write "\" module=\"";
    write m;
    write "\" >";
    newline_add();
    usings_to_xml imports;
    newline();
    write "<coretypes>";
    newline_add();
    implements_to_xml il;
    newline_sub();
    write "</coretypes>";
    newline();
    newline();
    write "<properties>";
    newline_add();
    properties_to_xml pl;
    newline_sub();
    write "</properties>";
    newline();
    write "<percolators>";
    newline_add();
    percolators_to_xml perco;
    newline_sub();
    write "</percolators>";
    newline();
    write "<definitions>";
    newline_add();
    definitions_to_xml def;
    newline_sub();
    write "</definitions>";
    newline();
    write "<provides>"; 
    newline_add();
    provides_to_xml prov;
    newline_sub(); 
    write "</provides>";
    newline();
    write "<requires>"; 
    newline_add();
    requires_to_xml req;
    newline_sub(); 
    write "</requires>";
    newline();
    write "<exportations>";
    newline_add();
    exports_to_xml exp;
    newline_sub();
    write "</exportations>";
    newline();
    write "<binds>";
    newline_add();
    binds_to_xml bind;
    newline_sub();
    write "</binds>";
    newline();
    invariants_to_xml cl;
    write "<methods>"; 
    newline_add();
    methods_to_xml fl;
    newline_sub(); 
    write "</methods>";
    behavior_to_xml beh;
    newline_sub();
    write "</composite>"
  end
;;




(* ************************************************************** *)
(*            Partie concernant la fonction publique              *)
(* ************************************************************** *)

let tamago_to_xml t output =
  begin
    tamagocc_convert_out := output;
    write "<?xml version=\"1.0\" ?>";
    newline();
    write "<?xml-stylesheet type=\"text/xsl\" href=\"http://www-poleia.lip6.fr/~belhaouari/TamagoCDL.xsl\" ?>";
    newline();
    begin
      match t with
	| Service(imports,n,m,el,il,pl,cl,fl,beh) ->
	    service_to_xml imports n m el il pl cl fl beh
	| Component(imports,n,m,il,pl,cl,fl,beh,percol,prov,req) ->
	    component_to_xml imports n m il pl cl fl beh percol prov req
	| Assembly(imports,n,m,dl,bl) ->
	    assembly_to_xml imports n m dl bl
	| Composite(imports,n,m,il,pl,cl,fl,beh,perco,prov,req,exp,def,bind) ->
	    composite_to_xml imports n m il pl cl fl beh perco prov req exp def bind
    end;
    newline();
  end
;;

