/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.ui.editors.text.TextEditor;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLEditor extends TextEditor {
	protected void initializeEditor() {
		super.initializeEditor();
		// Attache la configuration
		setSourceViewerConfiguration(new CDLSourceViewerConfiguration());
	}
	
	
}
