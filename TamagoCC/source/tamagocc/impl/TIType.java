package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.TType;
import tamagocc.api.CatType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * @author Hakim BELHAOUARI
 */
public class TIType implements TType {

    private String type;
    private CatType category;
    private TType arraytype;
    private ArrayList<TType> generics;
    
    /**
     * 
     */
    private TIType(String s,CatType cat) {
        super();
        type = s;
        category = cat;
        arraytype = null;
        generics = new ArrayList<TType>();
    }
    
    private TIType(String s,CatType cat, TType t) {
    	super();
    	type = s;
    	category = cat;
    	arraytype = t;
    	generics = new ArrayList<TType>();
    }
    
    private TIType(String s,CatType cat,Collection<TType> gens) {
        super();
        type = s;
        category = cat;
        arraytype = null;
        generics = new ArrayList<TType>(gens);
    }
    
    private TIType(String s,CatType cat, TType t,Collection<TType> gens) {
    	super();
    	type = s;
    	category = cat;
    	arraytype = t;
    	generics = new ArrayList<TType>(gens);
    }
    
    
    public boolean isArray() {
    	return (category == CatType.ARRAY);
    } 
    
    /**
     * @see tamagocc.api.TType#getType()
     */
    public String getType() {
        return type;
    }

    public TType getArrayType() {
    	return arraytype;
    }
    
    /**
     * @see tamagocc.api.TType#catType()
     */
    public CatType catType() {
        /*if(type.equals("void"))
            return CatType.VOID;
        else if(type.equals("int"))
            return CatType.INTEGER;
        else if(type.equals("real"))
            return CatType.FLOAT;
        else if(type.equals("bool"))
            return CatType.BOOL;
        else if(type.equals("String"))
        	return CatType.STRING;
        else if(type.equals("alpha"))
        	return CatType.GENERIC;
        else
            return CatType.OBJECT;
        */
    	return category;
    }
    
    public boolean equals(Object o) {
        if(o instanceof TType) {
            return (((TType)o).catType()==catType());
        }
        else 
            return false;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitType(this);
    }

	public int compareTo(TType o) {
		Integer i1 = new Integer(catType().ordinal());
		Integer i2 = new Integer(((TType)o).catType().ordinal());
		return i1.compareTo(i2);
	}

	
	public static TType generateType(String name) {
		int idx;
		name = name.trim();
		idx = name.lastIndexOf("[]");
		if(idx > 0) {
			TType type = generateType(name.substring(0,idx));
			return new TIType(name,CatType.ARRAY,type);
		}
		else {
			if(name.equals("void"))
	            return new TIType(name,CatType.VOID);
	        else if(name.equals("int"))
	            return new TIType(name,CatType.INTEGER);
	        else if(name.equals("real"))
	            return new TIType(name,CatType.REAL);
	        else if(name.equals("bool"))
	            return new TIType(name,CatType.BOOL);
	        else if(name.equals("String"))
	        	return new TIType(name,CatType.STRING);
	        else if(name.equals("string"))
	        	return new TIType("String",CatType.STRING);
	        else if(name.equals("alpha"))
	        	return new TIType(name,CatType.GENERIC);
	        else {
	        	idx = name.indexOf("<");
	        	if(idx == -1)
	        		return new TIType(name,CatType.OBJECT);
	        	else {
	        		ArrayList<TType> gens = new ArrayList<TType>();
	        		String gen = name.substring(idx+1,name.lastIndexOf(">"));
	        		for (String string : gen.split(",")) {
	        			gens.add(generateType(string));
	        		}
	        		return new TIType(name,CatType.OBJECT,gens);
	        	}
	        }
		}
	}
	
	public static void main(String args[]) {
		String type = "  java.util.Set<Integer<Double>, String>[]".trim();
		int idx = type.lastIndexOf("[]");
		type = type.substring(0,idx);
		idx = type.indexOf("<");
		System.out.println("type:"+type.substring(0,idx));
		String gen = type.substring(idx+1,type.lastIndexOf(">"));
		System.out.println("type:"+gen);
		for (String string : gen.split(",")) {
			System.out.println(" gen:("+string.trim()+")");
		} 
		
	}

	/**
	 * @see tamagocc.api.TType#getParametricType(int)
	 */
	public TType getParametricType(int i) {
		return generics.get(i);
	}

	/**
	 * @see tamagocc.api.TType#getParametricsTypes()
	 */
	public TType[] getParametricsTypes() {
		TType[] array = new TIType[generics.size()];
		System.arraycopy(generics.toArray(), 0, array, 0, generics.size());
		return array;
	}

	/**
	 * @see tamagocc.api.TType#isParametric()
	 */
	public boolean isParametric() {
		return generics.size() > 0;
	}
}
