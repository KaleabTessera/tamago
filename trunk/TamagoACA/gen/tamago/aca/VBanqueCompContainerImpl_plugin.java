package tamago.aca;
import tamago.ext.tamagocc.*;
import tamago.*;
import java.util.Hashtable;
import java.util.ArrayList;
/*
    
 ******************************************
 *              VBanqueComp              *
 ******************************************
 *
 * DO NOT EDIT THIS FILE.
 * THIS FILE IS GENERATED AUTOMATICALLY BY TAMAGOCC
 * Created date : Mon Feb 14 14:41:50 CET 2011
 * authors : Hakim Belhaouari and Frederic Peschanski
 * site : http://www-poleia.lip6.fr/~belhaouari/Tamago/

*/
public class VBanqueCompContainerImpl_plugin
     extends tamago.ext.tamagocc.TamagoCCContainerImpl
    implements tamago.aca.VBanqueCompContainer
{
    // Members Variables
    private tamago.aca.VBanqueComp tamago_decorated_component;

    // Properties
    /**
        Getter of the property temp
    */
    public int getTemp(){
        return tamago_decorated_component.getTemp();
    }
    /**
        Getter of the property balance
    */
    public int getBalance(){
        return tamago_decorated_component.getBalance();
    }

    // Methods
    protected void fetchServiceBehavior(TamagoCCMethodID mid) throws TamagoCCServiceBehaviorException{
        if((current.id() == 2)) {
            if(methodID("deposit").equals(mid)) {
                current = states.get(1);
                return ;
            }
        }
        if((current.id() == 1)) {
            if(methodID("modify").equals(mid)) {
                current = states.get(1);
                return ;
            }
            if(methodID("validate").equals(mid)) {
                current = states.get(0);
                return ;
            }
        }
        if((current.id() == 0)) {
        }
    }


    /*
        Don't modify this constructor !

    */
    public VBanqueCompContainerImpl_plugin (tamago.aca.VBanqueComp component)
    {
        tamago_decorated_component = ((tamago.aca.VBanqueComp) component);
        //  main IDs
        registerID("modify", "modify");
        registerID("deposit", "deposit");
        registerID("validate", "validate");
        //  secondary IDs
        registerID("modify", "modify");
        registerID("deposit", "deposit");
        registerID("validate", "validate");
        //  states generations
        TamagoCCState state = null;
        state = newstate();
        include(state, "final");
        state = newstate();
        include(state, "ballotage");
        allow(state, methodID("modify"));
        allow(state, methodID("validate"));
        state = newstate();
        include(state, "adepose");
        allow(state, methodID("deposit"));
        //  Initialize the current default state
        current = states.get(2);
        // ----- if necessary you can add some code under this ligne ----
    }


    public RequiredService[] getRequiredServices(){
        RequiredService[] rs = new RequiredService[0];
        return rs;
    }


    public void bind(String label, TamagoCCService service) throws ServiceBindException{
        tamago_decorated_component.bind(label, service);
    }


    public boolean isBinded(){
        return (tamago_decorated_component == null);
    }


    public void core_set_properties(String property, Object value){
        throw new TamagoCCHotSwappingException();
    }


    public void tamago_hotswapping(TamagoCCComponent component){
        int sav_temp = getTemp();
        int sav_balance = getBalance();
        tamago_decorated_component = ((tamago.aca.VBanqueComp) component);
        tamago_decorated_component.core_set_properties("temp", sav_temp);
        tamago_decorated_component.core_set_properties("balance", sav_balance);
    }


    public void modify(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // Initialisation of variables
        // Invariant test before precondition
        // Environment of Precondition
        if(!canCallMethod(methodID("modify"))) {
            throw new TamagoCCBehaviorException(methodID("modify"));
        }
        // Precondition test
        // No precondition detected
        // Initialisation of at pre expression
        // redirect call
        tamago_decorated_component.modify(quad);
        // Environment of Postcondition
        // Postcondition test
        // No postcondition detected
        // Invariant test after postcondition
        // Update behavioral's state
        fetchServiceBehavior(methodID("modify"));
        // Return of the redirect method if needed
    }


    public void deposit(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // Initialisation of variables
        // Invariant test before precondition
        // Environment of Precondition
        if(!canCallMethod(methodID("deposit"))) {
            throw new TamagoCCBehaviorException(methodID("deposit"));
        }
        // Precondition test
        // No precondition detected
        // Initialisation of at pre expression
        // redirect call
        tamago_decorated_component.deposit(quad);
        // Environment of Postcondition
        // Postcondition test
        // No postcondition detected
        // Invariant test after postcondition
        // Update behavioral's state
        fetchServiceBehavior(methodID("deposit"));
        // Return of the redirect method if needed
    }


    public void validate(tamago.ext.aca.Quad quad) throws tamago.TamagoException{
        // Initialisation of variables
        // Invariant test before precondition
        // Environment of Precondition
        if(!canCallMethod(methodID("validate"))) {
            throw new TamagoCCBehaviorException(methodID("validate"));
        }
        // Precondition test
        // No precondition detected
        // Initialisation of at pre expression
        // redirect call
        tamago_decorated_component.validate(quad);
        // Environment of Postcondition
        // Postcondition test
        // No postcondition detected
        // Invariant test after postcondition
        // Update behavioral's state
        fetchServiceBehavior(methodID("validate"));
        // Return of the redirect method if needed
    }
}