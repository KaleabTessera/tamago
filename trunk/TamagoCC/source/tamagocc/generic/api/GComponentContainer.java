package tamagocc.generic.api;

import java.util.Iterator;

//import tamagocc.util.TamagoCCMethodList;
//import tamagocc.util.TamagoCCPropertyList;

/**
 * Cette classe represente un container mis en memoire,
 * ce container sera utilise par un generator pour generer le bon langage.
 *
 * @author Hakim Belhaouari et Frederic Peschanski
 *
 */
public interface GComponentContainer extends GTamago {
	
	 Iterator<GPercolator> getAllowedPercolators();

	 /**
	 * 
	 * @return Return all module of all implemented services (and super-service,
	 * mean here, the paren service of service).
	 */
	// Iterator<GModule>
	Iterator<GModule> getImports();
	
	/**
	 * @return Return all implemented service(and parents of service).
	 */
	// Iterator<GProvide>
	Iterator<GProvide> getAllProvide();
	
	/**
	 * @return Return all requires service (and parents of service).
	 */
	Iterator<GRequire> getAllRequire();
	

	// TamagoCCPropertyList getAllProperties();
	// TamagoCCMethodList getAllMethod();
}
