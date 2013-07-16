/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IFileEditorInput;
import org.tamago.eclipse.cdl.CDLEditorException;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.wizards.SettingsTamagoTest.SettingsStrategy;

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.compiler.TamagoCDLLexer;
import tamagocc.compiler.TamagoCDLParser;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamago;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagotest.TamagoTest;
import tamagotest.TamagoTestContext;
import tamagotest.fixpoint.MaxDepth;
import tamagotest.report.TamagoTestGeneratorJUnit;
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
public class CDLTest  extends CDLTask implements TamagoTestUI {

	private IFileEditorInput input;
	private int quantity;
	private int scenariosize;
	private String component;
	private boolean isbusiness;
	private SettingsStrategy strategy;
	
	private transient int pos_step;
	private int pos_quantity;


	public String toString() {
		StringBuilder sb = new StringBuilder("Launch Tamago-Test on ");
		sb.append(input);
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
	public CDLTest(IFileEditorInput fileinput, String component, boolean isBusiness, int quantity, int scenariosize,SettingsStrategy strategy) throws CDLEditorException {
		this.input = fileinput;
		this.quantity = quantity;
		this.scenariosize = scenariosize;
		this.component = component;
		this.isbusiness = isBusiness;
		this.strategy = strategy;
		
	}


	public void run(){
		CDLEditorPlugin.getDefault().showConsole();
		IProject project = retreveProject(input);
		try {
			monitor.subTask("Initialize system...");
			
			prepareEnv(project);
			
			
			TTamago tamago = parseInputCDLFile(input);
			GTamago gtamago = (GTamago) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(tamago);
			// --- nouveau code
			
			
			TamagoTest.resetContext();
			TamagoTestContext ctx =  TamagoTest.getContext();
			ctx.setContract(gtamago);
			ctx.setGenReport(new TamagoTestGeneratorJUnit(ctx));
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
			
			PipedOutputStream pos = new PipedOutputStream();
			final PipedInputStream pis = new PipedInputStream(pos);
			
			ctx.setOutputStream(pos);
			
			final IFile outputfile = searchAndOrCreat(project, "test", tamago.getModule(), "Test"+tamago.getName() + ctx.getJUnitSuffix()+".java");
			
			new Thread() {
				public void run() {
					try {
						if(outputfile.exists())
							outputfile.setContents(pis, true, false, monitor);
						else
							outputfile.create(pis, true, monitor);
					}
					catch(Throwable e) {
						CDLEditorPlugin.getDefault().log("Error:\n "+e);
					}
				};
			}.start();
			
			TamagoTest.generateTestFile(gtamago);
			pos.flush();
			pos.close();
			
			monitor.worked(1);
			
			TamagoCCLogger.infoln(1,"---------------------------------------");
			TamagoCCLogger.infoln(0,"End test generation with success");
		}
		catch(Exception e) {
			TamagoCCLogger.infoln(e);
			TamagoCCLogger.infoln(0,"Test generation failed!!!!");
			//IWorkbench workbench = PlatformUI.getWorkbench();
			//MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Test generation failed!", e.getMessage());
		}
		finally {
			try {
				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
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
