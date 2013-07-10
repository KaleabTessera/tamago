/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.compiler.CDLTest;
import org.tamago.eclipse.cdl.wizards.SettingsTamagoTest;
import org.tamago.eclipse.cdl.wizards.SettingsTamagoTest.SettingsTamagoTestAnswer;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCompileTest implements CDLActionner {

	/**
	 * 
	 */
	public TamagoCompileTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.tamago.eclipse.cdl.editor.util.CDLActionner#run()
	 */
	@Override
	public void run() {
		System.out.println("Test generation");
		try   {
			
			IWorkbench workbench = PlatformUI.getWorkbench();
			CDLEditorPlugin.getDefault().log("TamagoTest for CDL specification - version 1.5");
			CDLEditorPlugin.getDefault().log("\tauthor: Hakim Belhaouari");
			
			IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			IEditorInput input = (IEditorInput) editor.getEditorInput(); // il semblerait qu'il utilise ca
			//CDLEditorPlugin.getDefault().log("Input:"+input.getClass().getName());
			
			if(input instanceof IPathEditorInput) {
				IPath path = ((IPathEditorInput)input).getPath();
				File file = path.toFile();
				
				SettingsTamagoTest stt = new SettingsTamagoTest(workbench.getDisplay().getActiveShell());
				if(stt.open() == SettingsTamagoTestAnswer.OK) {
					
					
					//CDLTest test = new CDLTest(file, stt.getComponentName(), stt.isBusinessCode(), stt.getQuantity(), stt.getScenarioSize(), stt.getStrategy());
					//test.start();
					ProgressMonitorDialog pmd = new ProgressMonitorDialog(workbench.getDisplay().getActiveShell());
					
					//pmd.run(true, true, test);
				}
				else {
					CDLEditorPlugin.getDefault().log("Operation break by user");
				}
				
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
