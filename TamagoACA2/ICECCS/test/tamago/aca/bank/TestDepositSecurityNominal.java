package tamago.aca.bank;
public class TestDepositSecurityNominal
     extends junit.framework.TestCase
{
    // Members Variables
    private DepositSecurity code;

    // Properties

    // Methods
    public TestDepositSecurityNominal ()
    {
    }


    public void setUp(){
        code = null;
        // Make user definition below this line
    }


    public void testScenario0(){
        // Step: 0 Method: init
        // Transition: ninit--{init}-->initialized
        try {
            code.init();
            boolean __tamagotest_oracle = ((code.getOpNumber() < 0) && acaInitialised);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 0. " + exc.getMessage()));
        }
        // Step: 1 Method: deposit
        // Transition: initialized--{deposit}-->deposed
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(code.getPlay().isCorrectACA(aca) && "clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 1. " + exc.getMessage()));
        }
        // Step: 2 Method: register
        // Transition: deposed--{register}-->registered
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(code.getPlay().isCorrectACA(aca) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user))) {
                fail("Wrong Test");
            }
            code.register(aca);
            boolean __tamagotest_oracle = ((code.getOpNumber() > 0) && ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam())));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 2. " + exc.getMessage()));
        }
        // Step: 3 Method: check
        // Transition: registered--{check}-->waited
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(code.getPlay().isCorrectACA(aca) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.check(aca);
            boolean __tamagotest_oracle = ((code.getOpNumber() > 0) && ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam())));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 3. " + exc.getMessage()));
        }
        // Step: 4 Method: cancel
        // Transition: waited--{cancel}-->cancelled
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(code.getPlay().isCorrectACA(aca) && "banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user))) {
                fail("Wrong Test");
            }
            code.cancel(aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 4. " + exc.getMessage()));
        }
        // Step: 5 Method: init
        // Transition: cancelled--{init}-->initialized
        try {
            code.init();
            boolean __tamagotest_oracle = ((code.getOpNumber() < 0) && acaInitialised);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 5. " + exc.getMessage()));
        }
    }
}