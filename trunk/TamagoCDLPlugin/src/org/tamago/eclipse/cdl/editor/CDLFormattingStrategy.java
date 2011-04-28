/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLFormattingStrategy implements IFormattingStrategy {

	@SuppressWarnings("unused")
	private ISourceViewer sourceViewer;

	/**
	 * @param sourceViewer 
	 * 
	 */
	public CDLFormattingStrategy(ISourceViewer sourceViewer) {
		this.sourceViewer = sourceViewer;
	}

	/**
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#format(java.lang.String, boolean, java.lang.String, int[])
	 */
	public String format(String content, boolean isLineStart,
			String indentation, int[] positions) {
		CDLEditorPlugin.getDefault().log("LOG:"+content+":"+isLineStart+":"+indentation);
		return null;
	}

	/**
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStarts(java.lang.String)
	 */
	public void formatterStarts(String initialIndentation) {
		CDLEditorPlugin.getDefault().log("LOG:"+initialIndentation);
	}

	/**
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStops()
	 */
	public void formatterStops() {
		// TODO Auto-generated method stub

	}

}
