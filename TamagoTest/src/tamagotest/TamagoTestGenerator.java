/**
 * 
 */
package tamagotest;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Generate the output format from the Scenario includes in the TamagoTestContext parameter.
 * All inherited implementations MUST provide a constructor with an unique argument that is the context
 * (TamagoTestContext).
 * 
 * @author Hakim Belhaouari
 *
 */
public abstract class TamagoTestGenerator {

	protected TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public TamagoTestGenerator(TamagoTestContext ctx) {
		this.ctx = ctx;
	}

	public abstract void prepare() throws TamagoTestException;
	
	public abstract void write() throws TamagoTestException,IOException;
	
	public abstract void write(OutputStream os) throws TamagoTestException,IOException;
}
