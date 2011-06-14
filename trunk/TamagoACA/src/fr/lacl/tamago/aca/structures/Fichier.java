package fr.lacl.tamago.aca.structures;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Fichier 
{
	public Table play = null;
	public Liste permission = null;
	public Liste interdiction = null;
	public Elem sod = null;
	public Elem obligation = null;
	public Elem complex = null;
	
	public List <Nuplet> listePermission;
	public List <Nuplet> listeInterdiction;
	
	public Elem fullComplex()
	{
		Parallele resultat = new Parallele();
		if(sod.getClass().getSimpleName().equals("Parallele"))
		{
			Iterator<Elem> it=((Parallele)sod).contenu.iterator();
			while(it.hasNext())
				resultat.addElem(it.next());
		}
		else
		{
			resultat.addElem(sod);
		}
		if(obligation.getClass().getSimpleName().equals("Parallele"))
		{
			Iterator<Elem> it=((Parallele)obligation).contenu.iterator();
			while(it.hasNext())
				resultat.addElem(it.next());
		}
		else
		{
			resultat.addElem(obligation);
		}
		if(complex.getClass().getSimpleName().equals("Parallele"))
		{
			Iterator<Elem> it=((Parallele)complex).contenu.iterator();
			while(it.hasNext())
				resultat.addElem(it.next());
		}
		else
		{
			resultat.addElem(complex);
		}
		return resultat;
		
	}
	
	public String toSCD()
	{
		String resultat = "";
		//!\\ tous les parallele doivent etre flattenisé
		
		
		//entête
		resultat += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
					"<processus name=\"test.MonTest\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"TamagoACA.xsd\">\n";
		//récupérer les permissions
		resultat += "<permissions>\n";
		//recuperer la liste des permissions
		Nuplet temp=new Nuplet();
		Iterator<Nuplet> itr = permission.contenu.iterator();
		while(itr.hasNext())
		{
			resultat+="<atomic forbidden=\"false\">\n";
			temp=itr.next();
			resultat+=temp.toSCD();
			resultat+="</atomic>\n";
		}
		resultat += "</permissions>\n";
		resultat += "<bans>\n";
		//recuperer la liste des interdictions
		temp=new Nuplet();
		itr = permission.contenu.iterator();
		while(itr.hasNext())
		{
			resultat+="<atomic forbidden=\"true\">\n";
			temp=itr.next();
			resultat+=temp.toSCD();
			resultat+="</atomic>\n";
		}
		resultat += "</bans>\n";
		
		resultat += "<complex>\n";
		//recupérer l'expression complexe
		Elem comp = fullComplex();
		resultat+= comp.toSCD();
		
		resultat += "</complex>\n";
		resultat += "</processus>\n";
		
		
//		listePermission = new Vector<Nuplet>();
//		permission.liste(listePermission);
//		//ecrire chaque permission
//		for(int p =0 ;p<listePermission.size();p++)
//		{
//			resultat+=listePermission.get(p).toSCD();
//		}
//		
//		resultat += "</permissions>\n";
//		//récupérer les interdictions
//		resultat += "<bans>\n";
//		//recuperer la liste des interdictions
//		listeInterdiction = new Vector<Nuplet>();
//		interdiction.liste(listeInterdiction);
//		//ecrire chaque interdictions
//		for(int i =0 ;i<listeInterdiction.size();i++)
//		{
//			resultat+=listeInterdiction.get(i).toSCD();
//		}
//		
//		resultat += "</bans>\n";
//		resultat += " <complex>\n<parstrong>\n<child>\n";
//		//récupérer les SoD dans complex en strong parallele
//		sod.isFirstParallele();
//		resultat += "</child>\n<child>\n";
//		//récupérer les obligations dans complex en strong parallele
//		
//		resultat += "</child>\n<child>\n";
//		//récupérer le complex dans complex avec le strong parallele
//		
//		//pied-de-page
//		resultat += "</child>\n</parstrong>\n</complex>\n</processus>\n";
		return resultat;
	}
	
	public String toString()
	{
		String resultat = null;
		resultat += "Voici le contenu du fichier:\n\n";
		resultat += "play :\n";
		if(play==null)
				resultat += "null\n";
			else
				resultat += play.toString();
		resultat += "permissions :\n";
		if(permission==null)
				resultat += "null\n";
			else
				resultat += permission.toString();
		resultat += "interdiction :\n";
		if(interdiction==null)
				resultat += "null\n";
			else
				resultat += interdiction.toString();
		resultat += "sod :\n";
		if(sod==null)
				resultat += "null\n";
			else
				resultat += sod.toString();
		resultat += "obligation :\n";
		if(obligation==null)
				resultat += "null\n";
			else
				resultat += obligation.toString();
		resultat += "complex :\n";
		if(complex==null)
				resultat += "null\n";
			else
				resultat += complex.toString();
		return resultat ;
	}

}
