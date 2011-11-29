/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

import javapop.framework.ParseResult;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorException;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.wizards.SettingsTamagoTest.SettingsStrategy;

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagotest.TamagoTest;
import tamagotest.TamagoTestContext;
import tamagotest.fixpoint.MaxDepth;
import tamagotest.strategy.BadScenarioStrategy;
import tamagotest.strategy.BoundedStrategy;
import tamagotest.strategy.CoverageStrategy;
import tamagotest.strategy.NominalStrategy;
import tamagotest.strategy.UnboundBehStrategy;
import tamagotest.strategy.UnboundStrategy;
import tamagotest.util.TamagoTestUI;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLTest implements IRunnableWithProgress, TamagoTestUI {

	private File filecdl;
	private String outputdir;
	private String tamagoccpath;
	private int quantity;
	private int scenariosize;
	private String component;
	private boolean isbusiness;
	private SettingsStrategy strategy;
	
	private transient IProgressMonitor monitor;
	private transient int pos_step;
	private int pos_quantity;


	public String toString() {
		StringBuilder sb = new StringBuilder("Launch Tamago-Test on ");
		sb.append(filecdl. getName());
		sb.append(" ["+quantity);
		sb.append("; "+scenariosize);
		sb.append("; "+component);
		sb.append("; "+isbusiness);
		sb.append("; "+strategy);
		sb.append("]");
		return sb.toString();
	}
	
	
	/**
	 * @param b 
	 * @param file 
	 * @throws CDLEditorException 
	 * 
	 */
	public CDLTest(File filecdl, String component, boolean isBusiness, int quantity, int scenariosize,SettingsStrategy strategy) throws CDLEditorException {
		this.filecdl = filecdl;
		//String name = filecdl.getName();
		outputdir = filecdl.getParentFile().getParent()+File.separator+"test";//+File.separator+name.substring(0,name.lastIndexOf('.'))+".xml";

		tamagoccpath = filecdl.getParentFile().getParent()+File.separator+"xmls";

		this.quantity = quantity;
		this.scenariosize = scenariosize;
		this.component = component;
		this.isbusiness = isBusiness;
		this.strategy = strategy;
		//if(!filexml.exists())
		//	throw new CDLEditorException("Inaccessible output location: "+output+"!");
	}


	public void run(){
		CDLEditorPlugin.getDefault().showConsole();
		
		try {
			monitor.subTask("Initialize system...");
			
			TamagoCCPool pool = TamagoCCPool.getDefaultPool();
			TamagoCCParser parser = TamagoCCParser.getDefaultParser();
			TamagoCCPercolation.initialisation();
			parser.setTamagoCCPool(pool);

			TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
			TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());


			pool.addTamagoCCPath(tamagoccpath);
			TTamago tamago = null;
			// --- nouveau code
			CDLGrammar cdlgrammar = new CDLGrammar();
			
			try {
				CDLEditorPlugin.getDefault().log("Generation of the abstract contract...");
				FileInputStream fis = new FileInputStream(filecdl);
				DataInputStream dis = new DataInputStream(fis);
				byte[] b = new byte[dis.available()];
				dis.readFully(b);
				String str = new String(b);
				ParseResult<TTamago> result = cdlgrammar.parse(new javapop.framework.input.StringParseInput(str));
				monitor.worked(1);
				monitor.subTask("Parse file...");
				if(result.isError()) {
					CDLEditorPlugin.getDefault().log("End compilation with a SYNTAX ERROR.");
					CDLEditorPlugin.getDefault().log(result.getErrorMessage());
					CDLEditorPlugin.getDefault().log(result.getDetailedErrorMessage());
					CDLEditorPlugin.getDefault().showConsole();
					return;
				}
				else if(result.isNull()) {
					CDLEditorPlugin.getDefault().log("End compilation with a empty node");
					return;
				}
				else {
					CDLEditorPlugin.getDefault().log("End compilation with success");
					tamago = result.getResult();
					pool.remove(tamago);
					TamagoCCGPool.getDefaultTamagoCCGPool().remove(tamago.getName(), tamago.getModule());
					TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), tamago);

				}
				//				CDLEditorPlugin.getDefault().log(node.toString());
			}
			catch(Exception e) {
				CDLEditorPlugin.getDefault().log("End compilation with an exception");
				IWorkbench workbench = PlatformUI.getWorkbench();
				MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "AST Generation failed", e.getMessage());
				//throw new CDLEditorException(e);
				return;
			}
			TamagoTest.resetContext();
			TamagoTestContext ctx =  TamagoTest.getContext();
			ctx.setDestination(outputdir);
			ctx.setCount(quantity);
			MaxDepth.MAX_DEPTH = scenariosize;

			switch(strategy) {
			case NOMINAL: {
				NominalStrategy strategy = new NominalStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			case LIMITTEST: {
				BoundedStrategy strategy = new BoundedStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			case OUTBOUND: {
				UnboundStrategy strategy = new UnboundStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			case OUTBOUNDBEH: {
				UnboundBehStrategy strategy = new UnboundBehStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			case SCENARIO: {
				BadScenarioStrategy strategy = new BadScenarioStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			case ALLTRANSITIONS: {
				CoverageStrategy strategy = new CoverageStrategy();
				ctx.setStrategy(strategy);
				strategy.initialize(ctx);
				break;
			}
			}
			if(component != null && component.length() > 0) {
				if(isbusiness)
					ctx.setBusinessCode(component);
				else
					ctx.setComponent(component);
			}
			monitor.worked(1);
			monitor.subTask("Tamago-Test is animating the contract...");
			TamagoTest.ui = this;
			TamagoTest.generateTestFile(tamago);
			
			monitor.worked(1);
			
			TamagoCCLogger.infoln(1,"---------------------------------------");
			TamagoCCLogger.infoln(0,"End test generation with success");
		}
		catch(Exception e) {
			TamagoCCLogger.infoln(e);
			TamagoCCLogger.infoln(0,"Test generation failed!!!!");
			IWorkbench workbench = PlatformUI.getWorkbench();
			MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Test generation failed!", e.getMessage());

		}
	}


	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		try {
			this.monitor = monitor;
			pos_quantity = 0;
			pos_step = 0;
			monitor.beginTask(toString(),quantity*scenariosize+ 5);
			run();
		} finally {
			monitor.done();
		}
	}


	@Override
	public void beginScenario() {
		
		
	}


	@Override
	public void endScenario() {
		pos_quantity++; 
		monitor.subTask("Tamago-Test is animating the contract ("+pos_quantity+"/"+quantity+")...");
	}


	@Override
	public void finishStep() {
		pos_step++;
		monitor.worked(1);
	}


	@Override
	public boolean canContinue() {
		try {return !monitor.isCanceled(); }
		catch(Exception e) {
			return true;
		}
	}


	@Override
	public void beginWriteJUnit() {
		monitor.subTask("Tamago-Test is writing the JUnit file...");
		monitor.worked(1);
	}


	@Override
	public void beginWriteTest() {
		monitor.subTask("Tamago-Test is writing the abstract harness...");
		monitor.worked(1);
	}


	@Override
	public void endWriteJUnit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void endWriteTest() {
		// TODO Auto-generated method stub
		
	}

}
