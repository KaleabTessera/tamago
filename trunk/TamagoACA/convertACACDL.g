grammar convertACACDL  ;
options {
	output=AST;
	//k =9;
}

listeProcessus 
	: declProcessus (SEMI! declProcessus)* SEMI! 
	;

declProcessus
	: STRING^ LP! RP! DEFI! expression 
	;
  
expression
	: process
	| list
	| tableau
	;

tableau
	: triplet (LIST^ triplet)*
	;

list
	: action (LIST^ action)*
	;

process
	: operation ((IL^|PA^) operation)*
	;
	
//operationC
	//: contrainte
//	: sod 
//	| obligation 
//	;

triplet
	: LT! token COMA! token COMA! token RT!
	;	

operation 
	: sod 
	| obligation 
	//: contrainte
	//| action
	;

//contrainte
//	: (SOD^|OBLI^) LP! STRING COMA! action COMA! action RP!
//	;

sod
	: SOD^ LP! STRING COMA! action COMA! action RP!
	;

obligation
	: OBLI^ LP! STRING COMA! action COMA! action RP!
	; 

action
	: LT^ envsec COMA! evenement RT!
	;

envsec
	: token COMA! token COMA! token COMA! token
	;	

evenement
	: STRING^ LP! STRING? (COMA! STRING)*  RP!
	;
	
token
	: WILDCARD 
	| STRING
	| NOT^ STRING
	; 
	
WILDCARD
	: '_'
	;	
LIST
	: '&'
	;

STRING
	: ('a'..'z') ('a'..'z'|'0'..'9')*
    	;
    	
DEFI
	: '==' 
	;
	
SEMI
	: ';'
	;
	
NOT 
	: '!'
	;
	
LT
	: '<'
	;
	
RT
	: '>'
	;
	
SOD
	:'SOD'
	;
	
OBLI 
	: 'OBL' 
	;
	
LP
	: '('
	;
	
RP
	: ')'
	;
	
COMA
	: ','
	;

PA	
	: '||'
	;

IL 	
	: '|||'
	;
	
WS  
	: (' '|'\n')+ {$channel=HIDDEN;} 
	;
//DIGIT
//	: '0'..'9' ;
//INT 
//	: (DIGIT)+ ;
//WS  
//	: (' '
//    	|   '\t'
//    	|   '\n'
//    	|   '\r')
//        { _ttype = Token.SKIP; }
//    ;
//
//
//ID
//options {
//    testLiterals = true;
//}
//    :   ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
//    ;
//protected
//HEX_DIGIT
//    :   ('0'..'9'|'A'..'F'|'a'..'f')
//    ;
//
//protected
//ESC
//    :   '\\'
//        (   'n'
//        |   'r'
//        |   't'
//        |   'b'
//        |   'f'
//        |   '"'
//        |   '\''
//        |   '\\'
//        |   ('u')+ HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
//        |   ('0'..'3')
//            (
//              :   ('0'..'9')
//                (
//                :   '0'..'9'
//                )?
//            )?
//        |   ('4'..'7')
//            (
//            :   ('0'..'9')
//            )?
//       )
//    ;
//
//CHAR
//    :   '\'' ( ESC | ~'\'' ) '\''
//    ;
//
//SL_COMMENT
//    :   '//'
//        (~('\n'|'\r'))* ('\n'|'\r'('\n')?)
//       // {$setType(Token.SKIP); newline();}
//    ;
//
//
//ML_COMMENT
//    :   '/*'
//        (  
//        :
//            { LA(2)!='/' }? '*'
//        |   '\r' '\n'       {newline();}
//        |   '\r'            {newline();}
//        |   '\n'            {newline();}
//        //|   ~('*'|'\n'|'\r')
//        )*
//        '*/'
//        {$setType(Token.SKIP);}
//    ;
