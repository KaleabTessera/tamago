package tamagotest;

import java.util.ArrayList;
import java.util.Iterator;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoEnvironment;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GQuantifier;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GICall;
import tamagocc.generic.impl.GICast;
import tamagocc.generic.impl.GIExistColl;
import tamagocc.generic.impl.GIExistRange;
import tamagocc.generic.impl.GIForallColl;
import tamagocc.generic.impl.GIForallRange;
import tamagocc.generic.impl.GIInLabel;
import tamagocc.generic.impl.GINot;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIQuantifierVariable;
import tamagocc.generic.impl.GIRead;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestRename implements TamagoCCGExpressionVisitor{

	private TamagoEnvironment env;
	private transient boolean atpre;
	
	/**
	 * 
	 */
	public TamagoTestRename(TamagoEnvironment env) {
		this.env = env;
		atpre = false;
	}

	public Object visitBool(GBool bool) throws TamagoCCException {
		return bool;
	}

	public Object visitCall(GCall call) throws TamagoCCException {
		ArrayList<GExpression> arguments = new ArrayList<GExpression>();
		Iterator<GExpression> args = call.getArguments();
		while(args.hasNext()) {
			GExpression arg = args.next();
			arguments.add((GExpression) arg.visitExpression(this));
		}
		return new GICall(call.getName(),arguments);
	}

	public Object visitExpression(GExpression expression) throws TamagoCCException {
		throw new TamagoCCException("unsupported expression");
	}

	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		GExpression target  = (GExpression) inlabel.getTarget().visitExpression(this);
		GExpression subexpr = (GExpression) inlabel.getSubExpression().visitExpression(this);
		
		GIInLabel lab = new GIInLabel(target,subexpr);
		return lab;
	}

	public Object visitInteger(GInteger interger) throws TamagoCCException {
		return interger;
	}

	public Object visitLanguageExpr(GLanguageExpr languageExpr) throws TamagoCCException {
		return languageExpr;
	}

	public Object visitNil(GNil nil) throws TamagoCCException {
		return nil;
	}

	public Object visitNoContract(GNoContract nocontract) throws TamagoCCException {
		return nocontract;
	}

	public Object visitNot(GNot not) throws TamagoCCException {
		return new GINot((GExpression) not.getTerm().visitExpression(this));
	}

	public Object visitOperator(GOperator operator) throws TamagoCCException {
		
		GIOperator renvoie = new GIOperator(operator.getOperator());
		
		Iterator<GExpression> opes = operator.getOperands();
		while(opes.hasNext()) {
			GExpression ope = opes.next();
			renvoie.addOperand((GExpression) ope.visitExpression(this));
		}

		return renvoie;
	}

	public Object visitPre(GAtPre atpre) throws TamagoCCException {
		this.atpre = true;
		GExpression expr = (GExpression) atpre.getRawExpr().visitExpression(this);
		this.atpre = false;
		return expr;
	}

	public Object visitQuantifierVariable(GIQuantifierVariable variable) throws TamagoCCException {
		
		GExpression body = (GExpression) variable.getQuantifier().getBody().visitExpression(this);
		GQuantifier quant = null;
		switch(variable.getQuantifier().getQuantifierType()) {
		case EXISTCOLL: {
			GExpression coll = (GExpression) ((GExistColl)variable.getQuantifier()).getCollection().visitExpression(this); 
			quant =  new GIExistColl(variable.getQuantifier().getType(),variable.getQuantifier().getVariable(),
					coll,body);
			break;
		}
		case EXISTRANGE: {
			GExpression deb = (GExpression)((GExistRange)variable.getQuantifier()).getMin().visitExpression(this);
			GExpression fin = (GExpression)((GExistRange)variable.getQuantifier()).getMax().visitExpression(this);
			quant = new GIExistRange(variable.getQuantifier().getType(),variable.getQuantifier().getVariable(),
					deb,fin,body);
			break;
		}
		case EXISTSET:
			throw new TamagoCCException("Mauvaise Heuristique me contacter FORALLRANGE!!!");
		case FORALLCOLL:
			GExpression coll = (GExpression) ((GForallColl)variable.getQuantifier()).getCollection().visitExpression(this); 
			quant = new GIForallColl(variable.getQuantifier().getType(),variable.getQuantifier().getVariable(),
					coll,body);
			break;
		case FORALLRANGE:
			GExpression deb = (GExpression)((GForallRange)variable.getQuantifier()).getMin().visitExpression(this);
			GExpression fin = (GExpression)((GForallRange)variable.getQuantifier()).getMax().visitExpression(this);
			quant = new GIForallRange(variable.getQuantifier().getType(),variable.getQuantifier().getVariable(),
					deb,fin,body);
			break;
		case FORALLSET:
			throw new TamagoCCException("Mauvaise Heuristique me contacter FORALLSET!!!");
		}
		if(quant == null)
			throw new TamagoCCException("ERROR FORGET ONE CASE IN QUANTIFIER EXPRESSION (really strange?)");
		return new GIQuantifierVariable(variable.getName(), quant);
	}

	public Object visitRead(GRead read) throws TamagoCCException {
		if(this.atpre) {
			//return read;
			if(read.hasIndex())
				return new GIRead(read.getName(),(GExpression) read.getIndex().visitExpression(this));
			else
				return new GIRead(read.getName());
		}
		else {
			if(env.containsKey(read.getName())) {
				String name = read.getName()+"@post";
				TamagoBuilder tb = null;
				try {
					tb = TamagoTest.getContext().getStrategy().searchBuilder(name, env.get(read.getName()).getType(),
							TamagoTest.getContext().getContract(),env);
					env.put(name, tb);
					if(read.hasIndex())
						return new GIRead(name,(GExpression) read.getIndex().visitExpression(this));
					else
						return new GIRead(name);
				} catch (Exception e) {
					throw new TamagoCCException(e);
				}
			}
			
			TamagoCCLogger.infoln(3,"*Warning*: Unfound property in environment: "+read.getName());
			return read;
		}
	}

	public Object visitReal(GReal real) throws TamagoCCException {
		return real;
	}

	public Object visitReturn(GReturn ret) throws TamagoCCException {
		return ret;
	}

	public Object visitString(GString string) throws TamagoCCException {
		return string;
	}

	public Object visitVariable(GVariable variable) throws TamagoCCException {
		return variable;
	}

	/**
	 * TODO pas sur de l'implantation
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
	 */
	public Object visitCast(GCast cast) throws TamagoCCException {
		return new GICast(cast.getType(), (GExpression)cast.getExpression().visitExpression(this));
	}

	@Override
	public Object visitInState(GInState giInState) throws TamagoCCException {
		return giInState;
	}

	/*private TamagoBuilder searchBuilder(String name,GType param) throws TamagoCCException{
		try {
			Class<?> cb = (Class<?>) Class.forName("tamago.builder.impl.Builder"+param.getType().toLowerCase());
			Constructor<?> cons = cb.getConstructor(new Class[] { TamagoEnvironment.class, String.class , Backtracking.class });
			return (TamagoBuilder) cons.newInstance(new Object[] { env, name , new Backtracking() });
		} catch (Exception e) {
			throw new TamagoCCException(e);
		} 
	}*/
}
