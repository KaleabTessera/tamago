package fr.lacl.tamago.aca.structures;

import java.util.List;

public class Chaine  extends Elem 
{
	public String valeur;
	
	public String toString() 
	{
		String resultat = valeur ;
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
	}

	public boolean containNOT() 
	{
		return false;
	}

	public boolean containOnlyNOT() 
	{
		if(valeur.equals("_"))
			return true;
		else
			return false;
	}
	
	public boolean isWildcard()
	{
		if(valeur.equals("_"))
			return true;
		else 
			return false;
	}
	
	public void liste(List<Nuplet> listePermission) 
	{
		
	}

	public String toSCD(boolean perePar,boolean pereInter) 
	{
		String resultat = "";
		resultat += "=\"" + valeur + "\"";
		return resultat;
	}

	public void isFirstParallele() 
	{
		
	}

	@Override
	public void addElem(Elem t) {
		// TODO Auto-generated method stub
		
	}

	public String getNom() 
	{
		return valeur;
	}

	
	
	
}
