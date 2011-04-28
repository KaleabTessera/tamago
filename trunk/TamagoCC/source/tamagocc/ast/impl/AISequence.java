/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.ASequence;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AISequence implements ASequence {

	private ArrayList<AInstruction> sequence;	
	private AComment comment;
	/**
	 * 
	 */
	public AISequence(Collection<AInstruction> instructions) {
		super();
		this.sequence = new ArrayList<AInstruction>();
		this.sequence.addAll(instructions);
		comment = new AINoComment();
	}
	
	public AISequence() {
		super();
		this.sequence = new ArrayList<AInstruction>();
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.ASequence#getInstructions()
	 */
	public Iterator<AInstruction> getInstructions() {
		return sequence.iterator();
	}
	
	public void addInstruction(AInstruction inst) {
		if(inst instanceof ASequence) {
			addSequence((AISequence) inst);
		}
		else
			sequence.add(inst);
	}

	public void addSequence(AISequence seq ) {
		sequence.addAll(seq.sequence);
	}
	
	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.SEQUENCE;
	}
	
	public AInstruction flattern() {
		if(sequence.size() == 1)
			return sequence.get(0);
		else
			return this;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitSequence(this);
	}

	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

	public int size() {
		return sequence.size();
	}
}
