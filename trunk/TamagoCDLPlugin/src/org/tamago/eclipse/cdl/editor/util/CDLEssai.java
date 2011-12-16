/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;

/**
 * @author hbelhaou
 *
 */
public class CDLEssai extends Action {


	/**
	 * @param text
	 */
	public CDLEssai(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		myTest();
	}
	
	private void myTest() {
		
		IWorkspace workspace =  ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		//IProject proj = root.getProject();
		//System.out.println("PROJET COURANT: "+proj.getName());
		
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			System.out.println("PROJETS : "+project.getName());
			
			try {
				System.out.println("JDT nature: "+ project.isNatureEnabled("org.eclipse.jdt.core.javanature"));
			} catch (CoreException e) {
				e.printStackTrace(System.out);
			}
		}

	}

}
