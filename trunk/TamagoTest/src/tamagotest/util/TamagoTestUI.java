/**
 * 
 */
package tamagotest.util;

/**
 * @author Hakim Belhaouari
 *
 */
public interface TamagoTestUI {

	void beginScenario();

	void endScenario();

	void finishStep();

	boolean canContinue();

	void beginWriteJUnit();

	void endWriteJUnit();

	void beginWriteTest();

	void endWriteTest();

}
