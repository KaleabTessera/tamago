/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ACatchBlock;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.ATryCatchFinal;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AITryCatchFinal implements ATryCatchFinal {

	private AInstruction trybody;
	private ArrayList<ACatchBlock> catchblocks;
	private AInstruction finalbody;
	private AComment comment;
	
	/**
	 * 
	 */
	public AITryCatchFinal(AInstruction trybody,ACatchBlock catchblock) {
		super();
		this.trybody = trybody;
		this.catchblocks = new ArrayList<ACatchBlock>(2);
		this.catchblocks.add(catchblock);
		this.finalbody = AINoInstruction.getNoInstruction();
		comment = new AINoComment();
	}
	
	public AITryCatchFinal(AInstruction trybody,Collection<ACatchBlock> catchblocks) {
		super();
		this.trybody = trybody;
		this.catchblocks = new ArrayList<ACatchBlock>(2);
		this.catchblocks.addAll(catchblocks);
		this.finalbody = AINoInstruction.getNoInstruction();
		comment = new AINoComment();
	}

	public AITryCatchFinal(AInstruction trybody,ACatchBlock catchblock,AInstruction finalbody) {
		super();
		this.trybody = trybody;
		this.catchblocks = new ArrayList<ACatchBlock>(2);
		this.catchblocks.add(catchblock);
		this.finalbody = finalbody;
		comment = new AINoComment();
	}


	public AITryCatchFinal(AInstruction trybody,Collection<ACatchBlock> catchblocks,AInstruction finalbody) {
		super();
		this.trybody = trybody;
		this.catchblocks = new ArrayList<ACatchBlock>();
		this.catchblocks.addAll(catchblocks);
		this.finalbody = finalbody;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.ATryCatchFinal#getTryBody()
	 */
	public AInstruction getTryBody() {
		return trybody;
	}

	/**
	 * @see tamagocc.ast.api.ATryCatchFinal#getCatchBlock()
	 */
	public Iterable<ACatchBlock> getCatchBlock() {
		return catchblocks;
	}

	public void addCatchBlock(ACatchBlock c) {
		catchblocks.add(c);
	}
	
	/**
	 * @see tamagocc.ast.api.ATryCatchFinal#getFinalBody()
	 */
	public AInstruction getFinalBody() {
		return finalbody;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.TRYCATCHFINAL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitTryCatchFinal(this);
	}
	
	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

}
