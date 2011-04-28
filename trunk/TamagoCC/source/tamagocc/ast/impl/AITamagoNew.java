/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AITamagoNew extends AINew implements ATamagoNew {

	private int entitytype;
	private String percolator;
	private AModule module;
	private boolean defaultPercolator;
	
	
	/**
	 * @param type
	 */
	public AITamagoNew(AType type,AModule module,int entitytype) {
		super(type);
		this.entitytype = entitytype;
		this.module = module;
		this.percolator = "";
		this.defaultPercolator = true;
	}

	public AITamagoNew(AType type,AModule module,int entitytype,String percolatorname) {
		super(type);
		this.entitytype = entitytype;
		this.module = module;
		this.percolator = "";
		this.defaultPercolator = false;
		this.percolator = percolatorname;
	}	
	
	public AITamagoNew(String compo,int entitytype) {
		super(null);
		int pos = compo.lastIndexOf(".");
		String module = compo.substring(0,pos);
		String type = compo.substring(pos+1);
		this.entitytype = entitytype;
		this.module = new AIModule(module);
		this.type = AIType.generateType(type);
		this.percolator = "";
		this.defaultPercolator = true;
	}

	public AITamagoNew(String compo,int entitytype,String percolatorname) {
		super(null);
		this.entitytype = entitytype;
		int pos = compo.lastIndexOf(".");
		String module = compo.substring(0,pos);
		String type = compo.substring(pos+1);
		this.entitytype = entitytype;
		this.module = new AIModule(module);
		this.type = AIType.generateType(type);
		this.percolator = "";
		this.defaultPercolator = false;
		this.percolator = percolatorname;
	}	
		
	
	/**
	 * @see tamagocc.ast.api.ATamagoNew#getEntityType()
	 */
	public int getEntityType() {
		return entitytype;
	}

	/**
	 * @see tamagocc.ast.api.ATamagoNew#getPercolatorName()
	 */
	public String getPercolatorName() {
		return percolator;
	}

	/**
	 * @see tamagocc.ast.api.ATamagoNew#getModule()
	 */
	public AModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.ast.api.ATamagoNew#useDefaultPercolator()
	 */
	public boolean useDefaultPercolator() {
		return defaultPercolator;
	}

	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitTamagoNew(this);
	}
	
	public static void main(String args[]) {
		String test = "tamago.ext.tamagocc.ImplBankAccount";
		
		int pos = test.lastIndexOf(".");
		
		String module = test.substring(0,pos);
		String type = test.substring(pos+1);
		
		System.out.println(module+":"+type);
	}

}
