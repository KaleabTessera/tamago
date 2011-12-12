/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import javapop.framework.DefaultParseContext;
import javapop.framework.ParseContext;
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
import tamagocc.parser.CDLGrammarProvider;
import tamagocc.parser.TamagoCCParserCDL;
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
			// on prepare la lecture
			InputStream input  = getClass().getResourceAsStream("/dist/cdlpluginlibs.jar/CDLGrammarPop.txt");
			CDLGrammarProvider.setCDLGrammar(input);
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println(input);
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			System.err.println("************************************************************************************");
			
			
			TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
			TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());

			pool.addTamagoCCPath(outputdir);
			TTamago tamago = null;
			// --- nouveau code
				CDLEditorPlugin.getDefault().log("Debut de la génération du fichier XML");
				FileInputStream fis = new FileInputStream(in);
				DataInputStream dis = new DataInputStream(fis);
				byte[] b = new byte[dis.available()];
				dis.readFully(b);
				String str = new String(b);
				monitor.worked(1);
				monitor.subTask("Parsing the CDL file...");
				tamago = TamagoCCParserCDL.parse(new javapop.framework.input.StringParseInput(str));
				CDLEditorPlugin.getDefault().log("Fin de la compilation avec succes");
				FileOutputStream fos = new FileOutputStream(output);
				monitor.worked(1);
				monitor.subTask("Generation of the abstract contract...");
				TamagoCCToXml.generateXMLFile(tamago, fos);
				TamagoCCLogger.infoln(1,"---------------------------------------");
				TamagoCCLogger.infoln(0,"Generation du contrat XML réussit!");
				monitor.worked(1);
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
