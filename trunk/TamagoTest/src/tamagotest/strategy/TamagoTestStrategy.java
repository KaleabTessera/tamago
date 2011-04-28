/**
 * 
 */
package tamagotest.strategy;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoBuilderFactory;
import tamago.builder.TamagoEnvironment;
import tamago.csp.TamagoCSP;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GType;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestTransSelector;

/**
 * Basic interface for specifying the test strategy.
 * @author Hakim Belhaouari
 */
public interface TamagoTestStrategy extends TamagoBuilderFactory {

	
	TamagoBuilder searchBuilder(String variable, GType type, GMethod method,TamagoEnvironment env) throws TamagoTestStrategyException;
	TamagoBuilder searchBuilder(String property, GType type, GTamago entity,TamagoEnvironment env) throws TamagoTestStrategyException;
	
	TamagoTestTransSelector getFixPoint();
	int getMaxLengthScenario();
	void setMaxLengthScenario(int value);
	
	boolean makeNewScenario();
	String getScenarioName();
	
	
	// ORDRE DES FONCTIONS IMPORTANTES pour les strategies
	// Prerequisite en premier
	// oracle second
	// precondition troisieme
	
	/**
	 * Allow to the strategy to modify the what will be generated
	 * @param expr
	 * @return
	 * @throws TamagoCCException 
	 */
	GExpression strategyForPrerequisite(GExpression expr) throws TamagoCCException;
	GExpression strategyForOracle(GExpression expr) throws TamagoCCException;
	// sert de filtrage dans l'exe de test
	GExpression strategyForPrecondition(GExpression expr) throws TamagoCCException;
	void setFixPoint(TamagoTestTransSelector fixpoint);
	
	
	// AJOUTE UNE FONCTION POUR FAIRE LA STRATEGIE UNIQUEMENT POUR LA PRECONDITION AFIN DE FILTRER
	// DYNAMIQUEMENT LES VALEURS IMPOSSIBLES (ON BUTTERA EN TOUCHE AVEC UN TRUE SI BESOIN)
	
	/*
	 * Permet de reinitialiser la strategie de test
	 *
	 */
	void reset();
	
	
	void initialize(TamagoTestContext ctx);
	String getName();
	
	// send CSP solutions to the strategie currently i dont know 
	// if the strategy must be modify the CSP
	// this one allow to the strategie to get CSPvalues
	void registerTestCase(TamagoCSP csp);
}
