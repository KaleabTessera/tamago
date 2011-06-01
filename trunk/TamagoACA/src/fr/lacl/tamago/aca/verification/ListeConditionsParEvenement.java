package fr.lacl.tamago.aca.verification;

import java.util.Iterator;
import java.util.Vector;

import fr.lacl.tamago.aca.structures.Elem;
import fr.lacl.tamago.aca.structures.EnvironnementSecurite;


public class ListeConditionsParEvenement 
{
	public Elem evenement;
	public Vector<Elem> conditions = new Vector<Elem>(10);
	
	public void addCondition(Elem t)
	{
		conditions.add(t);
	}
	
	public String toString()
	{
		String resultat = evenement.toString()+"\n";
		Iterator<Elem> itr = conditions.iterator();
		while(itr.hasNext())
			resultat +=  "    " + itr.next().toString() + "\n";
		return resultat;
	}
	
	public String getNomEvenement()
	{
		return evenement.getNom();
	}

	public void retirer(Elem permProhibCond) 
	{
		Iterator<Elem> it = conditions.iterator();
		EnvironnementSecurite temp;
		EnvironnementSecurite asupp = (EnvironnementSecurite)permProhibCond;
		boolean flag = true;
		while(it.hasNext()&&flag)
		{
			temp=(EnvironnementSecurite)it.next();
			if(temp.user.getNom().equals(asupp.user.getNom())
					&& temp.role.getNom().equals(asupp.role.getNom())
					&& temp.organisation.getNom().equals(asupp.organisation.getNom())
				)//temp et permprohibcond coincide
			{
				//retirer temp
				conditions.remove(temp);
				flag=false;
			}
		}
	}
	
	

}
