package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.CatType;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GType;

public class GIType implements GType {

	public static GType TYPE_BOOL = GIType.generateType("bool");
	public static GType TYPE_INT = GIType.generateType("int");
	public static GType TYPE_REAL = GIType.generateType("real");
	public static GType TYPE_VOID = GIType.generateType("void");
	public static GType TYPE_STRING = GIType.generateType("String");
	
	private String type;
	private CatType category;
	private GType array;
    private ArrayList<GType> generics;
	
	private GIType(String t, CatType cat) {
		super();
		this.type = t.trim();
		category = cat;
		array = null;
		generics = new ArrayList<GType>();
	}
	
	private GIType(String t, CatType cat,Collection<GType> gens) {
		super();
		this.type = t.trim();
		category = cat;
		array = null;
		generics = new ArrayList<GType>(gens);
	}

	public GIType(String t, CatType cat, GType type) {
		super();
		this.type = t.trim();
		category = cat;
		array = type;
	}
	

	public String getType() {
		return type;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GIType) {
			GIType type = (GIType)o;
			return ((type.catType() == catType())&&(type.getType().equals(this.type))); 
		}
		else
			return false;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitType(this);
	}

	public GType getArrayType() {
		return array;
	}

	public boolean isArray() {
		return (category == CatType.ARRAY);
	}
	
	public static GType generateType(String name) {
		int idx;
		idx = name.lastIndexOf("[]");
		if(idx > 0) {
			GType type = generateType(name.substring(0,idx));
			return new GIType(name,CatType.ARRAY,type);
		}
		else {
			if(name.equals("void"))
	            return new GIType(name,CatType.VOID);
	        else if(name.equals("int"))
	            return new GIType(name,CatType.INTEGER);
	        else if(name.equals("real"))
	            return new GIType(name,CatType.REAL);
	        else if(name.equals("bool"))
	            return new GIType(name,CatType.BOOL);
	        else if(name.equals("String"))
	        	return new GIType(name,CatType.STRING);
	        else if(name.equals("alpha"))
	        	return new GIType(name,CatType.GENERIC);
	        else {
	        	idx = name.indexOf("<");
	        	if(idx == -1)
	        		return new GIType(name,CatType.OBJECT);
	        	else {
	        		ArrayList<GType> gens = new ArrayList<GType>();
	        		String gen = name.substring(idx+1,name.lastIndexOf(">"));
	        		for (String string : gen.split(",")) {
	        			gens.add(generateType(string));
	        		}
	        		return new GIType(name,CatType.OBJECT,gens);
	        	}
	        }
		}
	}

	public CatType catType() {
		return category;
	}

	/**
	 * @see tamagocc.generic.api.GType#getParametricType(int)
	 */
	public GType getParametricType(int i) {
		return generics.get(i);
	}

	/**
	 * @see tamagocc.generic.api.GType#getParametricsTypes()
	 */
	public GType[] getParametricsTypes() {
		GType[] array = new GIType[generics.size()];
		System.arraycopy(generics.toArray(), 0, array, 0, generics.size());
		return array;
	}

	/**
	 * @see tamagocc.generic.api.GType#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.size() > 0;
	}

	public String SimpleType() {
		String res = getType();
		res = res.substring(res.lastIndexOf(".")+1);
		return res;
	}
}
