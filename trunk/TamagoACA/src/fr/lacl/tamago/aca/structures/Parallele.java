package fr.lacl.tamago.aca.structures;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Parallele extends Elem 
 {
	public Elem fils1;
	public Elem fils2;
	public boolean isFirst;
	
	public Vector<Elem> contenu = new Vector<Elem>(10);
	
	public void addElem(Elem t)
	{
		contenu.add(t);
	}
	
	public String toString()
	{
		String resultat = "(";
		Iterator<Elem> itr = contenu.iterator();
		while(itr.hasNext())
			resultat +=  " || " + itr.next().toString() + "\n";
		resultat+=")\n";
		return resultat;
	}
	
	public boolean containIL() 
	{
		boolean resultat = false || fils1.containIL() || fils2.containIL();
		return resultat;
	}

	public boolean containNOT() 
	{
		boolean resultat = false || fils1.containNOT() || fils2.containNOT();
		return resultat;
	}
	
	public boolean containOnlyNOT() 
	{
		boolean resultat = fils1.containOnlyNOT() && fils2.containOnlyNOT();
		return resultat;
	}

	public void liste(List<Nuplet> listePermission) 
	{
		fils1.liste(listePermission);
		fils2.liste(listePermission);
	}

	public void isFirstParallele() 
	{
		if(fils1.getClass().getSimpleName().equals("Parallele"))
		{
			((Parallele)fils1).isFirst=false;
		}
		fils1.isFirstParallele();
		if(fils2.getClass().getSimpleName().equals("Parallele"))
		{
			((Parallele)fils2).isFirst=false;
		}
		fils2.isFirstParallele();
	}

	public String toSCD(boolean perePar, boolean pereInter) 
	{
		return null;
	}
}
