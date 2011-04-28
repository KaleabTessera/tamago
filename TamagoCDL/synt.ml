exception TamagoCDL of string;;


(** all simple type supported by Tamago-CDL. Notice : string is considered as a 
    simple type, because most Object-Oriented Language add high support for string. *)
type tamagotype =
 | Tint
 | Treal
 | Tbool
 | Tvoid
 | Tstring
 | Tobject of string * tamagotype list
 | Tarray of tamagotype
;;

(** Accessmode represents the access mode for properties (read, write and read write) *)
type accessmode =
 | Aread
 | Awrite
 | Areadwrite
;;

type flatope =
  | Fand
  | For
  | Fxor
;;

(** type for relation supported by Tamago-CDL*)
type relop = 
 | Plt
 | Pleq
 | Pgt
 | Pgeq
 | Pneq
 | Peq
;;

(** all binary operators supported by Tamago-CDL *)
type binop =
 | Padd
 | Psub
 | Ptimes
 | Pquo
 | Pmod
;;

type quantifierop =
  | Forall
  | Exist
;;


(** this type represents all expression allowed in the assertions for contracts.*)
type expression =
 | Pbool of bool
 | Pint of int
 | Preal of float
 | Pstring of string
 | Prel of relop * expression * expression
 | Pand of expression list
 | Por of expression list
 | Pxor of expression list
 | Pimply of expression * expression
 | Pequiv of expression * expression
 | Pnot of expression
 | Pcast of tamagotype * expression
 | Preturn
 | Preturnindex of expression
 | Pnull
 | Pvar of string
 | Pvarindex of string * expression
 | Pcall of string * expression list
 | Patpre of expression
 | Pproperty of string
 | Ppropertyindex of string * expression
 | Pbinop of binop * expression * expression
 | Pinlabel of expression * expression
 | Pquantifierrange of quantifierop * string * tamagotype * expression * expression * expression
 | Pquantifierset of quantifierop * string * tamagotype * (expression list) * expression
 | Pquantifiercoll of quantifierop * string * tamagotype * expression * expression
 | Planguage of string
| Pinstate of string list
 ;;

type category = string;;
type message = string;;

type condition =
  | NoCondition
  | Precondition  of category * message * expression 
  | Postcondition of category * message * expression
  | Invariant     of category * message * expression
  | Condition     of category * message * expression
;;

type state =
  | State of string * (string list)
;;

type transition =
  | Transition of string * string * string
  | TransitionCond of string * string * string * expression
;;

type behavior =
  | Behavior of string * state list * (transition list)
  | NoBehavior
;;

type property =
  | Property of  string * tamagotype * accessmode
;;

type implements = 
  | Implements of string
;;

type parameter =
  | Parameter of string * tamagotype
;;
 
type functionality = 
  | Method of string * tamagotype * (parameter list) *
      string * condition * condition
;;

type extends =
  | Refine of string * string
  | Include of string * string
;;

(* ================================================ PARTIE DES COMPOSANTS *)
type provide =
  | Provide of string * string
;;

type require =
  | Require of string * string * string
;;

(** representation of a percolator **)
type percolator=
  | Percolator of string
  | AllPercolator
;;
      
(* ================================================ PARTIE DES ASSEMBLYS *)
type bind = 
  | Bind of string * string * string * expression * expression
;;

type definition =
  | Definition of string * string * string
;;

(* ================================================ PARTIE DES COMPOSITES *)
type export =
  | Export of string * string * expression
;;

(* =============================================== PARTIE LES IMPORTATIONS *)

type namespace =
  | Namespace of string * namespace
  | EndNamespace
;; 

type using = 
  | Using of namespace
;;

(* ================================================ PARTIE DES ENTITES *)
type tamago =
  | Service of (using list) * string * string * (extends list) * (implements list) *
      (property list) * (condition list) * (functionality list) *
      behavior
  | Component of (using list) * string * string * (implements list) *
      (property list) * (condition list) * (functionality list) *
      behavior * (percolator list) * (provide list) * (require list) 
  | Assembly of (using list) * string * string * (definition list) * (bind list)
  | Composite of (using list) * string * string * (implements list) *
      (property list) * (condition list) * (functionality list) *
      behavior * (percolator list) * (provide list) * (require list) * 
      (export list) * (definition list) * (bind list)
;;

(* ======================================================================== *)
 
