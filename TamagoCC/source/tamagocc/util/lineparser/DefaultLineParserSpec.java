/**
 * 
 */
package tamagocc.util.lineparser;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.LineParserException;

/**
 * @author hakim
 *
 */
public class DefaultLineParserSpec implements LineParserSpec {

	protected ArrayList<String> aliases;
	protected String command;
	protected String description;
	
	
	public DefaultLineParserSpec(String cmd) {
		this(cmd,"(no description)");
	}

	public DefaultLineParserSpec(String cmd,String description) {
		this(cmd,description,new ArrayList<String>());
	}
	
	public DefaultLineParserSpec(String cmd,String description, Collection<String> aliases) {
		this.aliases = new ArrayList<String>(aliases);
		command = cmd;
		this.description = description;
	}

	public DefaultLineParserSpec(String cmd,String description, String ...aliases) {
		this.aliases = new ArrayList<String>();
		for (String string : aliases) {
			this.aliases.add(string);
		}
		command = cmd;
		this.description = description;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	@Override
	public String getCommand() {
		return command;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getAliases()
	 */
	@Override
	public Collection<String> getAliases() {
		return aliases;
	}

	public void addAlias(String alias) {
		if(!aliases.contains(alias))
			aliases.add(alias);
	}
	
	
	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getArity() {
		return 0; // aucun argument
	}

	@Override
	public void setArgument(int pos, String value) throws LineParserException {
		
	}

	@Override
	public void fire() throws LineParserException {
		
	}

	@Override
	public boolean immediateFire() {
		return false;
	}

	@Override
	public boolean isOptionnal() {
		return true;
	}

}
