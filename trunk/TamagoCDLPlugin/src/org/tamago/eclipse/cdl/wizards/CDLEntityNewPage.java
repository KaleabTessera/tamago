package org.tamago.eclipse.cdl.wizards;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (cdl).
 */

public class CDLEntityNewPage extends WizardPage {
	private Text containerText;

	private Text fileText;
	private Text moduleText;

	private ISelection selection;

	private Combo combo;

	private Button forceCDLS;
	private Button forceXMLS;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public CDLEntityNewPage(ISelection selection) {
		super("wizardPage");
		setTitle("Creation of CDL Contract for Tamago");
		setDescription("This wizard creates a new file with *.cdl that can satisfy the contract of TamagoCC.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);
		label.setText("&Container:");

		containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		containerText.setLayoutData(gd);
		containerText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		Button button = new Button(container, SWT.PUSH);
		button.setText("Browse...");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleBrowse();
			}
		});
		label = new Label(container, SWT.NULL);
		label.setText("&Entity name:");

		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		label = new Label(container,SWT.NULL);

		label = new Label(container, SWT.NULL);
		label.setText("&Module of contract:");

		moduleText = new Text(container,SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		moduleText.setLayoutData(gd);
		moduleText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});

		label = new Label(container,SWT.NULL);

		label = new Label(container, SWT.NULL);
		label.setText("&Type of Contract:");

		combo = new Combo(container,SWT.READ_ONLY);
		combo.add("Service");
		combo.add("Component");
		combo.select(0);

		label = new Label(container,SWT.NULL);
		label = new Label(container, SWT.NULL);
		label.setText("Misc:");

		forceCDLS = new Button(container,SWT.CHECK);
		forceCDLS.setText("Do not check cdls folder (DEBUG)");

		label = new Label(container,SWT.NULL);
		label = new Label(container,SWT.NULL);

		forceXMLS = new Button(container,SWT.CHECK);
		forceXMLS.setText("Do not check xmls folder (DEBUG)");

		label = new Label(container,SWT.NULL);
		label = new Label(container,SWT.NULL);

		Button refresh = new Button(container,SWT.PUSH);
		refresh.setText("Refresh options (DEBUG)");
		refresh.setToolTipText("Sometimes some verifications fails, this one allow the user to force the check without make any event");
		refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				dialogChanged();
			}
		});

		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText("MyContract");
		moduleText.setText("mymodule");
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(
				getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
		"Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		boolean warning = false;
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
			.findMember(new Path(getContainerName()));
		String fileName = fileText.getText();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}

		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}

		IResource resource = root.getFolder(new Path(getContainerName()));
		if(!forceCDLS() && (resource != null) && (!resource.getLocation().toFile().exists() || !(resource instanceof IContainer)) ) {
			updateStatus("Contracts must be located in a cdls folder. Creats it if needed");
			return;
		}

		File xmlsdirectory = new File(container.getFullPath().toFile().getParentFile().getAbsolutePath(),"xmls");
		String containerName = xmlsdirectory.getAbsolutePath();
		resource = root.getFolder(new Path(containerName));
		if (!forceXMLS() && (resource != null) && (!resource.getLocation().toFile().exists() || !(resource instanceof IContainer)) ) {
			try {
				updateStatus("The plugin needs a xmls folder at the same level of the cdls folder. You must creat it ("+xmlsdirectory.getCanonicalPath()+").");
			} catch (IOException e) {
				updateStatus("The plugin needs a xmls folder at the same level of the cdls folder. You must creat it.");
			}
			return;
		}


		if (fileName.length() == 0) {
			updateStatus("Contract name must be specified");
			return;
		}

		if (!fileName.matches("[a-zA-Z_]\\w*")) {
			updateStatus("Contract name must be valid");
			return;
		}

		if(!moduleText.getText().matches("[a-zA-Z_]\\w*([.][a-zA-Z_]\\w*)*")) {
			updateStatus("Module name must be valid");
			return;
		}
		
		if(Character.isLowerCase(fileName.charAt(0))) {
			setMessage("Contract name should begin by a uppercase letter",DialogPage.WARNING);
			warning = true;
		}

		if(moduleText.getText().matches(".*[A-Z_].*")) {
			setMessage("Module name should use only lowercase letter",DialogPage.WARNING);
			warning = true;
		}

		if(warning)
			setPageComplete(true);
		else // on efface tous les vieux messages
			updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setMessage(message,DialogPage.WARNING);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText()+".cdl";
	}
	
	public String getName() {
		return fileText.getText();
	}
	
	public String getModule() {
		return moduleText.getText();
	}

	public String getLevelContract() {
		return combo.getItem(combo.getSelectionIndex());
	}

	public boolean forceCDLS() {
		return forceCDLS.getSelection();
	}
	public boolean forceXMLS() {
		return forceXMLS.getSelection();
	}
}