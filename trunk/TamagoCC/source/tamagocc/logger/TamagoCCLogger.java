package tamagocc.logger;


import java.io.*;

/**
 * @author Hakim BELHAOUARI
 * 11 juil. 2005 TamagoCCLogger.java
 */
/**
 */
public class TamagoCCLogger extends PrintStream {
	
	private TamagoCCLogger(OutputStream out) {
		super(out,true);
	}
	private TamagoCCLogger() {
		super(System.err,true);
	}
	
	private static TamagoCCLogger current = new TamagoCCLogger();
	
	private static int maxlevel = 1;
	private static String output;
	
	
	private static long unique = 0L;
	public static long getUniqueID() {
		return (unique++);
	}
	
	/**
	 * 
	 */
	public static void setOut(String outflux) 
	{
		output = outflux;
		if("stdout".equals(outflux)) {
			current = new TamagoCCLogger(System.out);
		}
		else if("stderr".equals(outflux)) {
			current = new TamagoCCLogger(System.err);
		}
		else {
			try {
				File fichier = createFileAndDir(outflux);
				FileOutputStream file = new FileOutputStream(fichier);
				current = new TamagoCCLogger(file);
			}
			catch(Exception e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void setOut(OutputStream os) 
	{
		output = "stream";
		current = new TamagoCCLogger(os);
	}
	
	public static void setLevel(int maxlevel) {
		TamagoCCLogger.maxlevel = maxlevel;
	}
	
	public static void infoln(int level,String msg) {
			if(level <= maxlevel) {
				if(msg == null)
					current.println("(null)");
				else
					current.println(msg);
				current.flush();
			}
		
	}
	
	public static void println(int level,String msg) {
		infoln(level,msg);
	}
	
	public static void print(int level,String msg) {
		info(level,msg);
	}
	
	/*public static void infoln(Exception e) {
		if(maxlevel != 0) {
			PrintStream ps = new PrintStream(dos,true);
			e.printStackTrace(ps);
			ps.flush();
		}
	}*/
	
	public static void infoln(Throwable e) {
		if(maxlevel != 0) {
			e.printStackTrace(current);
		}
	}
	
	public static void infoln(int lvl, Exception e) {
		if(lvl <= maxlevel) {
			e.printStackTrace(current);
		}
	}
	
	public static void info(Exception e) {
		if(maxlevel != 0) {
			e.printStackTrace(current);
		}
		
	}
	public static void info(int level,Exception e) {
		if(level <= maxlevel) {
			e.printStackTrace(current);
		}
		
	}
	public static void info(int level,String msg) {
		if(level <= maxlevel) {
			current.print(msg);
		}
	}
	
	private static File createFileAndDir(String s)
	throws IOException
	{
		int dernier = s.lastIndexOf(File.separator);
		String path="";
		String filename;
		if(dernier != -1) {
			path = s.substring(0,dernier);
			File directory = new File(path);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			filename = s.substring(dernier);     
		}
		else 
			filename = s;
		
		
		File file = new File(path+filename);
		return file;
	}

	public static int getLevel() {
		return maxlevel;
	}
	
	public static String getOutput() {
		return output;
	}
	
	
}
