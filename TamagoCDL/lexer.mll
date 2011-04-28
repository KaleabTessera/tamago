{
open Lexing;;
open Parser;;
let currentligne = ref 1;;
let nbcarparligne = ref 0;;


type terror = {
  ligne : int;
  col_deb : int;
  col_fin : int;
  lexeme : string;
};;


let tamago_error lexbuf =
  {
	lexeme = (Lexing.lexeme lexbuf);
	col_deb = (Lexing.lexeme_start lexbuf) - (!nbcarparligne );
	col_fin = (Lexing.lexeme_end lexbuf) - (!nbcarparligne);
	ligne = !currentligne
  }
;;

(*Printf.eprintf "Erreur Syntaxe sur <%s>\n"  (Lexing.lexeme lexbuf);
      let pos1 = Lexing.lexeme_start lexbuf in
      let pos2 = Lexing.lexeme_end lexbuf in
      prerr_endline ("\tErreur ligne "^(string_of_int !currentligne));
      Printf.eprintf "\tCaracteres : %d-%d\n" (pos1-(!nbcarparligne)) (pos2-(!nbcarparligne));
;;
*)

}

rule lexer = parse
	[' ' '\t' ] { lexer lexbuf }
| '\n' {currentligne:= !currentligne + 1;
	 nbcarparligne:= Lexing.lexeme_end lexbuf;
	lexer lexbuf }
(* Mots clef *)
| "service" { Service }
| "component" { Component }
| "composite" { Composite }
| "assembly" { Assembly }

| "using" { Using }
| "@lang" { Lang }
| "@instate" { InState }
| "id" { ID }
| "in" { In }
| "as" { As }
| "module" { Module }
| "property" { Property }
| "method" { Method }
| "refines" { Refines }
| "refine" { Refine }
| "includes" { Includes }
| "include" { Include }
| "provide" { Provide }
| "require" { Require }
| "export" { Export }
| "exportations" { Exportations }
| "bind" { Bind }
| "binds" { Binds }
| "definitions" { Definitions }
| "uses" { Definitions }
| "use" { Definition }
| "behavior" { Behavior }
| "state" { State }
| "states" { States }
| "allow" { Allow }
| "transition" { Transition }
| "transitions" { Transitions }
| "from" { From }
| "to" { To }
| "with" { With }
| "when" { When }
| "default" { Default }
| "client" { Client }
| "provider" { Provider }
| "by" { By }
| "forall" { Forall }
| "exists" { Exist }
| "naming" { Naming }
| "implements" { Implements }
| "coreinterfaces" { CoreInterface }
| "percolator" { Percolator }
| "percolators" { Percolators }
  (* Access *)
| "read" { Read }
| "write" { Write }
| "readwrite" {ReadWrite }
| "null" { Null }

  (* Contract *)
| "invariant" { Invariant }
| "pre" { Pre }
| "post" { Post }
| "fail" { Fail }

  (* types *)
| "int" { TypeInt }
| "boolean" { TypeBoolean }
| "bool" { TypeBoolean }
| "void" { TypeVoid }
| "real" { TypeReal }
| "String" { TypeString }
| "string" { TypeString }

  (* Syntaxe Logique *)
| "=" { Eq }
| "==" { Eq }
| "!=" { Neq }
| "<>" { Neq }
| "<=" { Leq }
| "<" { Lt }
| ">" { Gt }
| ">=" { Geq }
| "@pre" { AtPre }
| "@return" { Return }
| "!" { Not }
| "&&" { And }
| "||" { Or }
| "^" { Xor }
| "=>" { Imply }
| "<=>" { Equivalence }
| "+" { Add }
| "-" { Minus }
| "*" { Times }
| "/" { Quo }
| "%" { Mod }


  (* Symbole cle *)
| "(" { BracketO }
| ")" { BracketC }
| "{" { BraceO }
| "}" { BraceC }
| "[" { BrackO }
| "]" { BrackC }
| "," { Comma }
| ";" { SemiColon }
| "." { Dot }
| "#" { Sharp }
| ":" { Colon }
| ".." { DoubleDot }

| "true" { Bool(true) }
| "false" { Bool(false) }
| '"' ( [^ '"' ] | "\\\"" ) * '"' {
    let taille = (String.length (Lexing.lexeme lexbuf)) in
    let tok = Lexing.lexeme lexbuf in
      String (String.sub tok 1 (taille-2))
  }
| "//"( [^ '\n' ])* {
	 lexer lexbuf
  }
| ['0'-'9']+'.'['0'-'9']+ { Real(float_of_string (lexeme lexbuf)) }
| ['0'-'9']+ { Int(int_of_string (lexeme lexbuf)) }
| '-'['0'-'9']+ { Int(int_of_string (lexeme lexbuf)) }
| ['A'-'Z''a'-'z''_']['A'-'Z''a'-'z''0'-'9''_']* { Ident(lexeme lexbuf) }
| '$'[^'\n''$']*'$'+ { let str=(lexeme lexbuf) in
		       let res = String.sub str 1 ((String.length str)-2) in
			 AnyString(res) }
