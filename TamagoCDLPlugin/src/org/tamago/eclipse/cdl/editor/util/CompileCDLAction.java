/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.compiler.CompileCDL;


/**
 * @author Hakim Belhaouari
 *
 */
public class CompileCDLAction extends Action {
	/**
	 * 
	 */
	public CompileCDLAction() {
		super("G�n�ration du Contrat XML");
		String tooltip = "Génère le Contrat dans le répertoire '../xmls'. Cela suppose donc que votre fichier soit dans le répertoire 'cdls'.";
		this.setToolTipText(tooltip);
		this.setDescription(tooltip);
	}

	public boolean isEnabled() {
		return true;
	}

	public void run() {
		//CDLEditorPlugin.getDefault().getConsole().clearConsole();
		System.out.println("Compilation");
		try   {
			CDLEditorPlugin.getDefault().clearConsole();
			CDLEditorPlugin.getDefault().log("TamagoCC for CDL specification - version 0.3");
			CDLEditorPlugin.getDefault().log("\tauthor: Hakim Belhaouari");
			
			IWorkbench workbench = PlatformUI.getWorkbench();
			IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			IEditorInput input = (IEditorInput) editor.getEditorInput(); // il semblerait qu'il utilise ca
			//CDLEditorPlugin.getDefault().log("Input:"+input.getClass().getName());

			if(input instanceof IPathEditorInput) {
				IPath path = ((IPathEditorInput)input).getPath();
				File file = path.toFile();
				CompileCDL ccc = new CompileCDL(file);
				
				ProgressMonitorDialog pmd = new ProgressMonitorDialog(workbench.getDisplay().getActiveShell());
				
				pmd.run(true, false, ccc);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
