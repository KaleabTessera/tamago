package tamagocc.generic.api;

import java.util.Iterator;

public interface GMethod extends GObject {
	 /**
     * @return Renvoie le nom de la methode
     */
    String getName();
    
    /**
     * 
     * @return Renvoie le type de retour de la methode 
     */
    GType getType();
    
    /**
     * @return Renvoie l'identifiant de la methode ou renvoie la chaine
     * vide s'il y en a pas.
     */
    String getID();
    
    /**
     * 
     * @return Return the collection of parameters
     */
    Iterator<GParameter> getParameters();
    
    GVariable getSavedResult();
    boolean mustSaveResult();
    
    GCondition getPrecondition();
    GCondition getPostcondition();
    
    /**
     * 
     * @return Return the number of parameters
     */
    int getParameterNumber();
    
    
    /**
     * Indique si la methode passe en parametre a la meme signature que la methode
     * courante. Elle compare le type de retour, le nom de la methode et les types des
     * parametres.
     * @param method
     * @return
     */
    boolean sameSignature(GMethod method);
}
