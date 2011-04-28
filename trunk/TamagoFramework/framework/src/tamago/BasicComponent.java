/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** A helper implementation for Component interface
 * Basic (flat) components that do not rely on
 * implementation inheritance may safely extends this
 * abstract class.
 * 
 * @author Frederic Peschanski
 */
public abstract class BasicComponent implements Component {
    
    public void bind(Service s) throws ServiceBindException {
        throw new ServiceBindException("Unsupported service :"+s);
    }
}
