package tamagocc.logger;


import java.io.*;

/**
 * @author Hakim BELHAOUARI
 * 11 juil. 2005 TamagoCCLogger.java
 */
/**
 */
public abstract class TamagoCCLogger {
	
	private static DataOutputStream dos = new DataOutputStream(System.err);
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
			dos = new DataOutputStream(System.out);
		}
		else if("stderr".equals(outflux)) {
			dos = new DataOutputStream(System.err);
		}
		else {
			try {
				File fichier = createFileAndDir(outflux);
				FileOutputStream file = new FileOutputStream(fichier);
				dos = new DataOutputStream(file);
			}
			catch(Exception e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void setOut(OutputStream os) 
	{
		output = "stream";
		dos = new DataOutputStream(os);
	}
	
	public static void setLevel(int maxlevel) {
		TamagoCCLogger.maxlevel = maxlevel;
	}
	
	public static void infoln(int level,String msg) {
		try {
			if(level <= maxlevel) {
				if(msg == null)
					dos.writeBytes("(null)");
				else
					dos.writeBytes(msg);
				dos.writeBytes("\n");
				dos.flush();
			}
		}
		catch(IOException ioe) {
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
			PrintStream ps = new PrintStream(dos,true);
			e.printStackTrace(ps);
			ps.flush();
		}
	}
	
	public static void infoln(int lvl, Exception e) {
		if(lvl <= maxlevel) {
			PrintStream ps = new PrintStream(dos,true);
			e.printStackTrace(ps);
			ps.flush();
		}
	}
	
	public static void info(Exception e) {
		if(maxlevel != 0) {
			PrintStream ps = new PrintStream(dos,true);
			e.printStackTrace(ps);
			ps.flush();
		}
		
	}
	public static void info(int level,Exception e) {
		if(level <= maxlevel) {
			PrintStream ps = new PrintStream(dos,true);
			e.printStackTrace(ps);
			ps.flush();
		}
		
	}
	public static void info(int level,String msg) {
		try {
			if(level <= maxlevel) {
				dos.writeBytes(msg);
				dos.flush();
			}
		}
		catch(IOException ioe) {
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
