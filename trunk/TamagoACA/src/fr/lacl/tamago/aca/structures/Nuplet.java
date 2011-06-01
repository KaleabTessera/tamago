package fr.lacl.tamago.aca.structures;

import java.util.List;

public class Nuplet extends Elem 
 {
	public Elem environnement;
	public Elem evenement;
	
	public String toString() 
	{
		String resultat = "<"+ environnement +"," +evenement+">" ;
		return resultat;
	}

	public boolean containIL() 
	{
		return false;
	}
	
	public boolean containNOT() 
	{
		boolean resultat = false 
							|| environnement.containNOT() 
							|| evenement.containNOT();
		return resultat;
	}
	
	public boolean containOnlyNOT() 
	{
		boolean resultat =  environnement.containOnlyNOT() 
							&& evenement.containOnlyNOT();
		return resultat;
	}

	public void liste(List<Nuplet> listePermission) 
	{
		listePermission.add(this);
	}

	public static boolean compatibleInterPerm(Nuplet inter, Nuplet permis) 
	{
		boolean resultat = true;
		
		//si meme nom d'action
		EnvironnementSecurite envI = (EnvironnementSecurite)inter.environnement;
		EnvironnementSecurite envP = (EnvironnementSecurite)permis.environnement;
		
		if(((Evenement)inter.evenement).nom.equals(((Evenement)inter.evenement).nom) )
		{
			int compat = 0 ;
			int incomp = 0 ;
			
			//verifier si user sont != sauf si _
			Chaine chaineI;
			Chaine chaineP = (Chaine)(envP.user);
			if(!( envI.user.getClass().getSimpleName().equals("Chaine") || chaineP.isWildcard() ))
				{
					chaineI = (Chaine) ((Negation)(envI.user)).fils1;
					if(chaineI.valeur.equals(chaineP.valeur))
						incomp++;
					else
						compat++;
				}
			else
				compat++;
			//verifier si role sont != sauf si _
			chaineP = (Chaine)(envP.role);
			if(!( envI.role.getClass().getSimpleName().equals("Chaine") || chaineP.isWildcard() ))
				{
					chaineI = (Chaine) ((Negation)(envI.role)).fils1;
					if(chaineI.valeur.equals(chaineP.valeur))
						incomp++;
					else
						compat++;
				}
			else
				compat++;
			//verifier si organisation sont != sauf si _
			chaineP = (Chaine)(envP.organisation);
			if(!( envI.organisation.getClass().getSimpleName().equals("Chaine") || chaineP.isWildcard() ))
				{
					chaineI = (Chaine) ((Negation)(envI.organisation)).fils1;
					if(chaineI.valeur.equals(chaineP.valeur))
						incomp++;
					else
						compat++;
				}
			else
				compat++;
			//verifier si temps sont != sauf si _
			chaineP = (Chaine)(envP.temps);
			if(!( envI.temps.getClass().getSimpleName().equals("Chaine") || chaineP.isWildcard() ))
				{
					chaineI = (Chaine) ((Negation)(envI.temps)).fils1;
					if(chaineI.valeur.equals(chaineP.valeur))
						incomp++;
					else
						compat++;
				}
			else
				compat++;	
			//System.out.println("++++"+incomp +"/"+compat);
			if(incomp>0 && compat==0)
				resultat = false;
		}
		return resultat;
	}

	public String toSCD(boolean perePar,boolean pereInter) 
	{
		String resultat = "";
		if(this.containNOT())
			resultat += "<atomic forbidden=\"true\">\n";
		else
			resultat += "<atomic forbidden=\"false\">\n";
		resultat += environnement.toSCD();
		resultat += evenement.toSCD();
		return resultat;
	}

	public void isFirstParallele() 
	{
	}
}
