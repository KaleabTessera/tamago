package fr.lacl.tamago.aca.structures;

import java.util.List;

public class Obligation extends Elem 
{
	public Elem valeur;
	public Elem fils1;
	public Elem fils2;
	
	public String toString() 
	{
		String resultat = "Obligation sur : ";
		resultat += valeur.toString();
		resultat += fils1.toString();
		resultat += fils2.toString();
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
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

	public String toSCD(boolean perePar, boolean pereInter) 
	{
		return null;
	}
}
