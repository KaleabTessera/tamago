/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public class ServiceExportException extends TamagoException {
    public ServiceExportException(String message) {
        super("Cannot export service : " + message);
    }
}
