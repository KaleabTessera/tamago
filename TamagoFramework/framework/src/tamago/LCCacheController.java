/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public interface LCCacheController extends LifeCycleController {
    public void cache() throws LifeCycleException; 
    public void restore() throws LifeCycleException;
}

