/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.IOException;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IFileEditorInput;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

import tamagocc.TamagoCCParser;
import tamagocc.api.TTamago;
import tamagocc.compiler.TamagoCDLLexer;
import tamagocc.compiler.TamagoCDLParser;
import tamagocc.exception.TamagoCCException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;

/**
 * @author hbelhaou
 *
 */
public abstract class CDLTask implements IRunnableWithProgress {

	protected IProgressMonitor monitor;
	
	/**
	 * 
	 */
	public CDLTask() {
	}
	
	
	public void prepareEnv(IProject project) throws TamagoCCException, CoreException {
		monitor.subTask("Initialize environment...");
		monitor.worked(1);
		IFolder xmlsfolder = project.getFolder("xmls");
		if(!xmlsfolder.exists())
			xmlsfolder.create(true, true, monitor);
		
		
		TamagoCCPool pool = TamagoCCPool.getDefaultPool();
		TamagoCCParser xmlparser = TamagoCCParser.getDefaultParser();
		TamagoCCPercolation.initialisation();
		xmlparser.setTamagoCCPool(pool);
		
		TamagoCCLogger.setOut(CDLEditorPlugin.getDefault().getOutputStreamConsole());
		TamagoCCLogger.setLevel(CDLEditorPlugin.getDefault().getDebugLevel());
		
		pool.addTamagoCCPath(xmlsfolder.getFullPath().toOSString());
		
		
	}

	public TTamago parseInputCDLFile(IFileEditorInput input) throws IOException, CoreException, RecognitionException {
		monitor.worked(1);
		monitor.subTask("Parsing the CDL file...");
		
		ANTLRInputStream stream = new ANTLRInputStream(input.getFile().getContents());
		TamagoCDLLexer lexer = new TamagoCDLLexer(stream);
		CommonTokenStream token = new CommonTokenStream(lexer);
		TamagoCDLParser parser = new TamagoCDLParser(token);
		TamagoCDLParser.tamagoEntity_return res;
		try {
			res = parser.tamagoEntity();
			if(parser.getNumberOfSyntaxErrors() == 0 && res != null && res.value != null) {
				TTamago tamago = (TTamago)res.value;
				TamagoCCLogger.println(1,"compilation success");
				if(tamago instanceof TTamago) {
					TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), (TTamago)tamago);
				}
				else
					TamagoCCLogger.println(1, "Assembly not yet supported!");
				return tamago;
			}
			else {
				throw new RuntimeException("Compilation failed with strange reasons!");
			}
		} catch (RecognitionException e) {
			TamagoCCLogger.println(1, e.getMessage());
			throw e;
		}
		catch(RuntimeException e) {
			TamagoCCLogger.println(1, e.getMessage());
			throw e;
		}
	}
	
	protected IProject retreveProject(IFileEditorInput input) {
		IProject myProject = input.getFile().getProject();// myWorkspaceRoot.getProject();
		if(myProject.exists() && !myProject.isOpen())
			try {
				myProject.open(monitor);
				
			} catch (CoreException e1) {
				e1.printStackTrace();
				return null;
			}
		return myProject;
	}
	
	

	public static IFile searchAndOrCreat(IProject project, String module, String filename) throws CoreException {

		IFolder genfolder = project.getFolder("gen");
		if(!genfolder.exists())
			genfolder.create(true, true, null);	
		
		String[] reps = module.split(".");
		IFolder last = genfolder;
		for (String rep : reps) {
			IFolder tmp = last.getFolder(rep);
			if(!tmp.exists())
				tmp.create(true, true, null);
			last = tmp;
		}
		
		IFile res = last.getFile(filename);
		return res;
		
	}

}
