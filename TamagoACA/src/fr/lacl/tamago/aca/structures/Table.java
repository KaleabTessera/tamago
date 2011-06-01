package fr.lacl.tamago.aca.structures;

import java.util.Iterator;
import java.util.Vector;

public class Table 
{
	public Vector<Triplet> contenu = new Vector<Triplet>(10);
	
	public void addTriplet(Triplet t)
	{
		contenu.add(t);
	}
	
	public String toString()
	{
		String resultat = "";
		Iterator<Triplet> itr = contenu.iterator();
		while(itr.hasNext())
			resultat +=  " | " + itr.next().toString();
		return resultat;
	}

}
