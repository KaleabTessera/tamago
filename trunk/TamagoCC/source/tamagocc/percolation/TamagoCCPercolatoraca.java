/**
 * 
 */
package tamagocc.percolation;

import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.TamagoCCAST;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.impl.AIBodyMethodContainer;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.impl.GIAtPreInitialisation;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GIOperator;
import tamagocc.util.TamagoCCFailCondition;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCPercolatoraca extends TamagoCCPercolation {

	
	/**
	 * 
	 */
	public TamagoCCPercolatoraca(GTamago container,TamagoCCGVisitor generator) {
		super(container,generator);
		this.generator = generator;
	}

	/**
	 * @see tamagocc.percolation.TamagoCCPercolation#getName()
	 */
	public String getName() {
		return "aca";
	}

	/**
	 * 
	 * @see tamagocc.percolation.TamagoCCPercolation#fulfillEffPre(tamagocc.generic.api.GMethod, tamagocc.ast.impl.AIBodyMethodContainer, tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public void fulfillEffPre(GMethod method,AIBodyMethodContainer body, TamagoCCGPreExpressionVisitor traitement)
			throws TamagoCCException {
		
		TamagoCCFailCondition failcond = new TamagoCCFailCondition(TamagoCCFailCondition.PRECONDITION,genVariable());
		
		Iterator<GMethod> itelist = this.container.getAllMethods(method).iterator();
		AIOperator opOR = new AIOperator(TOpeName.opOr);
		AINot notopOR = new AINot(opOR);
		
		
		while(itelist.hasNext()) {
			GMethod m = (GMethod)itelist.next();
			
			AIVariable boolvars = genVariable();
			
			GCondition precondition = m.getPrecondition();
			Iterator<GPreExpression> ite = precondition.getExpression().getPreExpression().iterator();
			while(ite.hasNext()) {
				GPreExpression gexpr = (GPreExpression)ite.next();
				gexpr = (GPreExpression) gexpr.visitPreExpression(traitement);
				TamagoCCAST aexpr = (TamagoCCAST)gexpr.visit(generator);
				if(aexpr instanceof AExpression) {
					body.addInitPrecondition(new AIInstExpression((AExpression)aexpr));
				}
				else {
					body.addInitPrecondition((AInstruction)aexpr);
				}
				
			}
			
			AExpression expr = new AIBool(false);
			if(precondition.getExpression().getCategory() != GExpression.GExprType.NOCONTRACT) {
				GExpression gexpr = (GExpression) precondition.getExpression().visitPreExpression(traitement);
				expr = (AExpression)gexpr.visit(generator);
				AIInitialisation init = new AIInitialisation(boolvars.getIdent(),AIType.typeBOOLEAN,expr);
				body.addPreconditionInstruction(init);
				opOR.addOperand(boolvars);
				failcond.addFailedCondition(precondition,boolvars);
			}
		}
		
		AIIfThenElse ifthenelse = new AIIfThenElse(notopOR,failcond.generateThrowException(),AINoInstruction.getNoInstruction());
		
		
		if(opOR.getOperandsCount() > 0) {		
			body.addPreconditionInstruction(ifthenelse);
		}
		else {
			AINoInstruction infoinst = new AINoInstruction();
			infoinst.setComment(new AIInlineComment("No precondition detected"));
			body.addPreconditionInstruction(infoinst);
		}
		
	}

	/**
	 * 
	 * @see tamagocc.percolation.TamagoCCPercolation#fulfillEffPost(tamagocc.generic.api.GMethod, tamagocc.ast.impl.AIBodyMethodContainer, tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public void fulfillEffPost(GMethod method,AIBodyMethodContainer body, TamagoCCGPreExpressionVisitor traitement)
			throws TamagoCCException {
		TamagoCCFailCondition failcond = new TamagoCCFailCondition(TamagoCCFailCondition.POSTCONDITION,genVariable());
		
		Iterator<GMethod> itelist = this.container.getAllMethods(method).iterator();
		AIOperator opAnd = new AIOperator(TOpeName.opAnd);
		AINot notopAND = new AINot(opAnd);
		
		
		while(itelist.hasNext()) {
			GMethod m = (GMethod)itelist.next();
			
			AIVariable boolvars = genVariable();
			
			GCondition postcondition = m.getPostcondition();
			Iterator<GPreExpression> ite = postcondition.getExpression().getPreExpression().iterator();
			while(ite.hasNext()) {
				GPreExpression gexpr = (GPreExpression)ite.next();
				boolean isatpre = gexpr instanceof GIAtPreInitialisation;
				gexpr = (GPreExpression) gexpr.visitPreExpression(traitement);
				TamagoCCAST aexpr = (TamagoCCAST)gexpr.visit(generator);
				if(aexpr instanceof AExpression) {
					if(isatpre)
						body.addAtPrecondition(new AIInstExpression((AExpression)aexpr));
					else
						body.addInitPostcondition(new AIInstExpression((AExpression)aexpr));
				}
				else {
					if(isatpre)
						body.addAtPrecondition((AInstruction)aexpr);
					else
						body.addInitPostcondition((AInstruction)aexpr);
				}
				
			}
			
			AExpression expr = new AIBool(false);
			if(postcondition.getExpression().getCategory() != GExpression.GExprType.NOCONTRACT) {
				GExpression gexpr = (GExpression) postcondition.getExpression().visitPreExpression(traitement);
				expr = (AExpression)gexpr.visit(generator);
				AIInitialisation init = new AIInitialisation(boolvars.getIdent(),AIType.typeBOOLEAN,expr);
				body.addPostconditionInstruction(init);
				opAnd.addOperand(boolvars);
				failcond.addFailedCondition(postcondition,boolvars);
			}
		}
		
		AIIfThenElse ifthenelse = new AIIfThenElse(notopAND,failcond.generateThrowException(),AINoInstruction.getNoInstruction());
		
		if(opAnd.getOperandsCount() > 0) {		
			body.addPostconditionInstruction(ifthenelse);
		}
		else {
			AINoInstruction infoinst = new AINoInstruction();
			infoinst.setComment(new AIInlineComment("No postcondition detected"));
			body.addPostconditionInstruction(infoinst);
		}
		
	}

	
	public GExpression genEffPostExpr(GMethod method) throws TamagoCCException {
		Iterator<GMethod> itelist = this.container.getAllMethods(method).iterator();
		GIOperator opAnd = new GIOperator(TOpeName.opAnd);
		
				
		while(itelist.hasNext()) {
			GMethod m = (GMethod)itelist.next();
			
			
			GCondition postcondition = m.getPostcondition();
			
			if(postcondition.getExpression().getCategory() != GExpression.GExprType.NOCONTRACT) {
				opAnd.addOperand(postcondition.getExpression());
			}
		}
		if(opAnd.getArity() == 0)
			return new GINoContract();
		else
			return opAnd; 
	}

	public GExpression genEffPreExpr(GMethod method) throws TamagoCCException {
		Iterator<GMethod> itelist = this.container.getAllMethods(method).iterator();
		GIOperator opOr = new GIOperator(TOpeName.opOr);
		
				
		while(itelist.hasNext()) {
			GMethod m = (GMethod)itelist.next();
			
			
			GCondition precondition = m.getPrecondition();
			
			if(precondition.getExpression().getCategory() != GExpression.GExprType.NOCONTRACT) {
				opOr.addOperand(precondition.getExpression());
			}
		}
		if(opOr.getArity() == 0)
			return new GINoContract();
		else
			return opOr; 
	}

}
