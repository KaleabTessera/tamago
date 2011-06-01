package fr.lacl.tamago.aca.structures;

import java.util.Iterator;
import java.util.Vector;

public class Liste
{
	public Vector<Nuplet> contenu = new Vector<Nuplet>(10);
	
	public void addNuplet(Nuplet t)
	{
		contenu.add(t);
	}
	
	public String toString()
	{
		String resultat = "";
		Iterator<Nuplet> itr = contenu.iterator();
		while(itr.hasNext())
			resultat +=  " | " + itr.next().toString()+"\n";
		return resultat;
	}

}
