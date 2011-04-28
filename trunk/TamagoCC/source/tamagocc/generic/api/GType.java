package tamagocc.generic.api;

import tamagocc.api.CatType;

public interface GType extends GObject {
   
    /**
     * Renvoie la chaine de caractere qui correspond au type
     * @return representation textuelle du type
     */
    String getType();
    
    /**
     * Indique la categorie du type
     * @return specifie la categorie du type
     */
    CatType catType();    
    
    boolean isArray();
    
    GType getArrayType();
    
    boolean isParametric();
    
    GType[] getParametricsTypes();
    
    GType getParametricType(int i);

	String SimpleType();  
}
