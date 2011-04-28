/**
 * 
 */
package tamagocc.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import tamagocc.exception.TamagoCCException;

/**
 * This class provides a simply object for indentting some code. This class relies on the
 * DataOutputStream in order manage information.
 * 
 * @author Hakim Belhaouari
 */
public class TamagoCCIndentator {

	private DataOutputStream dos;
	private int tabulate;
	/**
	 * Represents the space in the document.
	 * By default equals to 4.
	 */
	public static int nbspace = 4;
	private boolean mustindent;
	private boolean mustCR;
	
	/**
	 * Construct an indentator, and write in the specified output
	 * @param os : ouput stream
	 */
	public TamagoCCIndentator(OutputStream os) {
		super();
		dos = new DataOutputStream(os);
		mustindent = false;
		tabulate = 0;
		mustCR = false;
	}
	
	public int getTabulate() {
		return tabulate;
	}
	public void setTabulate(int tab) {
		this.tabulate = tab;
	}
	
	public void open(String str) throws TamagoCCException {
		println("<"+str+">");
		shiftright();
	}
	
	public void close(String str) throws TamagoCCException {
		shiftleft();
		println("</"+str+">");
	}
	
	public void open(String tag, String... attr) throws TamagoCCException {
		if(attr.length == 0) {
			println("<"+tag+">");
			shiftright();
		}
		else {
			print("<"+tag);
			for(int i=0; i < attr.length; i+=2) {
				print(" "+attr[i]+"=\""+attr[i+1]+"\"");
			}
			println(">");
			shiftright();
		}
	}
	
	public void openclose(String tag, String... attr) throws TamagoCCException {
		if(attr.length == 0) {
			println("<"+tag+" />");
		}
		else {
			print("<"+tag);
			for(int i=0; i < attr.length; i+=2) {
				print(" "+attr[i]+"=\""+attr[i+1]+"\"");
			}
			println("/>");
		}
	}
	
	/**
	 * Construct an indentator, and write in the specified output. 
	 * @param os : ouput stream
	 */
	public TamagoCCIndentator(DataOutputStream os) {
		super();
		dos = os;
		mustindent = false;
		tabulate = 0;
		mustCR = true;
	}
	
	
	/**
	 * Print the given string in the current output. It validates a newlines or indentation with
	 * a lazy approach. Thus the print looks like better.
	 * @param b
	 * @throws TamagoCCException
	 */
	public void print(String b) throws TamagoCCException {
		try {
			if(b.length() == 0)
				return;
			if(mustCR) 
				core_newline();
			if(mustindent) {
				for(int i=0;i < (tabulate*nbspace);i++)
					dos.writeBytes(" ");
				mustindent = false;
			}
			dos.writeBytes(b);
		}
		catch(IOException ioe) {
			throw new TamagoCCException(ioe.getMessage(),ioe);
		}
	}
	
	
	
	/**
	 * Reduces the indentation depth
	 */
	public void shiftleft() {
		newline();
		if(tabulate > 0)
			tabulate--;
	}
	
	/**
	 * Increase the indentation depth
	 */
	public void shiftright() {
		newline();
		tabulate++;
	}
	
	/**
	 * Print the current string to the output and apply a newline character at the end.
	 * @param b : a string
	 * @throws TamagoCCException
	 */
	public void println(String b) throws TamagoCCException {
		print(b);
		newline();
		try {
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method indicates if the indentator must write a new line.
	 * <em>Caution</em> :  this function does not accumulate all newlines.
	 * If you want make many newline you must force the caracter.
	 */
	public void newline() {
		mustCR = true;
	}
	
	/**
	 * Force the output of the newline character.
	 * @throws TamagoCCException
	 */
	public void forceNewline() throws TamagoCCException {
		print("");
		core_newline();
	}
	
	private void core_newline() throws TamagoCCException {
		try {
			dos.writeBytes("\n");
			mustindent = true;
			dos.flush();
			mustCR = false;
		}
		catch(IOException ioe) {
			throw new TamagoCCException(ioe.getMessage(),ioe);
		}
	}
	
	/**
	 * Write a space.
	 * @throws TamagoCCException
	 */
	public void space() throws TamagoCCException {
		print(" ");
	}

}
