package fr.lacl.tamago.aca.parser;


import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import fr.lacl.tamago.aca.structures.Chaine;
import fr.lacl.tamago.aca.structures.Elem;
import fr.lacl.tamago.aca.structures.Entrelacement;
import fr.lacl.tamago.aca.structures.EnvironnementSecurite;
import fr.lacl.tamago.aca.structures.Evenement;
import fr.lacl.tamago.aca.structures.Fichier;
import fr.lacl.tamago.aca.structures.Liste;
import fr.lacl.tamago.aca.structures.Negation;
import fr.lacl.tamago.aca.structures.Nuplet;
import fr.lacl.tamago.aca.structures.Obligation;
import fr.lacl.tamago.aca.structures.Parallele;
import fr.lacl.tamago.aca.structures.Sod;
import fr.lacl.tamago.aca.structures.Table;
import fr.lacl.tamago.aca.structures.Triplet;
import fr.lacl.tamago.aca.verification.Verification;



public class TreeWalker
{
	public static boolean debug;
	private static CommonTree tree;

	public static void main(String args[]) throws Exception 
	{

		convertACACDLLexer lex = new convertACACDLLexer(new ANTLRFileStream("/Users/plouf/Dropbox/boulot/workspace/convertACACDL/src/__Test___input.txt", "UTF8"));

		CommonTokenStream tokens = new CommonTokenStream(lex);

		convertACACDLParser g = new convertACACDLParser(tokens);

		RuleReturnScope result = null ;
		try 
		{
			result = g.listeProcessus();

		} 
		catch (RecognitionException e) 
		{
			e.printStackTrace();
		}

		tree = (CommonTree)result.getTree();
		//printTree();
		Fichier fichier = treeToFichier(tree);
		//fichier.verification();
		//System.out.println(fichier);
		if(Verification.permissionsOuProhibitionEffectives(fichier.play,fichier.permission))
		{
			System.out.println("permission et play compatibles");
		}
		else
		{
			System.out.println("permission et play incompatibles");
		}
		if(Verification.permissionsOuProhibitionEffectives(fichier.play,fichier.interdiction))
		{
			System.out.println("prohibitions et play compatibles");
		}
		else
		{
			System.out.println("prohibitions et play incompatibles");
		}
		if(Verification.permissionsEtProhibitionCompatibles(fichier.play,fichier.permission,fichier.interdiction))
		{
			System.out.println("prohibitions et permissions compatibles");
		}
		else
		{
			System.out.println("prohibitions et permissions incompatibles");
		}
		if(Verification.contientDeadlock(fichier.play,fichier.permission,fichier.interdiction,((Parallele)(fichier.fullComplex()))))
		{
			System.out.println("prohibitions et permissions compatibles");
		}
		else
		{
			System.out.println("prohibitions et permissions incompatibles");
		}
		
		//			System.out.println();
		//System.out.println(fichier.toSCD(false,false));
	}

	public static void printTree()
	{
		CommonTree temp=tree;
		printTree(temp,0);
	}

	private static void printTree(CommonTree tree,int k) 
	{
		CommonTree t = tree;
		if (t != null) 
		{
			for (int i = 1; i <= k; i++) System.out.print(" ");
			System.out.println(t.getText());
			for(int i=0;i<t.getChildCount();i++)
			{
				CommonTree t1 = (CommonTree) t.getChild(i);
				printTree(t1, k + 3);
			}
		}
	}

	public static Fichier treeToFichier(CommonTree tree) throws Exception
	{
		CommonTree t = tree;
		Fichier resultat = new Fichier();
		for(int i=0;i<t.getChildCount();i++)
		{
			CommonTree t1 = (CommonTree) t.getChild(i);
			if(t1.getText().toLowerCase().equals("play"))
			{
				resultat.play=processToTable(t1.getChild(0));
			}
			if(t1.getText().toLowerCase().equals("permission"))
			{
				resultat.permission=processToListePerm(t1.getChild(0));
			}
			if(t1.getText().toLowerCase().equals("interdiction"))
			{
				resultat.interdiction=processToListeInter(t1.getChild(0));
			}
			if(t1.getText().toLowerCase().equals("sod"))
			{
				resultat.sod=processToSOD(t1.getChild(0));
			}
			if(t1.getText().toLowerCase().equals("obligation"))
			{
				resultat.obligation=processToOBL(t1.getChild(0));
			}
			if(t1.getText().toLowerCase().equals("complex"))
			{
				resultat.complex=processToComplex(t1.getChild(0));
			}
		}
		return resultat;
	}

	private static Elem processToComplex(Tree child) throws Exception
	{
		int premier;
		Elem resultat;
		if(child.getType()==convertACACDLParser.OBLI)
		{
			premier = 0;
			resultat = fromProcessToObl((CommonTree)child);
			return resultat;
		}
		if(child.getType()==convertACACDLParser.SOD)
		{
			premier = 0;
			resultat = fromProcessToSod((CommonTree)child);
			return resultat;
		}
		else if(child.getType()==convertACACDLParser.PA)
		{
			resultat = new Parallele();
			premier = 1;
		}
		else if(child.getType()==convertACACDLParser.IL)
		{
			resultat = new Entrelacement();
			premier = 2;
		}
		else
		{
			throw new Exception();
		}
		return processToCOMPLEX(child,resultat,premier);
	}
	
	private static Elem processToCOMPLEX(Tree child, Elem resultat, int prov) throws Exception
	//prov = 0 si OBLI , 1 si PA, 2 si IL
	{
		CommonTree temp = (CommonTree) child;
		//on est PA on reste PA : on applanit
		if(temp.getType()==convertACACDLParser.PA && prov==1)
		{
			processToCOMPLEX(child.getChild(0),resultat,1);
			processToCOMPLEX(child.getChild(1),resultat,1);
			return resultat;
		}
		//on est IL on reste IL : on applanit
		else if(temp.getType()==convertACACDLParser.IL && prov==2)
		{
			processToCOMPLEX(child.getChild(0),resultat,2);
			processToCOMPLEX(child.getChild(1),resultat,2);
			return resultat;
		}
		//on est IL on devient PA : on change d'etage
		else if(temp.getType()==convertACACDLParser.PA && prov==2)
		{
			Elem nouveau = new Parallele();
			processToCOMPLEX(child.getChild(0),nouveau,1);
			processToCOMPLEX(child.getChild(1),nouveau,1);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est PA on devient IL : on change d'etage
		else if(temp.getType()==convertACACDLParser.IL && prov==1)
		{
			Elem nouveau = new Entrelacement();
			processToCOMPLEX(child.getChild(0),nouveau,2);
			processToCOMPLEX(child.getChild(1),nouveau,2);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est une obligation : on ajoute le resultat
		else if(temp.getType()==convertACACDLParser.OBLI)
		{
			resultat.addElem(fromProcessToObl(temp));
		}
		//on est une SOD : on ajoute le resultat
		else if(temp.getType()==convertACACDLParser.SOD)
		{
			resultat.addElem(fromProcessToSod(temp));
		}
		//on renvoie une exception
		else
		{
			throw new Exception();
		}
		return resultat;
	}
	
	private static Elem processToOBL(Tree child) throws Exception
	{
		int premier;
		Elem resultat;
		if(child.getType()==convertACACDLParser.OBLI)
		{
			premier = 0;
			resultat = fromProcessToObl((CommonTree)child);
			return resultat;
		}
		else if(child.getType()==convertACACDLParser.PA)
		{
			resultat = new Parallele();
			premier = 1;
		}
		else if(child.getType()==convertACACDLParser.IL)
		{
			resultat = new Entrelacement();
			premier = 2;
		}
		else
		{
			throw new Exception();
		}
		return processToOBLI(child,resultat,premier);
	}
	
	private static Obligation fromProcessToObl(CommonTree child) throws Exception
	{
		Obligation result = new Obligation();
		//on verifie que la Obligtion est sur un parametre de securite connu
		if(child.getChild(0).getText().toLowerCase().equals("user")
				||child.getChild(0).getText().toLowerCase().equals("role")
				||child.getChild(0).getText().toLowerCase().equals("organisation")
				||child.getChild(0).getText().toLowerCase().equals("temps"))
		{
			//on recupere les deux parties de la obligation
			Tree filsg = child.getChild(1);
			Tree filsd = child.getChild(2);
			//on verifie que la obligation est bien definies des deux cote en fonction du parametre de securite precise
			if(child.getChild(0).getText().toLowerCase().equals("user")
					&& filsd.getChild(0).getType()==convertACACDLParser.STRING 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.STRING 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{
				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("role")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.STRING
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.STRING
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("organisation")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.STRING
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.STRING
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("temps")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.STRING
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.STRING
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else
			{
				throw new Exception();
			}
		}
		else
		{
			throw new Exception();
		}
		return result;
	}

	private static Elem processToOBLI(Tree child, Elem resultat, int prov) throws Exception
	//prov = 0 si OBLI , 1 si PA, 2 si IL
	{
		CommonTree temp = (CommonTree) child;
		//on est PA on reste PA : on applanit
		if(temp.getType()==convertACACDLParser.PA && prov==1)
		{
			processToOBLI(child.getChild(0),resultat,1);
			processToOBLI(child.getChild(1),resultat,1);
			return resultat;
		}
		//on est IL on reste IL : on applanit
		else if(temp.getType()==convertACACDLParser.IL && prov==2)
		{
			processToOBLI(child.getChild(0),resultat,2);
			processToOBLI(child.getChild(1),resultat,2);
			return resultat;
		}
		//on est IL on devient PA : on change d'etage
		else if(temp.getType()==convertACACDLParser.PA && prov==2)
		{
			Elem nouveau = new Parallele();
			processToOBLI(child.getChild(0),nouveau,1);
			processToOBLI(child.getChild(1),nouveau,1);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est PA on devient IL : on change d'etage
		else if(temp.getType()==convertACACDLParser.IL && prov==1)
		{
			Elem nouveau = new Entrelacement();
			processToOBLI(child.getChild(0),nouveau,2);
			processToOBLI(child.getChild(1),nouveau,2);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est une SOD : on ajoute le resultat
		else if(temp.getType()==convertACACDLParser.OBLI)
		{
			resultat.addElem(fromProcessToObl(temp));
		}
		//on renvoie une exception
		else
		{
			throw new Exception();
		}
		return resultat;
	}

	
	private static Elem processToSOD(Tree child) throws Exception
	{
		int premier;
		Elem resultat;
		if(child.getType()==convertACACDLParser.SOD)
		{
			premier = 0;
			resultat = fromProcessToSod((CommonTree)child);
			return resultat;
		}
		else if(child.getType()==convertACACDLParser.PA)
		{
			resultat = new Parallele();
			premier = 1;
		}
		else if(child.getType()==convertACACDLParser.IL)
		{
			resultat = new Entrelacement();
			premier = 2;
		}
		else
		{
			throw new Exception();
		}
		return processToSOD(child,resultat,premier);
	}
	
	private static Elem processToSOD(Tree child, Elem resultat, int prov) throws Exception
	//prov = 0 si SOD , 1 si PA, 2 si IL
	{
		CommonTree temp = (CommonTree) child;
		//on est PA on reste PA : on applanit
		if(temp.getType()==convertACACDLParser.PA && prov==1)
		{
			processToSOD(child.getChild(0),resultat,1);
			processToSOD(child.getChild(1),resultat,1);
			return resultat;
		}
		//on est IL on reste IL : on applanit
		else if(temp.getType()==convertACACDLParser.IL && prov==2)
		{
			processToSOD(child.getChild(0),resultat,2);
			processToSOD(child.getChild(1),resultat,2);
			return resultat;
		}
		//on est IL on devient PA : on change d'etage
		else if(temp.getType()==convertACACDLParser.PA && prov==2)
		{
			Elem nouveau = new Parallele();
			processToSOD(child.getChild(0),nouveau,1);
			processToSOD(child.getChild(1),nouveau,1);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est PA on devient IL : on change d'etage
		else if(temp.getType()==convertACACDLParser.IL && prov==1)
		{
			Elem nouveau = new Entrelacement();
			processToSOD(child.getChild(0),nouveau,2);
			processToSOD(child.getChild(1),nouveau,2);
			resultat.addElem(nouveau);
			return resultat;
		}
		//on est une SOD : on ajoute le resultat
		else if(temp.getType()==convertACACDLParser.SOD)
		{
			resultat.addElem(fromProcessToSod(temp));
		}
		//on renvoie une exception
		else
		{
			throw new Exception();
		}
		return resultat;
	}

	private static Sod fromProcessToSod(CommonTree child) throws Exception
	{
		Sod result = new Sod();
		//on verifie que la SOD est sur un parametre de securite connu
		if(child.getChild(0).getText().toLowerCase().equals("user")
				||child.getChild(0).getText().toLowerCase().equals("role")
				||child.getChild(0).getText().toLowerCase().equals("organisation")
				||child.getChild(0).getText().toLowerCase().equals("temps"))
		{
			//on recupere les deux parties de la sod
			Tree filsg = child.getChild(1);
			Tree filsd = child.getChild(2);
			//on verifie que la sod est bien definies des deux cote en fonction du parametre de securite precise
			if(child.getChild(0).getText().toLowerCase().equals("user")
					&& filsd.getChild(0).getType()==convertACACDLParser.NOT 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.STRING 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{
				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("role")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.NOT
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.STRING
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("organisation")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.NOT
					&& filsd.getChild(3).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.STRING
					&& filsg.getChild(3).getType()==convertACACDLParser.WILDCARD
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else if(child.getChild(0).getText().toLowerCase().equals("temps")
					&& filsd.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsd.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsd.getChild(3).getType()==convertACACDLParser.NOT
					&& filsg.getChild(0).getType()==convertACACDLParser.WILDCARD 
					&& filsg.getChild(1).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(2).getType()==convertACACDLParser.WILDCARD
					&& filsg.getChild(3).getType()==convertACACDLParser.STRING
			)
			{

				Nuplet fg = processToNuplet(filsg);
				Nuplet fd = processToNuplet(filsd);
				Chaine valeur = fromStringToChaine(child.getChild(0));
				result.fils1=fg;
				result.fils2=fd;
				result.valeur=valeur;
			}
			else
			{
				throw new Exception();
			}
		}
		else
		{
			throw new Exception();
		}
		return result;
	}

	private static Nuplet processToNuplet(Tree filsg) throws Exception 
	{
		Nuplet resultat = new Nuplet();
		EnvironnementSecurite envi = new EnvironnementSecurite();
		//le user
		if(filsg.getChild(0).getType()==convertACACDLParser.NOT)
		{
			envi.user = fromNotToNegation(filsg.getChild(0));
		}
		if(filsg.getChild(0).getType()==convertACACDLParser.WILDCARD)
		{
			envi.user = fromWildcardToChaine();
		}
		if(filsg.getChild(0).getType()==convertACACDLParser.STRING)
		{
			envi.user = fromStringToChaine(filsg.getChild(0));
		}
		//le role
		if(filsg.getChild(1).getType()==convertACACDLParser.NOT)
		{
			envi.role = fromNotToNegation(filsg.getChild(1));
		}
		if(filsg.getChild(1).getType()==convertACACDLParser.WILDCARD)
		{
			envi.role = fromWildcardToChaine();
		}
		if(filsg.getChild(1).getType()==convertACACDLParser.STRING)
		{
			envi.role = fromStringToChaine(filsg.getChild(1));
		}
		//l'organisation
		if(filsg.getChild(2).getType()==convertACACDLParser.NOT)
		{
			envi.organisation = fromNotToNegation(filsg.getChild(2));
		}
		if(filsg.getChild(2).getType()==convertACACDLParser.WILDCARD)
		{
			envi.organisation = fromWildcardToChaine();
		}
		if(filsg.getChild(2).getType()==convertACACDLParser.STRING)
		{
			envi.organisation = fromStringToChaine(filsg.getChild(2));
		}
		//le temps
		if(filsg.getChild(3).getType()==convertACACDLParser.NOT)
		{
			envi.temps = fromNotToNegation(filsg.getChild(3));
		}
		if(filsg.getChild(3).getType()==convertACACDLParser.WILDCARD)
		{
			envi.temps = fromWildcardToChaine();
		}
		if(filsg.getChild(3).getType()==convertACACDLParser.STRING)
		{
			envi.temps = fromStringToChaine(filsg.getChild(3));
		}
		//l'evenement
		Evenement eve = new Evenement();
		Chaine result = new Chaine();
		((Chaine)result).valeur = filsg.getChild(4).getText();
		eve.nom = result;
		//on les mets dans le resultat
		resultat.environnement = envi;
		resultat.evenement = eve;
		return resultat;
	}

	private static Liste processToListeInter(Tree child) throws Exception
	{
		Liste resultat = new Liste();
		CommonTree temp = (CommonTree) child;
		while(temp.getChildCount()==2)
		{
			//on teste qu'on bien une table sinon on renvoie une exception
			if(temp.getType()==convertACACDLParser.LIST)
			{
				//on recupere le premier fils
				CommonTree fils1 = (CommonTree) temp.getChild(1);
				//on traite le premier fils
				Nuplet ligne = fromLtToPermInter(fils1);
				//on met le triplet dans la table resultat
				resultat.addNuplet(ligne);
			}
			else
			{
				throw new Exception();
			}
			temp = (CommonTree) temp.getChild(0);
		}
		if(temp.getType()!=convertACACDLParser.LT )
		{
			throw new Exception();
		}
		else
		{
			//on traite le dernier element
			Nuplet ligne = fromLtToPermInter(temp);
			//on met le triplet dans la table resultat
			resultat.addNuplet(ligne);
		}
		return resultat;
	}

	private static Nuplet fromLtToPermInter(CommonTree fils1) throws Exception 
	{
		//On crŽe l'ŽlŽment ˆ insŽrer dans le rŽsultat
		Nuplet ligne = new Nuplet();
		//On test que c'est bien un triplet
		if(fils1.getType()!=convertACACDLParser.LT || fils1.getChildCount()!=5)
		{
			throw new Exception();
		}
		else
		{
			//on test que chacun des element est un string ou wildcard
			if((fils1.getChild(0).getType()!=convertACACDLParser.NOT 
					&& fils1.getChild(0).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(1).getType()!=convertACACDLParser.NOT 
					&& fils1.getChild(1).getType()!=convertACACDLParser.WILDCARD)
				|| (fils1.getChild(2).getType()!=convertACACDLParser.NOT 
					&& fils1.getChild(2).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(3).getType()!=convertACACDLParser.NOT 
					&& fils1.getChild(3).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(4).getType()!=convertACACDLParser.STRING ) )
			{
				throw new Exception();
			}
			//on rŽcupre l'environnement de sŽcuritŽ
			EnvironnementSecurite envi = new EnvironnementSecurite();
			//le user
			if(fils1.getChild(0).getType()==convertACACDLParser.NOT)
			{
				envi.user = fromNotToNegation(fils1.getChild(0));
			}
			if(fils1.getChild(0).getType()==convertACACDLParser.WILDCARD)
			{
				envi.user = fromWildcardToChaine();
			}
			//le role
			if(fils1.getChild(1).getType()==convertACACDLParser.NOT)
			{
				envi.role  = fromNotToNegation(fils1.getChild(1));
			}
			if(fils1.getChild(1).getType()==convertACACDLParser.WILDCARD)
			{
				envi.role = fromWildcardToChaine();
			}
			//l'organisation
			if(fils1.getChild(2).getType()==convertACACDLParser.NOT)
			{
				envi.organisation  = fromNotToNegation(fils1.getChild(2));
			}
			if(fils1.getChild(2).getType()==convertACACDLParser.WILDCARD)
			{
				envi.organisation = fromWildcardToChaine();
			}
			//le temps
			if(fils1.getChild(3).getType()==convertACACDLParser.NOT)
			{
				envi.temps  = fromNotToNegation(fils1.getChild(3));
			}
			if(fils1.getChild(3).getType()==convertACACDLParser.WILDCARD)
			{
				envi.temps = fromWildcardToChaine();
			}
			//on recupere l'action
			Evenement eve = new Evenement();
			Chaine resultat = new Chaine();
			((Chaine)resultat).valeur = fils1.getChild(4).getText();
			eve.nom = resultat;
			//on les mets dans le resultat
			ligne.environnement = envi;
			ligne.evenement = eve;
		}
		return ligne;
	}

	private static Chaine fromWildcardToChaine()
	{
		Chaine resultat = new Chaine();
		((Chaine)resultat).valeur = new String("_");
		return resultat;
	}

	private static Negation fromNotToNegation(Tree tree2)
	{
		Chaine resultat = new Chaine();
		resultat.valeur = tree2.getChild(0).getText();
		Negation resul = new Negation();
		resul.fils1 = resultat;
		return resul;
	}

	private static Liste processToListePerm(Tree child) throws Exception
	{
		Liste resultat = new Liste();
		CommonTree temp = (CommonTree) child;
		while(temp.getChildCount()==2)
		{
			//on teste qu'on bien une table sinon on renvoie une exception
			if(temp.getType()==convertACACDLParser.LIST)
			{
				//on recupere le premier fils
				CommonTree fils1 = (CommonTree) temp.getChild(1);
				//on traite le premier fils
				Nuplet ligne = fromLtToPermElem(fils1);
				//on met le triplet dans la table resultat
				resultat.addNuplet(ligne);
			}
			else
			{
				throw new Exception();
			}
			temp = (CommonTree) temp.getChild(0);
		}
		if(temp.getType()!=convertACACDLParser.LT )
		{
			throw new Exception();
		}
		else
		{
			//on traite le dernier element
			Nuplet ligne = fromLtToPermElem(temp);
			//on met le triplet dans la table resultat
			resultat.addNuplet(ligne);
		}
		return resultat;
	}

	private static Nuplet fromLtToPermElem(CommonTree fils1) throws Exception 
	{
		//On crŽe l'ŽlŽment ˆ insŽrer dans le rŽsultat
		Nuplet ligne = new Nuplet();
		//On test que c'est bien un triplet
		if(fils1.getType()!=convertACACDLParser.LT || fils1.getChildCount()!=5)
		{
			throw new Exception();
		}
		else
		{
			//on test que chacun des element est un string ou wildcard
			if((fils1.getChild(0).getType()!=convertACACDLParser.STRING 
					&& fils1.getChild(0).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(1).getType()!=convertACACDLParser.STRING
					&& fils1.getChild(1).getType()!=convertACACDLParser.WILDCARD)
				|| (fils1.getChild(2).getType()!=convertACACDLParser.STRING 
					&& fils1.getChild(2).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(3).getType()!=convertACACDLParser.STRING 
					&& fils1.getChild(3).getType()!=convertACACDLParser.WILDCARD) 
				|| (fils1.getChild(4).getType()!=convertACACDLParser.STRING ) )
			{
				throw new Exception();
			}
			//on rŽcupre l'environnement de sŽcuritŽ
			EnvironnementSecurite envi = new EnvironnementSecurite();
			//le user
			if(fils1.getChild(0).getType()==convertACACDLParser.STRING)
			{
				envi.user = fromStringToChaine(fils1.getChild(0));
			}
			if(fils1.getChild(0).getType()==convertACACDLParser.WILDCARD)
			{
				envi.user = fromWildcardToChaine();
			}
			//le role
			if(fils1.getChild(1).getType()==convertACACDLParser.STRING)
			{
				envi.role =fromStringToChaine(fils1.getChild(1));
			}
			if(fils1.getChild(1).getType()==convertACACDLParser.WILDCARD)
			{
				envi.role = fromWildcardToChaine();
			}
			//l'organisation
			if(fils1.getChild(2).getType()==convertACACDLParser.STRING)
			{
				envi.organisation = fromStringToChaine(fils1.getChild(2));
			}
			if(fils1.getChild(2).getType()==convertACACDLParser.WILDCARD)
			{
				envi.organisation = fromWildcardToChaine();
			}
			//le temps
			if(fils1.getChild(3).getType()==convertACACDLParser.STRING)
			{
				envi.temps =fromStringToChaine(fils1.getChild(3));
			}
			if(fils1.getChild(3).getType()==convertACACDLParser.WILDCARD)
			{
				envi.temps = fromWildcardToChaine();
			}
			//on recupere l'action
			Evenement eve = new Evenement();
			Chaine resultat = new Chaine();
			((Chaine)resultat).valeur = fils1.getChild(4).getText();
			eve.nom = resultat;
			//on les mets dans le resultat
			ligne.environnement = envi;
			ligne.evenement = eve;
		}
		return ligne;
	}

	private static Chaine fromStringToChaine(Tree t)
	{
		Chaine resultat = new Chaine();
		((Chaine)resultat).valeur = t.getText();
		return resultat;
	}
	private static Table processToTable(Tree child) throws Exception
	{
		Table resultat = new Table();
		CommonTree temp = (CommonTree) child;
		while(temp.getChildCount()==2)
		{
			//on teste qu'on bien une table sinon on renvoie une exception
			if(temp.getType()==convertACACDLParser.TABL)
			{
				//on recupere le premier fils
				CommonTree fils1 = (CommonTree) temp.getChild(1);
				//on traite le premier fils
				Triplet ligne = fromLtToTriplet(fils1);
				//on met le triplet dans la table resultat
				resultat.addTriplet(ligne);
			}
			else
			{
				throw new Exception();
			}
			temp = (CommonTree) temp.getChild(0);

		}
		if(temp.getType()!=convertACACDLParser.LT )
		{
			throw new Exception();
		}
		else
		{
			//on traite le dernier element
			Triplet ligne = fromLtToTriplet(temp);
			//on met le triplet dans la table resultat
			resultat.addTriplet(ligne);
		}
		return resultat;
	}

	private static Triplet fromLtToTriplet(CommonTree fils1) throws Exception
	{
		//On crŽe l'ŽlŽment ˆ insŽrer dans le rŽsultat
		Triplet ligne = new Triplet();
		//On test que c'est bien un triplet
		if(fils1.getType()!=convertACACDLParser.LT || fils1.getChildCount()!=3)
		{
			throw new Exception();
		}
		else
		{
			//on test que chacun des element est un string
			if(fils1.getChild(0).getType()!=convertACACDLParser.STRING 
					|| fils1.getChild(1).getType()!=convertACACDLParser.STRING 
					|| fils1.getChild(2).getType()!=convertACACDLParser.STRING )
			{
				throw new Exception();
			}
			//on les met dans le triplet
			ligne.user=fils1.getChild(0).getText();
			ligne.role=fils1.getChild(1).getText();
			ligne.organisation=fils1.getChild(2).getText();
		}
		return ligne;
	}

	private static Elem processToElem(Tree child) 
	{
		// TODO Auto-generated method stub
		Elem resultat = null;
		CommonTree temp = (CommonTree) child;
		//System.out.println(">>>>>  "+ temp.getText());
		if(temp.getType()==convertACACDLParser.TABL)
		{
			resultat = new Parallele();
			((Parallele)resultat).fils1 = processToElem(temp.getChild(0));
			((Parallele)resultat).fils2 = processToElem(temp.getChild(1));
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.IL)
		{
			resultat = new Entrelacement();
			((Entrelacement)resultat).fils1 = processToElem(temp.getChild(0));
			((Entrelacement)resultat).fils2 = processToElem(temp.getChild(1));
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.NOT)
		{
			resultat = new Negation();
			((Negation)resultat).fils1 = processToElem(temp.getChild(0));
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.OBLI)
		{
			resultat = new Obligation();
			((Obligation)resultat).valeur = processToElem(temp.getChild(0));
			((Obligation)resultat).fils1 = processToElem(temp.getChild(1));
			((Obligation)resultat).fils2 = processToElem(temp.getChild(2));
			//System.out.println("-------<XX>  " + resultat);
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.PA)
		{
			resultat = new Parallele();
			((Parallele)resultat).fils1 = processToElem(temp.getChild(0));
			((Parallele)resultat).fils2 = processToElem(temp.getChild(1));
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.SOD)
		{
			resultat = new Sod();
			((Sod)resultat).valeur = processToElem(temp.getChild(0));
			((Sod)resultat).fils1 = processToElem(temp.getChild(1));
			((Sod)resultat).fils2 = processToElem(temp.getChild(2));
			//System.out.println("-------<>  " + resultat);
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.STRING)
		{
			resultat = new Chaine();
			((Chaine)resultat).valeur = temp.getText();
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.WILDCARD)
		{
			resultat = new Chaine();
			((Chaine)resultat).valeur = new String("_");
			return resultat;
		}
		if(temp.getType()==convertACACDLParser.LT)
		{
			EnvironnementSecurite envi = new EnvironnementSecurite();
			envi.user = processToElem(temp.getChild(0));
			envi.role = processToElem(temp.getChild(1));
			envi.organisation = processToElem(temp.getChild(2));
			envi.temps = processToElem(temp.getChild(3));
			Evenement even = new Evenement();
			even.nom = new Chaine();
			((Chaine)even.nom).valeur = temp.getChild(4).getText();
			if(temp.getChild(4).getChildCount()!=0)
			{
				even.arguments = new Chaine();
				((Chaine)even.arguments).valeur = temp.getChild(4).getChild(0).getText();
			}

			resultat = new Nuplet();
			((Nuplet)resultat).environnement= envi;
			((Nuplet)resultat).evenement=even;

			return resultat;

		}
		return resultat;		
	}
}
