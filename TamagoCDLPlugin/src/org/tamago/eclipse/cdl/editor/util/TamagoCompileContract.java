/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.compiler.CompileCC;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCompileContract implements CDLActionner {

	/**
	 * 
	 */
	public TamagoCompileContract() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.tamago.eclipse.cdl.editor.util.CDLActionner#run()
	 */
	public void run() {
		System.out.println("Compilation");
		try   {
			CDLEditorPlugin.getDefault().log("TamagoCC for CDL specification - version 2.0b");
			CDLEditorPlugin.getDefault().log("\tauthor: Hakim Belhaouari");
			
			IWorkbench workbench = PlatformUI.getWorkbench();
			IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			IEditorInput input = (IEditorInput) editor.getEditorInput(); // il semblerait qu'il utilise ca
			//CDLEditorPlugin.getDefault().log("Input:"+input.getClass().getName());
			
			if(input instanceof IPathEditorInput) {
				IPath path = ((IPathEditorInput)input).getPath();
				File file = path.toFile();
				CompileCC ccc = new CompileCC(file,false);
				
				ProgressMonitorDialog pmd = new ProgressMonitorDialog(workbench.getDisplay().getActiveShell());
				
				pmd.run(true, false, ccc);
				
			}
			else {
				CDLEditorPlugin.getDefault().log("Wrong type of the Editor => incompatible version of eclipse");
			}
		}
		catch(Exception e) {
			e.printStackTrace(new PrintStream(CDLEditorPlugin.getDefault().getOutputStreamConsole()));
		}
	}

}
