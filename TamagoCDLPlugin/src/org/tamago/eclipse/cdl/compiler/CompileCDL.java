/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IFileEditorInput;
import org.tamago.eclipse.cdl.CDLEditorException;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.compiler.TamagoCDLLexer;
import tamagocc.compiler.TamagoCDLParser;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.TamagoCCToXml;


/**
 * @author Hakim Belhaouari
 *
 */
public class CompileCDL implements IRunnableWithProgress {

	private IFileEditorInput input;
	private IProgressMonitor monitor;

	
	public CompileCDL(IFileEditorInput fileinput) {
		this.input = fileinput;
	}
	
	/**
	 * @throws CDLEditorException 
	 * 
	 */
	public void run()  {
		//IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject myProject = input.getFile().getProject();// myWorkspaceRoot.getProject();
		if(myProject.exists() && !myProject.isOpen())
			try {
				myProject.open(monitor);
			} catch (CoreException e1) {
				e1.printStackTrace();
				return;
			}
		
		try {
			monitor.subTask("Initialize environment...");
			IFile ifile = input.getFile();
			File file = ifile.getFullPath().toFile();
			String name = file.getName();
			
			IFolder xmlsfolder = myProject.getFolder("xmls");
			if(!xmlsfolder.exists())
				xmlsfolder.create(true, true, monitor);
			
			TamagoCCPool pool = TamagoCCPool.getDefaultPool();
			TamagoCCParser xmlparser = TamagoCCParser.getDefaultParser();
			TamagoCCPercolation.initialisation();
			xmlparser.setTamagoCCPool(pool);
			// on prepare la lecture
			//InputStream input  = getClass().getResourceAsStream("/CDLGrammarPop.txt");
			//CDLGrammarProvider.setCDLGrammar(input);

			
			TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
			TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());
			
			TamagoCCLogger.println(3, "Project name: "+myProject.getName());
			
			
			pool.addTamagoCCPath(xmlsfolder.getFullPath().toOSString());
			TTamago tamago = null;
			// --- nouveau code
				CDLEditorPlugin.getDefault().log("Debut de la génération du fichier XML");
				monitor.worked(1);
				monitor.subTask("Parsing the CDL file...");
				
				ANTLRInputStream stream = new ANTLRInputStream(ifile.getContents());
				TamagoCDLLexer lexer = new TamagoCDLLexer(stream);
				CommonTokenStream token = new CommonTokenStream(lexer);
				TamagoCDLParser parser = new TamagoCDLParser(token);
				TamagoCDLParser.tamagoEntity_return res;
				try {
					res = parser.tamagoEntity();
					if(parser.getNumberOfSyntaxErrors() == 0 && res != null && res.value != null) {
						tamago = (TTamago)res.value;
						TamagoCCLogger.println(1,"compilation success");
						if(tamago instanceof TTamago) {
							TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), (TTamago)tamago);
						}
						else
							TamagoCCLogger.println(1, "Assembly not yet supported!");
					}
					else {
						throw new Exception("Compilation failed!");
					}
				} catch (RecognitionException e) {
					TamagoCCLogger.println(1, e.getMessage());
					throw e;
				}
				catch(RuntimeException e) {
					TamagoCCLogger.println(1, e.getMessage());
					throw e;
				}
								
				String contractxmlname = name.substring(0,name.lastIndexOf('.'))+".xml";
				final IFile outputfile = xmlsfolder.getFile(contractxmlname);
				
				//String outputdir = file.getParentFile().getParent()+File.separator+"xmls";
				//String output = outputdir+File.separator+name.substring(0,name.lastIndexOf('.'))+".xml";
				
				CDLEditorPlugin.getDefault().log("Contract: "+file.toString());
				CDLEditorPlugin.getDefault().log("Output: "+outputfile.toString());
				
				
				CDLEditorPlugin.getDefault().log("Fin de la compilation avec succes");
				monitor.subTask("Generation of the abstract contract...");
				monitor.worked(1);
				
				PipedOutputStream pos = new PipedOutputStream();
				final PipedInputStream pis = new PipedInputStream(pos);
				
				new Thread() {
					@Override
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
					}
				}.start();
				
				
				TamagoCCToXml.generateXMLFile(tamago, pos);
				pos.flush();
				pos.close();
				
				TamagoCCLogger.infoln(1,"---------------------------------------");
				TamagoCCLogger.infoln(0,"Generation du contrat XML réussit!");
				monitor.worked(1);
		}
		catch(NullPointerException e) {
			CDLEditorPlugin.getDefault().log("Fin de la compilation sur NullPointerException");
			e.printStackTrace(new PrintStream(CDLEditorPlugin.getDefault().getOutputStreamConsole()));
			CDLEditorPlugin.getDefault().showConsole();
			//IWorkbench workbench = PlatformUI.getWorkbench();
			//MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation du contrat AST echoue", "Generation of the XML contract fail with the following message: NullPointerException");
		}
		catch(Throwable e) {
			CDLEditorPlugin.getDefault().log("Fin de la compilation sur erreur");
			CDLEditorPlugin.getDefault().log(e.getMessage());
			e.printStackTrace(new PrintStream(CDLEditorPlugin.getDefault().getOutputStreamConsole()));
			CDLEditorPlugin.getDefault().showConsole();
			//IWorkbench workbench = PlatformUI.getWorkbench();
			//MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation du contrat AST echoue", "Generation of the XML contract fail with the following message:\n"+e.getMessage());
		}
		finally {
			try {
				myProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);
			} catch (CoreException e) {
				e.printStackTrace();
			}
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
