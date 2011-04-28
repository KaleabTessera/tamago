package tamagocc.api;

/**
 * this interface represents the required percolator in a composition for a component.
 * Furthermore, the component contains a list of all allowed percolator,
 * we can specify the meta name * (star) for generate all percolators. 
 * @author Hakim Belhaouari
 */
public interface TPercolator extends TObject {
	
	/**
	 * 
	 * @return the name of the required percolator
	 */
	String getName();
	
}
