package tamago.aca.bank;
public class TestDeposit
     extends junit.framework.TestCase
{
    // Members Variables
    private Deposit code;

    // Properties

    // Methods
    public TestDeposit ()
    {
    }


    public void setUp(){
        code = null;
        // Make user definition below this line
    }


    public void testScenario0(){
        // Step: 0 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 2275;
            int cid = -5317;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 0. " + exc.getMessage()));
        }
        // Step: 1 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -7735;
            int cid = 5896;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 1. " + exc.getMessage()));
        }
        // Step: 2 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -1946;
            int cid = 9139;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 2. " + exc.getMessage()));
        }
        // Step: 3 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -1376;
            int cid = -9319;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 3. " + exc.getMessage()));
        }
        // Step: 4 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -805;
            int cid = -3052;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 4. " + exc.getMessage()));
        }
        // Step: 5 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -8412;
            int cid = 3681;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 5. " + exc.getMessage()));
        }
        // Step: 6 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -4218;
            int cid = 7971;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 6. " + exc.getMessage()));
        }
        // Step: 7 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -7247;
            int cid = -4922;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 7. " + exc.getMessage()));
        }
        // Step: 8 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 1447;
            int cid = -276;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 8. " + exc.getMessage()));
        }
        // Step: 9 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -7886;
            int cid = -9542;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 9. " + exc.getMessage()));
        }
        // Step: 10 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -5954;
            int cid = 8132;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 10. " + exc.getMessage()));
        }
        // Step: 11 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 2001;
            int cid = -6466;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 11. " + exc.getMessage()));
        }
        // Step: 12 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -2776;
            int cid = -157;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 12. " + exc.getMessage()));
        }
        // Step: 13 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 5672;
            int cid = -8791;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 13. " + exc.getMessage()));
        }
        // Step: 14 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -8657;
            int cid = -7921;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 14. " + exc.getMessage()));
        }
        // Step: 15 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -4854;
            int cid = -3244;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 15. " + exc.getMessage()));
        }
        // Step: 16 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -2957;
            int cid = -4651;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 16. " + exc.getMessage()));
        }
        // Step: 17 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -2476;
            int cid = 7062;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 17. " + exc.getMessage()));
        }
        // Step: 18 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 2051;
            int cid = -6838;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 18. " + exc.getMessage()));
        }
        // Step: 19 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 2709;
            int cid = -6034;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 19. " + exc.getMessage()));
        }
        // Step: 20 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -2903;
            int cid = -1851;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 20. " + exc.getMessage()));
        }
        // Step: 21 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 2851;
            int cid = -9626;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 21. " + exc.getMessage()));
        }
        // Step: 22 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 60;
            int cid = -5658;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 22. " + exc.getMessage()));
        }
        // Step: 23 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -3262;
            int cid = 5163;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 23. " + exc.getMessage()));
        }
        // Step: 24 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 7018;
            int cid = -5051;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 24. " + exc.getMessage()));
        }
        // Step: 25 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -4667;
            int cid = 9846;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 25. " + exc.getMessage()));
        }
        // Step: 26 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 3062;
            int cid = 8664;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 26. " + exc.getMessage()));
        }
        // Step: 27 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 9274;
            int cid = -8671;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 27. " + exc.getMessage()));
        }
        // Step: 28 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -5132;
            int cid = -7175;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 28. " + exc.getMessage()));
        }
        // Step: 29 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 5354;
            int cid = 5800;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 29. " + exc.getMessage()));
        }
        // Step: 30 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -1730;
            int cid = -141;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 30. " + exc.getMessage()));
        }
        // Step: 31 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 6844;
            int cid = 5072;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 31. " + exc.getMessage()));
        }
        // Step: 32 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -5624;
            int cid = -4217;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 32. " + exc.getMessage()));
        }
        // Step: 33 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 5324;
            int cid = -9023;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 33. " + exc.getMessage()));
        }
        // Step: 34 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -8799;
            int cid = -3017;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 34. " + exc.getMessage()));
        }
        // Step: 35 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -3623;
            int cid = 3371;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 35. " + exc.getMessage()));
        }
        // Step: 36 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -5564;
            int cid = -1470;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 36. " + exc.getMessage()));
        }
        // Step: 37 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 6977;
            int cid = 5055;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 37. " + exc.getMessage()));
        }
        // Step: 38 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 6158;
            int cid = 1296;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 38. " + exc.getMessage()));
        }
        // Step: 39 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -4920;
            int cid = -9641;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 39. " + exc.getMessage()));
        }
        // Step: 40 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -5350;
            int cid = 5167;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 40. " + exc.getMessage()));
        }
        // Step: 41 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -8359;
            int cid = -1764;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 41. " + exc.getMessage()));
        }
        // Step: 42 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -4550;
            int cid = -3158;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 42. " + exc.getMessage()));
        }
        // Step: 43 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 4974;
            int cid = 3925;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 43. " + exc.getMessage()));
        }
        // Step: 44 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -7679;
            int cid = -2310;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 44. " + exc.getMessage()));
        }
        // Step: 45 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -7735;
            int cid = -9421;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 45. " + exc.getMessage()));
        }
        // Step: 46 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -927;
            int cid = -6527;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 46. " + exc.getMessage()));
        }
        // Step: 47 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 4304;
            int cid = -4596;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 47. " + exc.getMessage()));
        }
        // Step: 48 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 781;
            int cid = 9269;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 48. " + exc.getMessage()));
        }
        // Step: 49 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 2274;
            int cid = -9756;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 49. " + exc.getMessage()));
        }
        // Step: 50 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -9994;
            int cid = -9745;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 50. " + exc.getMessage()));
        }
        // Step: 51 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 6929;
            int cid = -4892;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 51. " + exc.getMessage()));
        }
        // Step: 52 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 1397;
            int cid = -9212;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 52. " + exc.getMessage()));
        }
        // Step: 53 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 7863;
            int cid = -6131;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 53. " + exc.getMessage()));
        }
        // Step: 54 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 2859;
            int cid = 1501;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 54. " + exc.getMessage()));
        }
        // Step: 55 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 4821;
            int cid = -7999;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 55. " + exc.getMessage()));
        }
        // Step: 56 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 8129;
            int cid = -6022;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 56. " + exc.getMessage()));
        }
        // Step: 57 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -1434;
            int cid = -9302;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 57. " + exc.getMessage()));
        }
        // Step: 58 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -9144;
            int cid = -9868;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 58. " + exc.getMessage()));
        }
        // Step: 59 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 4595;
            int cid = -8425;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 59. " + exc.getMessage()));
        }
        // Step: 60 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 2931;
            int cid = -6561;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 60. " + exc.getMessage()));
        }
        // Step: 61 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -7426;
            int cid = -1594;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 61. " + exc.getMessage()));
        }
        // Step: 62 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 4692;
            int cid = -9649;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 62. " + exc.getMessage()));
        }
        // Step: 63 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 9835;
            int cid = -3602;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 63. " + exc.getMessage()));
        }
        // Step: 64 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -264;
            int cid = 8892;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 64. " + exc.getMessage()));
        }
        // Step: 65 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 6620;
            int cid = 6117;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 65. " + exc.getMessage()));
        }
        // Step: 66 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 1898;
            int cid = -6424;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 66. " + exc.getMessage()));
        }
        // Step: 67 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -5323;
            int cid = 2351;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 67. " + exc.getMessage()));
        }
        // Step: 68 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -332;
            int cid = -5392;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 68. " + exc.getMessage()));
        }
        // Step: 69 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 723;
            int cid = 3835;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 69. " + exc.getMessage()));
        }
        // Step: 70 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 1601;
            int cid = -7667;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 70. " + exc.getMessage()));
        }
        // Step: 71 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -9547;
            int cid = -6575;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 71. " + exc.getMessage()));
        }
        // Step: 72 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -5368;
            int cid = 2136;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 72. " + exc.getMessage()));
        }
        // Step: 73 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 373;
            int cid = 8768;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 73. " + exc.getMessage()));
        }
        // Step: 74 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 4986;
            int cid = 7801;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 74. " + exc.getMessage()));
        }
        // Step: 75 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -8126;
            int cid = -2899;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 75. " + exc.getMessage()));
        }
        // Step: 76 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -9133;
            int cid = -8862;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 76. " + exc.getMessage()));
        }
        // Step: 77 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -2429;
            int cid = -6253;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 77. " + exc.getMessage()));
        }
        // Step: 78 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -1795;
            int cid = -2748;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 78. " + exc.getMessage()));
        }
        // Step: 79 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 9983;
            int cid = 4244;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 79. " + exc.getMessage()));
        }
        // Step: 80 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 5388;
            int cid = -9216;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 80. " + exc.getMessage()));
        }
        // Step: 81 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 6021;
            int cid = -4191;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 81. " + exc.getMessage()));
        }
        // Step: 82 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -9007;
            int cid = -6270;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 82. " + exc.getMessage()));
        }
        // Step: 83 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 2078;
            int cid = 1330;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 83. " + exc.getMessage()));
        }
        // Step: 84 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 2351;
            int cid = -5967;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 84. " + exc.getMessage()));
        }
        // Step: 85 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 2879;
            int cid = -800;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 85. " + exc.getMessage()));
        }
        // Step: 86 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 8573;
            int cid = -63;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 86. " + exc.getMessage()));
        }
        // Step: 87 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -9990;
            int cid = -1176;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 87. " + exc.getMessage()));
        }
        // Step: 88 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -1044;
            int cid = -7202;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 88. " + exc.getMessage()));
        }
        // Step: 89 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 1056;
            int cid = -1261;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 89. " + exc.getMessage()));
        }
        // Step: 90 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 5140;
            int cid = -33;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.check(clid, cid, aca);
            boolean __tamagotest_oracle = ("check".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 90. " + exc.getMessage()));
        }
        // Step: 91 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 399;
            int cid = -763;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 91. " + exc.getMessage()));
        }
        // Step: 92 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 4318;
            int cid = 934;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 92. " + exc.getMessage()));
        }
        // Step: 93 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -3293;
            int cid = -3870;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.validate_director(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate_dir".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("validate") && (aca.user != code.getHistoric().getSecuParamFromID("validate").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 93. " + exc.getMessage()));
        }
        // Step: 94 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 6553;
            int cid = -7079;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 94. " + exc.getMessage()));
        }
        // Step: 95 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 2982;
            int cid = -983;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"elisa".equals(aca.user) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.cancel(clid, cid, aca);
            boolean __tamagotest_oracle = ("cancel".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 95. " + exc.getMessage()));
        }
        // Step: 96 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 1006;
            int cid = 284;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 96. " + exc.getMessage()));
        }
        // Step: 97 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -8257;
            int cid = -3312;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("banker".equals(aca.role) && "director".equals(aca.role) && !"Toronto".equals(aca.org) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.validate(clid, cid, aca);
            boolean __tamagotest_oracle = ("validate".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (aca.user != code.getHistoric().getSecuParamFromID("deposit").getUser()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 97. " + exc.getMessage()));
        }
        // Step: 98 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -676;
            int cid = -6421;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!(("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org)))) {
                fail("Wrong Test");
            }
            code.register(clid, cid, aca);
            boolean __tamagotest_oracle = ("register".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()) && code.getHistoric().mustDone("deposit") && (code.getHistoric().getSecuParamFromID("deposit").getUser() == aca.user));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 98. " + exc.getMessage()));
        }
        // Step: 99 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 7297;
            int cid = 930;
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!("clerk".equals(aca.role) && "banker".equals(aca.role) && !"customer".equals(aca.role) && (("alphonse".equals(aca.user) || "boris".equals(aca.user) || "catherine".equals(aca.user) || "damien".equals(aca.user) || "elise".equals(aca.user) || "franck".equals(aca.user)) && ("customer".equals(aca.role) || "clerk".equals(aca.role) || "banker".equals(aca.role) || "director".equals(aca.role)) && ("montreal".equals(aca.org) || "toronto".equals(aca.org))))) {
                fail("Wrong Test");
            }
            code.deposit(clid, cid, aca);
            boolean __tamagotest_oracle = ("deposit".equals(code.getHistoric().lastAction()) && (aca == code.getHistoric().lastSecuParam()));
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 99. " + exc.getMessage()));
        }
    }
}