package tamagocc.api;

import java.util.Iterator;
/**
 * This class represents the service behavior specified in the CDL language.
 * @author Hakim BELHAOUARI
 */
public interface TBehavior extends TObject {
    /**
     * @return Returns the iterator of all specified states. 
     */
    Iterator<TState> getStates();
    
    /**
     * @return Returns the iterator of all specified transitions
     */
    Iterator<TTransition> getTransitions();
    

    /**
     * @return Returns the default state of the service behavior.
     */
    String getDefaultState();
    
/* TODO: augmenter ici les methodes pour faire la selection des bonnes methode..etat.. */
}
