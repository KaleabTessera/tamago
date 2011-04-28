/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ABodyMethodContainer;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.ASequence;
import tamagocc.exception.TamagoCCException;

/**
 * This class represents the body of the implementation of a Container for a specific percolation pattern.
 * It is inherit from ASequence, and allow user to add in specific location the correct instruction.
 * 
 * A body of a container is separated in 10 parts:
 * <ol>
 * <li>initbody</li> : affect initial value to specific variable 
 * <li>preinvariant</li> : check invariants
 * <li>initprecondition</li> : compute the environnement for variable
 * <li>precondition</li> : check the effective precondition
 * <li>redirection</li> : recursive call
 * <li>initpostcondition</li> : compute the environnement for the postcondition
 * <li>postcondition</li> : check the effective postcondition
 * <li>postinvariant</li> : check invariants
 * <li>updatebehavior</li> : update the behavior
 * <li>returnresult</li> : return a result if necessary
 * </ol>
 * @author Hakim Belhaouari
 */

/*
 * TODO : Modifier cette classe pour prendre en compte le service behavior
 */
public class AIBodyMethodContainer implements ABodyMethodContainer {

	private AISequence initbody;
	private AISequence preinvariant;
	private AISequence preinitcondition;
	private AISequence precondition;
	private AISequence redirection;
	private AISequence postinitcondition;
	private AISequence postcondition;
	private AISequence postinvariant;
	private AISequence updatebehavior;
	private AISequence returnresult;
	private AComment comment;
	private AISequence atprecondition;
	
	/**
	 * 
	 */
	public AIBodyMethodContainer() {
		super();
		initbody = new AISequence();
		preinvariant = new AISequence();
		precondition = new AISequence();
		redirection = new AISequence();
		postcondition = new AISequence();
		postinvariant = new AISequence();
		updatebehavior = new AISequence();
		returnresult = new AISequence();
		preinitcondition = new AISequence();
		postinitcondition = new AISequence();
		atprecondition = new AISequence(); 
		
		comment = new AINoComment();
		
		initbody.addInstruction(new AINoInstruction("Initialisation of variables"));
		preinvariant.addInstruction(new AINoInstruction("Invariant test before precondition"));
		preinitcondition.addInstruction(new AINoInstruction("Environment of Precondition"));
		precondition.addInstruction(new AINoInstruction("Precondition test"));
		redirection.addInstruction(new AINoInstruction("redirect call"));
		postinitcondition.addInstruction(new AINoInstruction("Environment of Postcondition"));
		postcondition.addInstruction(new AINoInstruction("Postcondition test"));
		postinvariant.addInstruction(new AINoInstruction("Invariant test after postcondition"));
		updatebehavior.addInstruction(new AINoInstruction("Update behavioral's state"));
		returnresult.addInstruction(new AINoInstruction("Return of the redirect method if needed"));
		atprecondition.addInstruction(new AINoInstruction("Initialisation of at pre expression"));
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getInitBody()
	 */
	public ASequence getInitBody() {
		return initbody;
	}
	
	public void addInitBodyInstruction(AInstruction inst) {
		initbody.addInstruction(inst);
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getPreInvariant()
	 */
	public ASequence getPreInvariant() {
		return preinvariant;
	}
	
	public void addPreInvariantInstruction(AInstruction inst) {
		preinvariant.addInstruction(inst);
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getPrecondition()
	 */
	public ASequence getPrecondition() {
		return precondition;
	}

	public void addPreconditionInstruction(AInstruction inst) {
		precondition.addInstruction(inst);
	}
	
	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getRedirection()
	 */
	public ASequence getRedirection() {
		return redirection;
	}
	
	public void addRedirectionInstruction(AInstruction inst) {
		redirection.addInstruction(inst);
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getPostcondition()
	 */
	public ASequence getPostcondition() {
		return postcondition;
	}
	
	public void addPostconditionInstruction(AInstruction inst) {
		postcondition.addInstruction(inst);
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#Postinvariant()
	 */
	public ASequence getPostinvariant() {
		return postinvariant;
	}
	
	public void addPostInvariantInstruction(AInstruction inst) {
		postinvariant.addInstruction(inst);
	}

	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getUpdateBehavior()
	 */
	public ASequence getUpdateBehavior() {
		return updatebehavior;
	}

	public void addUpdateBehaviorInstruction(AInstruction inst) {
		updatebehavior.addInstruction(inst);
	}
	
	/**
	 * @see tamagocc.ast.api.ABodyMethodContainer#getReturnResult()
	 */
	public ASequence getReturnResult() {
		return returnresult;
	}
	
	public void addReturnResult(AInstruction inst) {
		returnresult.addInstruction(inst);
	}
	

	/**
	 * @see tamagocc.ast.api.ASequence#getInstructions()
	 */
	public Iterator<AInstruction> getInstructions() {
		ArrayList<AInstruction> list = new ArrayList<AInstruction>();
		Iterator<? extends AInstruction> tmp;
		
		tmp = initbody.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = preinvariant.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}

		tmp = preinitcondition.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = precondition.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = atprecondition.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = redirection.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
				
		tmp = postinitcondition.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = postcondition.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = postinvariant.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = updatebehavior.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		tmp = returnresult.getInstructions();
		while(tmp.hasNext()) {
			AInstruction inst = (AInstruction)tmp.next();
			list.add(inst);
		}
		
		return list.iterator();
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.SEQUENCE;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getComment()
	 */
	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitSequence(this);
	}

	public ASequence getInitPostcondition() {
		return preinitcondition;
	}

	
	public void addInitPostcondition(AInstruction i) {
		postinitcondition.addInstruction(i);
	}

	public ASequence getInitPrecondition() {
		return postinitcondition;
	}
	public void addInitPrecondition(AInstruction i) {
		preinitcondition.addInstruction(i);
	}

	public void addAtPrecondition(AInstruction i) {
		atprecondition.addInstruction(i);		
	}

}
