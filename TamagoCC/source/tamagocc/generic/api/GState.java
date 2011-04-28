package tamagocc.generic.api;

import java.util.Iterator;

public interface GState extends GObject,Cloneable {
	String getName();
	
	Iterator<GAllow> getAllowsMethod();
	// Iterator<GAllow>
	
	
	Iterator<GTransition> getIncomingTransitions();
	Iterator<GTransition> getOutgoingTransitions();
	
	boolean addIncomingTransition(GTransition t);
	boolean addOutgoingTransition(GTransition t);
	
	int sizeOutgoingTransitions();
	
	GTamagoEntity getParent();
	
	String getFullName();
	
	boolean isImplicit();

	int sizeAllowedMethods();
}
