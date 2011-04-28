type token =
    Ident of string
  | Int of int
  | Bool of bool
  | Real of float
  | String of string
  | Commentaire of string
  | AnyString of string
  | Service
  | Component
  | Composite
  | Assembly
  | Using
  | Lang
  | InState
  | Module
  | Property
  | Method
  | Refines
  | Refine
  | Includes
  | Include
  | Provide
  | Require
  | In
  | As
  | Naming
  | Export
  | Bind
  | Definition
  | Behavior
  | State
  | Allow
  | Transition
  | From
  | To
  | With
  | Transitions
  | Default
  | When
  | Client
  | Provider
  | By
  | States
  | Definitions
  | Binds
  | Exportations
  | ID
  | Forall
  | Exist
  | Null
  | Percolator
  | Percolators
  | CoreInterface
  | Implements
  | Read
  | Write
  | ReadWrite
  | Invariant
  | Pre
  | Post
  | Fail
  | TypeInt
  | TypeBoolean
  | TypeVoid
  | TypeReal
  | TypeString
  | Eq
  | Neq
  | Leq
  | Lt
  | Gt
  | Geq
  | AtPre
  | And
  | Or
  | Xor
  | Return
  | Not
  | Imply
  | Equivalence
  | Add
  | Minus
  | Times
  | Quo
  | Mod
  | BracketO
  | BracketC
  | BraceO
  | BraceC
  | BrackO
  | BrackC
  | Comma
  | SemiColon
  | Dot
  | Sharp
  | Colon
  | DoubleDot
val isClass : string -> bool
type parseoperator =
    Orelop of Synt.relop
  | Obinop of Synt.binop
  | Oand
  | Oor
  | Oxor
  | Oimply
  | Oequiv
val tamagotype_generiques : Synt.tamagotype list -> string
val string_tamagotype : Synt.tamagotype -> string
val log : 'a -> unit
val yytransl_const : int array
val yytransl_block : int array
val yylhs : string
val yylen : string
val yydefred : string
val yydgoto : string
val yysindex : string
val yyrindex : string
val yygindex : string
val yytablesize : int
val yytable : string
val yycheck : string
val yynames_const : string
val yynames_block : string
val yyact : (Parsing.parser_env -> Obj.t) array
val yytables : Parsing.parse_tables
val tamago : (Lexing.lexbuf -> token) -> Lexing.lexbuf -> Synt.tamago
