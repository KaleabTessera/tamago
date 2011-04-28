package tamago.ext.tamagocc;


public interface TamagoCCComponent extends TamagoCCEntity,RequireServiceNaming {
	/**
	 * This Method is used when the container must change the underlying implementation.
	 * The first step is to lock the component (container role), and to re-inject the new observable entity
	 * to the new implementation. Then the new implementation (which knows the semantic of the component) can react.
	 * If an error occur the method can throw the {@link TamagoCCHotSwappingException} exception in order to indicate
	 * the error.
	 * 
	 * @param property : name of the property specified in the contract
	 * @param value : value of the property in the previous implementation.
	 */
	void core_set_properties(String property,Object value) throws TamagoCCHotSwappingException;
}