package fr.lacl.tamago.aca.verification;

import fr.lacl.tamago.aca.structures.Elem;

public class Tableau 
{
	Elem[][] correspondance;
	
	public Tableau()
	{
		super();
	}
	
	public Tableau(int taille)
	{
		super();
		correspondance = new Elem[taille][2];
		for(int i=0;i<taille;i++)
			for(int j = 0 ; j<2 ; j++)
				correspondance[i][j]=null;
	}
	
	public boolean ajouter(Elem racine, Elem feuille)
	{
		boolean resultat = false;
		
		//recuperer l'indice de la racine
		String variable = racine.getNom();
		int indiceRacine= (int)(Integer.parseInt(variable.substring(2)));
		
		
		
		return resultat;
		
	}

}
