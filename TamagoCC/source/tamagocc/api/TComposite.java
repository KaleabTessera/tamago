package tamagocc.api;

import java.util.Iterator;


/**
 * 
 * This interface represents the declaration of a composite specidied in the CDL language.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TComposite extends TTamago,TComponent,TAssembly {
    /**
     * @see tamagocc.api.TExport
     * @return Return the iterator of all exported service by subcomponents
     */
    Iterator<TExport> getExports();
}
