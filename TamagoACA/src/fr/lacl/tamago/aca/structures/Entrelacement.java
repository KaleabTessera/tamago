package fr.lacl.tamago.aca.structures;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Entrelacement extends Elem 
{
	public Elem fils1 ;
	public Elem fils2 ;
	
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
			resultat +=  " ||| " + itr.next().toString() + "\n";
		resultat+=")\n";
		return resultat;
	}
	
	public boolean containIL() 
	{
		return true;
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
			((Parallele)fils1).isFirst=true;
		}
		fils1.isFirstParallele();
		if(fils2.getClass().getSimpleName().equals("Parallele"))
		{
			((Parallele)fils2).isFirst=true;
		}
		fils2.isFirstParallele();
	
	}

	public String toSCD() 
	{
		
		String resultat = "<parweak>\n";
		Iterator<Elem> itr = contenu.iterator();
		while(itr.hasNext())
			resultat += "<child>\n "+ itr.next().toSCD() + "</child>\n";
		resultat+="</parweak>\n\n";
		return resultat;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		throw new RuntimeException("methode non supportee");
	}
}
