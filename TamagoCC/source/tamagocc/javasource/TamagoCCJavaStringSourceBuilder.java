/**
 * 
 */
package tamagocc.javasource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import tamagocc.ast.api.ACall;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ARead;
import tamagocc.ast.api.AVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguage;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCJavaStringSourceBuilder extends TamagoCCGeneratorTargetLanguageBuilder {

	/**
	 * 
	 */
	public TamagoCCJavaStringSourceBuilder() {
		super();
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder#getLanguage()
	 */
	public String getLanguage() {
		return "Java 1.5 (beta)";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder#getDescription()
	 */
	public String getDescription() {
		return "";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder#getAuthor()
	 */
	public String getAuthor() {
		return "Hakim Belhaouari";
	}

	/**
	 * @see tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder#getTargetLanguage(tamagocc.ast.api.AEntity, java.io.File)
	 */
	public TamagoCCGeneratorTargetLanguage getTargetLanguage(AEntity entity, File directory) throws TamagoCCException {
		return new TamagoCCJavaStringSource(entity, new ByteArrayOutputStream());
	}
	
	public TamagoCCGeneratorTargetLanguage getTargetLanguage(AEntity entity, OutputStream stream) throws TamagoCCException {
		return new TamagoCCJavaStringSource(entity,stream);
	}
	

	public String generateInterfaceName(GTamagoEntity entity) {
		return genericName(entity, "");
	}

	public String generateSkeletonName(GTamagoEntity entity) {
		return genericName(entity, "Stub");
	}

	private String genericName(GTamagoEntity entity, String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(entity.getName());
		sb.append(suffix);
		/*if(entity.isParametric()) {
			sb.append("<");
			GType[] gens = entity.getParametrizedTypes();
			for (GType type : gens) {
				sb.append(type.getType());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(">");
		}*/
		
		return sb.toString();
	}
	
	public String generateContainerInterfaceName(GTamagoEntity entity) {
		return genericName(entity, "Container");
	}

	public String generateContainerImplementationName(GTamagoEntity entity,GPercolator percolator) {
		return genericName(entity,"ContainerImpl_"+percolator.getName());
	}

	public String generateFullClassNameFromEntity(GTamagoEntity entity) {
		return entity.getNameAsType().getType();
	}
	
	public static boolean isSimpleType(String self, AExpression expr) {
		return getSimpleType(self, expr).isPrimitive();
	}
	
	public static Class<?> getSimpleType(String self, AExpression expr) {
		try {
			Class<?> c =  Class.forName(self);
			switch(expr.getExpressionType()) {
			case AExpression.VARIABLE:
				AVariable var = (AVariable)expr;
				Field field = c.getField(var.getIdent().getName());
				return field.getType();
			case AExpression.READ: {
				ARead read = (ARead)expr;
				Method meth = c.getMethod(TamagoCCJavaStringSource.propertyToMethod(read.getName()),null);
				return meth.getReturnType();
			}
			case AExpression.CALL: {
				ACall call = (ACall) expr;
				Iterator<AExpression> exprs =  call.getArguments();
				Class[] args = new Class[call.getArgCount()];
				int pos = 0;
				while(exprs.hasNext()) {
					AExpression e = exprs.next();
					args[pos] = getSimpleType(self, e);
					pos++;
				}
				Method meth = c.getMethod(call.getIdent().getName(), args);
				return meth.getReturnType();
			}
			default:
				TamagoCCLogger.println(3, "TYPE PROBLEM IN JAVA LANGUAGE:");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return int.class;
	}
	
}
