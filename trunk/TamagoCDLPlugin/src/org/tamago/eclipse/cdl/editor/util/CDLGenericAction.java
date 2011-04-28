/**
 * 
 */
package org.tamago.eclipse.cdl.editor.util;

import org.eclipse.jface.action.Action;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLGenericAction extends Action {
	private CDLActionner csa;
	
	/**
	 * @param text
	 */
	public CDLGenericAction(String text, CDLActionner action) {
		super(text);
		csa = action;
		
	}
	
	public CDLGenericAction(String text, String tooltips, CDLActionner action) {
		super(text);
		csa = action;
		this.setToolTipText(tooltips);
		this.setDescription(tooltips);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		CDLEditorPlugin.getDefault().clearConsole();
		if(csa != null)
			csa.run();
	}
}
