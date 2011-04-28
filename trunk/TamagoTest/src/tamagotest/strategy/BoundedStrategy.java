/**
 * 
 */
package tamagotest.strategy;

import java.lang.reflect.Constructor;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoEnvironment;
import tamago.builder.impl.Builderarray;
import tamago.builder.impl.Builderint;
import tamago.csp.Backtracking;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GType;
import tamagocc.generic.impl.GIType;

/**
 * @author Hakim Belhaouari
 *
 */
public class BoundedStrategy extends NominalStrategy {

	/**
	 * 
	 */
	public BoundedStrategy() {
		
	}
	
	/**
	 * default search strategy
	 * @see tamago.builder.TamagoBuilderFactory#searchBuilder(tamago.builder.TamagoEnvironment, java.lang.String, tamagocc.generic.api.GType, tamago.csp.Backtracking)
	 */
	public TamagoBuilder searchBuilder(TamagoEnvironment env, String name, GType type, Backtracking b) {
		try {
			switch(type.catType()) {
			case ARRAY:
				return new Builderarray(this, env,name,type,new Backtracking());
			case INTEGER:
				return new Builderint(env, name, GIType.TYPE_INT, b, true);
			case BOOL:
			case OBJECT:
			case REAL:
			case STRING:
			case VOID: {
				Class<?> cb = (Class<?>) Class.forName("tamago.builder.impl.Builder"+type.SimpleType().toLowerCase());
				Constructor<?> cons = cb.getConstructor(new Class[] { TamagoEnvironment.class, String.class , GType.class, Backtracking.class });
				return (TamagoBuilder) cons.newInstance(new Object[] {env,  name, null,new Backtracking() });
			}
			}
		}
		catch(Exception ex) {
			throw new TamagoTestStrategyException(ex);
		}
		throw new TamagoTestStrategyException("Problem occur for searching the builder of "+name);
	}
	
	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#searchBuilder(java.lang.String, tamagocc.generic.api.GType, tamagocc.generic.api.GMethod)
	 */
	public TamagoBuilder searchBuilder(String name, GType type,
			GMethod method,TamagoEnvironment env) {
		return searchBuilder(env,name,type,null);
	}

	/**
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @see tamagotest.strategy.TamagoTestStrategy#searchBuilder(java.lang.String, tamagocc.generic.api.GType, tamagocc.generic.api.GTamago)
	 */
	public TamagoBuilder searchBuilder(String name, GType type,
			GTamago entity,TamagoEnvironment env) throws TamagoTestStrategyException {
		return searchBuilder(env,name,type,null);
	}

}
