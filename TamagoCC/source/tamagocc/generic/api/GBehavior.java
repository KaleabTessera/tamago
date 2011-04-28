package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 */
public interface GBehavior extends GObject {
	
	Iterable<? extends GState> getDefaultStates();	
	
	Iterable<? extends GState> getStates();
	
	Iterable<GTransition> getTransitions();
	
	int countStates();
	int countTransitions();
}
