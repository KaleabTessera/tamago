package org.tamago.eclipse.cdl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.tamago.eclipse.cdl.editor.scanner.CDLTextAttributeProvider;

/**
 * @author Hakim Belhaouari
 */
public class CDLCommentScanner extends RuleBasedScanner {
	/**
	 * Constructeur. Cr�e les r�gles utilis�es pour la coloration
	 * syntaxique.
	 * @param provider Classe fournissant les attributs de texte.
	 */
	public CDLCommentScanner(CDLTextAttributeProvider provider) {
		List<IRule> rules = new ArrayList<IRule>();		// Contiendra les r�gles
		IToken comment		= new Token(provider.getAttribute(CDLTextAttributeProvider.COMMENT_ATTRIBUTE));

		rules.add(new EndOfLineRule("//",comment));//new SingleLineRule("--", null, comment, '\0', true));
		
		// Conversion de la List en tableau pour la passer � la m�thode setRules
		IRule[] param = new IRule[rules.size()];
		rules.toArray(param);
		setRules(param);
	}
}
