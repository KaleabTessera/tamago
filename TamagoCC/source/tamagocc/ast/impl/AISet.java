/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.ASet;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AISet implements ASet {

	private ArrayList<AExpression> exprs;
	private String name;
	
	
	private static int num = 0;
	public static String genName() {
		return "__set"+(num++);
	}
	
	/**
	 * 
	 */
	public AISet(Collection<AExpression> list) {
		exprs = new ArrayList<AExpression>(list);
		name = genName();
	}
	
	public AISet() {
		exprs = new ArrayList<AExpression>();
		name = genName();
	}

	
	/**
	 * @see tamagocc.ast.api.ASet#getExpressionCollection()
	 */
	public Collection<AExpression> getExpressionCollection() {
		return exprs;
	}

	/**
	 * @see tamagocc.ast.api.ASet#getExpressions()
	 */
	public Iterator<AExpression> getExpressions() {
		return exprs.iterator();
	}

	/**
	 * @see tamagocc.ast.api.ASet#size()
	 */
	public int size() {
		return exprs.size();
	}
	
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	@SuppressWarnings("deprecation")
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitSet(this);
	}

	
	public AInstruction getInitialize() {
		AIType type = (AIType) AIType.generateType("java.util.ArrayList");
		AIIdent ident = new AIIdent(getName()); 
		AIVariable varset = new AIVariable(ident);
		AINew newobj = new AINew(type);
		AIInitialisation initset = new AIInitialisation(ident,type,newobj);
		AISequence renvoie = new AISequence();
		renvoie.addInstruction(initset);
				
		for (AExpression e : getExpressionCollection()) {
			AICall addcall = new AICall(new AIIdent("add"));
			addcall.addArgument(e);
			AIInLabel inarray = new AIInLabel(varset,addcall);
			renvoie.addInstruction(new AIInstExpression(inarray));
		}
		return renvoie;
	}

}
