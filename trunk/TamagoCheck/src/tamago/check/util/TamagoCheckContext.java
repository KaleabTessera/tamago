/**
 * 
 */
package tamago.check.util;

import java.io.File;

import tamago.builder.TamagoBuilderFactory;
import tamago.check.report.GenericTamagoCheckReport;
import tamago.check.report.TamagoCheckReport;
import tamago.check.report.TamagoCheckReportOpt;
import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GIPercolator;
import tamagocc.percolation.TamagoCCPercolation;

/**
 * @author aliquando
 *
 */
public class TamagoCheckContext {

	private TamagoBuilderFactory strategy ;
	private GTamago currentcontract;
	private GenericTamagoCheckReport report;
	private String perconame;
	private TamagoCCPercolation percolation;
	private TamagoCCGeneratorTargetLanguageBuilder generator;
	private File dest;
	private GExpression dnfinvariant;
	private int maxsearch;
	
	/**
	 * 
	 */
	public TamagoCheckContext() {
		strategy = new TamagoCheckBuilder();
		report = new TamagoCheckReportOpt();
		perconame = "plugin";
		dest = new File(".");
		dnfinvariant = new GINoContract();
		maxsearch = -1;
		try {
			generator = (TamagoCCGeneratorTargetLanguageBuilder)Class.forName("tamagocc.javasource.TamagoCCJavaSourceBuilder").newInstance();
		}
		catch(Exception e) {
			throw new Error(e);
		} 
	}

	public TamagoCCPercolation getPercolation() {
		return percolation;
	}
	
	public void setContract(GTamago contract) throws TamagoCCException {
		currentcontract = contract;
		percolation = TamagoCCPercolation.getPercolator(new GIPercolator(perconame), contract, new TamagoCheckConverter(contract));
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

	public GTamago getContract() { 
		return currentcontract;
	}
	
	public TamagoBuilderFactory getTypeBuilderFactory() {
		return strategy;
	}
	
	public GExpression selectedDNFInvariant() {
		return dnfinvariant;
	}
	
	public void setSelectedDNFInvariant(GExpression expr) {
		dnfinvariant = expr;
	}

	public GenericTamagoCheckReport getReport() {
		return report;
	}

	public void setMaxDepth(int parseInt) {
		maxsearch = parseInt;		
	}

	public int getMaxsearch() {
		return maxsearch;
	}
	
	

}
