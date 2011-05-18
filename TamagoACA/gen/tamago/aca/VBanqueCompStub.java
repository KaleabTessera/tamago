package tamago.aca;
import tamago.*;
import tamago.ext.tamagocc.*;
/*
    
 ******************************************
 *              VBanqueComp              *
 ******************************************
 *
 * YOU MUST EDIT THIS FILE.
 * TAMAGOCC GENERATES AUTOMATICALLY THE SKELETON.
 * Created date : Mon Feb 14 14:41:50 CET 2011
 * authors : Hakim Belhaouari and Frederic Peschanski
 * site : http://www-poleia.lip6.fr/~belhaouari/Tamago/

*/
public class VBanqueCompStub
    implements tamago.aca.VBanqueComp
{
    // Members Variables

    // Properties
    /**
        Getter of the property temp
    */
    public int getTemp(){
        // TODO : complete this method
        return 0;
    }
    /**
        Getter of the property balance
    */
    public int getBalance(){
        // TODO : complete this method
        return 0;
    }

    // Methods
    // DON'T MODIFY THIS METHOD
    public void bind(String label, TamagoCCService service) throws ServiceBindException{
        throw new ServiceBindException(label);
    }


    public void core_set_properties(String property, Object value){
        if("temp".equals(property)) {
            // Complete the affectation of the observable properties.
        }
        if("balance".equals(property)) {
            // Complete the affectation of the observable properties.
        }
    }


    public VBanqueCompStub ()
    {
    }


    public void modify(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void deposit(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void validate(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // TODO : complete this method
    }
}