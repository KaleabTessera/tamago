/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLPartitionScanner extends RuleBasedPartitionScanner {

	/** Identifiant de la partition de commentaires (valeur : "__pos_cdl_comment") */
	public final static String CDL_COMMENT = "__pos_cdl_comment";
	/** Tableau contenant tous les types de partitions possibles */
	public final static String[] CDL_PARTITION_TYPES =
		new String[] {
			CDL_COMMENT,
		};

	/**
	 * Constructeur par défaut. Positionne les différentes règles.
	 */
	public CDLPartitionScanner() {
		super();

		// Token renvoyé dans le cas d'un commentaire
		IToken cdlComment = new Token(CDL_COMMENT);

		/* Nous sommes obligés d'avoir des règles pour les
		 * 		chaînes afin d'éviter que des commentaires ne soient
		 * 		démarrés à l'intérieur de chaînes ("eoi // toto", toto
		 * 		passe en commentaires ce qui n'est pas souhaité). */
		IPredicateRule[] rules = new IPredicateRule[] {
				// Règle pour les commentaires
				new EndOfLineRule("//", cdlComment),
				/* On met un token UNDEFINED pour traiter les chaînes en tant
				 * que code Java.
				 */
				new SingleLineRule("\"", "\"", Token.UNDEFINED, '\0', true)
			};
		// Prend en compte les règles
		setPredicateRules(rules);
	}
}
