package fr.lacl.tamago.aca.structures;

import java.util.List;

public class Evenement extends Elem 
 
{
	public Elem nom;
	public Elem arguments;
	
	public String toString() 
	{
		String resultat = nom + "("+arguments+")" ;
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
	}
	
	public boolean containNOT() 
	{
		boolean resultat = nom.containNOT() || arguments.containNOT();
		return resultat;
	}

	public boolean containOnlyNOT() 
	{
		return true;
	}

	public void liste(List<Nuplet> listePermission) 
	{	
	}

	public String toSCD()
	{
		String resultat = "";
		resultat += "<action name";
		resultat += nom.toSCD();
		resultat += "/>\n";
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
		
		return nom.toString();
	}
}
