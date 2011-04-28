package tamagocc.api;


/**
 * This interface is the root of all entities.
 * @author Hakim Belhaouari
 */
public interface TTamagoEntity extends TObject {
	public static final int TAMAGO_SERVICE = 0;
	public static final int TAMAGO_COMPONENT = 1;
	public static final int TAMAGO_COMPOSITE = 2;
	public static final int TAMAGO_ASSEMBLY = 3;
    
    
    /**
     * @return The name of the entity 
     */
    String getName();
    
    /**
     * @return Return the name of the module
     */
    String getModule();
    
    /**
     * @return Return the type of the entity
     */
    int getTamagoType();
    
   
    boolean isParametric();
    
    TType[] getParametrizedTypes();
    
    TType getParametricType(int i);
    
    TType getNameAsType(); // permet d'afficher les types parametre dans le nom et le module
}
