%{
open Synt

let rec isClass (s:string) =
  if(('A' <= s.[0])&&(s.[0] <= 'Z')) then
    true
  else
    begin
      if(s.[0] == '_') then
	isClass(String.sub s 1 ((String.length s)-1))
      else
	false
    end
;;

type parseoperator =
    | Orelop of relop
    | Obinop of binop
    | Oand
    | Oor
    | Oxor
    | Oimply
    | Oequiv
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
    | Tarray(t) -> (string_tamagotype t)^"[]"
    | Tobject(t,g) -> 
		  begin
			 if((List.length g) > 0) then
				t^"<"^(tamagotype_generiques g)^">"
			 else
				t
		  end

;;

let log g = 
  (* (print_string g) *) ()
;;


%}


/* Constants */
%token <string> Ident
%token <int> Int
%token <bool> Bool
%token <float> Real
%token <string> String
%token <string> Commentaire
%token <string> AnyString

/* Keyword */
%token Service Component Composite Assembly
%token Using Lang InState
%token Module Property Method
%token Refines Refine Includes Include
%token Provide Require
%token In As Naming
%token Export Bind Definition
%token Behavior State Allow Transition
%token From To With Transitions Default When
%token Client Provider By States Transitions
%token Definitions Binds Exportations
%token ID Forall Exist Null
%token Percolator Percolators
%token CoreInterface Implements

/* Access */
%token Read Write ReadWrite

/* Contract */
%token Invariant Pre Post Fail

/* Types */
%token TypeInt TypeBoolean
%token TypeVoid TypeReal
%token TypeString

/* Logical operator */
%token Eq Neq Leq Lt Gt Geq
%token AtPre
%token And Or Xor
%token Return
%token Not
%token Imply Equivalence 
%token Add Minus Times Quo Mod

/* Divers */
%token BracketO BracketC
%token BraceO BraceC
%token BrackO BrackC
%token Comma SemiColon Dot Sharp
%token Colon DoubleDot


/* Arbitrairement à gauche */
/* operateurs logique */
%left Equivalence /* faudra peut etre */
%left Imply  /* inverser ces deux lignes*/
%left And
%left Xor
%left Or
%right Not
%left AtPre

/* operateurs de contrat */
%left Eq
%left Neq Leq Lt Gt Geq /* ca n'a pas d'importance voilà :p */


/* operateurs arithmetique */
%left Add Minus
%left Mod Quo /* TODO : A revoir */
%left Times


%right Dot

%start tamago

%type <tamago> tamago

%%

tmodule:
| Ident { $1 }
| Ident Dot tmodule { $1^"."^$3 }
;

declusing:
| Using namespace { Using($2) }
;

namespace:
| Ident Dot namespace { Namespace($1,$3) }
| Times SemiColon { Namespace("*",EndNamespace); }
| Ident SemiColon { Namespace($1,EndNamespace) }
| { EndNamespace }
;

usings:
| declusing usings { $1::$2 }
| { [] }
;

allowlist:
| { [] }
| Allow tmodule SemiColon allowlist
  { $2::$4 }
;

states:
| { [] }
| State Ident BraceO allowlist BraceC states
  { State($2,$4)::$6 }
;

transitions:
| { [] }
| Transition From Ident To Ident With tmodule SemiColon transitions
 { Transition($3,$5,$7)::$9 }
| Transition From Ident To Ident With tmodule When expression SemiColon transitions
 { TransitionCond($3,$5,$7,$9)::$11 }
;

defaultstate:
| { "" }
| Default State Ident SemiColon { $3 }
;

behavioroptionnel:
| { NoBehavior }
| behavior { $1 }
;

behavior:
| Behavior BraceO 
    defaultstate 
    States BraceO 
       states 
    BraceC
    Transitions BraceO
        transitions
    BraceC
  BraceC
 { 
   Behavior($3,$6,$10)
 }
;


refine:
| Refine Service IdentType Module tmodule SemiColon { Refine($3,$5) } 
;

refinelist:
| { [] }
| refine refinelist { $1::$2 }
;

inclusion:
| Include Service IdentType Module tmodule SemiColon { Include($3,$5) } 
;

includelist:
| { [] }
| inclusion includelist { $1::$2 }
;

extendsoptionnel:
| { [] }
| refine extendsoptionnel { $1::$2 }
| inclusion extendsoptionnel { $1::$2 }
| Refines BraceO refinelist BraceC extendsoptionnel { $3@$5 }
| Includes BraceO includelist BraceC extendsoptionnel { $3@$5 }
;

properties:
| { [] }
| Property access ttype Ident SemiColon properties
      { Property($4,$3,$2)::$6 }
;


operator:
| Eq { Orelop(Peq) }
| Neq { Orelop(Pneq) }
| Lt { Orelop(Plt) }
| Leq { Orelop(Pleq) }
| Gt { Orelop(Pgt) }
| Geq { Orelop(Pgeq) }
| And { Oand }
| Or { Oor }
| Xor { Oxor }
| Imply { Oimply }
| Equivalence { Oequiv }
| Add { Obinop(Padd) }
| Minus { Obinop(Psub) }
| Times { Obinop(Ptimes) }
| Quo { Obinop(Pquo) }
| Mod { Obinop(Pmod) }
;

opequant:
| Forall { Forall }
| Exist { Exist }
;

expression:
| Bool { (log "BOOL"); Pbool($1) }
| Int { (log "INT") ;Pint($1) }
| Real { (log "REAL");Preal($1) }
| String { (log "STRING");Pstring($1) }
| Not expression { (log "NOT EXPR");Pnot $2 }
| BracketO BracketO ttype BracketC expression BracketC { (log "CAST");Pcast($3,$5) }
| accessexpression { (log "ACCESEXPR"); $1 }
| Null { (log "NULL");Pnull }
| BracketO expression BracketC   { (log "PAREN");$2 }
| Sharp Ident  { (log "READ"); Pproperty $2}
| Sharp Ident  BrackO expression BrackC { (log "READIDX"); Ppropertyindex($2,$4) }
| expression operator expression 
      {
	(log "OPERATOR");
	match $2 with
	  | Orelop(r) -> Prel(r,$1,$3)
	  | Obinop(b) -> Pbinop(b,$1,$3)
	  | Oand -> Pand([$1;$3])
	  | Oor -> Por([$1;$3])
	  | Oxor -> Pxor([$1;$3])
	  | Oimply -> Pimply($1,$3)
	  | Oequiv -> Pequiv($1,$3)
      }
| opequant  Ident Colon ttype In expression DoubleDot expression
	  BraceO expression BraceC
   {
     Pquantifierrange($1,$2,$4,$6,$8,$10)
   }
| opequant Ident Colon ttype In BrackO expressions BrackC  BraceO expression BraceC
   {
     Pquantifierset($1,$2,$4,$7,$10)
   }
| opequant Ident Colon ttype In expression  BraceO expression BraceC
   {
     Pquantifiercoll($1,$2,$4,$6,$8)
   }     
| Lang BraceO AnyString BraceC
   {
     Planguage($3)
   }
| InState BracketO listident BracketC
  {
   Pinstate($3)
  }
;

listident:
| Ident Comma listident { $1::$3 }
| Ident { [$1] }
;

accessexpression:
| accessexpression Dot accessexpression { (log "DOT EXPR") ; Pinlabel($1,$3) }
| Return BrackO expression BrackC  {Preturnindex($3)}
| Ident BrackO expression BrackC  { Pvarindex($1,$3) }
| Ident { Pvar $1 }
| Return  {Preturn}
| Ident BracketO expressions BracketC  { Pcall($1,$3) }
| accessexpression AtPre  { (log "ATPRE");Patpre  $1}
| BracketO accessexpression BracketC   { $2 }
| Sharp Ident  {Pproperty $2}
| Sharp Ident  BrackO expression BrackC { Ppropertyindex($2,$4) }
| BracketO accessexpression BracketC   { (log "PAREN");$2 }
;

expressions:
| expression { [$1]}
| expression Comma expressions {$1::$3 }
| { [] }
;

ttype:
| TypeInt { Tint }
| TypeVoid { Tvoid }
| TypeBoolean { Tbool }
| TypeReal{ Treal }
| TypeString { Tstring }
| tmodule tgenericlist { Tobject($1,$2) }
| ttype BrackO BrackC { Tarray $1 }
;

tgenericlist:
| Lt tgeneric Gt { $2 }
| { [] }
;

tgeneric:
| ttype Comma tgeneric { $1::$3 }
| ttype { [$1] }
| { [] }

access:
| Read {Aread}
| Write {Awrite}
| ReadWrite {Areadwrite}
;


implements:
|  { [] }
| Implements tmodule SemiColon implements
  { Implements($2)::$4 }
;


IDoptionnel:
| ID tmodule SemiColon { $2 }
| { "" }

parameter:
| ttype Ident { Parameter($2,$1) } 
;

parametersoptionnel:
| { []}
| parameters {$1}
;

parameters:
| parameter {[$1]}
| parameter Comma parameters {$1::$3}
;


tamagomethods:
| { [] }
| Method ttype Ident BracketO parametersoptionnel BracketC
   BraceO
   IDoptionnel
   precondition
   postcondition
   BraceC tamagomethods
   {
     Method($3,$2,$5,$8,$9,$10)::$12
   }
;


provides:
| { [] }
| Provide Service IdentType In tmodule SemiColon provides
  { Provide($3,$5)::$7 }
;

requires:
| { [] }
| Require Service IdentType In tmodule As Ident SemiColon requires
  { Require($3,$5,$7)::$9 }
;

condition:
| expression Fail String SemiColon
  { Condition("",$3,$1) }
| expression SemiColon
  { Condition("","",$1) }
;

precondition:
| { NoCondition }
| Pre condition
 { 
   match $2 with 
     | NoCondition -> NoCondition
     | Precondition(_) -> $2
     | Condition(cat,msg,expr) -> Precondition(cat,msg,expr)
     | _ -> raise (TamagoCDL "Precondition Condition failed")
 }
;

postcondition:
| { NoCondition }
| Post condition
 { 
   match $2 with 
     | NoCondition -> NoCondition
     | Postcondition(_) -> $2
     | Condition(cat,msg,expr) -> Postcondition(cat,msg,expr)
     | _ -> raise (TamagoCDL "Postcondition Condition failed")
 }
;

invariants:
| { [] }
| Invariant condition invariants
 { 
   match $2 with 
     | NoCondition -> NoCondition::$3
     | Invariant(_) -> $2::$3
     | Condition(cat,msg,expr) -> Invariant(cat,msg,expr)::$3
     | _ -> raise (TamagoCDL "Invariant Condition failed")
 }
;

binds:
| { [] }
| Bind Naming Ident Service IdentType Module tmodule Client expression
      Provider expression SemiColon binds
 {
   Bind($3,$5,$7,$9,$11)::$13
 }
;

definitions:
| { [] }
| Definition IdentType In tmodule As Ident SemiColon definitions
 {
   Definition($2,$4,$6)::$8
 }

exports:
| { [] }
| Export Service IdentType Module tmodule By expression SemiColon exports
  { 
    Export($3,$5,$7)::$9 
  }

percolators:
| { [] }
| Percolator Times SemiColon percolators
  {
    [AllPercolator]
  }
| Percolator Ident SemiColon percolators
  {
    Percolator($2)::$4
  }
;

IdentType:
| Ident tgenericlist { 
    if (List.length $2) > 0 then
      $1^"<"^(tamagotype_generiques $2)^">" 
    else
      $1
  }

tamago:
| Module tmodule SemiColon usings
    Service  IdentType BraceO
    implements
    extendsoptionnel
    properties
    invariants
    tamagomethods
    behavioroptionnel
  BraceC
 {
   Service($4,$6,$2,$9,$8,$10,$11,$12,$13)
 }
| Module tmodule SemiColon usings
    Component  IdentType BraceO
    implements
    provides
    requires
    properties
    percolators
    invariants
    tamagomethods
    behavioroptionnel
  BraceC
 {
   Component($4,$6,$2,$8,$11,$13,$14,$15,$12,$9,$10)
 }
| Module tmodule SemiColon usings
    Composite  IdentType BraceO
    implements //8
    provides //9
    requires //10
    properties //11
    percolators //12
    invariants //13
    definitions //14
    binds //15
    exports //16
    tamagomethods //17
    behavioroptionnel //18
  BraceC
 {
   Composite($4,$6,$2,$8,$11,$13,$17,$18,$12,$9,$10,$16,$14,$15)
 }
| Module tmodule SemiColon usings
     Assembly IdentType BraceO
     definitions
     binds
     BraceC
  {
    Assembly($4,$6,$2,$8,$9)
  }    
;
