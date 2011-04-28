/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import java.io.File;
import java.io.PrintStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.tamago.eclipse.cdl.CDLEditorPlugin;
import org.tamago.eclipse.cdl.compiler.CompileCC;

/**
 * @author belhaouari
 *
 */
public class CDLTamagoCCPath implements CDLActionner {

	/**
	 * 
	 */
	public CDLTamagoCCPath() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Extends the TamagoCCPath");
		try   {
			CDLEditorPlugin.getDefault().log("affect TamagoCCPath");
			CDLEditorPlugin.getDefault().log("\tauthor: Hakim Belhaouari");
			
			
		}
		catch(Exception e) {
			e.printStackTrace(new PrintStream(CDLEditorPlugin.getDefault().getOutputStreamConsole()));
		}
	}

}
