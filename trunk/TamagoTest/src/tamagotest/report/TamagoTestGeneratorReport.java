/**
 * 
 */
package tamagotest.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import tamagocc.ast.api.AInstruction;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.util.TamagoCCIndentator;
import tamagotest.TamagoTest;
import tamagotest.TamagoTestCase;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestException;
import tamagotest.TamagoTestGenerator;
import tamagotest.TamagoTestScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestGeneratorReport extends TamagoTestGenerator {

	
	// METTRE ICI L'AUGMENTATION
	// DU XML AVEC LE ATPREORACLE les informations atpre pour les oracles
	
	
	private TamagoCCIndentator indent;
	private ASTtoXml asttoxml;
	/**
	 * 
	 */
	public TamagoTestGeneratorReport(TamagoTestContext ctx) {
		super(ctx);
	}

	public void prepare() throws TamagoTestException {
		
	}

	public void write() throws TamagoTestException, IOException {
		try {
			FileOutputStream fos = new FileOutputStream(ctx.getDestination() + File.separator+ ctx.getContract().getName()+"_harness.xml");
			writeCore(fos);
		}
		catch(TamagoCCException tcce) {
			throw new TamagoTestException(tcce);
		}
	}

	private void genScenario(TamagoTestScenario scenario) throws TamagoCCException {
		long tmp = 0;
		for (TamagoTestCase cas : scenario) {
			indent.print("<testcase num=\"");
			indent.print(""+tmp);
			indent.println("\" >");
			indent.shiftright();
			
			indent.print("<call name=\"");
			indent.print(cas.method());
			indent.println("\" />");
			
			GMethod gmethod = ctx.getContract().getMethod(cas.method());
			if(cas.hasResult()) {
				indent.print("<result>");
				indent.shiftright();
				indent.println("<value>");
				indent.shiftright();
				cas.getResultExpr().visit(asttoxml);
				indent.shiftleft();
				indent.println("</value>");
				if(cas.getResultPreInst().getInstructionType() != AInstruction.NOINSTRUCTION) {
					indent.println("<initial>");
					indent.shiftright();
					cas.getResultPreInst().visit(asttoxml);
					indent.shiftleft();
					indent.println("</initial>");
				}
				if(cas.getResultPostInst().getInstructionType() != AInstruction.NOINSTRUCTION) {
					indent.println("<postinitial>");
					indent.shiftright();
					cas.getResultPostInst().visit(asttoxml);
					indent.shiftleft();
					indent.println("</postinitial>");
				}
				indent.newline();
				indent.shiftleft();
				indent.println("</result>");
			}
			
			
			Iterator<GParameter> params = gmethod.getParameters();
			indent.println("<datatest>");
			indent.shiftright();
			while(params.hasNext()) {
				String arg = params.next().getName();
				if(cas.contains(arg)) {
					indent.print("<parameter name=\"");
					indent.print(arg);
					indent.println("\" >");
					indent.shiftright();
					indent.println("<value>");
					indent.shiftright();
					cas.get(arg).l().visit(asttoxml);
					indent.shiftleft();
					indent.println("</value>");
					if(cas.get(arg).c().getInstructionType() != AInstruction.NOINSTRUCTION) {
						indent.println("<initial>");
						indent.shiftright();
						cas.get(arg).c().visit(asttoxml);
						indent.shiftleft();
						indent.println("</initial>");
					}
					if(cas.get(arg).r().getInstructionType() != AInstruction.NOINSTRUCTION) {
						indent.println("<postinitial>");
						indent.shiftright();
						cas.get(arg).r().visit(asttoxml);
						indent.shiftleft();
						indent.println("</postinitial>");
					}
					indent.newline();
					indent.shiftleft();
					indent.println("</parameter>");
				}
				else {
					indent.print("<unknown-parameter name=\"");
					indent.print(arg);
					indent.print("\" map=\"");
					indent.print(TamagoTestCase.map());
					indent.println("\" />");
				}
			}
			indent.shiftleft();
			indent.println("</datatest>");

			// precondition
			indent.println("<precondition>");
			indent.shiftright();
			cas.getPrecondition().visit(asttoxml);
			indent.shiftleft();
			indent.println("</precondition>");
			indent.println("<init-precondition>");
			indent.shiftright();
			cas.getInitPrecondition().visit(asttoxml);
			indent.shiftleft();
			indent.println("</init-precondition>");
			
			// oracle
			indent.println("<pre-oracle>");
			indent.shiftright();
			cas.getAtPreOracle().visit(asttoxml);
			indent.shiftleft();
			indent.println("</pre-oracle>");
			indent.println("<init-oracle>");
			indent.shiftright();
			cas.getInitOracle().visit(asttoxml);
			indent.shiftleft();
			indent.println("</init-oracle>");
			indent.println("<oracle>");
			indent.shiftright();
			cas.getOracle().visit(asttoxml);
			indent.shiftleft();
			indent.println("</oracle>");
			indent.shiftleft();
			indent.println("</testcase>");
			tmp++;
		}
	}
	private void writeCore(OutputStream os) throws TamagoCCException {
		indent = new TamagoCCIndentator(os);
		asttoxml = new ASTtoXml(indent);
	
		
		indent.println("<?xml version=\"1.0\" ?>");
		indent.println("<tamagotest>");
		indent.shiftright();
		
		indent.print("<version major=\"");
		indent.print(""+TamagoTest.VERSION_MAJOR);
		indent.print("\" minor=\"");
		indent.println(""+TamagoTest.VERSION_MINOR+"\" />");			
		
		indent.print("<contract type=\"");
		switch(ctx.getContract().getCategoryEntity()) {
		case GTamagoEntity.TAMAGO_SERVICE: indent.print("service"); break;
		case GTamagoEntity.TAMAGO_COMPONENT: indent.print("component"); break;
		case GTamagoEntity.TAMAGO_COMPOSITE: indent.print("composite"); break;
		}
		indent.print("\" name=\"");
		indent.print(ctx.getContract().getName());
		indent.print("\" module=\"");
		indent.print(ctx.getContract().getModule().getFullModule());
		indent.println("\" />");
		
		indent.print("<percolator name=\"");
		indent.print(ctx.getPercolation().getName());
		indent.println("\" />");
		
		indent.print("<scenarios count=\"");
		indent.print(""+ctx.getScenariosCount());
		indent.println("\" />");
		
		for (TamagoTestScenario tts : ctx.getScenarios()) {
			indent.print("<scenario size=\"");
			indent.print(""+tts.size());
			indent.println("\" >");
			indent.shiftright();
			genScenario(tts);
			indent.shiftleft();
			indent.println("</scenario>");
		}
		indent.shiftleft();
		indent.println("</tamagotest>");
	}
	
	public void write(OutputStream os) throws TamagoTestException, IOException {
		try {
			writeCore(os);			
		}
		catch(TamagoCCException tcce) {
			throw new TamagoTestException(tcce);
		}
	}

	
}
