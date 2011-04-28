/**
 * 
 */
package org.tamago.eclipse.cdl.editor.scanner;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLPropertyDetector implements IWordDetector {

	/**
	 * 
	 */
	public CDLPropertyDetector() {
		
	}

	/**
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
	 */
	public boolean isWordPart(char arg0) {
		return Character.isJavaIdentifierPart(arg0);
	}

	/**
	 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
	 */
	public boolean isWordStart(char arg0) {
		return '#'== arg0;
	}

}
