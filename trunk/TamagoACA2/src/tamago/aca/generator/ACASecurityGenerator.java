/**
 * 
 */
package tamago.aca.generator;

import tamagocc.generator.TamagoCCGeneratorCommon;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GTamagoEntity;

/**
 * @author hbelhaou
 *
 */
public class ACASecurityGenerator extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	/**
	 * 
	 */
	public ACASecurityGenerator(GTamagoEntity entity) {
		super(null, entity);
	}

}
