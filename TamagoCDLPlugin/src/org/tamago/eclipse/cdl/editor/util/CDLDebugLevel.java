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
public class CDLDebugLevel extends Action {
	private int level;
	
	public CDLDebugLevel(String name,String tooltip,int lvl,boolean checked) {
		super(name);
		this.level = lvl;
		this.setToolTipText(tooltip);
		this.setDescription(tooltip);
		this.setChecked(checked);
		if(checked)
			CDLEditorPlugin.getDefault().setDebugLevel(this,level);
	}
	
	
	@Override
	public void run() {
		CDLEditorPlugin.getDefault().setDebugLevel(this,level);
	}
}
