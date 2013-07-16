/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IFileEditorInput;
import org.tamago.eclipse.cdl.CDLEditorException;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

import tamagocc.api.TTamago;
import tamagocc.ast.api.AEntity;
import tamagocc.generator.TamagoCCGenerator;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.javasource.TamagoCCJavaSource;
import tamagocc.javasource.TamagoCCJavaSourceBuilder;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author Hakim Belhaouari
 *
 */
public class CompileCC extends CDLTask {

	private boolean skel;
	private IFileEditorInput input;
	
	/**
	 * @param b 
	 * @param file 
	 * @throws CDLEditorException 
	 * 
	 */
	public CompileCC(IFileEditorInput fileinput, boolean b) throws CDLEditorException {
		
		this.input = fileinput;
		skel = b;
	}


	public void run() {
		
		IProject project = retreveProject(input);
		try {
			prepareEnv(project);
				
			TTamago tamago = parseInputCDLFile(input);
			// --- nouveau code
			
			monitor.worked(1);
			monitor.subTask("Analyze the parsed contract...");
			TamagoCCJavaSourceBuilder builder = new TamagoCCJavaSourceBuilder();
            TamagoCCGPool gpool = TamagoCCGPool.getDefaultTamagoCCGPool();
            GTamagoEntity entity =  gpool.getGTamagoEntity(tamago);
            
            TamagoCCGenerator generator = new TamagoCCGenerator(builder,entity,".",true,!skel,false);
            monitor.worked(1);
			monitor.subTask("Generation of necessary file...");
			ArrayList<AEntity> entities = generator.generateAST();
			for (AEntity e : entities) {
				
				
				PipedOutputStream pos = new PipedOutputStream();
				final PipedInputStream pis = new PipedInputStream(pos);
				
				
				TamagoCCJavaSource tl = (TamagoCCJavaSource) builder.getTargetLanguage(e, pos);
				
				/*
				 String name = target.getName();
				 name = (name.substring(0,1).toUpperCase()+name.substring(1));
				 */
				String name = e.getName();
				String filename =  (name.substring(0,1).toUpperCase()+name.substring(1))+".java";
				
				final IFile outputfile = searchAndOrCreat(project,"gen", e.getModule().getFullName(),filename); 
				
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
				
				tl.generate();
				pos.flush();
				pos.close();
				
				
				//IPackageFragment project;
				//ICompilationUnit unit = project.createCompilationUnit(tl.getFinalDestination().getName(), stream.toString(), true, monitor);
			}
			TamagoCCLogger.infoln(1,"---------------------------------------");
            TamagoCCLogger.infoln(0,"Generation des conteneurs reussit!");
            monitor.worked(1);
        }
        catch(Exception e) {
            TamagoCCLogger.infoln(e);
            TamagoCCLogger.infoln(0,"Generation des conteneurs echoue!!!!");
            CDLEditorPlugin.getDefault().showConsole();
			//IWorkbench workbench = PlatformUI.getWorkbench();
            //MessageDialog.openError(workbench.getActiveWorkbenchWindow().getShell(), "Generation des conteneurs echoues", e.getMessage());
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
