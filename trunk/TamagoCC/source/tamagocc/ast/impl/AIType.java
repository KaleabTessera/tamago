/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.CatType;
import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIType implements AType {

	private CatType cat;
	private String type;
	private AType array;
	private ArrayList<AType> generics;
	
	
	public static AIType typeBOOLEAN = new AIType("bool",CatType.BOOL);
	public static AIType typeVOID = new AIType("void",CatType.VOID);
	public static AIType typeREAL = new AIType("real",CatType.REAL);
	public static AIType typeINTEGER = new AIType("int",CatType.INTEGER);
	public static AIType typeSTRING = new AIType("String",CatType.STRING);
	
	
	private AIType(String type,CatType cat) {
		super();
		this.cat = cat;
		this.type = type;
		this.array = null;
		generics = new ArrayList<AType>();
	}
	private AIType(String type,CatType cat, Collection<AType> gens) {
		super();
		this.cat = cat;
		this.type = type;
		this.array = null;
		generics = new ArrayList<AType>(gens);
	}
	
	private AIType(CatType cat, String type,AType t) {
		super();
		this.cat = cat;
		this.type = type;// + "[]";
		this.array = t;
		generics = new ArrayList<AType>();
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitType(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof AIType) {
			AIType t = (AIType) o;
			return (getCategoryType() == t.getCategoryType()) && t.getType().equals(getType()) && (isArray()==t.isArray());
		}
		else 
			return false;
	}


	public CatType getCategoryType() {
		return cat;
	}


	public String getType() {
		return type;
	}

	public boolean isArray() {
		return cat == CatType.ARRAY;
	}


	public AType getArrayType() {
		return array;
	}
	
	public static AType generateType(String name) {
		int idx;
		idx = name.lastIndexOf("[]");
		if(idx > 0) {
			AType type = generateType(name.substring(0,idx));
			return new AIType(CatType.ARRAY,name,type);
		}
		else {
			if(name.equals("void"))
	            return new AIType(name,CatType.VOID);
	        else if(name.equals("int"))
	            return new AIType(name,CatType.INTEGER);
	        else if(name.equals("real"))
	            return new AIType(name,CatType.REAL);
	        else if(name.equals("bool"))
	            return new AIType(name,CatType.BOOL);
	        else if(name.equals("String"))
	        	return new AIType(name,CatType.STRING);
	        else if(name.equals("string"))
	        	return new AIType("String",CatType.STRING);
	        else if(name.equals("alpha"))
	        	return new AIType(name,CatType.GENERIC);
	        else {
	        	idx = name.indexOf("<");
	        	if(idx == -1)
	        		return new AIType(name,CatType.OBJECT);
	        	else {
	        		ArrayList<AType> gens = new ArrayList<AType>();
	        		String gen = name.substring(idx+1,name.lastIndexOf(">"));
	        		for (String string : gen.split(",")) {
	        			gens.add(generateType(string));
	        		}
	        		return new AIType(name,CatType.OBJECT,gens);
	        	}
	        }
		}
	}

	/**
	 * @see tamagocc.ast.api.AType#getParametricType(int)
	 */
	public AType getParametricType(int i) {
		return generics.get(i);
	}

	/**
	 * @see tamagocc.ast.api.AType#getParametricsTypes()
	 */
	public AType[] getParametricsTypes() {
		AType[] array = new AIType[generics.size()];
		System.arraycopy(generics.toArray(), 0, array, 0, generics.size());
		return array;
	}

	/**
	 * @see tamagocc.ast.api.AType#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.size() > 0;
	}
	
	
}
