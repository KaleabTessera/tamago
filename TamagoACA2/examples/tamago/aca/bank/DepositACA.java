package tamago.aca.bank;
/*
    
 ******************************************
 *               DepositACA               *
 ******************************************
 *
 * DO NOT EDIT THIS FILE.
 * THIS FILE IS GENERATED AUTOMATICALLY BY TAMAGOCC
 * Created date : Thu Feb 02 17:02:18 CET 2012
 * authors : Hakim Belhaouari and Frederic Peschanski
 * site : http://www-poleia.lip6.fr/~belhaouari/Tamago/

*/
public interface DepositACA
    extends tamago.ext.tamagocc.TamagoCCComponent
{
    // Members Variables

    // Properties
    /**
        Getter of the property historic
    */
    public tamago.ext.aca2.Historic getHistoric();

    // Methods
    /**
        This method allow user to access to the required service.(You do not need to implement this method, TamagoCC generates it in the skeleton)
    */
    public tamago.aca.bank.Deposit acamodel();


    public void validate_director(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;


    public void validate(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;


    public void register(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;


    public void check(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;


    public void cancel(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;


    public void deposit(int clid, int cid, tamago.ext.aca2.ACA aca) throws tamago.TamagoException;
}