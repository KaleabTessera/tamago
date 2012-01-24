package tamagocc.util;

import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GForallSet;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GIQuantifierVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCMakeReadableGExpression implements
		TamagoCCGExpressionVisitor {

	
	private StringBuffer sb;
	private GExpression expr;
	private String result;
	/**
	 * 
	 */
	public TamagoCCMakeReadableGExpression(GExpression expr) {
		super();
		sb = new StringBuffer();
		this.expr = expr;
		result = null;
	}
	
	public String getStrExpression() throws TamagoCCException {
		if(result == null) {
			expr.visitExpression(this);
			result = sb.toString();
		}
		return result;
	}
	
	public static String toString(GExpression expr) {
		TamagoCCMakeReadableGExpression mlg = new TamagoCCMakeReadableGExpression(expr);
		try {
			return mlg.getStrExpression();
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
		return "<error>";
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExpression(tamagocc.generic.api.GExpression)
	 */
	public Object visitExpression(GExpression expression)
			throws TamagoCCException {
		throw new TamagoCCException("TamagoCCMakeReadableGExpression : unsupported visit");
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitPre(tamagocc.generic.api.GAtPre)
	 */
	public Object visitPre(GAtPre atpre) throws TamagoCCException {
		atpre.getRawExpr().visitExpression(this);
		sb.append("@pre");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitBool(tamagocc.generic.api.GBool)
	 */
	public Object visitBool(GBool bool) throws TamagoCCException {
		sb.append(""+bool.getValue());
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCall(tamagocc.generic.api.GCall)
	 */
	public Object visitCall(GCall call) throws TamagoCCException {
		sb.append(call.getName());
		sb.append("(");
		Iterator<? extends GExpression> args = call.getArguments();
		while(args.hasNext()) {
			GExpression expr = (GExpression)args.next();
			expr.visitExpression(this);
			if(args.hasNext())
				sb.append(", ");
		}
		sb.append(")");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInLabel(tamagocc.generic.api.GInLabel)
	 */
	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		inlabel.getTarget().visitExpression(this);
		sb.append(".");
		inlabel.getSubExpression().visitExpression(this);
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInteger(tamagocc.generic.api.GInteger)
	 */
	public Object visitInteger(GInteger interger) throws TamagoCCException {
		sb.append(""+interger.getValue());
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNoContract(tamagocc.generic.api.GNoContract)
	 */
	public Object visitNoContract(GNoContract nocontract)
			throws TamagoCCException {
		sb.append(" [nocontract] ");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNot(tamagocc.generic.api.GNot)
	 */
	public Object visitNot(GNot not) throws TamagoCCException {
		sb.append("not(");
		not.getTerm().visitExpression(this);
		sb.append(")");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitOperator(tamagocc.generic.api.GOperator)
	 */
	public Object visitOperator(GOperator operator) throws TamagoCCException {
		Iterator<? extends GExpression> operands = operator.getOperands();
		sb.append("(");
		while(operands.hasNext()) {
			GExpression expr = (GExpression)operands.next();
			expr.visitExpression(this);
			if(operands.hasNext()) {
				sb.append(" "+operator(operator.getOperator())+" ");
			}
		}
		sb.append(")");
		return null;
	}
	
	private String operator(TOpeName op) throws TamagoCCException {
		switch(op.getID()) {
		case TOpeName.EG: return("==");
        case TOpeName.NE: return( "!=");
        case TOpeName.INF: return( "<");
        case TOpeName.INFEG: return( "<=");
        case TOpeName.SUP: return( ">");
        case TOpeName.SUPEG: return( ">=");
        case TOpeName.PLUS: return( "+");
        case TOpeName.MINUS: return( "-");
        case TOpeName.TIMES: return( "*");
        case TOpeName.MOD: return ("mod");
        case TOpeName.QUO: return( "quo");
        case TOpeName.AND: return( "and");
        case TOpeName.OR: return( "or");
        case TOpeName.XOR: return( "xor");
        case TOpeName.IMPLY: return("=>");
        case TOpeName.EQUIV: return "<=>";
        default:
            throw new TamagoCCException("TamagoCCMakeReadableGExpression : unknow operator<"+op.getID()+">");
		}
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitRead(tamagocc.generic.api.GRead)
	 */
	public Object visitRead(GRead read) throws TamagoCCException {
		sb.append("#"+read.getName());
		if(read.hasIndex()) {
			sb.append("[");
			read.getIndex().visitExpression(this);
			sb.append("]");
		}
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReal(tamagocc.generic.api.GReal)
	 */
	public Object visitReal(GReal real) throws TamagoCCException {
		sb.append(""+real.getValue());
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReturn(tamagocc.generic.api.GReturn)
	 */
	public Object visitReturn(GReturn ret) throws TamagoCCException {
		//ret.getVariable().visitExpression(this);
		sb.append("@return");
		if(ret.hasIndex()) {
			sb.append("[");
			ret.getIndex().visitExpression(this);
			sb.append("]");
		}
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitString(tamagocc.generic.api.GString)
	 */
	public Object visitString(GString string) throws TamagoCCException {
		sb.append("\\\""+string.getValue()+"\\\"");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitVariable(tamagocc.generic.api.GVariable)
	 */
	public Object visitVariable(GVariable variable) throws TamagoCCException {
		sb.append(variable.getName());
		if(variable.hasIndex()) {
			sb.append("[");
			variable.getIndex().visitExpression(this);
			sb.append("]");
		}
		return null;
	}

	public Object visitExistRange(GExistRange r) throws TamagoCCException {
		sb.append("\\\\exist ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in ");
		r.getMin().visitExpression(this);
		sb.append("..");
		r.getMax().visitExpression(this);
		sb.append(" { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	public Object visitExistSet(GExistSet r) throws TamagoCCException {
		sb.append("\\\\exist ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in [ ");
		Iterator<GExpression> exprs = r.getSet().getElements().iterator();
		while(exprs.hasNext()) {
			exprs.next().visitExpression(this);
			if(exprs.hasNext()) {
				sb.append(", ");
			}
		}
		
		sb.append(" ]  { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	public Object visitForallRange(GForallRange r) throws TamagoCCException {
		sb.append("\\\\forall ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in ");
		r.getMin().visitExpression(this);
		sb.append("..");
		r.getMax().visitExpression(this);
		sb.append(" { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	public Object visitForallSet(GForallSet r) throws TamagoCCException {
		sb.append("\\\\forall ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in [ ");
		Iterator<GExpression> exprs = r.getSet().getElements().iterator();
		while(exprs.hasNext()) {
			exprs.next().visitExpression(this);
			if(exprs.hasNext()) {
				sb.append(", ");
			}
		}
		
		sb.append(" ]  { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	public Object visitQuantifierVariable(GIQuantifierVariable variable) throws TamagoCCException {
		switch(variable.getQuantifier().getQuantifierType()) {
		case EXISTRANGE:
			return visitExistRange((GExistRange) variable.getQuantifier());
		case EXISTSET:
			return visitExistSet((GExistSet) variable.getQuantifier());
		case FORALLRANGE:
			return visitForallRange((GForallRange) variable.getQuantifier());
		case FORALLSET:
			return visitForallSet((GForallSet) variable.getQuantifier());
		case EXISTCOLL:
			return visitExistColl((GExistColl)variable.getQuantifier());
		case FORALLCOLL:
			return visitForallColl((GForallColl)variable.getQuantifier());
		}
		throw new TamagoCCException("Unsupported element");
	}

	/**
	 * @param quantifier
	 * @return
	 * @throws TamagoCCException 
	 */
	public Object visitExistColl(GExistColl r) throws TamagoCCException {
		sb.append("\\\\exists ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in ");
		r.getCollection().visitExpression(this);
		sb.append(" { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	/**
	 * @param quantifier
	 * @return
	 * @throws TamagoCCException 
	 */
	public Object visitForallColl(GForallColl r) throws TamagoCCException {
		sb.append("\\\\forall ");
		r.getVariable().visitExpression(this);
		sb.append(":");
		sb.append(r.getType().getType());
		sb.append(" in ");
		r.getCollection().visitExpression(this);
		sb.append(" { ");
		r.getBody().visitExpression(this);
		sb.append(" } ");
		return null;
	}

	public Object visitNil(GNil nil) throws TamagoCCException {
		sb.append("null");
		return null;
	}

	public Object visitLanguageExpr(GLanguageExpr languageExpr)
			throws TamagoCCException {
		sb.append(languageExpr.getExpression());
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
	 */
	public Object visitCast(GCast cast) throws TamagoCCException {
		sb.append("((");
		sb.append(cast.getType().getType());
		sb.append(")");
		cast.getExpression().visitExpression(this);
		sb.append(")");
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitSet(tamagocc.generic.api.GSet)
	 */
	public Object visitSet(GSet set) throws TamagoCCException {
		// fonction inutile deja incluse
		return null;
	}

	@Override
	public Object visitInState(GInState l) throws TamagoCCException {
		sb.append("@InState(");
		Iterator<String> ite = l.getInState().iterator();
		while(ite.hasNext()) {
			String st = ite.next();
			sb.append("\""+st+"\"");
			if(ite.hasNext())
				sb.append(",");
		}
		sb.append(")");
		return null;
	}

	@Override
	public Object visitIsBound(GIsBound giIsBound) throws TamagoCCException {
		sb.append("@isBound(");
		sb.append(giIsBound.getLabel());
		sb.append(")");
		return sb.toString();
	}
}
