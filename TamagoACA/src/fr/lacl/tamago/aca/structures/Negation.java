package fr.lacl.tamago.aca.structures;

import java.util.List;

public class Negation  extends Elem 
{
	public Elem fils1;

	public String toString() 
	{
		String resultat = "! ";
		resultat += fils1.toString();
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
	}

	public boolean containNOT() 
	{
		return true;
	}

	public boolean containOnlyNOT() 
	{
		return true;
	}

	public void liste(List<Nuplet> listePermission) 
	{
		fils1.liste(listePermission);
	}

	public String toSCD(boolean perePar,boolean pereInter) 
	{
		return fils1.toSCD();
	}

	public void isFirstParallele() 
	{
		if(fils1.getClass().getSimpleName().equals("Parallele"))
		{
			((Parallele)fils1).isFirst=true;
		}
		fils1.isFirstParallele();
	}

	@Override
	public void addElem(Elem t) {
		// TODO Auto-generated method stub
		
	}

	public String getNom() 
	{
		return fils1.getNom();
	}

	@Override
	public String toSCD() 
	{
		String resultat = "";
		resultat += fils1.toSCD();
		return resultat;
	}
	
}
