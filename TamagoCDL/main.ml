open Parser;;
open Lexer;;
open Synt;;
open Convertion;;
open Convertion_latex;;	 
open Arg;;
open List;;

type output =
  | Output of string*(tamago -> out_channel  -> unit)
;;

let coutput = ref (Output ("XML(default)", tamago_to_xml));;


let refoutput = ref stdout;;

let setoutput (file:string) = 
  refoutput := (open_out file)
;;

 let setoutputLatex () = 
   prerr_endline("Mode Latex"); 
   coutput := Output ("Latex",tamago_to_tex) 
 ;; 

let setoutputXML () = 
  prerr_endline("Mode XML");
  coutput := Output ("XML",tamago_to_xml)
;;


let arguments = [
  ("-xml", Unit(setoutputXML), "Generate XML code");
  ("-latex", Unit(setoutputLatex), "Generate Latex code"); 
  ("-o",String(setoutput), "<file> Output file")
];;

let parsefile input = 
  let lexbuf = Lexing.from_channel input in
	 try 
		let l = tamago Lexer.lexer lexbuf in
        begin
			 prerr_endline("Parsing OK");
			 l
        end
	 with
		  Parsing.Parse_error ->
			 Printf.eprintf "Erreur Syntaxe sur <%s>\n"  (Lexing.lexeme lexbuf);
			 let pos1 = Lexing.lexeme_start lexbuf in
			 let pos2 = Lexing.lexeme_end lexbuf in
				prerr_endline ("\tErreur ligne "^(string_of_int !currentligne));
				Printf.eprintf "\tCaracteres : %d-%d\n" (pos1-(!nbcarparligne)) (pos2-(!nbcarparligne));
				exit(230);
		| _ ->
			 Printf.eprintf "Erreur Inconnu sur <%s>\n"  (Lexing.lexeme lexbuf);
			 let pos1 = Lexing.lexeme_start lexbuf in
			 let pos2 = Lexing.lexeme_end lexbuf in
				prerr_endline ("\tErreur ligne "^(string_of_int !currentligne));
				Printf.eprintf "\tCaracteres : %d-%d\n" (pos1-(!nbcarparligne)) (pos2-(!nbcarparligne));
				exit(230);
;;


let convertfile cdl =
  try 
    Printf.eprintf "Open target file %s\n" cdl;
    let entree = open_in cdl in
    let Output(style,func) = !coutput in 
    let entity = parsefile entree in
		begin
		  prerr_endline ("Convertion : "^style);
		  func entity (!refoutput);
		  prerr_endline "Convertion Terminated"
		end
  with
		Sys_error(x) -> 
		  begin 
			 prerr_endline ("Error in file \""^Sys.argv.(1)^"\" : "^x);
			 exit(324)
		  end
;;

(Arg.parse arguments convertfile ("Usage of "^Sys.argv.(0)^" :"));;

