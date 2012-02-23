package tamago.aca.bank;
import tamago.*;
import tamago.ext.tamagocc.*;
/*
    
 ******************************************
 *                CDeposit                *
 ******************************************
 *
 * YOU MUST EDIT THIS FILE.
 * TAMAGOCC GENERATES AUTOMATICALLY THE SKELETON.
 * Created date : Thu Feb 23 22:31:07 CET 2012
 * authors : Hakim Belhaouari and Frederic Peschanski
 * site : http://code.google.com/p/tamago/

*/
public class CDepositStub
    implements tamago.aca.bank.CDeposit
{
    // Members Variables
    // Don't manage this variable, let the framework manage it.
    private tamago.aca.bank.Client user;
    // Don't manage this variable, let the framework manage it.
    private tamago.aca.bank.Cheque cheq;
    // Don't manage this variable, let the framework manage it.
    private tamago.aca.bank.Bank bank;

    // Properties
    /**
        Getter of the property opNumber
    */
    public int getOpNumber(){
        // TODO : complete this method
        return 0;
    }
    /**
        Getter of the property acaInitialised
    */
    public boolean getAcaInitialised(){
        // TODO : complete this method
        return false;
    }
    /**
        Setter of the property acaInitialised
    */
    public void setAcaInitialised(boolean acaInitialised){
        // TODO : complete this method
    }

    // Methods
    // DON'T MODIFY THIS METHOD
    public void bind(String label, TamagoCCService service) throws ServiceBindException{
        if(label.equals("user")) {
            user = ((Client) service);
            return ;
        }
        if(label.equals("cheq")) {
            cheq = ((Cheque) service);
            return ;
        }
        if(label.equals("bank")) {
            bank = ((Bank) service);
            return ;
        }
        throw new ServiceBindException(label);
    }


    public void core_set_properties(String property, Object value){
        if("opNumber".equals(property)) {
            // Complete the affectation of the observable properties.
        }
        if("acaInitialised".equals(property)) {
            // Complete the affectation of the observable properties.
        }
    }


    public CDepositStub ()
    {
    }


    public void validate(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void check(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void validate_director(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void cancel(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void init() throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void register(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public void deposit(tamago.ext.aca2.ACA aca) throws tamago.TamagoException{
        // TODO : complete this method
    }


    public tamago.aca.bank.Client user(){
        // DON'T MODIFY THIS METHOD
        return user;
    }


    public void setuser(tamago.aca.bank.Client tamago_param){
        user = tamago_param;
    }


    public tamago.aca.bank.Cheque cheq(){
        // DON'T MODIFY THIS METHOD
        return cheq;
    }


    public void setcheq(tamago.aca.bank.Cheque tamago_param){
        cheq = tamago_param;
    }


    public tamago.aca.bank.Bank bank(){
        // DON'T MODIFY THIS METHOD
        return bank;
    }


    public void setbank(tamago.aca.bank.Bank tamago_param){
        bank = tamago_param;
    }
}