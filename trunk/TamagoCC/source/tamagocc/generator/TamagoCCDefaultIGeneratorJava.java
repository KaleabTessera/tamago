/**
 * 
 */
package tamagocc.generator;

import tamagocc.javasource.TamagoCCJavaSourceBuilder;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCDefaultIGeneratorJava implements TamagoCCIGenerator {
	private static TamagoCCIGenerator instance = new TamagoCCDefaultIGeneratorJava();
	private TamagoCCJavaSourceBuilder builder;
	
	/**
	 * 
	 */
	private TamagoCCDefaultIGeneratorJava() {
		builder = new TamagoCCJavaSourceBuilder();
	}

	/**
	 * @see tamagocc.generator.TamagoCCIGenerator#getBuilder()
	 */
	@Override
	public TamagoCCGeneratorTargetLanguageBuilder getBuilder() {
		return builder;
	}

	public static TamagoCCIGenerator getInstance() {
		return instance ;
	}

}
