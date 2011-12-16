package tamagocc;

import tamagocc.api.TTamagoEntity;
import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGenerator;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.lineparser.LineParser;
import tamagocc.util.lineparser.TamagoCCDest;
import tamagocc.util.lineparser.TamagoCCFiles;
import tamagocc.util.lineparser.TamagoCCLogFile;
import tamagocc.util.lineparser.TamagoCCLogLevel;
import tamagocc.util.lineparser.TamagoCCNoServiceInterface;
import tamagocc.util.lineparser.TamagoCCNoSkeleton;
import tamagocc.util.lineparser.TamagoCCPathCmd;
import tamagocc.util.lineparser.TamagoCCPercolator;
import tamagocc.util.lineparser.TamagoCCSetXSD;


/**
 * 
 * This class is the enty point of the Tamago Contract Compiler (TamagoCC).
 * It parse the command line, and set flags of all proposed options. 
 * Options are defined as follows:
 * <ul>
 * <li><b>--tamagoccpath</b> : list of paths to be searched for CDL files</li>
 * <li><b>-d,--destination</b> : output directory</li>
 * <li><b>--debug</b> : verbose level (silent = 0)</li>
 * <li><b>--logfile</b> : specify the logging file (stdout or stderr can be specify)</li>
 * <li><b>-h,--help</b> : print help in the console</li>
 * <li><b>--percolator</b> : specify the fullname class to add a percolator to the environment </li> 
 * <li><b>--generator</b> : specify the fullname for the factory of one target language
 * <li><b>-setxsd</b> : specify the url where to find the XSD file.
 * </ul>
 * @author Hakim Belhaouari
 */
public class TamagoCC {
    private static final String TAMAGOCC_VERSION = "1.0(alpha)";

    private static boolean noskeleton = false;
    private static boolean noserviceinterface = false;
    private static TamagoCCParser parser;
    private static TamagoCCPool pool;
    //private static String percolator = "tamagocc.percolation.TamagoCCPlugin";
    private static String generator = "tamagocc.javasource.TamagoCCJavaSourceBuilder";
   
    /**
     * Default address of the last XSD Schema
     */
    static String defaultXSD_LOCATION = "http://www-poleia.lip6.fr/~belhaouari/TamagoNS.xsd"; 
    
    
    private static String destdir = ".";
    private static LineParser cmdparser;
    private static TamagoCCFiles files;


	/**
	 * Gets the default Pool, which contains all syntaxic tree of all visited contract.
	 * @return Return the default pool of syntactic CDL file
	 */
    public static TamagoCCPool getTamagoCCPool() {
        return pool;
    }
    
    /**
     * Gets the default parser for Tamago-CDL file.
     * @return Return the default parser of CDL file
     */
    public static TamagoCCParser getTamagoCCParser() {
        return parser;
    }
       
    
    /**
     * This function initialize the environment for TamagoCC, with specified 
     * arguments.
     * @param args : corresponds to the command line
     */
    public static void initialize(String args[]) throws TamagoCCException,LineParserException {
        //super();
        environnement_initialisation();
        if(args.length == 0)
            TamagoCCLogger.println(0,cmdparser.toString());
        cmdparser.parse(args);
    }
        
    /**
     * This method initialize all element in the framework
     *
     */
    private static void environnement_initialisation() throws TamagoCCException,LineParserException {
    	// initialisation des membres 
        parser = TamagoCCParser.getDefaultParser();
        pool = TamagoCCPool.getDefaultPool();
        TamagoCCPercolation.initialisation();
        cmdparser = new LineParser("tamagocc","TamagoCC : Tamago Contract Compiler\nAuthors : Hakim Belhaouari <hakim.belhaouari@lip6.fr>\nVersion : "+TAMAGOCC_VERSION);
        files = new TamagoCCFiles();
        cmdparser.addSpec(new TamagoCCDest());
        cmdparser.addSpec(new tamagocc.util.lineparser.TamagoCCGenerator());
        cmdparser.addSpec(new TamagoCCLogFile());
        cmdparser.addSpec(new TamagoCCLogLevel());
        cmdparser.addSpec(new TamagoCCPathCmd());
        cmdparser.addSpec(new TamagoCCPercolator());
        cmdparser.addSpec(new TamagoCCSetXSD());
        cmdparser.addSpec(new TamagoCCNoSkeleton());
        cmdparser.addSpec(new TamagoCCNoServiceInterface());
        cmdparser.setDefaultSpec(files);
    }
    
    
    /**
     * This method begin the generation of contract code for the specified target language
     * of all file specified in the command-line.
     */
    public static void run() {
        try {
                        
            for (String filename : files.getItems())
            {
                try {
                    TamagoCCLogger.infoln(1,"Build Abstract Syntax Tree of '"+filename+"' ...");
                    TTamagoEntity c = parser.parse(filename);
                    TamagoCCLogger.infoln(1,"Build [OK].");
                    generate(c);
                    /*
                    TamagoCCLogger.infoln(1,"Generation of Tamago file for '"+filename+"'...");

                    TamagoCCGeneratorTargetLanguageBuilder builder = (TamagoCCGeneratorTargetLanguageBuilder)Class.forName(generator).newInstance();
                    GTamagoEntity entity =  TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(c);//TamagoCCConverter.generateGTamagoEntity(c);
                                      
                    TamagoCCGenerator generator = new TamagoCCGenerator(builder,entity,getOutputDir(),true,flagNoSkeleton(),flagNoServiceInterface());
                    
                    generator.generate();
                    
                    TamagoCCLogger.infoln(1,"End generation with Success");
                    TamagoCCLogger.infoln(1,"--------------------------------");*/
                }
                catch(Exception e) {
                    TamagoCCLogger.infoln(e);
                    System.exit(2);
                }
            }
        }
        catch(Exception cnfe) {
            TamagoCCLogger.infoln(cnfe);
            System.exit(3);
        }
    }
   
    public static void generate(TTamagoEntity c) throws InstantiationException, IllegalAccessException, ClassNotFoundException, TamagoCCException{
    	TamagoCCLogger.infoln(1,"Generation of Tamago file for '"+c.getName()+"'...");

        TamagoCCGeneratorTargetLanguageBuilder builder = (TamagoCCGeneratorTargetLanguageBuilder)Class.forName(generator).newInstance();
        GTamagoEntity entity =  TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(c);//TamagoCCConverter.generateGTamagoEntity(c);
                          
        TamagoCCGenerator generator = new TamagoCCGenerator(builder,entity,getOutputDir(),true,flagNoSkeleton(),flagNoServiceInterface());
        
        generator.generate();
        
        TamagoCCLogger.infoln(1,"End generation with Success");
        TamagoCCLogger.infoln(1,"--------------------------------");    	
    }
    
    /**
     * Allow to a client to get the specified output directory for generated file
     * @return the output directory specified in the command line
     */
    public static String getOutputDir() {
    	return destdir;
    }
    
    /**
     * Allow to a client to specify a particular output directory.
     * @param destdir : an existed directory
     */
    public static void setOutputDir(String destdir) {
    	TamagoCC.destdir = destdir;
    }
    
    /**
     * Specify the (full) name of the generator, for converting byte-code present in Tamago.
     * @param gen : specify the name of a generator
     */
    public static void setGenerator(String gen) {
    	TamagoCC.generator = gen;
    }
    
    /**
     * Indicates if TamagoCC must generate skeleton of component
     * @return <b>true</b> if the compiler must generate skeleton, else <b>false</b> 
     */
	public static boolean flagNoSkeleton() {
		return noskeleton; 
	}
	
	/**
	 * Allow to the user to specify if TamagoCC must generate skeleton of component/composite.
	 * @param flag : set <b>true</b> do not generate skeleton, else <b>false</b>.
	 */
	public static void setFlagNoSkeleton(boolean flag) {
		noskeleton = flag;
	}

	/**
	 * Indicates if TamagoCC generate interface for service
	 * @return <b>true</b> if the compiler must generate interfaces, else <b>false</b>
	 */
	public static boolean flagNoServiceInterface() {
		return noserviceinterface;
	}

	/**
	 * Specify to TamagoCC does not generate interface of services.
	 * @param flag
	 */
	public static void setFlagNoServiceInterface(boolean flag) {
		noserviceinterface = flag;
	}
	
}
