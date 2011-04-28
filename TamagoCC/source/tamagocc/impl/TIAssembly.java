package tamagocc.impl;

import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.TAssembly;
import tamagocc.api.TBehavior;
import tamagocc.api.TBind;
import tamagocc.api.TDefinition;
import tamagocc.api.TInvariant;
import tamagocc.api.TTamagoEntity;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

public class TIAssembly implements TAssembly {

	private Collection<TDefinition> definitions;
	private String name;
	private String module;
	private Collection<TBind> binds;
	private Collection<TInvariant> invariants;
	private TBehavior behavior;
	private TType[] generics;
	
	public TIAssembly(String name,
					  String module,
					  Collection<TDefinition> definitions,
					  Collection<TBind> binds,
					  Collection<TInvariant> invariants,
					  TBehavior behavior,
					  Collection<TType> paramtypes) 
	{
		super();
		this.name = name;
		this.module = module;
		this.binds = binds;
		this.definitions = definitions;
		this.invariants = invariants;
		this.behavior = behavior;
		generics = new TType[paramtypes.size()];
		System.arraycopy(paramtypes.toArray(), 0, generics, 0, generics.length);
	}

	public String getName() {
		return(name);
	}

	public String getModule() {
		return module;
	}

	public Iterator<TDefinition> getDefinitions() {
		return definitions.iterator(); 
	}

	public Iterator<TBind> getBinds() {
		return binds.iterator();
	}
	
	public Iterator<TInvariant> getInvariants() {
		return invariants.iterator();
	}
	
	public TBehavior getBehavior() {
		return behavior;
	}
	
	
	

	public int getTamagoType() {
		return TTamagoEntity.TAMAGO_ASSEMBLY;
	}

	/**
	 * 
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitAssembly(this);
	}

	/**
	 * @see tamagocc.api.TTamagoEntity#getParametricType(int)
	 */
	public TType getParametricType(int i) {
		return generics[i];
	}

	/**
	 * @see tamagocc.api.TTamagoEntity#getParametrizedTypes()
	 */
	public TType[] getParametrizedTypes() {
		return generics;
	}

	/**
	 * @see tamagocc.api.TTamagoEntity#isParametric()
	 */
	public boolean isParametric() {
		return (generics.length > 0);
	}

	/**
	 * @see tamagocc.api.TTamagoEntity#getNameAsType()
	 */
	public TType getNameAsType() {
		if(module.length() > 0)
			return TIType.generateType(module+"."+name);
		else
			return TIType.generateType(name);
	}

	public void addParamType(TType type) {
		TType[] res = new TIType[generics.length + 1];
		System.arraycopy(generics,0,res,0,generics.length);
		res[generics.length] = type;
		generics = res;
	}
	
}
