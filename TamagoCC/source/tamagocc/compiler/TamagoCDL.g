
grammar TamagoCDL;

options { 
    backtrack=true;
    memoize=true;
    output=AST;
    ASTLabelType=CommonTree;
}


@header {
package tamagocc.compiler;

import tamagocc.api.*;
import tamagocc.impl.*;
import java.util.Collection;
import java.util.ArrayList;
import tamagocc.util.Triplet;
}


@lexer::header {
package tamagocc.compiler;

import tamagocc.api.*;
import tamagocc.impl.*;
import java.util.Collection;
import java.util.ArrayList;
import tamagocc.util.Triplet;
}


tamagoEntity returns [TTamagoEntity value, String mod, Collection<TNamespace> uses]
@init { $uses = new ArrayList<TNamespace>(); }
@after {
 $value = TamagoCDLEaseFactory.entity($mod,$uses,$value);
}
	:	m=moduleDeclaration
	(u=usingDeclaration { $uses.add($u.value); })*
	  (s=serviceEntity { $mod=$m.value; $value =$s.value; }
	   | c=componentEntity { $mod=$m.value; $value=$c.value; })
	;
	
	
percolator returns [TPercolator value]
	 :	'percolator'^ ('*' { $value = TIPercolator.getAllPercolator();  }
	  		| ID { $value = new TIPercolator($ID.text); }) ';'!
	 ;


require returns [TRequire value]
	:	'require'^ 'service'! n=ID 'in'! q=qualifiedName 'as'! l=ID ';'! 
		{ $value = TamagoCDLEaseFactory.require($n.text,$q.value,$l.text);  }
	;	
	

provide returns [TProvide value]
	:	'provide'^ 'service'! n=ID 'in'! q=qualifiedName ';'! 
		{ $value = TamagoCDLEaseFactory.provide($n.text,$q.value);  }
	;	
	
moduleDeclaration returns [ String value ]
@init { $value = ""; }
	:	'module'^ s=qualifiedName ';' { $value=$s.value; }
	;
	
qualifiedName returns [String value ]
@init {
	$value="";
}
	:	s=ID ('.' d=ID {$value= $d.text+"."+$value; })* { $value=$s.text+"."+$value;  }
	;
	
qualifiedNameWithWildCard returns [String value]
@init { $value =""; }
	:	( s=ID ('.' d=ID {$value=$d.text+"."+$value; } )* 
	('.*' {$value=".*"; })? ) { $value=$s.text+"."+$value; }
	;
	
usingDeclaration returns [TNamespace value]
	:	'using'^ p=qualifiedNameWithWildCard ';'! { $value= new TINamespace($p.value); }
	;
	
serviceEntity returns [TService value, String name,Collection<TImplements> impls, 
	Collection<TRefineService> refs, Collection<TIncludeService> incs, Collection<TProperty> props,
	Collection<TInvariant> invs, Collection<TMethod> meths, TBehavior beh]
@init {
$impls = new ArrayList<TImplements>();
$refs = new ArrayList<TRefineService>();
$incs = new ArrayList<TIncludeService>();
$props = new ArrayList<TProperty>();
$invs = new ArrayList<TInvariant>();
$meths = new ArrayList<TMethod>();
}
@after {
$value = TamagoCDLEaseFactory.service($name,$impls,$refs,$incs,$props,$invs,$meths,$beh);
}
	:
		'service'^ label=ID '{'!
		(i=implementsInterface { $impls.add($i.value); })*
		(r=refineService {$refs.add($r.value); })*
		(c=includeService {$incs.add($c.value); } )*
		(p=propertyDeclaration {$props.add($p.value); })*
		(v=invariantExpression {$invs.add($v.value); })*
		(x=methodDeclaration {$meths.add($x.value); })*
		
		(b=behaviorDeclaration { $beh=$b.value; })?
		'}'!
		{ $name=$label.text; }
	;
	
componentEntity returns [TComponent value, String name,Collection<TPercolator> percos, Collection<TImplements> impls,
	Collection<TProvide> refs, Collection<TRequire> incs, Collection<TProperty> props,
	Collection<TInvariant> invs, Collection<TMethod> meths, TBehavior beh]
@init {
$percos = new ArrayList<TPercolator>();
$impls = new ArrayList<TImplements>();
$refs = new ArrayList<TProvide>();
$incs = new ArrayList<TRequire>();
$props = new ArrayList<TProperty>();
$invs = new ArrayList<TInvariant>();
$meths = new ArrayList<TMethod>();
}
@after {
$value = TamagoCDLEaseFactory.component($name,$impls,$refs,$incs,$props,$invs,$meths,$beh,$percos);
}
	:
		'component'^ label=ID '{'!
		(o=percolator { $percos.add($o.value); } )*
		(i=implementsInterface { $impls.add($i.value); })*
		(r=provide {$refs.add($r.value); })*
		(c=require {$incs.add($c.value); } )*
		(p=propertyDeclaration {$props.add($p.value); })*
		(v=invariantExpression {$invs.add($v.value); })*
		(x=methodDeclaration {$meths.add($x.value); })*
		
		(b=behaviorDeclaration { $beh=$b.value; })?
		'}'!
		{ $name=$label.text; }
	;
	
implementsInterface returns [TImplements value]
	:	'implements'^ p=qualifiedName ';'! { $value = new TIImplements($p.value); }
	;

refineService returns [TRefineService value]
	:	'refine'^ 'service'! n=ID 'in'! q=qualifiedName ';'! { $value = TamagoCDLEaseFactory.refine($n.text,$q.value);  }
	|       'refine'^ 'service'! qualifiedName ';'!
	;
	
includeService returns [TIncludeService value]
	:	'include'^ 'service'! n=ID 'in'! q=qualifiedName ';'! { $value = TamagoCDLEaseFactory.include($n.text,$q.value); }
	|       'include'^ 'service'! qualifiedName ';'!
	;
	
propertyDeclaration returns [TProperty value ]
	:	'property'^ a=accessProperty t=type n=ID ';'! 
		{ 
			$value = new TIProperty($n.text,$t.value,$a.value); 
		} 
	;

accessProperty returns [TAccess value]
	:	
	  'readwrite' { $value = new TIAccess("rw");  }
	| 'read'      { $value = new TIAccess("r"); }
	| 'write'     { $value = new TIAccess("w"); }
	;
	
type returns [ TType value, String prim, boolean flags]
@init {
	$flags=false;
}
@after {
	if($flags)
		$value=TIType.generateType($prim+"[]");
	else
		$value=TIType.generateType($prim);
	
}
	:	(p=primitiveType {$prim=$p.value; }| q=qualifiedName {$prim=$q.value;} )
	 ('[]' { $flags = true; } )? 
	 
	 ;
primitiveType returns [String value ]
	:	'int' { $value="int"; } 
	| 'void'  { $value="void"; } 
	| 'bool'  { $value="bool"; } 
	| 'boolean'  { $value="bool"; } 
	| 'real'  { $value="real"; } 
	| 'double'  { $value="real"; } 
	| 'String'  { $value="String"; } 
	| 'string' { $value="String"; } 
	;
	
methodDeclaration returns [TMethod value]
	:
		'method'^ t=type n=ID a=parameters '{'!
			('id'^ d=ID ';'!)?
			('pre'^ p=preconditionExpression ';'!)?
			('post'^ q=postconditionExpression ';'!)?
		'}'!
	{$value = TamagoCDLEaseFactory.method($t.value, $n.text, $a.params, $d.text, $p.value, $q.value); }
	;

parameters returns [Collection<TParameter> params]
@init{
$params = new ArrayList<TParameter>();
}
	:	'(' (a=type b=ID (',' c=type d=ID 
			{ $params.add(TamagoCDLEaseFactory.param($c.value,$d.text)); }
		   )*
  			{ $params.add(TamagoCDLEaseFactory.param($a.value,$b.text)); }
		   )? ')'
	;
	

arguments returns [Collection<TExpression> value]
@init {
$value = new ArrayList<TExpression>();
}
	:	'(' (a=expression {$value.add($a.value);}
			(',' b=expression { $value.add($b.value); })*)? ')'
	;

behaviorDeclaration returns [TIBehavior value, String def, Collection<TState> states, Collection<TTransition> transitions ]
@init {
	$states = new ArrayList<TState>();
	$transitions = new ArrayList<TTransition>();
	$def = "";
}

	:	'behavior'^ '{'!
	('default'^ 'state'! n=ID ';'! {$def = $n.text;})?
	('states'^ '{'!
		(s=stateDeclaration {$states.add($s.value); } )*
	'}'!)?
	('transitions' '{'
		(t=transitionDeclaration {$transitions.add($t.value);})*
	'}')?
	'}'
	 { $value = new TIBehavior($states,$transitions, $def); }
	;
stateDeclaration returns [ Collection<TAllow> allows, TState value]
@init {
$allows = new ArrayList<TAllow>();
}

	:	'state'^ i=ID '{'! (a=allowDeclaration { $allows.add($a.value); })* '}'!
		{  $value = new TIState($i.text, $allows);  }
	;
	
allowDeclaration returns [TAllow value]
	:	'allow'^ n=ID ';'! {$value = new TIAllow($n.text); }
	;

transitionDeclaration returns [TTransition value, TExpression guard]

	:	'transition'^ 'from'! f=ID 'to'! t=ID 'with'! w=ID ('when'! e=expression {$guard=$e.value;})? ';'!
		{ $value = TamagoCDLEaseFactory.transition($f.text,$t.text,$w.text,$guard); }
	;

invariantExpression returns [TInvariant value]
	:	'invariant'^ e=expression ('fail' f=STRING)? ';'
		{ $value = TamagoCDLEaseFactory.invariant($e.value,$f.text); }
	;

preconditionExpression returns [TCondition value]
	:	'pre'^ e=expression ('fail' f=STRING)? ';'
		{ $value = TamagoCDLEaseFactory.precond($e.value,$f.text); }
	;
	
postconditionExpression returns [TCondition value]
	:	'post'^ e=expression ('fail' f=STRING)? ';'
		{ $value = TamagoCDLEaseFactory.postcond($e.value,$f.text); }
	;

// Cause of the bug in the use of label for left and right member i can distinguish them 
// http://www.antlr.org/pipermail/antlr-interest/2011-October/042906.html

expression returns [TExpression value, TIOperator opor]
@init{
$opor = new TIOperator(TOpeName.opOr);
}
@after {
	if($opor.size() == 1)
		$value = $opor.getOperand(0);
	else
		$value = $opor;
}
	:	andExpression 
		('||'^ andExpressionbis { $opor.addOperand($andExpressionbis.value); })*
		{ $opor.addOperand($andExpression.value); }
	;
andExpressionbis returns [TExpression value]
:	andExpression { $value = $andExpression.value; };



andExpression returns [TExpression value, TIOperator opand]
@init{
$opand = new TIOperator(TOpeName.opAnd);
}
@after {
	if($opand.size() == 1)
		$value = $opand.getOperand(0);
	else
		$value = $opand;
}
	:	relQuantExpression 
		('&&'^ relQuantExpressionbis { $opand.addOperand($relQuantExpressionbis.value); })*
		{ $opand.addOperand($relQuantExpression.value); }
	;
	
relQuantExpressionbis returns [TExpression value]
: relQuantExpression { $value = $relQuantExpression.value; };

relQuantExpression returns [TExpression value]
	: 
	  litBoolean { $value=$litBoolean.value; }
	| quantExpression { $value=$quantExpression.value; }
	| relExpression { $value=$relExpression.value; }
	| primaryExpression { $value=$primaryExpression.value; }
	| notExpression { $value=$notExpression.value; }
	;

notExpression returns [TExpression value]
	:	NOTOPERATOR expression { $value = new TINot($expression.value); };

litBoolean returns [TBool value]
	:	'true' { $value = new TIBool(true); } 
	| 'false' { $value = new TIBool(false); };
quantExpression returns [TExpression value]
	:	quant=QUANTIFIER
	var=ID
	':'
	t=type
	
	dom=domainQuant
	
	'{'
		body=expression
	'}'
	{$value = TamagoCDLEaseFactory.quant($quant.text,$var.text,$t.value,
			$dom.kind,$dom.value,$body.value); }
	;

domainQuant returns [String kind, Collection<Object> value]
@init {
$value = new ArrayList<Object>();
}
	:
	'in' 'set' '[' a=expression (',' b=expression {$value.add($b.value);} )* ']' { $kind = "set"; $value.add($a.value);  }
	|'in' 'coll' c=expression { $kind="coll"; $value.add(c.value); }
	| 'from' d=expression 'to' e=expression {$kind="range"; $value.add($d.value); $value.add($e.value); }
	;

relExpression returns [TExpression value, String op, ArrayList<TExpression> exprs ]
@after {
$value = TamagoCDLEaseFactory.operator($op,$exprs);
}
	:	a=arithExpression 
		(RELOP^
		b=arithExpression {$op = $RELOP.text; $exprs.add($b.value); } )?
		
		{$exprs.add($a.value);}
	;

arithExpression returns [TExpression value, String op, ArrayList<TExpression> exprs]
@after {
$value = TamagoCDLEaseFactory.operator($op,$exprs);
}
	:	multExpression 
		(ADDOP^
		multExpressionbis { $op = $ADDOP.text; $exprs.add($multExpressionbis.value); } )?
		{ $exprs.add($multExpression.value); }
	;
multExpressionbis returns [TExpression value]
: multExpression { $value = $multExpression.value; };

multExpression returns [TExpression value, String op, ArrayList<TExpression> exprs]
@after {
$value = TamagoCDLEaseFactory.operator($op,$exprs);
}
	:	unaryExpression
		(MULTOP^
		unaryExpressionbis { $op = $MULTOP.text; $exprs.add($unaryExpressionbis.value); } )?
		 { $exprs.add($unaryExpression.value); }
	;
unaryExpressionbis returns [TExpression value]
	:
	unaryExpression { $value = $unaryExpression.value; }
	;
unaryExpression returns [TExpression value]
	:	a=minusArithExpression {$value=$a.value;} 
	| b=primaryExpression {$value=$b.value; } ;

minusArithExpression returns [TExpression value]
	:	'-' a=arithExpression { 
		if($a.value instanceof TInteger) {
			TInteger tin = (TInteger)$a.value;
			$value = new TIInteger(- tin.getValue() );
		}
		else if($a.value instanceof TReal) {
			TReal trea = (TReal)$a.value;
			$value = new TIReal(- trea.getValue());
		}
		else {
			$value = new TIOperator(TOpeName.opTimes);
			((TIOperator)$value).addOperand(new TIInteger(-1));
			((TIOperator)$value).addOperand($a.value);
		}
		} ;

primaryExpression returns [TExpression value]
	: '(' a=expression ')' {$value=$a.value;}
	 | b=literalUntypedExpression {$value=$b.value; }
	 | c=literalArithExpression { $value=$c.value; }
	 | d=thisExpression {$value=$d.value; }
	 | e=varExpression { $value=$e.value; }
	 | f=returnExpression { $value=$f.value; }
	 | g=readExpression { $value=$g.value; }
	 ;

literalUntypedExpression returns [TExpression value]
	:	'null'^ { $value=new TINil(); }| 'nil'^ { $value=new TINil(); };

literalArithExpression  returns [TExpression value]
	:	
	  f=FLOAT { $value = new TIReal(Double.parseDouble($f.text)); }
	| i=INT { $value = new TIInteger(Integer.parseInt($i.text)); } 
	| s=STRING { $value = new TIString($s.text); }
	;
returnExpression returns [TExpression value, Triplet<TExpression,Collection<TExpression>,Boolean> a ]
	:	'@return'^ ( identSuffix { $a =$identSuffix.value; })? 
		{if($a != null) $value= TamagoCDLEaseFactory.inlabelReturn($a);
		 else $value=new TIReturn(); };
thisExpression returns [TExpression value, TExpression sub, Triplet<TExpression,Collection<TExpression>,Boolean> d]
	:	'this'^ ('.'^ primaryExpression { $sub = $primaryExpression.value; } )* (identSuffix {$d=$identSuffix.value;})?
		{$value = TamagoCDLEaseFactory.inlabelSelf($sub,$d); }
	;
	
varExpression returns [TExpression value, TExpression sub, Triplet<TExpression,Collection<TExpression>,Boolean> d]
	:	
	v=ID ('.'^ primaryExpression { $sub = $primaryExpression.value; } )* (identSuffix {$d=$identSuffix.value;})?
	{ $value = TamagoCDLEaseFactory.inlabelVar($v.text,$sub,$d); }
	
	;
	
readExpression returns [TExpression value, TExpression sub,Triplet<TExpression,Collection<TExpression>,Boolean> d]
: 
	'#'^ r=ID ('.'^ primaryExpression { $sub = $primaryExpression.value; } )* (identSuffix {$d=$identSuffix.value;})?
	{ $value= TamagoCDLEaseFactory.inlabelRead($r.text,$sub,$d); }
	;

identSuffix returns [ tamagocc.util.Triplet<TExpression,Collection<TExpression>,Boolean> value]
@init {
	$value = new Triplet<TExpression,Collection<TExpression>,Boolean>(null,null,new Boolean(false));
}
	:	(a=arrayIndexExpression {$value.setL($a.value);}
			|b=arguments { $value.setC($b.value) ; } )
	('@pre' {$value.setR(new Boolean(true)); })?
	{ }
	;
	
arrayIndexExpression returns [TExpression value]
	:	'[' i=arithExpression ']' {$value=$i.value;};

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
