/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;

/**
 * @author Hakim Belhaouari
 *
 */
public interface ANew extends AExpression {
	/*
	 * faire le ANew pour realiser la commande new
	 * ou pour demander a la plateforme de realise des operations de creation
	 */
	
	AType getType();
	Iterator<AExpression> getArguments();
	int size();
}
