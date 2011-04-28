package tamagocc.generic.api;


public interface GTamagoEntity extends GObject {
	
	public static final int TAMAGO_SERVICE = 0;
	public static final int TAMAGO_COMPONENT = 1;
	public static final int TAMAGO_ASSEMBLY= 2;
	public static final int TAMAGO_COMPOSITE= 3;
	
	
	/**
	 * @return Return the name of the entity, with the correct transformation
	 */
	String getName();

	/**
	 * @return Return the module of the entity, it is the same as the original entity.
	 */
	GModule getModule();
	
	
	int getCategoryEntity();
	
	
	
	boolean isParametric();
	
	GType[] getParametrizedTypes();
	GType getParametrizedType(int i);
	GType getNameAsType();
}
