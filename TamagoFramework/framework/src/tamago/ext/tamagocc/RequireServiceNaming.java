package tamago.ext.tamagocc;

import tamago.*;


public interface RequireServiceNaming {
    
    
    void bind(String label,TamagoCCService service) throws ServiceBindException;
}
