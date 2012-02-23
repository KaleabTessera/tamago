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
            if(!) {
                fail("Wrong Test");
            }
            code.init();
            boolean __tamagotest_oracle = (code.getOpNumber() < 0);
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
            if(!) {
                fail("Wrong Test");
            }
            code.deposit(aca);
            assertTrue(true);
        }
        catch(Exception exc) {
            fail(("Test failed at step 1. " + exc.getMessage()));
        }
        // Step: 2 Method: register
        // Transition: deposed--{register}-->registered
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.register(aca);
            boolean __tamagotest_oracle = (code.getOpNumber() > 0);
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
            if(!) {
                fail("Wrong Test");
            }
            code.check(aca);
            boolean __tamagotest_oracle = (code.getOpNumber() > 0);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 3. " + exc.getMessage()));
        }
        // Step: 4 Method: validate_dir
        // Transition: waited--{validate_dir}-->validateDir
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.validate_director(aca);
            assertTrue(true);
        }
        catch(Exception exc) {
            fail(("Test failed at step 4. " + exc.getMessage()));
        }
        // Step: 5 Method: validate
        // Transition: validateDir--{validate}-->completed
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.validate(aca);
            assertTrue(true);
        }
        catch(Exception exc) {
            fail(("Test failed at step 5. " + exc.getMessage()));
        }
        // Step: 6 Method: init
        // Transition: completed--{init}-->initialized
        try {
            if(!) {
                fail("Wrong Test");
            }
            code.init();
            boolean __tamagotest_oracle = (code.getOpNumber() < 0);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 6. " + exc.getMessage()));
        }
        // Step: 7 Method: deposit
        // Transition: initialized--{deposit}-->deposed
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.deposit(aca);
            assertTrue(true);
        }
        catch(Exception exc) {
            fail(("Test failed at step 7. " + exc.getMessage()));
        }
        // Step: 8 Method: register
        // Transition: deposed--{register}-->registered
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.register(aca);
            boolean __tamagotest_oracle = (code.getOpNumber() > 0);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 8. " + exc.getMessage()));
        }
        // Step: 9 Method: check
        // Transition: registered--{check}-->waited
        try {
            // TODO fulfill the parameter: aca
            tamago.ext.aca2.ACA aca;
            if(!) {
                fail("Wrong Test");
            }
            code.check(aca);
            boolean __tamagotest_oracle = (code.getOpNumber() > 0);
            assertTrue(__tamagotest_oracle);
        }
        catch(Exception exc) {
            fail(("Test failed at step 9. " + exc.getMessage()));
        }
    }
}