package tamago.ext.tamagocc;

import tamago.ServiceBindException;
import tamago.TamagoException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface TamagoCCContainer extends TamagoCCComponent,RequireServiceNaming {
	public boolean isBinded();
	
	public RequiredService[] getRequiredServices();
	public void bind(String label, TamagoCCService instance) throws ServiceBindException;
	public RequiredService getRequiredService(String name,String module) throws TamagoException;
	
	public void tamago_hotswapping(TamagoCCComponent component) throws TamagoCCHotSwappingException;
}
