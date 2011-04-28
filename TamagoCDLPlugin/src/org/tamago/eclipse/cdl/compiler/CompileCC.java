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

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGenerator;
import tamagocc.generator.TamagoCCGeneratorTargetLanguageBuilder;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.javasource.TamagoCCJavaSourceBuilder;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;

/**
 * @author Hakim Belhaouari
 *
 */
public class CompileCC implements IRunnableWithProgress {

	private File filecdl;
	private boolean skel;
	private String outputdir;
	private String tamagoccpath;
	
	private transient IProgressMonitor monitor;


	/**
	 * @param b 
	 * @param file 
	 * @throws CDLEditorException 
	 * 
	 */
	public CompileCC(File filecdl, boolean b) throws CDLEditorException {
		this.filecdl = filecdl;
		String name = filecdl.getName();
		outputdir = filecdl.getParentFile().getParent()+File.separator+"gen";//+File.separator+name.substring(0,name.lastIndexOf('.'))+".xml";
		
		tamagoccpath = filecdl.getParentFile().getParent()+File.separator+"xmls";

		skel = b;
		//if(!filexml.exists())
		//	throw new CDLEditorException("Inaccessible output location: "+output+"!");
	}


	public void run() {
		monitor.subTask("Initialize environment...");
		TamagoCCPool pool = TamagoCCPool.getDefaultPool();
		TamagoCCParser parser = TamagoCCParser.getDefaultParser();
		try {
			TamagoCCPercolation.initialisation();
		} catch (TamagoCCException e1) {
			CDLEditorPlugin.getDefault().log("ERROR during initialisation!!");
			e1.printStackTrace();
			IWorkbench workbench = PlatformUI.getWorkbench();
			MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation du contrat XML echoue", e1.getMessage());
			CDLEditorPlugin.getDefault().showConsole();
			return;
		}
		parser.setTamagoCCPool(pool);
		
		TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
		TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());
		try {
			
			pool.addTamagoCCPath(tamagoccpath);
			TTamago tamago = null;
			// --- nouveau code
			CDLGrammar cdlgrammar = new CDLGrammar();
			try {
				CDLEditorPlugin.getDefault().log("Debut de la compilation");
				FileInputStream fis = new FileInputStream(filecdl);
				DataInputStream dis = new DataInputStream(fis);
				byte[] b = new byte[dis.available()];
				dis.readFully(b);
				String str = new String(b);
				monitor.worked(1);
				monitor.subTask("Parsing the CDL file...");
				ParseResult<TTamago> result = cdlgrammar.parse(new javapop.framework.input.StringParseInput(str));
				if(result.isError()) {
					CDLEditorPlugin.getDefault().log("Fin de la compilation sur Erreur de SYNTAXE");
					CDLEditorPlugin.getDefault().log(result.getErrorMessage());
					CDLEditorPlugin.getDefault().log(result.getDetailedErrorMessage());
					CDLEditorPlugin.getDefault().showConsole();
					return;
				}
				else if(result.isNull()) {
					CDLEditorPlugin.getDefault().log("Fin de la compilation sur un noeud NULL");
					CDLEditorPlugin.getDefault().showConsole();
					return;
				}
				else {
					CDLEditorPlugin.getDefault().log("Fin de la compilation avec succes");
					tamago = result.getResult();
					pool.remove(tamago);
					TamagoCCGPool.getDefaultTamagoCCGPool().remove(tamago.getName(), tamago.getModule());
					TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), tamago);
					
				}
//				CDLEditorPlugin.getDefault().log(node.toString());
			}
			catch(Exception e) {
				CDLEditorPlugin.getDefault().log("Fin de la compilation sur erreur");
				IWorkbench workbench = PlatformUI.getWorkbench();
				MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation du contrat XML echoue", e.getMessage());
				CDLEditorPlugin.getDefault().showConsole();
				return;
				//throw new CDLEditorException(e);
			}
			monitor.worked(1);
			monitor.subTask("Analyze the parsed contract...");
			TamagoCCGeneratorTargetLanguageBuilder builder = new TamagoCCJavaSourceBuilder();
            TamagoCCGPool gpool = TamagoCCGPool.getDefaultTamagoCCGPool();
            GTamagoEntity entity =  gpool.getGTamagoEntity(tamago);
            TamagoCCGenerator generator = new TamagoCCGenerator(builder,entity,outputdir,true,!skel,false);
            
            monitor.worked(1);
			monitor.subTask("Generation of necessary file...");
			generator.generate();
            
            TamagoCCLogger.infoln(1,"---------------------------------------");
            TamagoCCLogger.infoln(0,"Generation des conteneurs reussit!");
            monitor.worked(1);
        }
        catch(Exception e) {
            TamagoCCLogger.infoln(e);
            TamagoCCLogger.infoln(0,"Generation des conteneurs echoue!!!!");
            IWorkbench workbench = PlatformUI.getWorkbench();
			MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation des conteneurs echoues", e.getMessage());
			CDLEditorPlugin.getDefault().showConsole();
        }
	}


	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask("Launch Tamago-CC on the contract...", 4);
		this.monitor = monitor;
		try {
			run();
		}
		finally {
			monitor.done();
		}		
	}

}
