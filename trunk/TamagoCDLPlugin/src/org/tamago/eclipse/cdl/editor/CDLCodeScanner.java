package org.tamago.eclipse.cdl.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.tamago.eclipse.cdl.editor.scanner.CDLPropertyDetector;
import org.tamago.eclipse.cdl.editor.scanner.CDLTextAttributeProvider;
import org.tamago.eclipse.cdl.editor.scanner.FloatingPointNumberRule;

/**
 * @author Hakim Belhaouari
 */
public class CDLCodeScanner extends RuleBasedScanner {

	public static final String[] fgKeywords = new String[] {
		"component",	"service",		"composite",	"assembly",
		"read",			"write",		"readwrite",
		"using",		"@lang",		"id",			"in",
		"as",			"module",		"property",		"method",
		"refines",		"refine",		"includes",		"include",
		"provide",		"require",		"export",		"exportations",
		"bind",			"binds",		"definitions",	"uses",
		"use",			"behavior",		"state",		"states",
		"allow",		"transition",	"transitions",	"from",
		"to",			"with",			"when",			"default",
		"client",		"provider",		"by",			"forall",
		"exists",		"naming",		"implements",	"coreinterfaces",
		"percolator",	"percolators",	"null",			"invariant",
		"pre",			"post",			"fail",			"@pre",
		"@return",		"true",			"false",		"not",

		"String",		"string",		"void",		"bool",
		"int",			"real"
	};
	
	/**
	 * @author Hakim Belhaouari
	 */
	static class CDLWordDetector implements IWordDetector {
		/**
		 * Indique si le caract�re pass� en param�tre est valide �
		 * l'int�rieur d'un mot.
		 * @param c Caract�re � analyser.
		 * @return 	<code>true</code> si le caract�re est valide.
		 * 			<code>false</code> sinon.
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordPart(char)
		 */
		public boolean isWordPart(char c) {
		    return (Character.isLetterOrDigit(c) ||
		            c == '_');
		}
		
		/**
		 * Indique si le caract�re pass� en param�tre est valide au
		 * d�but d'un mot.
		 * @param c Caract�re � analyser.
		 * @return 	<code>true</code> si le caract�re est valide.
		 * 			<code>false</code> sinon.
		 * @see org.eclipse.jface.text.rules.IWordDetector#isWordStart(char)
		 */
		public boolean isWordStart(char c) {
			return (Character.isLetter(c)  || (c == '@'));
		}
	}
	
	
	/**
	 * Constructeur. Cr�e les r�gles utilis�es pour la coloration
	 * syntaxique.
	 * @param provider Classe fournissant les attributs de texte.
	 */
	public CDLCodeScanner(CDLTextAttributeProvider provider) {
		List<IRule> rules = new ArrayList<IRule>();		// Contiendra les r�gles
		
		/*
		 * Cr�ation des tokens. Chaque token contient l'attribut
		 * � appliquer au texte correspondant.
		 */
		IToken undefined 	= new Token(provider.getAttribute(CDLTextAttributeProvider.DEFAULT_ATTRIBUTE));
		IToken litteral 	= new Token(provider.getAttribute(CDLTextAttributeProvider.LITTERAL_ATTRIBUTE));
		IToken string 		= new Token(provider.getAttribute(CDLTextAttributeProvider.STRING_ATTRIBUTE));
		IToken keyword		= new Token(provider.getAttribute(CDLTextAttributeProvider.KEYWORD_ATTRIBUTE));
		IToken property		= new Token(provider.getAttribute(CDLTextAttributeProvider.PROPERTY_ATTRIBUTE));
		/*rules.add(new WhitespaceRule(new IWhitespaceDetector() {

			@Override
			public boolean isWhitespace(char c) {
				return Character.isWhitespace(c);
			}
			
		}));	*/	

		// R�gles pour les cha�nes
		
		rules.add(new SingleLineRule("\"", "\"", string, '\0', true));
		
		WordRule wrprop = new WordRule(new CDLPropertyDetector(),property);
		rules.add(wrprop);
		
		/*
		 * Cr�ation de la r�gle pour les mots cl�s. En deux temps :
		 * 	Tout d'abord on param�tre un d�tecteur qui va indiquer les
		 * caract�res valides pour le d�but et le contenu d'un mot,
		 * puis on ajoute une liste de mots en indiquant quel token doit
		 * �tre renvoy� pour chacun.
		 */
							// Si le mot n'est pas dans la liste, renvoie undefined
		WordRule wr = new WordRule(new CDLWordDetector(), undefined);
		// Ajout des pseudoInstructions
		for(int i = 0 ; i < fgKeywords.length ; ++i) {
		    wr.addWord(fgKeywords[i], keyword);
		}
		
		rules.add(wr);
		
		/*
		 * Attention � l'ordre. Si NumberRule est en premier, elle
		 * d�tectera le 0 du 0x des nombres hexa et consommera le 0
		 * ce qui emp�chera HexNumberRule de fonctionner. De plus
		 * elle consommera toute la chaine jusqu'� la lettre indiquant
		 * le format, ce qui bloquera OctalNumberRule
		 * et BinaryNumberRule.
		 */
		rules.add(new FloatingPointNumberRule(litteral)); // R�gle pour les nombres � virgule flottante
		rules.add(new NumberRule(litteral));			// R�gle pour les nombres d�cimaux
		
		// Conversion de la List en tableau pour la passer � la m�thode setRules
		IRule[] param = new IRule[rules.size()];
		rules.toArray(param);
		setRules(param);
	}
}
