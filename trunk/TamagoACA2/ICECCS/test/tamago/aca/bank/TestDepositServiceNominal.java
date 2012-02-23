package tamago.aca.bank;
public class TestDepositServiceNominal
     extends junit.framework.TestCase
{
    // Members Variables
    private DepositService code;

    // Properties

    // Methods
    public TestDepositServiceNominal ()
    {
    }


    public void setUp(){
        code = null;
        // Make user definition below this line
    }


    public void testScenario0(){
        // Step: 0 Method: validate
        // Transition: 0--{validate}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 0. " + exc.getMessage()));
        }
        // Step: 1 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 1. " + exc.getMessage()));
        }
        // Step: 2 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 2. " + exc.getMessage()));
        }
        // Step: 3 Method: register
        // Transition: 0--{register}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 3. " + exc.getMessage()));
        }
        // Step: 4 Method: register
        // Transition: 0--{register}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 4. " + exc.getMessage()));
        }
        // Step: 5 Method: check
        // Transition: 0--{check}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 5. " + exc.getMessage()));
        }
        // Step: 6 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 6. " + exc.getMessage()));
        }
        // Step: 7 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 7. " + exc.getMessage()));
        }
        // Step: 8 Method: check
        // Transition: 0--{check}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 8. " + exc.getMessage()));
        }
        // Step: 9 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 9. " + exc.getMessage()));
        }
    }
}