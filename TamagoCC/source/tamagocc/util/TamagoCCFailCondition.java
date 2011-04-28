/**
 * 
 */
package tamagocc.util;
 
import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIThrowException;
import tamagocc.ast.impl.AIType;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GCondition;

/**
 * @author belhaouari
 *
 */
public class TamagoCCFailCondition {

	public static final int INVARIANT = 0;
	public static final int PRECONDITION = 1;
	public static final int POSTCONDITION = 2;
	
	
	private ArrayList<InformationGA> conditions;
	private int type;
	private AVariable var;
	
	
	class InformationGA {
		GCondition condition;
		AExpression variable;
		
		InformationGA(GCondition cond,AExpression var) {
			condition = cond;
			variable = var;
		}
	}
	
		
	/**
	 * 
	 */
	public TamagoCCFailCondition(int type,AVariable var) {
		super();
		conditions = new ArrayList<InformationGA>();
		this.type = type;
		this.var = var;
	}
	
	public int getTypeCondition() {
		return type;
	}
	
	public void addFailedCondition(GCondition condition,AVariable var) {
		InformationGA info = new InformationGA(condition,var);		
		conditions.add(info);
	}
	
	public AInstruction generateThrowException() throws TamagoCCException {
		// TODO : ajouter la verification du flag de detail
		
		AISequence renvoie = new AISequence();
		Iterator<InformationGA> infos = conditions.iterator();
		
		AIType type = (AIType) AIType.generateType("java.util.ArrayList");
		AINew newtab = new AINew(type);
		AIInitialisation init = new AIInitialisation(var.getIdent(),type,newtab);
		renvoie.addInstruction(init);
		
		while(infos.hasNext()) {
			InformationGA info = (InformationGA)infos.next();
			// il faut tester si c'est a faux
			AINot nonboolvar = new AINot(info.variable);
			
			AIType itemtype = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailItem");
			AINew newitem = new AINew(itemtype);
			
			TamagoCCMakeReadableGExpression gen = new TamagoCCMakeReadableGExpression(info.condition.getExpression());
			newitem.addArguments(new AIString(gen.getStrExpression()));
			
			if(info.condition.getMessage().length() > 0) {
				newitem.addArguments(new AIString(info.condition.getMessage()));
			}
			
			
			AICall addarg = new AICall(new AIIdent("add"));
			addarg.addArgument(newitem);
			AIInLabel intab = new AIInLabel(var,addarg);
			
			AIIfThenElse testadd = new AIIfThenElse(nonboolvar,new AIInstExpression(intab),new AINoInstruction());
			
			renvoie.addInstruction(testadd);
		}
		
		
		
		AIThrowException throwexc = new AIThrowException(getTypeofThrow());
		throwexc.addArgument(var);
		renvoie.addInstruction(throwexc);
		
		return renvoie;		
	}

	public int getConditionCount() {
		return conditions.size();
	}
	
	private AType getTypeofThrow() {
		switch(type) {
		case INVARIANT:
			return (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailInvariant");
		case PRECONDITION:
			return (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPrecondition");
		case POSTCONDITION:
			return (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPostcondition");
		default:
			return (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailCondition");
		}
	}
}
