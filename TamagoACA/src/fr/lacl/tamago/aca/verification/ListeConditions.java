package fr.lacl.tamago.aca.verification;

import java.util.Iterator;
import java.util.Vector;

import fr.lacl.tamago.aca.structures.Elem;
import fr.lacl.tamago.aca.structures.EnvironnementSecurite;
import fr.lacl.tamago.aca.structures.Evenement;
import fr.lacl.tamago.aca.structures.Nuplet;


public class ListeConditions 
{
	public Vector<ListeConditionsParEvenement> conditions = new Vector<ListeConditionsParEvenement>(10);
	
	public void addCondition(ListeConditionsParEvenement t)
	{
		conditions.add(t);
	}
	
	public String toString()
	{
		String resultat = "(\n";
		Iterator<ListeConditionsParEvenement> itr = conditions.iterator();
		while(itr.hasNext())
			resultat +=itr.next().toString();
		resultat+=")\n";
		return resultat;
	}
	
	public boolean possedeEvenement(Evenement pE)
	{
		Iterator<ListeConditionsParEvenement> itr = conditions.iterator();
		ListeConditionsParEvenement courant;
		boolean result = false;
		while(itr.hasNext())
		{
			courant=itr.next();
			if(pE.getNom().equals(courant.getNomEvenement()))
			{
				result=true;
			}
		}
		return result;
	}

	public void ajouterCondition(Nuplet ajout)
	{
		ListeConditionsParEvenement courant;
		Iterator<ListeConditionsParEvenement> itr = conditions.iterator();
		boolean ajoutee = false;
		boolean besoin = false;
		while(itr.hasNext())
		{
			courant=itr.next();
			if(courant.evenement.getNom().equals(ajout.evenement.getNom()))
			{
				besoin = true;
				EnvironnementSecurite env1=(EnvironnementSecurite)ajout.environnement;
				Iterator<Elem>iter = courant.conditions.iterator();
				EnvironnementSecurite env2 =null;
				while(iter.hasNext())
				{
					env2=(EnvironnementSecurite)iter.next();
					if((env1.user.getClass().getSimpleName().equals(env2.user.getClass().getSimpleName())
							&& env1.user.getNom().equals(env2.user.getNom())
						&& env1.role.getClass().getSimpleName().equals(env2.role.getClass().getSimpleName())
							&& env1.role.getNom().equals(env2.role.getNom())
						&& env1.organisation.getClass().getSimpleName().equals(env2.organisation.getClass().getSimpleName())
							&& env1.organisation.getNom().equals(env2.organisation.getNom())
						&& env1.temps.getClass().getSimpleName().equals(env2.temps.getClass().getSimpleName())
							&& env1.temps.getNom().equals(env2.temps.getNom())
						))
					{
						besoin = false;
					}
				
				}
				if(besoin == true)
				{
//					String aaff ="--["+env1.user.getClass().getSimpleName();
//					aaff += "/"+env2.user.getClass().getSimpleName()+"]--[";
//					aaff += env1.user.getNom() +"/";
//					aaff += env2.user.getNom()+"]--[";
//					boolean test = env1.user.getClass().getSimpleName().equals(env2.user.getClass().getSimpleName())
//					&& env1.user.getNom().equals(env2.user.getNom())
//					&& env1.role.getClass().getSimpleName().equals(env2.role.getClass().getSimpleName())
//						&& env1.role.getNom().equals(env2.role.getNom())
//					&& env1.organisation.getClass().getSimpleName().equals(env2.organisation.getClass().getSimpleName())
//						&& env1.organisation.getNom().equals(env2.organisation.getNom())
//					&& env1.temps.getClass().getSimpleName().equals(env2.temps.getClass().getSimpleName())
//						&& env1.temps.getNom().equals(env2.temps.getNom());
//					aaff += test+"]--";
//					System.out.println(aaff);
					courant.addCondition(ajout.environnement);
				}
				ajoutee = true;
			}
		}
		if(ajoutee==false)
		{
			ListeConditionsParEvenement nouvelle = new ListeConditionsParEvenement();
			nouvelle.evenement=ajout.evenement;
			nouvelle.addCondition(ajout.environnement);
			this.addCondition(nouvelle);
		}
	}
}
