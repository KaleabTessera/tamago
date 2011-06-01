package fr.lacl.tamago.aca.verification;
import java.util.Iterator;
import java.util.Vector;

import fr.lacl.tamago.aca.structures.Chaine;
import fr.lacl.tamago.aca.structures.Elem;
import fr.lacl.tamago.aca.structures.Entrelacement;
import fr.lacl.tamago.aca.structures.EnvironnementSecurite;
import fr.lacl.tamago.aca.structures.Evenement;
import fr.lacl.tamago.aca.structures.Liste;
import fr.lacl.tamago.aca.structures.Negation;
import fr.lacl.tamago.aca.structures.Nuplet;
import fr.lacl.tamago.aca.structures.Obligation;
import fr.lacl.tamago.aca.structures.Parallele;
import fr.lacl.tamago.aca.structures.Sod;
import fr.lacl.tamago.aca.structures.Table;
import fr.lacl.tamago.aca.structures.Triplet;



public class Verification 
{
	private static int uCpt ;
	private static int rCpt ;
	private static int oCpt;
	private static Elem[][] variables;
	
	public static boolean contientDeadlock(Table play,
			Liste permission, Liste interdiction, Parallele parallele) throws Exception 
	{
		boolean resultat = true;
		//construire la foret
		Vector<ListeConditions> foret = new Vector<ListeConditions>(10);
		uCpt=0;
		rCpt=0;
		oCpt=0;
		foret = construireForet(parallele);
		System.out.println(foret);
		foret = simplifierVariables(foret);
		//resoudre la foret
		ListeConditions perm = permissionsEffectives(play,permission,interdiction);
		
		
		
		
		return resultat;
	}

	
	private static Vector<ListeConditions> simplifierVariables(Vector<ListeConditions> foret) 
	{
		Vector<ListeConditions> resultat = null;
		System.out.println("-->>"+uCpt+"/"+rCpt+"/"+oCpt);
		
		return resultat;
	}


	private static Vector<ListeConditions> construireForet(Elem pnoeud) throws Exception 
	{
		Vector<ListeConditions> resultat = new Vector<ListeConditions>(10);
		if(pnoeud.getClass().getSimpleName().equals("Parallele"))
		{
			//appel recursif
			Parallele noeud = (Parallele)pnoeud;
			Iterator<Elem> it = noeud.contenu.iterator();
			Elem courant;
			while(it.hasNext())
			{
				courant  = it.next();
				Vector<ListeConditions> reccurent = construireForet(courant);
//				Iterator<ListeConditions> aajou = reccurent.iterator();
//				while(aajou.hasNext())
//				{
					resultat=fusion(resultat,reccurent);
//				}
			}
		}
		else if(pnoeud.getClass().getSimpleName().equals("Entrelacement"))
		{
			//appel recursif
			Entrelacement noeud = (Entrelacement)pnoeud;
			Iterator<Elem> it = noeud.contenu.iterator();
			Elem courant;
			while(it.hasNext())
			{
				courant  = it.next();
				Vector<ListeConditions> reccurent = construireForet(courant);
				Iterator<ListeConditions> aajou = reccurent.iterator();
				while(aajou.hasNext())
				{
					resultat.add(aajou.next());
				}
			}
		}
		else if(pnoeud.getClass().getSimpleName().equals("Sod"))
		{
			//On cree une listcondparevenemtn pour le resultat
			ListeConditions tmp = new ListeConditions();
			//on verifie si c' est quantifie ou instancie
			int quantif = 0;
			Sod noeud = (Sod)pnoeud;
			Nuplet tmpsss = (Nuplet) noeud.fils1;
			EnvironnementSecurite fils1 = (EnvironnementSecurite)tmpsss.environnement;
			Nuplet tmpssz = (Nuplet) noeud.fils2;
			
			if(noeud.valeur.getNom().equals("user") &&fils1.user.getNom().equals("user"))
				quantif=1;
			if(noeud.valeur.getNom().equals("role") && fils1.role.getNom().equals("role"))
				{
					quantif=2;
//					System.out.println("--["+noeud.valeur +"/"+fils1.user+"]--");
				}
			if(noeud.valeur.getNom().equals("organisation") && fils1.organisation.getNom().equals("organisation"))
				quantif=3;
//			System.out.println("--["+noeud.valeur.getNom().equals("role") +"/"+fils1.role.equals("role")+"]--"+quantif);
//			System.out.println("--["+noeud.valeur +"/"+fils1.role+"]--"+quantif);
			//si quantifie
			if(quantif==1)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String util = "@u"+uCpt;
				uCpt++;
//				System.out.println("----+++++"+ util);
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif(util,"_","_");
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuNegatif(1,util,"_","_");
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
				
			}
			else if(quantif==2)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String rol = "@r"+rCpt;
				rCpt++;
//				System.out.println("----+++++"+ rol);
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif("_",rol,"_");
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuNegatif(2,"_",rol,"_");
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
			}
			else if(quantif==3)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String orga = "@o"+oCpt;
				oCpt++;
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif("_","_",orga);
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuNegatif(3,"_","_",orga);
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
			}
			else//si instancie
			{
				tmp.ajouterCondition(tmpsss);
				tmp.ajouterCondition(tmpssz);
			}
			//on l' ajoute dans le resultat
			resultat.add(tmp);
		}
		else if(pnoeud.getClass().getSimpleName().equals("Obligation"))
		{
			//On cree une listcondparevenemtn pour le resultat
			ListeConditions tmp = new ListeConditions();
			//on verifie si c' est quantifie ou instancie
			int quantif = 0;
			Obligation noeud = (Obligation)pnoeud;
			Nuplet tmpsss = (Nuplet) noeud.fils1;
			EnvironnementSecurite fils1 = (EnvironnementSecurite)tmpsss.environnement;
			Nuplet tmpssz = (Nuplet) noeud.fils2;
			if(noeud.valeur.getNom().equals("user") &&fils1.user.getNom().equals("user"))
				quantif=1;
			if(noeud.valeur.getNom().equals("role") && fils1.role.getNom().equals("role"))
				quantif=2;
			if(noeud.valeur.getNom().equals("organisation") && fils1.organisation.getNom().equals("organisation"))
				quantif=3;
			//si quantifie
			if(quantif==1)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String util = "@u"+uCpt;
				uCpt++;
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif(util,"_","_");
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuPositif(util,"_","_");
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
				
			}
			else if(quantif==2)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String rol = "@r"+rCpt;
				rCpt++;
//				System.out.println("----+++++"+ rol);
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif("_",rol,"_");
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuPositif("_",rol,"_");
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
			}
			else if(quantif==3)
			{
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils gauche
				String orga = "@o"+oCpt;
				oCpt++;
				Nuplet res = new Nuplet();
				res.environnement=envSecuPositif("_","_",orga);
				res.evenement=tmpsss.evenement;
				//on cree un nouveau nuplet avec la variable @u/r/o+?cpt pour le fils droit
				Nuplet res2 = new Nuplet();
				res2.environnement=envSecuPositif("_","_",orga);
				res2.evenement=tmpssz.evenement;
				//puis on les ajoute dans le resultat
				tmp.ajouterCondition(res);
				tmp.ajouterCondition(res2);
			}
			else//si instancie
			{
				tmp.ajouterCondition(tmpsss);
				tmp.ajouterCondition(tmpssz);
			}
			//on l' ajoute dans le resultat
			resultat.add(tmp);
		}
		else
		{
			throw new Exception();
		}
//		System.out.println("- - - - - - - - - - - -");
//		System.out.println(pnoeud);
//		System.out.println("------------");
//		System.out.println(resultat);
//		System.out.println("------------");
//		System.out.println(resultat);

		return resultat;
	}

	private static Vector<ListeConditions> fusion(Vector<ListeConditions> courant, Vector<ListeConditions> ajout)
	{
		Vector<ListeConditions> resultat = new Vector<ListeConditions>(10);
		if(courant.isEmpty())
		{
			resultat =ajout;
		}
		else
		{
			Iterator<ListeConditions> temp = ajout.iterator();
			while(temp.hasNext())
			{
				Iterator<ListeConditions> temp2 =fusion(courant,temp.next()).iterator();
				while(temp2.hasNext())
					resultat.add(temp2.next());
			}
			
		}
		return resultat;
	}
	
	private static Vector<ListeConditions> fusion(Vector<ListeConditions> courant, ListeConditions ajout) 
	{
		Vector<ListeConditions> resultat = new Vector<ListeConditions>(10);
		if(courant.isEmpty())
		{
			resultat.add(ajout);
		}
		else
		{
			Iterator<ListeConditions> it = courant.iterator();
			ListeConditions tmp ;
			while(it.hasNext())
			{
				tmp=it.next();
				Iterator<ListeConditionsParEvenement> evenement = ajout.conditions.iterator();
				ListeConditionsParEvenement eventTmp;
				while(evenement.hasNext())
				{
					eventTmp=evenement.next();
					Iterator<Elem>conditionCourante = eventTmp.conditions.iterator();
					Elem conditionTmp ;
					while(conditionCourante.hasNext())
					{
						conditionTmp=conditionCourante.next();
						Nuplet aajouter = new Nuplet();
						aajouter.environnement=conditionTmp;
						aajouter.evenement= eventTmp.evenement;
//						System.out.println("- - - - - - - - - - - -");
//						System.out.println(aajouter);
//						System.out.println("------------");
//						System.out.println(tmp);
//						System.out.println("------------");
//						System.out.println(resultat);
						tmp.ajouterCondition(aajouter);
//						System.out.println("------------");
//						System.out.println(tmp);
					}
				}
				resultat.add(tmp);
			}
		}
//		System.out.println("- - - - - - - - - - - -");
//		System.out.println(courant);
//		System.out.println("------------");
//		System.out.println(ajout);
//		System.out.println("------------");
//		System.out.println(resultat);
		return resultat;
	}


	public static EnvironnementSecurite envSecuPositif(String user,String role, String orga)
	{
		Chaine utili = new Chaine();
		utili.valeur=user;
		Chaine rol = new Chaine();
		rol.valeur=role;
		Chaine org = new Chaine();
		org.valeur=orga;
		Chaine tps = new Chaine();
		tps.valeur="_";
		EnvironnementSecurite secu = new EnvironnementSecurite();
		secu.user=utili;
		secu.role=rol;
		secu.organisation=org;
		secu.temps=tps;
		return secu;
	}
	public static EnvironnementSecurite envSecuNegatif(int lieu,String user,String role, String orga)
	{
		//lieu = 1 pour user, 2 pour role , 3 pour organisation
		Chaine utili = new Chaine();
		Chaine rol = new Chaine();
		Chaine org = new Chaine();
		Chaine tps = new Chaine();
		utili.valeur=user;
		rol.valeur=role;
		org.valeur=orga;
		tps.valeur="_";
		EnvironnementSecurite secu = new EnvironnementSecurite();
		if(lieu==1)
		{
			Negation non = new Negation();
			non.fils1=utili;
			secu.user=non;
			secu.role=rol;
			secu.organisation=org;
			secu.temps=tps;
		}else if(lieu==2)
		{
			Negation non = new Negation();
			secu.user=utili;
			non.fils1=rol;
			secu.role=non;
			secu.organisation=org;
			secu.temps=tps;
		}else if(lieu==3)
		{
			Negation non = new Negation();
			secu.user=non;
			secu.role=non;
			non.fils1=rol;
			secu.organisation=non;
			secu.temps=tps;
		}
		return secu;
	}

	public static boolean permissionsEtProhibitionCompatibles(Table play,
			Liste permission, Liste interdiction) 
	{
		boolean resultat = true;
		//recuperer les permissions effectives
		ListeConditions perm = permissionsEffectives(play,permission,interdiction);
		Iterator<ListeConditionsParEvenement> it = perm.conditions.iterator();
		while(it.hasNext())
			if(it.next().conditions.isEmpty())
				resultat=false;
		//si une action a des conditions vides c'est faux sinon c'est vrai
		return resultat;
	}

	public static ListeConditions permissionsEffectives(Table play,
			Liste permission, Liste interdiction) 
	{
		//mettre dans permissions le depliier de play et permission
		ListeConditions perm =depliePlay(play,permission);
		//mettre dans interdiction le deplier de play et interdiction
		ListeConditions prohib =depliePlay(play,interdiction);
		//retirer les interdictions de permissions
		//pour tous les elements de prohib
		Iterator<ListeConditionsParEvenement> itProhib = prohib.conditions.iterator();
		ListeConditionsParEvenement prohibElem;
		while(itProhib.hasNext())
		{
			prohibElem=itProhib.next();
			//trouver l element de perm s'il existe
			Iterator<ListeConditionsParEvenement> itPerm = perm.conditions.iterator();
			ListeConditionsParEvenement permElem;
			while(itPerm.hasNext())
			{
				permElem=itPerm.next();
				if(permElem.evenement.getNom().equals(prohibElem.evenement.getNom()))
				{
					//pour toutes les conditions de l'element de prohib
					Iterator<Elem> itProhibCond = prohibElem.conditions.iterator();
					Elem permProhibCond;
					while(itProhibCond.hasNext())
					{
						permProhibCond=itProhibCond.next();
						//les retirer de  l'element de perm s il y a lieu
						permElem.retirer(permProhibCond);
					}
				}
			}
		}
		return perm;
	}
	
	public static boolean permissionsOuProhibitionEffectives(Table aPlay, Liste aPermissions)
	{
		boolean resultat = true;
		ListeConditions tableau =depliePlay(aPlay,aPermissions);
		Iterator<ListeConditionsParEvenement> it = tableau.conditions.iterator();
		while(it.hasNext())
			if(it.next().conditions.isEmpty())
				resultat=false;
		return resultat;
	}
	
	public static ListeConditions depliePlay(Table aPlay, Liste aPermissions)
	{
		Table play = aPlay;
		Liste permission=aPermissions;
		//creer une ListeCondition
		ListeConditions tableau = new ListeConditions();
		//pour toutes les permisions
		Iterator<Nuplet> itr = permission.contenu.iterator();
		while(itr.hasNext())
		{
			//les rajouter dans ListeCondition
			tableau.ajouterCondition(itr.next());
		}
		//deplier le play dans le resultat précédent
		//creer un nouveau listeCondition pour resultat
		ListeConditions result = new ListeConditions();
		//pour chaque element courant de tableau
		Iterator<ListeConditionsParEvenement> it = tableau.conditions.iterator();
		ListeConditionsParEvenement ajout;
		ListeConditionsParEvenement courant;
		while(it.hasNext())
		{
			//creer un nouveau listeConditionparevenemnt pour l'evenement de courant
			ajout=new ListeConditionsParEvenement();
			courant = it.next();
			ajout.evenement=courant.evenement;
			//pour chacune des conditions de courant
			Iterator<Elem> iter = courant.conditions.iterator();
			Elem conditioncourante;
			while(iter.hasNext())
			{
				conditioncourante=iter.next();
				//prendre les elements de play corespondant et les mettre dans le nouvau listeConditionParEvenement
				Iterator<Triplet> iterplay = play.contenu.iterator();
				Triplet courantplay;
				while(iterplay.hasNext())
				{
					courantplay=iterplay.next();
					//courantplay et conditioncourante corresponde
					if(compatibleTripletEnvironnement(courantplay,(EnvironnementSecurite)conditioncourante))
					{
						//transformer courantplay en environnement de securite
						EnvironnementSecurite playenv = new EnvironnementSecurite();
						Chaine user = new Chaine();
						user.valeur=courantplay.user;
						playenv.user=user;
						Chaine role = new Chaine();
						role.valeur=courantplay.role;
						playenv.role=role;
						Chaine orga = new Chaine();
						orga.valeur=courantplay.organisation;
						playenv.organisation=orga;
						Chaine tps = new Chaine();
						tps.valeur="_";
						playenv.temps=tps;
						//rajoutercourantplay dans ajout
						ajout.addCondition(playenv);
					}
				}	
			}
			//rajouter le nouveau listeConDitionParEvenement dans le resultat
			result.addCondition(ajout);
		}
		return result;
	}

	private static boolean compatibleTripletEnvironnement(Triplet courantplay,
			EnvironnementSecurite conditioncourante) 
	{
		boolean resultat=false;
			if((conditioncourante.user.getNom().equals("_")
					||conditioncourante.user.getNom().equals(courantplay.user))
				&&(conditioncourante.role.getNom().equals("_")
					||conditioncourante.role.getNom().equals(courantplay.role))
				&&(conditioncourante.organisation.getNom().equals("_")
					||conditioncourante.organisation.getNom().equals(courantplay.organisation))	
			)
				resultat=true;
		return resultat;
	}

	

}
