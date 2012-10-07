
grammar TamagoCDL;

options { 
    backtrack=true;
    memoize=true;
    output=AST;
    ASTLabelType=CommonTree;
}


@header {
	package tamagocc.compiler;
}


@lexer::header {
	package tamagocc.compiler;
}


tamagoEntity
	:	moduleDeclaration
	(usingDeclaration)*
	(serviceEntity)
	;
	
moduleDeclaration
	:	^('module' qualifiedName ';')
	;
	
qualifiedName
	:	ID (('.' ID)+)?
	;
	
qualifiedNameWithWildCard
	:	ID ('.' ID)*
	('.*')?
	;
	
usingDeclaration
	:	'using' qualifiedNameWithWildCard ';'
	;
	
serviceEntity
	:
		'service' ID '{'
		(implementsInterface)*
		(refineService)*
		(includeService)*
		(propertyDeclaration)*
		(invariantExpression)*
		(methodDeclaration)*
		
		(behaviorDeclaration)?
		'}'
	;
	
implementsInterface
	:	'implements' qualifiedName ';'
	;

refineService
	:	'refine' 'service' ID 'in' qualifiedName ';'
	|       'refine' 'service' qualifiedName ';'
	;
	
includeService
	:	'include' 'service' ID 'in' qualifiedName ';'
	|       'include' 'service' qualifiedName ';'
	;
	
propertyDeclaration
	:	'property' accessProperty type ID ';'
	;

accessProperty
	:	'readwrite' | 'read' |'write'
	;
	
type	:	(primitiveType | qualifiedName)
	 ('[]')?
	 ;
primitiveType
	:	'int' | 'void' |'bool' | 'boolean' | 'real' | 'double' | 'String' | 'string'
	;
	
methodDeclaration
	:
		'method' type ID arguments '{'
			('id' ID ';')?
			('pre' preconditionExpression ';')?
			('post' postconditionExpression ';')?
		'}'
	;

arguments
	:	'(' (expression (',' expression)*)? ')'
	;

behaviorDeclaration
	:	'behavior' '{'
	('default' 'state' ID ';')?
	('states' '{'
		(stateDeclaration)*
	'}')?
	('transitions' '{'
		(transitionDeclaration)*
	'}')?
	'}'
	;
stateDeclaration
	:	'state' ID '{' (allowDeclaration)* '}'
	;
	
allowDeclaration
	:	'allow' ID ';'
	;

transitionDeclaration
	:	'transition' 'from' ID 'to' ID 'with' ID ('when' expression)? ';'
	;

invariantExpression
	:	'invariant' expression ('fail' STRING)? ';'
	;

preconditionExpression
	:	'pre' expression ('fail' STRING)? ';'
	;
	
postconditionExpression
	:	'post' expression ('fail' STRING)? ';'
	;

expression
	:	andExpression ('||' andExpression)*
	;
andExpression
	:	relQuantExpression ('&&' relQuantExpression)*
	;
relQuantExpression
	: 
	  litBoolean
	| quantExpression
	| relExpression
	| primaryExpression
	| notExpression
	;

notExpression
	:	NOTOPERATOR expression;

litBoolean
	:	'true' | 'false';
quantExpression
	:	QUANTIFIER
	ID
	':'
	type
	
	domainQuant
	
	'{'
		expression
	'}'
	;

domainQuant
	:
	'in' 'set' '[' expression (',' expression)* ']'
	|'in' 'coll' expression
	| 'from' expression 'to' expression
	;

relExpression
	:	arithExpression
		(RELOP
		arithExpression)?
	;

arithExpression
	:	multExpression
		(ADDOP
		multExpression)?
	;
multExpression
	:	unaryExpression
		(MULTOP
		unaryExpression)?
	;

unaryExpression
	:	minusArithExpression
	| primaryExpression;

minusArithExpression
	:	'-' arithExpression;

primaryExpression
	: '(' expression ')'
	 | literalUntypedExpression
	 | literalArithExpression
	 | thisExpression
	 | varExpression
	 | returnExpression
	 | readExpression
	 ;

literalUntypedExpression
	:	'null' | 'nil';

literalArithExpression
	:	
	  FLOAT
	| INT
	| STRING
	;
returnExpression
	:	'@return' ( identSuffix)?;
thisExpression
	:	'this' ('.' ID)* (identSuffix)?
	;
	
varExpression
	:	
	ID ('.' ID)* (identSuffix)?
	;
readExpression:
	'#' ID ('.' ID)* (identSuffix)?
	;

identSuffix
	:	(arrayIndexExpression|arguments)
	('@pre')?
	;
	
arrayIndexExpression
	:	'[' arithExpression ']';

// TOKEN LEXEME

NOTOPERATOR:	'not' | '!';
MULTOP	:	'*' | '/' | 'div' | 'mod' | '%';
ADDOP	:	'+' | '-';
RELOP	:	'<' '=' | '>' '=' | '>' | '<' |'==' |'=' | '!=' | '<>';
QUANTIFIER:	'forall'|'FORALL'|'exists'|'EXISTS';

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

INT :	'0'..'9'+
    ;

FLOAT
    :   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.' ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
