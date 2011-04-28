/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari
 */
public interface ATamagoNew extends ANew {
	
	public static final int COMPONENT = 0;
	public static final int COMPOSITE = 1;
	public static final int ASSEMBLY = 2;
	
	int getEntityType();
	String getPercolatorName();
	
	AModule getModule();
	boolean useDefaultPercolator();
	
}
