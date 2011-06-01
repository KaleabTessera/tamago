package fr.lacl.tamago.aca.structures;

import java.util.List;

public class EnvironnementSecurite  extends Elem 
{
	public Elem user;
	public Elem role;
	public Elem organisation;
	public Elem temps;
	
	public String toString() 
	{
		String resultat = "user = " + user + "," +
						  "role = " + role + "," +
						  "organisation = " + organisation +"," +
						  "temps = " + temps ;
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
	}
	
	public boolean containNOT() 
	{
		boolean resultat = user.containNOT() 
		 			 		|| role.containNOT()
		 			 		||organisation.containNOT()
		 			 		||temps.containNOT();
		
		return resultat;
	}
	
	public boolean containOnlyNOT() 
	{
		boolean resultat = user.containOnlyNOT() 
		 			 		&& role.containOnlyNOT()
		 			 		&& organisation.containOnlyNOT()
		 			 		&& temps.containOnlyNOT();
		
		return resultat;
	}

	public void liste(List<Nuplet> listePermission) 
	{
	}

	public String toSCD(boolean perePar,boolean pereInter) 
	{
		String resultat = "";
		resultat += "<user value";
		resultat += user.toSCD();
		resultat += "/>\n";
		resultat += "<role value";
		resultat += role.toSCD();
		resultat += "/>\n";
		resultat += "<organisation value";
		resultat += organisation.toSCD();
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
}
