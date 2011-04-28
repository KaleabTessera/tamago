/**
 * 
 */
package tamagocc.generic;

import tamagocc.api.TTamagoEntity;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GTamagoEntity;



/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public abstract class TamagoCCConverter  {	
	public abstract TTamagoEntity getTTamagoEntity();
	public abstract GTamagoEntity getGTamagoEntity() throws TamagoCCException;
	
}
