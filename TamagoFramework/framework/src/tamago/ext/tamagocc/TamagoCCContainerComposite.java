/**
 * 
 */
package tamago.ext.tamagocc;

/**
 * @author belhaouari
 *
 */
public interface TamagoCCContainerComposite extends TamagoCCContainer,
		TamagoCCComposite
{
	TamagoCCService[] getExportations();
}
