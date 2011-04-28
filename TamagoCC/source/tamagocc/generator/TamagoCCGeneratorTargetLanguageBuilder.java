/**
 * 
 */
package tamagocc.generator;

import java.io.File;
import java.io.OutputStream;

import tamagocc.ast.api.AEntity;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GTamagoEntity;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public abstract class TamagoCCGeneratorTargetLanguageBuilder {
	public TamagoCCGeneratorTargetLanguageBuilder() {
		
	}
	public abstract String generateInterfaceName(GTamagoEntity composite);
	public abstract String generateSkeletonName(GTamagoEntity composite);
	public abstract String generateContainerInterfaceName(GTamagoEntity composite);
	public abstract String generateContainerImplementationName(GTamagoEntity composite,GPercolator percolator);
	
	public abstract String generateFullClassNameFromEntity(GTamagoEntity entity);
	//public abstract String generateFullClassNameFromEntity(GModule module,String name);
	
	public abstract TamagoCCGeneratorTargetLanguage getTargetLanguage(AEntity entity,File directory) throws TamagoCCException;
	public abstract TamagoCCGeneratorTargetLanguage getTargetLanguage(AEntity entity,OutputStream stream) throws TamagoCCException;
	public abstract String getLanguage();
	public abstract String getDescription();
	public abstract String getAuthor();
}
