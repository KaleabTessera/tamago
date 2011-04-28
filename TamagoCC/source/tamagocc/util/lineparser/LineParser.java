/**
 * 
 */
package tamagocc.util.lineparser;

import java.util.ArrayList;
import java.util.Hashtable;

import tamagocc.exception.LineParserException;

/**
 * This class is a tool for helping developpers to parse the command line information.
 * It can regonize two kinds of information:
 * <ol>
 * <li>a command with thoses arguments</li>
 * <li>other informations (called default spec)</li>
 * </ol>
 * Each command line is defined with a specific object that implement {@link LineParserSpec},
 * and other default spec is required and must inherit of {@link LineParserDefaultSpec}.
 * <br>
 * Furthermore by default the LineParser generate two default command ('-h' and '--help')  
 * for generating automatic help from specified command. 
 * 
 * @author Hakim Belhaouari
 *
 */
public class LineParser {

	/** hashtable of command */ 
	private Hashtable<String, LineParserSpec> specs;
	/** an array of all specs */
	private ArrayList<LineParserSpec> visited;
	/** name of the executable */
	private String exename;
	/** description of the application */
	private String description;
	/** default behavior for the files */
	private LineParserDefaultSpec defspec;
	/** contains the number of default argument */ 
	private int listdefitem;
	/** indicates if we can call the help */
	private boolean askHelp;
	
	/**
	 * This class represents the command '-h' and '--help' for generating default
	 * documentation.
	 * 
	 * @author Hakim Belhaouari
	 */
	class HelpCommand implements LineParserSpec {
		private String cmd;
		
		HelpCommand(String cmd) {
			this.cmd = cmd;
		}
		
		public void fire() throws LineParserException {
			System.out.println(LineParser.this.toString());
			askHelp = true;
		}

		public int getArity() {
			return 0;
		}

		public String getCommand() {
			return cmd;
		}

		public String getDescription() {
			return "Print this help";
		}

		public void setArgument(int pos, String value) throws LineParserException {
			
		}

		public boolean immediateFire() {
			return true;
		}

		public boolean isOptionnal() {
			return true;
		}

	}

	
	/**
	 * It is the unique constructor, we specify the name of the executable and the associate description
	 * commonly this description describe what the executabe make.
	 *  
	 * @param exe : the name of the executable
	 * @param description : description of the application.
	 */
	public LineParser(String exe,String description) {
		exename = exe;
		this.description = description;
		defspec = null;
		specs = new Hashtable<String, LineParserSpec>();
		visited = new ArrayList<LineParserSpec>();
		askHelp = false;
		try {
			addSpec(new HelpCommand("--help"));
			addSpec(new HelpCommand("-h"));
		} catch(LineParserException e) { }
	}
	
	
	/**
	 * Add a new command on the line.
	 * @param spec : specification of the command
	 * @throws LineParserException
	 */
	public void addSpec(LineParserSpec spec) throws LineParserException {
		if(specs.containsKey(spec.getCommand()))
			throw new LineParserException("Command already specified : "+spec.getCommand());
		specs.put(spec.getCommand(), spec);
	}
	
	/**
	 * Replace the default specification of the line.
	 * @param spec : a LineParserDefaultSpec object
	 * @throws LineParserException
	 */
	public void setDefaultSpec(LineParserDefaultSpec spec) throws LineParserException {
		defspec = spec;
	}
	
	/**
	 * Function that must be called by the started class.
	 * @param args : objects from the main function
	 * @throws LineParserException
	 */
	public void parse(String[] args) throws LineParserException {
		int i =0;
		while(i < args.length) {
			String cmd = args[i];
			i++; // on se decale sur la prochaine commande ou argument
			
			if(specs.containsKey(cmd)) {
				// Cas ou on connait la commande
				i += parseCommand(args,i,specs.get(cmd));
			}
			else if(defspec == null) {
				// on a pas de commande par defaut
				throw new LineParserException("Unknown command : "+cmd);
			}
			else if(defspec.canAddItem()) {
				// on pas trouver la commande et on a un comportement par defaut favorable
				if(defspec.getArity() == 0) { // cas infini
					defspec.addItem(cmd);
					listdefitem = -1;
				}
				else if(listdefitem < defspec.getArity() ) { // limite
					defspec.addItem(cmd);
					listdefitem++;
				}
				else // depasse capacite
					throw new LineParserException("Too many "+defspec.getItemName());
			}
			else
				throw new LineParserException("Unknown command : "+cmd);
		}
		
		if((listdefitem == 0) && (defspec != null) &&(!defspec.canBeEmpty()) && (!askHelp)) {
			throw new LineParserException("No default argument for the executable");
		}
		fireAll();
	}
	
		
	
	private void fireAll() throws LineParserException {
		for (LineParserSpec spec : visited) {
			spec.fire();
		}
		visited.clear();
		if((defspec != null) && (defspec.canAddItem())) {
			defspec.fire();
			listdefitem = 0;
		}
	}


	private int parseCommand(String[] args, int deb, LineParserSpec spec) throws LineParserException {
		for(int i=0 ;i < spec.getArity();i++) {
			if(deb+i < args.length) {
				String value = args[deb+i];
				spec.setArgument(i, value);
			}
			else
				throw new LineParserException("Not Enough argument for the command : "+spec.getCommand());
		}
		if(spec.immediateFire())
			spec.fire();
		else
			visited.add(spec);
		return spec.getArity();
	}

	/**
	 * Return a string that represent the help of the current LineParser.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(description);
		sb.append("\nUsage : ");
		sb.append(exename);
		sb.append(" ");
		
		for (LineParserSpec spec : specs.values()) {
			if(spec.isOptionnal())
				sb.append("[ ");
			sb.append(spec.getCommand());
			switch(spec.getArity()) {
			case 0:
				break;
			case 1:
				sb.append(" arg1");
				break;
			case 2:
				sb.append(" arg1 arg2");
				break;
			default:
				sb.append(" arg1 ... arg");
				if(spec.getArity() >= 10)
					sb.append("n");
				else
					sb.append(spec.getArity());
			}
			if(spec.isOptionnal())
				sb.append(" ]");
			sb.append(" ");
		}
		if((defspec != null) && (defspec.canAddItem())) {
			sb.append(defspec.getItemName());
			if(defspec.getArity() == 0) {
				if(defspec.canBeEmpty())
					sb.append("*");
				else
					sb.append("+");
			}
			else if(defspec.getArity() == 1) {
				// ne fait rien
			}
			else {
				sb.append("^");
				sb.append(defspec.getArity());
			}
			sb.append("\n");
			sb.append("\t");
			sb.append(defspec.getItemName());
			sb.append(" : ");
			sb.append(defspec.getDescription());
		}
		sb.append("\n");
		sb.append("Options:");
		for (LineParserSpec spec : specs.values()) {
			sb.append("\n\t");
			sb.append(spec.getCommand());
			sb.append(" : ");
			sb.append(spec.getDescription());
			
		}
		
		return(sb.toString());
	}
	

	/**
	 * a Launcher that present an example of the LineParser
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LineParser toto =new LineParser("por","Execute"); 
			toto.parse(new String[] { "--help", "-h" } );
			System.out.println(toto.toString());
			toto.setDefaultSpec(new LineParserDefaultSpec() {

				public void addItem(String item) {
					
				}

				public boolean canAddItem() {
					return true;
				}

				public void fire() throws LineParserException {
					
				}

				public int getArity() {
					return 0;
				}

				public String getDescription() {
					return "Files to compile";
				}

				public String getItemName() {
					return "Files";
				}

				public boolean canBeEmpty() {
					return false;
				}
				
			});
			System.out.println(toto.toString());
			
		} catch (LineParserException e) {
			e.printStackTrace();
		}
	}

}
