# Summary #
  * The entry point is the rule `<_main_>`.
  * The rules are sorted
  * **Attention** binary operator are really recommanded (then protect expression with parenthesis)


```
<_main_> ::= 'module' <qualif-ident> ';' 'service' <ident> '{' (<implements>)* (<refine>)* (<include>)* (<property>)* (<invariant>)* (<method>)* (<behavior>)? '}'  | 'module' <qualif-ident> ';' 'component' <ident> '{' (<implements>)* <provides> <requires> (<property>)* (<percolator>)? (<invariant>)* (<method>)* (<behavior>)? '}'  |  end of file 
<access> ::= readwrite|read|write
<allow> ::= 'allow' <ident> ';' 
<arithexpr> ::= <arith-expr> ::=
   | <arith-expr> '+' <arith-expr>
   | <arith-expr> '-' <arith-expr>
   | <arith-expr> '*' <arith-expr>
   | <arith-expr> '/' <arith-expr>
   | <arith-expr> '%' <arith-expr>
   | '(' <arith-expr> ')'
   | <inlabel>
   | <cast>
   | <real>
   | <int>
   | <exprstring>
   | <read>
   | <call>
   | <var>
   | <nil>

<behavior> ::= 'behavior' '{' <default-state> <states> <transitions> '}' 
<bool> ::= true|false
<call> ::= <ident> '(' <list-arguments> ')' 
<cast> ::= '(' <type> ')' <relexpr>  | '|' <type> '|' <relexpr>  | 
<component-parser> ::= 'module' <qualif-ident> ';' 'component' <ident> '{' (<implements>)* <provides> <requires> (<property>)* (<percolator>)? (<invariant>)* (<method>)* (<behavior>)? '}' 
<default-state> ::= ('default' 'state' <ident> ';' )?
<exprstring> ::= <string>
<ident> ::= [a-zA-Z_]\w*
<implements> ::= 'implements' <qualif-ident> ';' 
<include> ::= 'include' 'service' <ident> 'module' <qualif-ident> ';' 
<inlabel> ::= <read> '.' <relexpr>  | <var> '.' <relexpr>  | 
<int> ::= (-)?([0-9]+)
<invariant> ::= 'invariant' <preexpression> 'fail' <string> ';'  | 'invariant' <preexpression> ';'  | 
<lineComment> ::= LineComment[start='//']
<list-allows> ::= (<allow>)*
<list-arguments> ::= ListOf[sep=',',min=0,max=2147483647](<logicexpr>)
<list-transitions> ::= (<transition>)*
<logicexpr> ::= <logic-expr> ::=
   | <logic-expr> '&&' <logic-expr>
   | <logic-expr> 'and' <logic-expr>
   | <logic-expr> '||' <logic-expr>
   | <logic-expr> 'or' <logic-expr>
   | <logic-expr> '^' <logic-expr>
   | <logic-expr> 'xor' <logic-expr>
   | <logic-expr> '-->' <logic-expr>
   | <logic-expr> '<-->' <logic-expr>
   | <logic-expr> '<->' <logic-expr>
   | 'not' <logic-expr>
   | '!' <logic-expr>
   | '(' <logic-expr> ')'
   | <relexpr>
   | <quantset>
   | <quantrange>
   | <quantcoll>

<method> ::= 'method' <type> <ident> '(' (<parameters>)? ')' '{' (<method-id>)? (<precondition>)? (<postcondition>)? '}' 
<method-id> ::= 'id' <ident> ';' 
<nil> ::= null|nil
<parameter> ::= <type> <ident> 
<parameters> ::= <parameter> ',' <parameters>  | <parameter> | 
<percolator> ::= 'percolator' '*' ';'  | 'percolator' ListOf[sep=<skip> ',' <skip> ,min=1,max=2147483647](<ident>) ';'  | 
<postarithexpr> ::= <post-arith-expr> ::=
   | <post-arith-expr> '+' <post-arith-expr>
   | <post-arith-expr> '-' <post-arith-expr>
   | <post-arith-expr> '*' <post-arith-expr>
   | <post-arith-expr> '/' <post-arith-expr>
   | <post-arith-expr> '%' <post-arith-expr>
   | <post-arith-expr> '@pre'
   | '(' <post-arith-expr> ')'
   | <postcast>
   | <postinlabel>
   | <real>
   | <int>
   | <exprstring>
   | <nil>
   | <postread>
   | <postvar>
   | <postcall>
   | <return>

<postcall> ::= <ident> '(' <postlist-arguments> ')' 
<postcast> ::= '(' <type> ')' <postrelexpr>  | '|' <type> '|' <postrelexpr>  | 
<postcondition> ::= 'post' <postexpression> 'fail' <string> ';'  | 'post' <postexpression> ';'  | 
<postexpression> ::= <postlogicexpr>
<postinlabel> ::= <postread> '.' <postrelexpr>  | <postvar> '.' <postrelexpr>  | 
<postlist-arguments> ::= ListOf[sep=',',min=0,max=2147483647](<postlogicexpr>)
<postlogicexpr> ::= <post-logic-expr> ::=
   | <post-logic-expr> '&&' <post-logic-expr>
   | <post-logic-expr> 'and' <post-logic-expr>
   | <post-logic-expr> '||' <post-logic-expr>
   | <post-logic-expr> 'or' <post-logic-expr>
   | <post-logic-expr> '^' <post-logic-expr>
   | <post-logic-expr> 'xor' <post-logic-expr>
   | <post-logic-expr> '-->' <post-logic-expr>
   | <post-logic-expr> '<-->' <post-logic-expr>
   | <post-logic-expr> '<->' <post-logic-expr>
   | 'not' <post-logic-expr>
   | '!' <post-logic-expr>
   | '(' <post-logic-expr> ')'
   | <postrelexpr>
   | <postquantset>
   | <postquantrange>
   | <postquantcoll>

<postquantcoll> ::= <quant> <ident> ':' <type> 'in' <postlogicexpr> '{' <postlogicexpr> '}' 
<postquantrange> ::= <quant> <ident> ':' <type> 'in' <postarithexpr> '..' <postarithexpr> '{' <postlogicexpr> '}' 
<postquantset> ::= <quant> <ident> ':' <type> 'in' '[' <postlist-arguments> ']' '{' <postlogicexpr> '}' 
<postread> ::= '#' <ident> '[' <postarithexpr> ']'  | '#' <ident>  | 
<postrelexpr> ::= <post-rel-expr> ::=
   | <post-rel-expr> '<=' <post-rel-expr>
   | <post-rel-expr> '==' <post-rel-expr>
   | <post-rel-expr> '>=' <post-rel-expr>
   | <post-rel-expr> '<' <post-rel-expr>
   | <post-rel-expr> '!=' <post-rel-expr>
   | <post-rel-expr> '>' <post-rel-expr>
   | <post-rel-expr> '@pre'
   | '(' <post-rel-expr> ')'
   | <postarithexpr>
   | <postinlabel>
   | <postcast>
   | <bool>
   | <return>
   | <postread>
   | <postcall>
   | <postvar>

<postvar> ::= <ident> '[' <postarithexpr> ']'  | <ident> | 
<precondition> ::= 'pre' <preexpression> 'fail' <string> ';'  | 'pre' <preexpression> ';'  | 
<preexpression> ::= <logicexpr>
<property> ::= 'property' <access> <type> <ident> ';' 
<provide> ::= 'provide' 'service' <ident> 'in' <qualif-ident> ';' 
<provides> ::= (<provide>)*
<qualif-ident> ::= [a-zA-Z_]\w*([.][a-zA-Z_]\w*)*(\[\])?
<quant> ::= forall|exists|FORALL|EXISTS
<quantcoll> ::= <quant> <ident> ':' <type> 'in' <logicexpr> '{' <logicexpr> '}' 
<quantrange> ::= <quant> <ident> ':' <type> 'in' <arithexpr> '..' <arithexpr> '{' <logicexpr> '}' 
<quantset> ::= <quant> <ident> ':' <type> 'in' '[' <list-arguments> ']' '{' <logicexpr> '}' 
<read> ::= '#' <ident> '[' <arithexpr> ']'  | '#' <ident>  | 
<real> ::= (-)?[0-9]+\.([0-9]+)?([eE]([+-])?[0-9]+)?
<refine> ::= 'refine' 'service' <ident> 'module' <qualif-ident> ';' 
<relexpr> ::= <rel-expr> ::=
   | <rel-expr> '<=' <rel-expr>
   | <rel-expr> '==' <rel-expr>
   | <rel-expr> '>=' <rel-expr>
   | <rel-expr> '<' <rel-expr>
   | <rel-expr> '!=' <rel-expr>
   | <rel-expr> '>' <rel-expr>
   | '(' <rel-expr> ')'
   | <arithexpr>
   | <inlabel>
   | <bool>
   | <cast>
   | <read>
   | <call>
   | <var>

<require> ::= 'require' 'service' <ident> 'in' <qualif-ident> 'as' <ident> ';' 
<requires> ::= (<require>)*
<return> ::= @return
<service-parser> ::= 'module' <qualif-ident> ';' 'service' <ident> '{' (<implements>)* (<refine>)* (<include>)* (<property>)* (<invariant>)* (<method>)* (<behavior>)? '}' 
<skip> ::= (<spaces> ; (<lineComment>)? ; )*
<spaces> ::= (any whitespace character)*
<state> ::= 'state' <ident> '{' (<list-allows>)? '}' 
<states> ::= 'states' '{' (<state>)* '}' 
<string> ::= "[^"]*"
<transition> ::= 'transition' 'from' <ident> 'to' <ident> 'with' <ident> 'when' <preexpression> ';'  | 'transition' 'from' <ident> 'to' <ident> 'with' <ident> ';'  | 
<transitions> ::= 'transitions' '{' <list-transitions> '}' 
<type> ::= [a-zA-Z_]\w*([.][a-zA-Z_]\w*)*(\[\])?
<var> ::= <ident> '[' <arithexpr> ']'  | <ident> | 

```