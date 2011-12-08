package tamagocc.parser;

import tamagocc.parser.cdlast.CDLAtPre;
import tamagocc.parser.cdlast.CDLBool;
import tamagocc.parser.cdlast.CDLCall;
import tamagocc.parser.cdlast.CDLCast;
import tamagocc.parser.cdlast.CDLExpression;
import tamagocc.parser.cdlast.CDLExpressionType;
import tamagocc.parser.cdlast.CDLInLabel;
import tamagocc.parser.cdlast.CDLInfix;
import tamagocc.parser.cdlast.CDLInteger;
import tamagocc.parser.cdlast.CDLNil;
import tamagocc.parser.cdlast.CDLNot;
import tamagocc.parser.cdlast.CDLQuantifier;
import tamagocc.parser.cdlast.CDLRead;
import tamagocc.parser.cdlast.CDLReal;
import tamagocc.parser.cdlast.CDLReturn;
import tamagocc.parser.cdlast.CDLString;
import tamagocc.parser.cdlast.CDLVariable;


public final class SearchTypeInfixOperator {

	public static CDLExpressionType getType(CDLExpression e) throws WrongTypeOperator  {
		SearchTypeInfixOperator stop = new SearchTypeInfixOperator();
		return stop.type(e);
	}
	
	private CDLExpressionType type(CDLExpression e) throws WrongTypeOperator {
		if(e instanceof CDLAtPre || e instanceof CDLCall || e instanceof CDLCast || e instanceof CDLVariable || e instanceof CDLRead || e instanceof CDLReturn || e instanceof CDLInLabel)
			return CDLExpressionType.NONE;
		if(e instanceof CDLBool || e instanceof CDLQuantifier)
			return CDLExpressionType.BOOL;
		if(e instanceof CDLNot) {
			CDLExpressionType type = type(((CDLNot) e).getOperand());
			if(type == CDLExpressionType.ARITH)
				throw new WrongTypeOperator(e,CDLExpressionType.BOOL, CDLExpressionType.ARITH);
			return CDLExpressionType.BOOL;
		}
		if(e instanceof CDLInteger || e instanceof CDLReal || e instanceof CDLNil || e instanceof CDLString) {
			return CDLExpressionType.ARITH;
		}
		
		if(e instanceof CDLInfix) {
			String op = e.getDescription();
			CDLExpressionType expected = CDLExpressionType.NONE;
			if("+".equals(op) || "-".equals(op) || "*".equals(op) || "/".equals(op)|| "%".equals(op))
				expected = CDLExpressionType.ARITH;
			else if("<".equals(op) || "<=".equals(op) || ">=".equals(op) || ">".equals(op)|| "==".equals(op) || "!=".equals(op)  || "<>".equals(op))
				expected = CDLExpressionType.REL;
			else if("&&".equals(op) || "||".equals(op) || "xor".equals(op) || "^".equals(op) || "and".equals(op) || "or".equals(op) || "xor".equals(op)      
					|| "-->".equals(op) || "==>".equals(op) || "<-->".equals(op) || "<===>".equals(op)|| "<==>".equals(op))
				expected = CDLExpressionType.BOOL;
			else
				throw new WrongTypeOperator("Unsupported operator: "+op);
			
			CDLExpressionType left = type(((CDLInfix) e).getLeftOperand());
			CDLExpressionType right = type(((CDLInfix) e).getRightOperand());
			
			switch(expected) {
			case ARITH:
			case REL:
				if(left == CDLExpressionType.ARITH || left == CDLExpressionType.NONE) {
					if(right == CDLExpressionType.ARITH || right == CDLExpressionType.NONE) {
						return expected;
					}
					else
						throw new WrongTypeOperator(e, expected, right);
				}
				else
					throw new WrongTypeOperator(e, expected, left);
			case BOOL:
				if(left == CDLExpressionType.BOOL || left == CDLExpressionType.NONE || left == CDLExpressionType.REL) {
					if(right == CDLExpressionType.BOOL || right == CDLExpressionType.NONE|| right == CDLExpressionType.REL) {
						return expected;
					}
					else
						throw new WrongTypeOperator(e, expected, right);
				}
				else
					throw new WrongTypeOperator(e, expected, left);
			} // end switch
			
		}
		
		return CDLExpressionType.NONE;
	}
	
	
	
	
}
