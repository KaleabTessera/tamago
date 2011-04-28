package tamagocc.util;


import java.util.*;
import tamagocc.*;
import java.io.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tamagocc.api.TTamago;
import tamagocc.exception.*;

/**
 * This class is the pool of all parsed contract. This class optimize the compilation and avoid the
 * compiler to compile already visited contract.
 * 
 * @author Hakim BELHAOUARI
 */
public class TamagoCCPool {

		/* *** DECLARATION STATIQUE DE L'OUTIL PAR DEFAUT *** */
	
	private static TamagoCCPool defaultPool = new TamagoCCPool(TamagoCCParser.getDefaultParser());
	public static TamagoCCPool getDefaultPool() {
		if(defaultPool == null)
			defaultPool = new TamagoCCPool(TamagoCCParser.getDefaultParser());
		return defaultPool;
	}

    /** 
     * Table de hachage qui contient tout les elements deja connu.
     * La cle correspond au nom du paquetage suivit de '.' et enfin se terminant
     * par le nom du composant/service/composite... 
     */
    private Hashtable<String,TTamago> hash;
    
    /**
     * Contient la liste des chemins de recherche.
     */
    private ArrayList<String> tamagoccpath;
    
    /**
     * Contient le parseur necessaire pour la compilation
     * pour determiner si le fichier contient bien ce que l'on souhaite obtenir.
     * 
     */
    private TamagoCCParser parser;
    
    /**
     * Constructeur le plus simple on lui passe un parser en parametre
     * pour generer l'arbre de syntaxe abstraite.
     * Les chemins de recherche sont limite au repertoire courant.
     */
    public TamagoCCPool(TamagoCCParser parser) {
        super();
        hash = new Hashtable<String,TTamago>();
        tamagoccpath = new ArrayList<String>();
        this.parser = parser;
        tamagoccpath.add("."+File.separator);
    }
    
    /**
     * Constructeur a 2 parametres
     * @param path : listes de chemin precis?e sous forme d'iterateur
     * @param parser : parser qui sera utiliser pour parser les fichiers tamagoCC
     * @see TamagoCCPool#TamagoCCPool(TamagoCCParser)
     */
    public TamagoCCPool(Iterator<String> path,TamagoCCParser parser) {
        super();
        hash = new Hashtable<String,TTamago>();
        tamagoccpath = new ArrayList<String>();
        tamagoccpath.add("."+File.separator);
        this.parser = parser;
        while(path.hasNext()) {
            String p = (String)path.next();
            addTamagoCCPath(p);
        }
        
    }
    
    /**
     * Constructeur a 2 parametres
     * @param listpath : contient la liste des chemins de recherches separer par le separateur de chemin
     * dependant du systeme d'exploitation (par exemple ":" sous linux et ";" sous windows).
     * @param parser : parser qui sera utiliser pour parser les fichiers tamagoCC
     * @see TamagoCCPool#TamagoCCPool(TamagoCCParser)
     */
    public TamagoCCPool(String listpath,TamagoCCParser parser) {
        super();
        hash = new Hashtable<String,TTamago>();
        tamagoccpath = new ArrayList<String>();
        this.parser = parser;
        tamagoccpath.add("."+File.separator);
        
        StringTokenizer token = new StringTokenizer(listpath,File.pathSeparator);
        while(token.hasMoreTokens()) {
            addTamagoCCPath(token.nextToken());
        }
        
    }
    
    /**
     * Fonction principale qui permet de recupere dans le pool l'arbre de syntaxe abstraite
     * en fonction du nom et de son paquetage.  
     * @param name : nom du fichier tamagoCC que l'on cherche
     * @param namepackage : nom du paquetage dans le cas de plusieur fichier demande
     * @return Renvoie un arbre de syntaxe abstrait qui correspond au noeud racine (Service,...) 
     * @throws ParserConfigurationException : Lance si par exemple le parser ne supporte pas les XML Schema
     * @throws IOException : Lance dans le cas ou le fichier connait un probleme quelconque.
     * @throws SAXException : Lance en cas de probleme du parser SAX
     * @throws TamagoCCException : Lance dans le cas ou TamagoCC connait un probleme varie.
     */
    public TTamago getTreeAbstractSyntax(String name,String namepackage)
    	throws TamagoCCException
    {
    	try {
    		if(hash.containsKey(namepackage+"."+name)) {
    			TTamago tamago = (TTamago)hash.get(namepackage+"."+name);
    			return tamago;            
    		}
    		else {
    			TTamago tamago = (TTamago) searchAndParse(name,namepackage);
    			return tamago;
    		}
    	}
    	catch(ParserConfigurationException pce) {
    		throw new TamagoCCException(pce);
    	}
    	catch(IOException io) {
    		throw new TamagoCCException(io);
    	}
    	catch(SAXException sax) {
    		throw new TamagoCCException(sax); 
    	}
    }
    
    public void addEntry(String name,String namepackage,TTamago tamago)
    {
    	String key2 = namepackage+"."+name;
   		if(!hash.containsKey(key2)) {
            hash.put(key2,tamago);            
    	}
    }
    
    /**
     * Permet d'augment les chemins de recherches dans le pool
     * @param path : chemin a ajouter dans le pool
     */
    public void addTamagoCCPath(String path) {
        if(!tamagoccpath.contains(path)) {
            tamagoccpath.add(path);
        }
    }
    
    /**
     * Ajoute tous les chemins separer par le caractere File.pathSeparator.
     * @param path : variable contenant
     */
    public void addTamagoCCListPath(String path) {
    	StringTokenizer token = new StringTokenizer(path,File.pathSeparator);
        while(token.hasMoreTokens()) {
            addTamagoCCPath(token.nextToken());
        }
    }
    
    
    public void addTamagoCCListPath(Iterator<String> paths) {
    	while(paths.hasNext()) {
            String p = (String)paths.next();
            addTamagoCCPath(p);
        }
    }
    
    /**
     * Permet de supprimer un chemin de recherches dans le pool.
     * @param path : chemin qu'il faut retirer du pool.
     */
    public void deleteTamagoCCPath(String path) {
        if(!tamagoccpath.contains(path))
            tamagoccpath.remove(path);
    }
    
    /**
     * Permet de supprimer un chemin de recherche du pool en fonction de son index.
     * @param index : index qui sera supprime du pool
     */
    public void deleteTamagoCCPath(int index) {
        tamagoccpath.remove(index);
    }
    
    /**
     * Permet de recuperer la liste des chemins de recherche du pool sous forme d'Iterateur
     * @return Renvoie un iterateur qui contient la liste des chemins de recherche.
     */
    public Iterator<String> getTamagoCCPath() {
        return tamagoccpath.iterator();
    }
    
    public void remove(String name,String namepackage) {
    	String key1 = namepackage+"."+name;
    	if(hash.containsKey(key1)) {
    		hash.remove(key1);
    	}
    }
    
    public void remove(TTamago t) {
    	remove(t.getName(),t.getModule());
    }
    
    /**
     * Permet de chercher et de parser un fichier que l'on a demande. La recherche se
     * fait exclusivement sur le disque dur et des chemins preciser.
     * <b>ATTENTION</b> : Utilisation interne de cette m?thode. 
     * @param name : Nom du fichier Tamago sans extension
     * @param namepackage : nom du paquetage de la classe recherche
     * @return Renvoie l'arbre de l'element si la recherche a reussi
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TamagoCCException
     */
    private TTamago searchAndParse(String name,String namepackage)
    	throws ParserConfigurationException,IOException,SAXException,TamagoCCException
    {
        Iterator<String> iterator = tamagoccpath.iterator();
        String key1 = namepackage+"."+name;
        while(iterator.hasNext()) {
            String path = (String)iterator.next();
            // TODO verifier ici
            // ancienne version : File file[] = searchFile(path,name+".xml");
            File file[] = searchFile(path,name);
            for(int i=0;i < file.length;++i) {
                TTamago tree = (TTamago)parser.parse(file[i].getAbsolutePath());
                String key2 = tree.getModule()+"."+tree.getName();
                hash.put(key2,tree);            
                if(key1.equals(key2))
                    return tree;
            }
        }
        throw new TamagoCCNotFoundFile("Unfound file for Package : "+namepackage+"\t Name : "+name);
    }
    
    private File[] searchFile(String directory,String filename) {
        File dir = new File(directory);
        File[] files = dir.listFiles(new TamagoCCFile(filename));
        return files;
    }

    private class TamagoCCFile implements FilenameFilter {
        String filename;
        
        TamagoCCFile(String filename) {
            this.filename = filename;
        }
        public boolean accept(File dir,String name) {
            if((name.startsWith(filename))&&(name.endsWith(".xml"))) {
                char c = name.charAt(filename.length());
                if((c== '.')||(c=='$'))
                    return true;
                else
                    return false;
            }
            return false;
        }
    }
}
