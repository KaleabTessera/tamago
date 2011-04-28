package org.tamago.eclipse.cdl.editor.scanner;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * D�tecte les nombres en virgule flottante :
 * xxx.y[yy[E[+/-]zzz]] 
 * @author S�bastien Le Ray
 */
public class FloatingPointNumberRule implements IRule {

    private IToken fToken;
    
	/**
	 * Constructeur.
	 * La r�gle renverra token si lors de l'analyse
	 * elle trouve une chaine correspondant � un nombre en virgule flottante. 
	 * @param token Token � renvoyer en cas de succ�s.
	 */
    public FloatingPointNumberRule(IToken token) {
        fToken = token;
    }

	/**
	 * D�termine si l'on est en pr�sence d'une chaine
	 * correspondant � un nombre en virgule flottante ou non.
	 * Appel� par l'ASMCodeScanner (via le RuleBasedScanner).
	 * @param scanner Scanner permettant de lire le texte.
	 * @return Le token pass� au constructeur si la chaine est une chaine binaire.
	 * <code>Token.UNDEFINED</code> sinon ce qui permet de continuer l'analyse.
	 * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
    public IToken evaluate(ICharacterScanner scanner) {
        int c = scanner.read();
        int size = 1;
        if(c == '-') {
        	size++;
        	c = scanner.read();
        }
        if(Character.isDigit(c)) {
            do {
                c = scanner.read();
                ++size;
            } while(Character.isDigit(c));
            if(c == '.') {
                c = scanner.read();
                ++size;
                if(Character.isDigit(c)) {
                    do {
                        c = scanner.read();
                        ++size;
                    } while(Character.isDigit(c));
                    if(c == 'E' || c == 'e') {
                        c = scanner.read();
                        ++size;
                        if(Character.isDigit(c)) {
                            do {
                                c = scanner.read();
                            } while(Character.isDigit(c));
                            scanner.unread();
                            return fToken;		// xxx.yyyEzzz
                        } else if(c == '+' || c == '-'){
                            c = scanner.read();
                            ++size;
                            if(Character.isDigit(c)) {
                                do {
                                    c = scanner.read();
                                } while(Character.isDigit(c));
                                scanner.unread();
                                return fToken;		// xxx.yyyE(+/-)zzz
                            }
                        }
                    } else {
                        scanner.unread();	// xxx.yyy
                        return fToken;
                    }
                }
            }
        }
        for( ; size > 0 ; --size) {
            scanner.unread();
        }
        return Token.UNDEFINED;
    }

}
