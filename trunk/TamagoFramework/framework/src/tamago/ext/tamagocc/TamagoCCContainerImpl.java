/**
 * 
 */
package tamago.ext.tamagocc;

import java.util.ArrayList;
import java.util.Hashtable;

import tamago.TamagoException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public abstract class TamagoCCContainerImpl implements TamagoCCContainer {

	protected ArrayList<TamagoCCState> states;
	protected Hashtable<String, TamagoCCMethodID> hash;
	protected TamagoCCState current;
	
	/**
	 * 
	 */
	public TamagoCCContainerImpl() {
		super();
		states = new ArrayList<TamagoCCState>();
		hash = new Hashtable<String, TamagoCCMethodID>();
		current = null;
	}
	
	
	
	public boolean canCallMethod(TamagoCCMethodID mid) {
		return current.canCallMethod(mid);
	}
	
	public TamagoCCMethodID methodID(String mid) {
		return hash.get(mid);
	}
	
	protected TamagoCCState newstate() {
		TamagoCCState state = new TamagoCCState(states.size());
		states.add(state);
		return state;
	}
	
	protected void include(TamagoCCState state,String name) {
		state.include(name);
	}
	protected void allow(TamagoCCState state,TamagoCCMethodID mid) {
		state.allow(mid);
	}
	
	protected void registerID(String a,String b) {
		if(hash.containsKey(a))
			return; // already done
		if(hash.containsKey(b)) {
			hash.put(a, hash.get(b));
		}
		else {
			hash.put(a, new TamagoCCMethodID(b));
		}
	}
	
	protected void fetchServiceBehavior(TamagoCCMethodID mid) throws TamagoCCServiceBehaviorException {
		// by default we do nothing
	}
	
	
	protected boolean isInState(String name) {
		return (current == null) || (current.isInState(name));
	}

	/**
	 * @see tamago.ext.tamagocc.TamagoCCContainer#getRequiredService(java.lang.String, java.lang.String)
	 */
	public RequiredService getRequiredService(String name,String module) throws TamagoException
	{
		RequiredService result = null;
		RequiredService[] tab = getRequiredServices();
		
		for(int i=0;i < tab.length;i++) {
			if(tab[i].getServiceName().equals(name)
					&&tab[i].getServiceModule().equals(module))
			{
				if(result != null)
					throw new TamagoException("Multiple required service called : "+module+"."+name);
				result = tab[i];
			}
		}
		if(result == null)
			throw new TamagoException("Unfind required service");
		else
			return result;
	}
}
