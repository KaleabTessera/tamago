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
            int clid = -5179;
            int cid = -3984;
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
        // Step: 1 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 7325;
            int cid = -253;
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
            fail(("Test failed at step 1. " + exc.getMessage()));
        }
        // Step: 2 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 9484;
            int cid = 9923;
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
            fail(("Test failed at step 2. " + exc.getMessage()));
        }
        // Step: 3 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -1394;
            int cid = -7718;
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
            fail(("Test failed at step 3. " + exc.getMessage()));
        }
        // Step: 4 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 715;
            int cid = -6358;
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
        // Step: 5 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 2559;
            int cid = 9616;
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
            fail(("Test failed at step 5. " + exc.getMessage()));
        }
        // Step: 6 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 1781;
            int cid = 468;
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
            fail(("Test failed at step 6. " + exc.getMessage()));
        }
        // Step: 7 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -4889;
            int cid = -5414;
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
            fail(("Test failed at step 7. " + exc.getMessage()));
        }
        // Step: 8 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 8692;
            int cid = -5180;
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
        // Step: 9 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -6716;
            int cid = 9834;
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
            fail(("Test failed at step 9. " + exc.getMessage()));
        }
        // Step: 10 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -5648;
            int cid = 1397;
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
            fail(("Test failed at step 10. " + exc.getMessage()));
        }
        // Step: 11 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -7464;
            int cid = -6585;
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
            fail(("Test failed at step 11. " + exc.getMessage()));
        }
        // Step: 12 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 4091;
            int cid = 1386;
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
        // Step: 13 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 5204;
            int cid = -3020;
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
            fail(("Test failed at step 13. " + exc.getMessage()));
        }
        // Step: 14 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 8085;
            int cid = 8469;
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
            fail(("Test failed at step 14. " + exc.getMessage()));
        }
        // Step: 15 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -3092;
            int cid = -6656;
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
            fail(("Test failed at step 15. " + exc.getMessage()));
        }
        // Step: 16 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -147;
            int cid = 780;
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
        // Step: 17 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -5125;
            int cid = 4719;
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
            fail(("Test failed at step 17. " + exc.getMessage()));
        }
        // Step: 18 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -3557;
            int cid = -3828;
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
            fail(("Test failed at step 18. " + exc.getMessage()));
        }
        // Step: 19 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -3663;
            int cid = -538;
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
            fail(("Test failed at step 19. " + exc.getMessage()));
        }
        // Step: 20 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 9078;
            int cid = -6485;
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
            fail(("Test failed at step 20. " + exc.getMessage()));
        }
        // Step: 21 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -7706;
            int cid = 2255;
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
            fail(("Test failed at step 21. " + exc.getMessage()));
        }
        // Step: 22 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -4282;
            int cid = -923;
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
        // Step: 23 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 6956;
            int cid = 7922;
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
            fail(("Test failed at step 23. " + exc.getMessage()));
        }
        // Step: 24 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 838;
            int cid = 5894;
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
            fail(("Test failed at step 24. " + exc.getMessage()));
        }
        // Step: 25 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 3705;
            int cid = 5600;
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
            fail(("Test failed at step 25. " + exc.getMessage()));
        }
        // Step: 26 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -8443;
            int cid = -3649;
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
            fail(("Test failed at step 26. " + exc.getMessage()));
        }
        // Step: 27 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 6208;
            int cid = 2611;
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
            fail(("Test failed at step 27. " + exc.getMessage()));
        }
        // Step: 28 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 629;
            int cid = 2141;
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
            fail(("Test failed at step 28. " + exc.getMessage()));
        }
        // Step: 29 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 211;
            int cid = -2178;
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
            fail(("Test failed at step 29. " + exc.getMessage()));
        }
        // Step: 30 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 6235;
            int cid = 3658;
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
            fail(("Test failed at step 30. " + exc.getMessage()));
        }
        // Step: 31 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 3426;
            int cid = 3934;
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
        // Step: 32 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -6679;
            int cid = 6598;
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
            fail(("Test failed at step 32. " + exc.getMessage()));
        }
        // Step: 33 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 6183;
            int cid = 2892;
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
            fail(("Test failed at step 33. " + exc.getMessage()));
        }
        // Step: 34 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -802;
            int cid = -4035;
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
            fail(("Test failed at step 34. " + exc.getMessage()));
        }
        // Step: 35 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 5270;
            int cid = -7219;
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
        // Step: 36 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 1342;
            int cid = 8771;
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
            fail(("Test failed at step 36. " + exc.getMessage()));
        }
        // Step: 37 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -9475;
            int cid = 7837;
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
            fail(("Test failed at step 37. " + exc.getMessage()));
        }
        // Step: 38 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -2163;
            int cid = 5671;
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
            fail(("Test failed at step 38. " + exc.getMessage()));
        }
        // Step: 39 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 7202;
            int cid = -1111;
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
            fail(("Test failed at step 39. " + exc.getMessage()));
        }
        // Step: 40 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 5709;
            int cid = 4689;
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
            fail(("Test failed at step 40. " + exc.getMessage()));
        }
        // Step: 41 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 78;
            int cid = 2186;
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
            fail(("Test failed at step 41. " + exc.getMessage()));
        }
        // Step: 42 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 6172;
            int cid = -4175;
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
            fail(("Test failed at step 42. " + exc.getMessage()));
        }
        // Step: 43 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -2419;
            int cid = -3876;
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
            fail(("Test failed at step 43. " + exc.getMessage()));
        }
        // Step: 44 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 1915;
            int cid = 840;
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
        // Step: 45 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 219;
            int cid = 8088;
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
            fail(("Test failed at step 45. " + exc.getMessage()));
        }
        // Step: 46 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 4874;
            int cid = -5656;
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
            fail(("Test failed at step 46. " + exc.getMessage()));
        }
        // Step: 47 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -5391;
            int cid = 2209;
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
            fail(("Test failed at step 47. " + exc.getMessage()));
        }
        // Step: 48 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 697;
            int cid = 77;
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
            fail(("Test failed at step 48. " + exc.getMessage()));
        }
        // Step: 49 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 1015;
            int cid = 7221;
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
            fail(("Test failed at step 49. " + exc.getMessage()));
        }
        // Step: 50 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 762;
            int cid = 5477;
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
            fail(("Test failed at step 50. " + exc.getMessage()));
        }
        // Step: 51 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -9917;
            int cid = -1437;
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
            fail(("Test failed at step 51. " + exc.getMessage()));
        }
        // Step: 52 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 162;
            int cid = 337;
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
            fail(("Test failed at step 52. " + exc.getMessage()));
        }
        // Step: 53 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -7315;
            int cid = -7875;
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
            fail(("Test failed at step 53. " + exc.getMessage()));
        }
        // Step: 54 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 3869;
            int cid = -3339;
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
            fail(("Test failed at step 54. " + exc.getMessage()));
        }
        // Step: 55 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -8771;
            int cid = -5059;
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
        // Step: 56 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 6974;
            int cid = 930;
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
            fail(("Test failed at step 56. " + exc.getMessage()));
        }
        // Step: 57 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 214;
            int cid = 9630;
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
            fail(("Test failed at step 57. " + exc.getMessage()));
        }
        // Step: 58 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 4248;
            int cid = 3461;
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
        // Step: 59 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -2629;
            int cid = -2211;
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
            fail(("Test failed at step 59. " + exc.getMessage()));
        }
        // Step: 60 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 8067;
            int cid = -9620;
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
            fail(("Test failed at step 60. " + exc.getMessage()));
        }
        // Step: 61 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -264;
            int cid = 8183;
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
            fail(("Test failed at step 61. " + exc.getMessage()));
        }
        // Step: 62 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -3609;
            int cid = -6025;
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
            fail(("Test failed at step 62. " + exc.getMessage()));
        }
        // Step: 63 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 4800;
            int cid = 2746;
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
            fail(("Test failed at step 63. " + exc.getMessage()));
        }
        // Step: 64 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -6422;
            int cid = 5839;
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
            fail(("Test failed at step 64. " + exc.getMessage()));
        }
        // Step: 65 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 8163;
            int cid = 9050;
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
            fail(("Test failed at step 65. " + exc.getMessage()));
        }
        // Step: 66 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 4031;
            int cid = 4018;
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
            fail(("Test failed at step 66. " + exc.getMessage()));
        }
        // Step: 67 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 3164;
            int cid = 6094;
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
            fail(("Test failed at step 67. " + exc.getMessage()));
        }
        // Step: 68 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 8884;
            int cid = 8176;
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
            fail(("Test failed at step 68. " + exc.getMessage()));
        }
        // Step: 69 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -2366;
            int cid = -5444;
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
            fail(("Test failed at step 69. " + exc.getMessage()));
        }
        // Step: 70 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 6960;
            int cid = 654;
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
            fail(("Test failed at step 70. " + exc.getMessage()));
        }
        // Step: 71 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -9132;
            int cid = 8688;
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
            fail(("Test failed at step 71. " + exc.getMessage()));
        }
        // Step: 72 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -4466;
            int cid = 3892;
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
            fail(("Test failed at step 72. " + exc.getMessage()));
        }
        // Step: 73 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = 4540;
            int cid = -6333;
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
            fail(("Test failed at step 73. " + exc.getMessage()));
        }
        // Step: 74 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -2417;
            int cid = 9855;
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
            fail(("Test failed at step 74. " + exc.getMessage()));
        }
        // Step: 75 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -4333;
            int cid = 5374;
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
        // Step: 76 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = 8514;
            int cid = 5666;
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
            fail(("Test failed at step 76. " + exc.getMessage()));
        }
        // Step: 77 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 9315;
            int cid = -4634;
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
            fail(("Test failed at step 77. " + exc.getMessage()));
        }
        // Step: 78 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -8769;
            int cid = 1280;
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
            fail(("Test failed at step 78. " + exc.getMessage()));
        }
        // Step: 79 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 2599;
            int cid = -4557;
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
            fail(("Test failed at step 79. " + exc.getMessage()));
        }
        // Step: 80 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 2659;
            int cid = -9125;
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
            fail(("Test failed at step 80. " + exc.getMessage()));
        }
        // Step: 81 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 3531;
            int cid = 3194;
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
            fail(("Test failed at step 81. " + exc.getMessage()));
        }
        // Step: 82 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 6936;
            int cid = -4059;
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
        // Step: 83 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -8563;
            int cid = 9272;
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
            fail(("Test failed at step 83. " + exc.getMessage()));
        }
        // Step: 84 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -5016;
            int cid = 8934;
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
            fail(("Test failed at step 84. " + exc.getMessage()));
        }
        // Step: 85 Method: validate_dir
        // Transition: 0--{validate_dir}-->0
        try {
            int clid = -2103;
            int cid = -9694;
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
            fail(("Test failed at step 85. " + exc.getMessage()));
        }
        // Step: 86 Method: check
        // Transition: 0--{check}-->0
        try {
            int clid = -3671;
            int cid = 5815;
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
            fail(("Test failed at step 86. " + exc.getMessage()));
        }
        // Step: 87 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 7772;
            int cid = 2506;
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
            fail(("Test failed at step 87. " + exc.getMessage()));
        }
        // Step: 88 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = -8476;
            int cid = -9244;
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
        // Step: 89 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 7248;
            int cid = 3605;
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
            fail(("Test failed at step 89. " + exc.getMessage()));
        }
        // Step: 90 Method: validate
        // Transition: 0--{validate}-->0
        try {
            int clid = 3168;
            int cid = 1403;
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
            fail(("Test failed at step 90. " + exc.getMessage()));
        }
        // Step: 91 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 5314;
            int cid = -3619;
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
        // Step: 92 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 1461;
            int cid = -318;
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
            fail(("Test failed at step 92. " + exc.getMessage()));
        }
        // Step: 93 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = 879;
            int cid = -6573;
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
            fail(("Test failed at step 93. " + exc.getMessage()));
        }
        // Step: 94 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = -8167;
            int cid = -3946;
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
            fail(("Test failed at step 94. " + exc.getMessage()));
        }
        // Step: 95 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 6149;
            int cid = -8425;
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
        // Step: 96 Method: deposit
        // Transition: 0--{deposit}-->0
        try {
            int clid = -7355;
            int cid = -4258;
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
            fail(("Test failed at step 96. " + exc.getMessage()));
        }
        // Step: 97 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = 9093;
            int cid = -599;
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
            fail(("Test failed at step 97. " + exc.getMessage()));
        }
        // Step: 98 Method: register
        // Transition: 0--{register}-->0
        try {
            int clid = 7141;
            int cid = -4468;
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
        // Step: 99 Method: cancel
        // Transition: 0--{cancel}-->0
        try {
            int clid = -5329;
            int cid = -4692;
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
            fail(("Test failed at step 99. " + exc.getMessage()));
        }
    }
}