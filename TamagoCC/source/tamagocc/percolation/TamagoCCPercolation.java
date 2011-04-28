package tamagocc.percolation;


import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.impl.AIBodyMethodContainer;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIThrowException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GTamago;
import tamagocc.logger.TamagoCCLogger;

/**
 * 
 * Projet : TamagoCC.<br/>
 * 
 * Cette classe permet de Generer le code qui sera utilise par Tamago.
 * Pour les preconditions et les postconditions effectives.<br/>
 * Elle se sert aussi d'un environnement pour Verifier que la sortie.
 * 
 * Stage de Master de Recherche en Science et Technologie du Logiciel
 * Specialite Algorithmique-Programmation
 * Encadrant : Frederic Peschanski 
 * 
 * @author Hakim Belhaouari and Frederic Peschanski
 * 23 juin 2005 TamagoCC.java
 */

public abstract class TamagoCCPercolation {
	private static int nbpercovar = 0;
	
	protected static AIVariable genVariable() {
		return new AIVariable(new AIIdent("__tamago_var_perco_"+(nbpercovar++)));
	}
    
    
	private static Hashtable<String,Class<TamagoCCPercolation>> tablepercolator;
	
	public static void initialisation() throws TamagoCCException {
		tablepercolator = new Hashtable<String,Class<TamagoCCPercolation>>();
		// Ajout des percolators interne a la plate forme
		addPercolator("plugin","tamagocc.percolation.TamagoCCPercolatorplugin");
		addPercolator("exact","tamagocc.percolation.TamagoCCPercolatorexact");
	}
	
	public static String[] getAllPercolatorName() {
		String[] renvoie = new String[tablepercolator.size()];
		
		int i = 0;
		Enumeration<String> keys =  tablepercolator.keys();
		while(keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			renvoie[i] = key;
			i++;
		}
		return renvoie;
	}
	
	@SuppressWarnings("unchecked")
	public static void addPercolator(String name,String percname)
		throws TamagoCCException
	{
		try {
			Class<TamagoCCPercolation> classpercolator = (Class<TamagoCCPercolation>) Class.forName(percname);
			// TODO : verifier si c bien necessaire classpercolator.newInstance(); // on teste 
			tablepercolator.put(name,classpercolator);
		}
		catch(Exception exc) {
			throw new TamagoCCException("Unfind the percolator called : "+percname);
		}		
	}
		
    @SuppressWarnings("unchecked")
	public static TamagoCCPercolation getPercolator(GPercolator percolator,GTamago container,TamagoCCGVisitor generator)
    	throws TamagoCCException
    {
    	
    	TamagoCCLogger.println(3,"Search Percolator called : "+percolator.getName());
    	if(!tablepercolator.containsKey(percolator.getName()))
    		throw new TamagoCCException("Unknown percolator called : "+percolator.getName());
    	TamagoCCLogger.println(3,"The percolator has been finded");
    	Class classpercolator = (Class)tablepercolator.get(percolator.getName());
    	
    	try {
    		
    		Constructor constperco = classpercolator.getConstructors()[0];//classpercolator.getConstructor(new Class[] { GComponentContainer.class, generator.getClass() } );
    		TamagoCCPercolation perco = (TamagoCCPercolation)constperco.newInstance(new Object[] { container,generator });
    		return perco;
    	}catch(Exception ie) {
    		throw new TamagoCCException(ie);
    	}
    }
    
    
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    // =============================================================================================
    
    protected GTamago container;
    protected TamagoCCGVisitor generator;

    protected TamagoCCPercolation(GTamago container,TamagoCCGVisitor generator) {
    	this.container = container;
    	this.generator = generator;
    }
    
    
    
    
    public abstract String getName();
        
    public abstract void fulfillEffPre(GMethod method,AIBodyMethodContainer body, TamagoCCGPreExpressionVisitor traitement) throws TamagoCCException;
    public abstract void fulfillEffPost(GMethod method,AIBodyMethodContainer body, TamagoCCGPreExpressionVisitor traitement) throws TamagoCCException;
    
    public abstract GExpression genEffPreExpr(GMethod method) throws TamagoCCException;
    public abstract GExpression genEffPostExpr(GMethod method) throws TamagoCCException;
    
    
    protected AInstruction getFailPreconditionInstructions(AExpression[] args) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPrecondition");
    	AIThrowException throwexception = new AIThrowException(typexception);
    	for(int i=0;i < args.length;i++) {
    		throwexception.addArgument(args[i]);
    	}
    	return throwexception;
    }
    
    protected AInstruction getFailPreconditionInstructions(Collection<AExpression> args) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPrecondition");
    	AIThrowException throwexception = new AIThrowException(typexception,args);
    	return throwexception;
    }
    
    protected AInstruction getFailPreconditionInstructions(String message) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPrecondition");
    	AIThrowException throwexception = new AIThrowException(typexception);
    	throwexception.addArgument(new AIString(message));
    	return throwexception;
    }
    
    
    protected AInstruction getFailPostconditionInstructions(AExpression[] args) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPostcondition");
    	AIThrowException throwexception = new AIThrowException(typexception);
    	for(int i=0;i < args.length;i++) {
    		throwexception.addArgument(args[i]);
    	}
    	return throwexception;
    }
    
    protected AInstruction getFailPostconditionInstructions(Collection<AExpression> args) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPostcondition");
    	AIThrowException throwexception = new AIThrowException(typexception,args);
    	return throwexception;
    }
    
    protected AInstruction getFailPostconditionInstructions(String message) {
    	AIType typexception = (AIType) AIType.generateType("tamago.ext.tamagocc.TamagoCCFailPostcondition");
    	AIThrowException throwexception = new AIThrowException(typexception);
    	throwexception.addArgument(new AIString(message));
    	return throwexception;
    }
    
}