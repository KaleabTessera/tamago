/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public interface LCPersistController extends LifeCycleController {
    public void persist() throws LifeCycleException; 
    public void unpersist() throws LifeCycleException;
}

