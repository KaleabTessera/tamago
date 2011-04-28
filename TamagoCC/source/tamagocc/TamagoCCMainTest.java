/**
 * 
 */
package tamagocc;

import tamagocc.api.TComponent;
import tamagocc.api.TService;
import tamagocc.api.TTamago;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGenericConverter;
import tamagocc.generic.api.GTamago;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.TamagoCCPrintBehavior;
import tamagocc.util.TamagoCCSynchronizeProduct;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCMainTest {

	
	private static void searchAndShowBehavior(String name,String module) 
	{
		try {
			TTamago entity = TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(name,module);
			GTamago gentity;
			switch(entity.getTamagoType()) {
			case TTamago.TAMAGO_SERVICE:
				TamagoCCGenericConverter converterS = new TamagoCCGenericConverter((TService)entity);
				gentity = (GTamago)converterS.getGTamagoEntity();
				break;
			case TTamago.TAMAGO_COMPONENT:
				TamagoCCGenericConverter converterC = new TamagoCCGenericConverter((TComponent)entity);
				gentity = (GTamago)converterC.getGTamagoEntity();
				break;
			default:
				throw new TamagoCCException("no supported example");
			}
			
			TamagoCCPrintBehavior printB1 = new TamagoCCPrintBehavior(gentity);
			printB1.setVisible(true);
			TamagoCCSynchronizeProduct product = new TamagoCCSynchronizeProduct(gentity);
			TamagoCCPrintBehavior printB2 = new TamagoCCPrintBehavior(product.getProduct(),"Synchronized Product "+gentity.getName());
			printB2.setVisible(true);
		}
		catch(Exception e) {
			TamagoCCLogger.println(1,"Erreur durant la generation du behavior pour le service "+name);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 	On initialise l'environnement 
			
			TamagoCC.initialize(args);
    		
			searchAndShowBehavior("B1","tamagoheritage");
			
			searchAndShowBehavior("B2","tamagoheritage");
			
			searchAndShowBehavior("B3","tamagoheritage");
			
			
			searchAndShowBehavior("B4","tamagoheritage");
			
			searchAndShowBehavior("B5","tamagoheritage");
			searchAndShowBehavior("B6","tamagoheritage");
			searchAndShowBehavior("B7","tamagoheritage");
			
			
			searchAndShowBehavior("B4C","tamagoheritage");
			searchAndShowBehavior("B5C","tamagoheritage");
			searchAndShowBehavior("B3C","tamagoheritage");
			/*searchAndShowBehavior("B6C","tamagoheritage");
			searchAndShowBehavior("B7C","tamagoheritage");*/
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}

	}

}
