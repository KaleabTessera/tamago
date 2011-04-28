package tamagocc.generator;

public interface TamagoCCIGenerator {

	/**
	 *Gets the current builder generator. 
	 * @return Return the generator for the specified language
	 */
	public abstract TamagoCCGeneratorTargetLanguageBuilder getBuilder();

}