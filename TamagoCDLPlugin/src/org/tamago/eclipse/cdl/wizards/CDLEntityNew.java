package org.tamago.eclipse.cdl.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "cdl". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class CDLEntityNew extends Wizard implements INewWizard {
	private CDLEntityNewPage page;
	private ISelection selection;

	/**
	 * Constructor for TamagoCDLServiceNew.
	 */
	public CDLEntityNew() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new CDLEntityNewPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		final String name = page.getName();
		final String module = page.getModule();
		
		final String kindcontract = page.getLevelContract();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, kindcontract, name, module, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 * @param kindcontract 
	 * @param kindcontract2 
	 */

	private void doFinish(
			String containerName,
			String fileName,
			String kindcontract,
			String name,
			String module,
			IProgressMonitor monitor)
	throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream(kindcontract,name,module);
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}

	/**
	 * We will initialize file contents with a sample text.
	 * @param kindcontract 
	 * @param module 
	 * @param name 
	 */

	private InputStream openContentStream(String kindcontract, String name, String module) {
		String contents = "";
		if("Service".equals(kindcontract)) {
			contents = "module "+module+";\n"+
			"\n"+
			"service "+name+"  {\n"+
			"	// -- Liste des interfaces sous-jacent implémentées suivant le patron suivant:\n"+
			"    // implements <fullname interface>;\n"+
			"\n"+
			"	// -- Liste des services raffinés suivant le patron suivant:\n"+
			"   //  refine service <service> module <module>;\n"+
			"	// -- Liste des services inclus suivant le patron suivant:\n"+
			"   //  include service <service> module <module>;\n"+
			"\n"+
			"	// -- Liste des propriétés suivant le patron suivant:\n"+
			"    // property <accessmode> <fulltypes> <name>;\n"+
			"    //       accessmode: read readwrite write\n"+
			"	\n"+
			"	// -- Liste des patrons de percolation compatible\n"+
			"    // percolator <shortname>;\n"+
			"    //    shortname: specified for each percolator\n"+
			"    //    example: plugin, exact\n"+
			"    //    put * for all available percolators (default)\n"+
			"\n"+
			"	// -- Liste des invariants suivant le patron suivant:\n"+
			"    // invariant <expression> [ fail \"<msg>\" ];\n"+
			"	\n"+
			"	// -- Liste des méthodes suivant le patron suivant:\n"+
			"    // method <type> <name>(<params>) {\n"+
			"    //  id <identification>; // optionnel\n"+
			"    //  pre <expr> [ fail \"<msg>\"]; // optionnel\n"+
			"    //  post <expr> [ fail \"<msg>\"]; // optionnel\n"+
			"    // }\n"+
			"	\n"+
			"    // ---  Automate de comportement (optionnel)\n"+
			"}\n";
		}
		else if("Component".equals(kindcontract)) {
			contents = "module "+module+";\n"+
			"\n"+
			"component "+name+" {\n"+
			"	// -- Liste des interfaces sous-jacent implémentées suivant le patron suivant:\n"+
			"    // implements <fullname interface>;\n"+
			"\n"+
			"	// -- Liste des services fournis suivant le patron suivant:\n"+
			"    // provide service S1 in mod1;\n"+
			"	// -- Liste des services requis suivant le patron suivant:\n"+
			"    // require service S2 in mod2 as lab;\n"+
			"\n"+
			"	// -- Liste des propriétés suivant le patron suivant:\n"+
			"    // property <accessmode> <fulltypes> <name>;\n"+
			"    //       accessmode: read readwrite write\n"+
			"	\n"+
			"	// -- Liste des patrons de percolation compatible\n"+
			"    // percolator <shortname>;\n"+
			"    //    shortname: specified for each percolator\n"+
			"    //    example: plugin, exact\n"+
			"    //    put * for all available percolators (default)\n"+
			"\n"+
			"	// -- Liste des invariants suivant le patron suivant:\n"+
			"    // invariant <expression> [ fail \"<msg>\" ];\n"+
			"	\n"+
			"	// -- Liste des méthodes suivant le patron suivant:\n"+
			"    // method <type> <name>(<params>) {\n"+
			"    //  id <identification>; // optionnel\n"+
			"    //  pre <expr> [ fail \"<msg>\"]; // optionnel\n"+
			"    //  post <expr> [ fail \"<msg>\"]; // optionnel\n"+
			"    // }\n"+
			"	\n"+
			"    // ---  Automate de comportement (optionnel)\n"+
			"}\n";
		}

		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "TamagoCDLPlugin", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}