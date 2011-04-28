/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
import tamagocc.generic.TamagoCCGPool;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.TamagoCCToXml;


/**
 * @author Hakim Belhaouari
 *
 */
public class CompileCDL implements IRunnableWithProgress {

	private File input;
	private IProgressMonitor monitor;

	/**
	 * @param file 
	 * 
	 */
	public CompileCDL(File file) {
		this.input = file;
	}

	/**
	 * @throws CDLEditorException 
	 * 
	 */
	public void run()  {
		try {
			monitor.subTask("Initialize environment...");
			String in = input.getAbsolutePath();
			String name = input.getName();
			String outputdir = input.getParentFile().getParent()+File.separator+"xmls";
			String output = outputdir+File.separator+name.substring(0,name.lastIndexOf('.'))+".xml";
			CDLEditorPlugin.getDefault().log("Contrat:"+input.getAbsolutePath());
			CDLEditorPlugin.getDefault().log("Sortie:"+output);
			TamagoCCPool pool = TamagoCCPool.getDefaultPool();
			TamagoCCParser parser = TamagoCCParser.getDefaultParser();
			TamagoCCPercolation.initialisation();
			parser.setTamagoCCPool(pool);
			

			TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
			TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());

			pool.addTamagoCCPath(outputdir);
			TTamago tamago = null;
			// --- nouveau code
			CDLGrammar cdlgrammar = new CDLGrammar();
				CDLEditorPlugin.getDefault().log("Debut de la génération du fichier XML");
				FileInputStream fis = new FileInputStream(in);
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
					FileOutputStream fos = new FileOutputStream(output);
					monitor.worked(1);
					monitor.subTask("Generation of the abstract contract...");
					TamagoCCToXml.generateXMLFile(tamago, fos);
					TamagoCCLogger.infoln(1,"---------------------------------------");
					TamagoCCLogger.infoln(0,"Generation du contrat XML réussit!");
					monitor.worked(1);
				}
		}
		catch(Throwable e) {
			CDLEditorPlugin.getDefault().log("Fin de la compilation sur erreur");
			CDLEditorPlugin.getDefault().log(e.getMessage());
			e.printStackTrace(new PrintStream(CDLEditorPlugin.getDefault().getOutputStreamConsole()));
			IWorkbench workbench = PlatformUI.getWorkbench();
			CDLEditorPlugin.getDefault().showConsole();
			MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation du contrat AST echoue", "Generation of the XML contract fail with the following message:\n"+e.getMessage());
		}
	
}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask("Production of the abstract contract...", 3);
		this.monitor = monitor;
		try {
			run();
		}
		finally {
			monitor.done();
		}
	}

}
