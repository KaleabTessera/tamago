package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TTransition;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TITransition.java
 */
/**
 */
public class TITransition implements TTransition {

    private String from;
    private String to;
    private String with;
    private TExpression condition;
    
    /**
     * @param f : nom de l'etat au depart 
     * @param t : nom de l'etat a l'arrivee
     * @param w : identifiant de la methode permettant de faire le changement
     */
    public TITransition(String f,String t,String w) {
        super();
        from = f;
        to = t;
        with = w;
        //condition = new TIBool(true);
        condition = new TINoContract();
    }
    
    /**
     * @param f : nom de l'etat au depart 
     * @param t : nom de l'etat a l'arrivee
     * @param w : identifiant de la methode permettant de faire le changement
     * @param c : correspond a la condition pour que la transition soit correct
     */
    public TITransition(String f,String t,String w,TExpression c) {
        super();
        from = f;
        to = t;
        with = w;
        condition = c;
    }
    

    /**
     * @see tamagocc.api.TTransition#getFrom()
     */
    public String getFrom() {
        return from;
    }

    /**
     * @see tamagocc.api.TTransition#getTo()
     */
    public String getTo() {
        return to;
    }
    
    /**
     * @see tamagocc.api.TTransition#getCondition()
     */
    public TExpression getCondition() {
        return condition;
    }

    /**
     * @see tamagocc.api.TTransition#getMethod()
     */
    public String getMethod() {
        return with;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TTransition) {
			TTransition p = (TTransition) o;
			return ( getFrom().equals(p.getFrom())
					&& getTo().equals(p.getTo())
					&& getMethod().equals(p.getMethod())
					&& getCondition().equals(p.getCondition())
					);
		}
    	return false;
    }

}
