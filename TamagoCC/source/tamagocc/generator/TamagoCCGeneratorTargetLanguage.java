/**
 * 
 */
package tamagocc.generator;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AEntity;
import tamagocc.exception.TamagoCCException;

/**
 * This class is the root for converting byte-code of Tamago for a target language.
 *  
 * 
 * @author Hakim Belhaouari
 */
public abstract  class TamagoCCGeneratorTargetLanguage implements TamagoCCASTVisitor {
	protected AEntity target;
	
	public TamagoCCGeneratorTargetLanguage(AEntity entity) {
		this.target = entity;
	}
	
	
	public abstract String getLanguage();
	public abstract String getDescription();
	public abstract String getAuthor();
	
	public abstract File getFinalDestination() throws TamagoCCException;
	public abstract OutputStream getFinalDestinationStream() throws TamagoCCException,IOException;
	
	public void generate() throws TamagoCCException {
		target.visit(this);
	}
}
