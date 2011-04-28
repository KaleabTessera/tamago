package tamagocc.generic.api;

import java.util.Iterator;


public interface GServiceInterface extends GTamago {

	/**
	 * @return Return all services included
	 */
	Iterator<GExtendService> getIncludes();
	// Iterator<GExtendService>
	
	/**
	 * @return Return all refined services.
	 */
	Iterator<GExtendService> getRefines();
	// Iterator<GExtendService>
	
	/**
	 * @return Return all extension of service (refine+include)
	 */
	Iterator<GExtendService> getExtends();
}
