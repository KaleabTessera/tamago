/**
 * 
 */
package org.tamago.eclipse.cdl.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Hakim Belhaouari
 *
 */
public class SettingsTamagoTest extends Dialog {

	private Button ok;
	private Button cancel;
	private Combo componentkind;
	private Combo typetest;
	private Text component;
	private Scale scenariosize;
	private Text scenariosizeview;
	private Scale quantity;
	private Text quantityview;
	private Shell dialogShell;
	private SettingsTamagoTestAnswer pushok;
	
	public enum SettingsTamagoTestAnswer { UNKOWN, CANCELLED, OK }
	
	private String res_component;
	private int res_quantity;
	private int res_size;
	private boolean res_isbusiness;
	private SettingsStrategy res_strategy;
	
	/**
	 * @param parent
	 */
	public SettingsTamagoTest(Shell parent) {
		super(parent);
		initialize();
		pushok = SettingsTamagoTestAnswer.UNKOWN;
	}

	private void initialize() {
		Shell parent = getParent();
		dialogShell = new Shell(parent, SWT.RESIZE | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

		GridData gd = null;
		GridLayout layout = new GridLayout(3,false);
		dialogShell.setLayout(layout);
		layout.numColumns = 3;
		
		layout.verticalSpacing = 9;
		dialogShell.setText("Settings for TamagoTest");
		dialogShell.setMinimumSize(320, 240);
		
		
		// --- 
		Label label = new Label(dialogShell, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText("Component:");
		
		component = new Text(dialogShell, SWT.BORDER | SWT.SINGLE);
		component.setText("");
		component.setToolTipText("Affect the component that will be tested with generated test. Let blank for not a specific component (you must yourself the setUp method).");
		component.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		component.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				res_component = component.getText();
			}
		});
		
		new Label(dialogShell, SWT.NULL);
		
		// --
		label = new  Label(dialogShell, SWT.NONE);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setText("Kind:");
		
		componentkind = new Combo(dialogShell, SWT.READ_ONLY);
		componentkind.add("Business code");
		componentkind.add("Code with contract");
		componentkind.select(0);
		res_isbusiness = true;
		componentkind.addSelectionListener(new  SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				res_isbusiness = (componentkind.getSelectionIndex() <= 0);
			}
		});
		
		
		new Label(dialogShell, SWT.NULL);

		// --	
		
		// --
		label = new  Label(dialogShell, SWT.NONE);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setText("Type of Test:");
		
		typetest = new Combo(dialogShell, SWT.READ_ONLY);
		typetest.add("Test nominal");
		typetest.add("Test limit");
		typetest.add("Test outbound");
		typetest.add("Test outbound deep");
		typetest.add("Test bad scenario");
		typetest.select(0);
		res_strategy = SettingsStrategy.NOMINAL;
		typetest.addSelectionListener(new  SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch(typetest.getSelectionIndex()) {
				case 0: res_strategy = SettingsStrategy.NOMINAL;   break;
				case 1: res_strategy = SettingsStrategy.LIMITTEST; break;
				case 2: res_strategy = SettingsStrategy.OUTBOUND; break;
				case 4: res_strategy = SettingsStrategy.SCENARIO; break;
				case 3: res_strategy = SettingsStrategy.OUTBOUNDBEH; break;
				default:
					res_strategy = SettingsStrategy.NOMINAL;   break;
				}
			}
		});
		
		
		new Label(dialogShell, SWT.NULL);

		// --	
		
		label = new Label(dialogShell, SWT.NONE);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setText("Quantity of test");
		
		quantity = new Scale(dialogShell, SWT.NONE);
		quantity.setMinimum(1);
		quantity.setMaximum(100);
		quantity.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		quantity.setToolTipText("Set the quantity of scenario that will be produced");
		quantity.setSelection(1);
		quantity.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				dragQuantity();
			}
		});
		
		quantityview = new Text(dialogShell, SWT.SINGLE | SWT.BORDER);
		quantityview.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		quantityview.setText("1");
		res_quantity = 1;
		quantityview.setToolTipText(quantity.getToolTipText());
		quantityview.addModifyListener(new  ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				modifiyQuantityView();
			}
		});
		// --
		
		label = new Label(dialogShell, SWT.NONE);
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		label.setText("Scenario length");
		
		scenariosize = new Scale(dialogShell, SWT.NONE);
		scenariosize.setMinimum(1);
		scenariosize.setMaximum(1000);
		scenariosize.setSelection(10);
		scenariosize.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		scenariosize.setToolTipText("Set the max length of produced scenario");
		scenariosize.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dragScenarioSize();
			}
		});
		
		/*scenariosize.addDragDetectListener(new DragDetectListener() {
			
			@Override
			public void dragDetected(DragDetectEvent e) {
				dragScenarioSize();
			}
		});*/
		
		scenariosizeview = new Text(dialogShell, SWT.SINGLE | SWT.BORDER);
		scenariosizeview.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		scenariosizeview.setToolTipText(scenariosize.getToolTipText());
		scenariosizeview.setText("10");
		res_size = 10;
		scenariosizeview.addModifyListener(new  ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				modifiyScenarioSizeView();
			}
		});
		// --
		
		new Label(dialogShell, SWT.NULL);
		new Label(dialogShell, SWT.NULL);
		new Label(dialogShell, SWT.NULL);
		
		new Label(dialogShell, SWT.NULL);
		
		ok = new Button(dialogShell, SWT.PUSH | SWT.CENTER | SWT.BOLD);
		ok.setText("OK");
		ok.setSize(100, 30);
		ok.setToolTipText("Launch the test generation");
		gd = new GridData(100,30);
		ok.setLayoutData(gd);
		ok.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleOK();
			}
		});
		
		
		cancel = new Button(dialogShell, SWT.PUSH | SWT.CENTER);
		cancel.setText("Cancel");
		cancel.setSize(100, 30);
		cancel.setToolTipText("Cancel the test generation");
		//gd = new GridData(GridData.FILL_HORIZONTAL);
		gd = new GridData(100,30);
		cancel.setLayoutData(gd);
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleCancel();
			}
		});
		
	}
	
	public SettingsTamagoTestAnswer open() {
		
		dialogShell.layout();
		dialogShell.pack();			
		dialogShell.setLocation(getParent().toDisplay(100, 100));
		dialogShell.open();
		Display display = dialogShell.getDisplay();
		while (!dialogShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return pushok;
	}

	protected void handleCancel() {		
		pushok = SettingsTamagoTestAnswer.CANCELLED;
		dialogShell.close();
	}

	protected void modifiyScenarioSizeView() {
		try {
			int lk = Integer.parseInt(scenariosizeview.getText());
			if(res_size != lk)
				res_size = lk;
			if(scenariosize.getSelection() != lk)
				scenariosize.setSelection(lk);
		}
		catch(Exception e) {
			
		}
	}

	protected void dragScenarioSize() {
		try {
			int lk = Integer.parseInt(scenariosizeview.getText());
			if(scenariosize.getSelection() != lk)
				scenariosizeview.setText(""+scenariosize.getSelection());
		}
		catch(Exception e) {
			
		}
	}

	protected void modifiyQuantityView() {
		try {
			int lk = Integer.parseInt(quantityview.getText());
			if(res_quantity != lk)
				res_quantity = lk;
			if(quantity.getSelection() != lk)
				quantity.setSelection(lk);
		}
		catch(Exception e) {
			
		}
	}

	protected void dragQuantity() {
		try {
			int lk = Integer.parseInt(quantityview.getText());
			if(quantity.getSelection() != lk)
				quantityview.setText(""+quantity.getSelection());
		}
		catch(Exception e) {
			
		}
	}

	protected void handleOK() {
		pushok = SettingsTamagoTestAnswer.OK;
		dialogShell.close();
	}

	/**
	 * @param parent
	 * @param style
	 */
	public SettingsTamagoTest(Shell parent, int style) {
		super(parent, style);
		initialize();
	}

	public int getQuantity() {
		return res_quantity;
	}
	
	public int getScenarioSize() {
		return res_size;
	}
	
	public boolean isBusinessCode() {
		return res_isbusiness;
	}
	
	public String getComponentName() {
		return res_component;
	}
	
	public enum SettingsStrategy {
		NOMINAL,
		LIMITTEST,
		OUTBOUND,
		SCENARIO, 
		OUTBOUNDBEH
	}
	
	public SettingsStrategy getStrategy() {
		return res_strategy;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Display display = Display.getDefault();
			Shell shell = new Shell(display);
			SettingsTamagoTest inst = new SettingsTamagoTest(shell);
			System.err.println("RESULTAT: "+inst.open());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
