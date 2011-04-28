/**
 * 
 */
package tamago.check.util;

import java.lang.reflect.Constructor;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoBuilderFactory;
import tamago.builder.TamagoEnvironment;
import tamago.builder.impl.Builderarray;
import tamago.check.exception.TamagoCheckException;
import tamago.csp.Backtracking;
import tamagocc.generic.api.GType;

/**
 * @author aliquando
 *
 */
public class TamagoCheckBuilder implements TamagoBuilderFactory {

	/**
	 * 
	 */
	public TamagoCheckBuilder() {
		// TODO Auto-generated constructor stub
	}

	public TamagoBuilder searchBuilder(TamagoEnvironment env, String name,
			GType type, Backtracking b) {
		try {
			switch(type.catType()) {
			case ARRAY:
				return new Builderarray(this, env,name,type,new Backtracking());
			case BOOL:
			case INTEGER:
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
			throw new TamagoCheckException(ex);
		}
		throw new TamagoCheckException("Problem occur for searching the builder of "+name);
	}

}
