/**
 * 
 */
package tamagotest;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GIPercolator;
import tamagocc.percolation.TamagoCCPercolation;
import tamagotest.report.TamagoTestGeneratorReport;
import tamagotest.strategy.NominalStrategy;
import tamagotest.strategy.TamagoTestStrategy;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestContext {

	private TamagoTestStrategy teststrategy;
	private GTamago currentcontract;
	private ArrayList<TamagoTestScenario> scenarios;
	private TamagoTestScenario scenario;
	private String perconame;
	private TamagoCCPercolation percolation;
	private TamagoCCGeneratorTargetLanguageBuilder generator;
	private File dest;
	private TamagoTestGenerator gen;
	private GExpression dnfinvariant;
	private int count;
	private String component_under_test;
	private String businesscode;
	private String junitsuffix;
	
	
	/**
	 * 
	 */
	public TamagoTestContext() {
		perconame = "plugin";
		teststrategy = new NominalStrategy();
		gen = new TamagoTestGeneratorReport(this);
		dest = new File(".");
		dnfinvariant = new GINoContract();
		try {
			generator = (TamagoCCGeneratorTargetLanguageBuilder)Class.forName("tamagocc.javasource.TamagoCCJavaSourceBuilder").newInstance();
		}
		catch(Exception e) {
			throw new Error(e);
		}
		scenarios = new ArrayList<TamagoTestScenario>();
		count = 1;
		component_under_test = "";
		junitsuffix = "";
	}
	
	public void setFixPoint(TamagoTestTransSelector fixpoint) {
		teststrategy.setFixPoint(fixpoint);
	}
	
	
	public TamagoCCPercolation getPercolation() {
		return percolation;
	}
	
	void setContract(GTamago contract) throws TamagoCCException {
		currentcontract = contract;
		scenario = new TamagoTestScenario(perconame);
		percolation = TamagoCCPercolation.getPercolator(new GIPercolator(perconame), contract, new TamagoTestConverter(contract));
	}
	
	public GTamago getContract() {
		return currentcontract;
	}
	
	public TamagoTestTransSelector getFixPointDectector() {
		return teststrategy.getFixPoint();
	}
	
	public TamagoTestScenario getScenario() {
		return scenario;
	}
	
	/*public TamagoEnvironment getEnvironment() {
		return env;
	}*/

	public void setGenerator(String value) throws LineParserException {
		try {
			generator = (TamagoCCGeneratorTargetLanguageBuilder)Class.forName(value).newInstance();
		} catch (Exception e) {
			throw new LineParserException(e);
		}
	}
	
	public TamagoCCGeneratorTargetLanguageBuilder getGenerator() {
		return generator;
	}

	public File getDestination() {
		return dest;
	}
		
	public void setDestination(String pathname) throws LineParserException {
		File file = new File(pathname);
		if(!file.exists())
			file.mkdirs();
		if(!file.isDirectory())
			throw new LineParserException("path is not a directory : "+pathname);
		dest = file;
	}
	
	public void addScenario(TamagoTestScenario tts) {
		scenarios.add(tts);
	}
	public void setScenario(TamagoTestScenario tts) {
		scenario = tts;
	}

	public void setGenReport(String genreport) throws LineParserException {
		try {
			Class<?> cla = Class.forName(genreport);
			Constructor<?> constructor = cla.getConstructor(new Class[] { TamagoTestContext.class });
			gen = (TamagoTestGenerator) constructor.newInstance(new Object[] { this });
		}
		catch(Exception e) {
			throw new LineParserException(e);
		}
	}
	
	public TamagoTestGenerator getGenReport() {
		return gen;
	}

	
	public GExpression selectedDNFInvariant() {
		return dnfinvariant;
	}
	
	public void setSelectedDNFInvariant(GExpression expr) {
		dnfinvariant = expr;
	}

	public Iterable<TamagoTestScenario> getScenarios() {
		return scenarios;
	}

	public int getScenariosCount() {
		return scenarios.size();
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int c) {
		this.count = c;
	}
	
	public TamagoTestStrategy getStrategy() {
		return teststrategy;
	}

	public String getComponent() {
		return component_under_test;
	}
	
	public void setComponent(String component_under_test) {
		this.component_under_test = component_under_test;
	}

	public void setStrategy(TamagoTestStrategy strategy) {
		teststrategy = strategy;
	}

	public void setBusinessCode(String value) {
		businesscode = value;		
	}
	
	public String getBusinessCode() {
		return businesscode;
	}

	public String getJUnitSuffix() {
		return junitsuffix;
	}
	public void setJUnitSuffix(String value) {
		junitsuffix = value;
	}

	public void initialize() {
		teststrategy.initialize(this);		
	}
}
