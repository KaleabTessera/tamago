/**
 * 
 */
package tamago.check.util;

import tamagocc.generator.TamagoCCGeneratorCommon;
import tamagocc.generic.api.GTamagoEntity;

/**
 * @author aliquando
 *
 */
public class TamagoCheckConverter extends TamagoCCGeneratorCommon {

	/**
	 * @param owner
	 * @param entity
	 */
	public TamagoCheckConverter(GTamagoEntity entity) {
		super(null, entity);
	}

}
