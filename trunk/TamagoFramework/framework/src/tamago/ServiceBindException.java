/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public class ServiceBindException extends TamagoException {
    public ServiceBindException(String message) {
        super("Cannot bind service : " + message);
    }
}
