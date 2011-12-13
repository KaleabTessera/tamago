/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorException;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.ast.api.AEntity;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGenerator;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.javasource.TamagoCCJavaStringSourceBuilder;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.parser.CDLGrammarProvider;
import tamagocc.parser.TamagoCCParserCDL;
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
		
		InputStream input  = getClass().getResourceAsStream("/CDLGrammarPop.txt");
		CDLGrammarProvider.setCDLGrammar(input);
		
		TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
		TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());
		try {
			
			pool.addTamagoCCPath(tamagoccpath);
			TTamago tamago = null;
			// --- nouveau code
			try {
				CDLEditorPlugin.getDefault().log("Debut de la compilation");
				FileInputStream fis = new FileInputStream(filecdl);
				DataInputStream dis = new DataInputStream(fis);
				byte[] b = new byte[dis.available()];
				dis.readFully(b);
				String str = new String(b);
				monitor.worked(1);
				monitor.subTask("Parsing the CDL file...");
				tamago = TamagoCCParserCDL.parse(new javapop.framework.input.StringParseInput(str)); 
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
			TamagoCCJavaStringSourceBuilder builder = new TamagoCCJavaStringSourceBuilder();
            TamagoCCGPool gpool = TamagoCCGPool.getDefaultTamagoCCGPool();
            GTamagoEntity entity =  gpool.getGTamagoEntity(tamago);
            TamagoCCGenerator generator = new TamagoCCGenerator(builder,entity,outputdir,true,!skel,false);
            monitor.worked(1);
			monitor.subTask("Generation of necessary file...");
			ArrayList<AEntity> entities = generator.generateAST();
			for (AEntity e : entities) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				builder.getTargetLanguage(e, stream);
				
				
			}
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
