package org.tamago.eclipse.cdl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ResourceBundle;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.tamago.eclipse.cdl.editor.CDLCodeScanner;
import org.tamago.eclipse.cdl.editor.CDLCommentScanner;
import org.tamago.eclipse.cdl.editor.CDLPartitionScanner;
import org.tamago.eclipse.cdl.editor.scanner.CDLTextAttributeProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class CDLEditorPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "TamagoCDLEditor";
	public static String CDL_PARTITIONING = "__pos_cdl_partitioning";
	// The shared instance
	private static CDLEditorPlugin plugin;
	/** Ressources */
	private ResourceBundle resourceBundle;
	/** Partitioner */
	private CDLPartitionScanner fPartitionScanner;
	/** Conteneur d'attributs pour le texte */
	private CDLTextAttributeProvider fTextAttributeProvider;
	/** Scanner pour la coloration des partitions de code */
	private CDLCodeScanner fCDLCodeScanner;
	private CDLCommentScanner fCDLCommentScanner;
	private MessageConsole fCDLConsole;
	private DataOutputStream fCDLout;
	
	private int debuglevel;
	private Action action;
	/**
	 * The constructor
	 */
	public CDLEditorPlugin() {
		System.setOut(new PrintStream(getOutputStreamConsole()));
		debuglevel = 0;
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if(fCDLout != null)
			fCDLout.flush();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static CDLEditorPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * @return
	 */
	public IPartitionTokenScanner getCDLPartitionScanner() {
		if(fPartitionScanner == null) {
			fPartitionScanner = new CDLPartitionScanner();
		}
		return fPartitionScanner;
	}

	/**
	 * @return
	 */
	public ITokenScanner getCDLCodeScanner() {
		if(fCDLCodeScanner == null) {
			fCDLCodeScanner = new CDLCodeScanner(getTextAttributeProvider());
		}
		return fCDLCodeScanner;
	}

	/**
	 * @return
	 */
	public CDLTextAttributeProvider getTextAttributeProvider() {
		if(fTextAttributeProvider == null) {
			fTextAttributeProvider = new CDLTextAttributeProvider();
		}
		return fTextAttributeProvider;
	}

	/**
	 * @return
	 */
	public ITokenScanner getCDLCommentScanner() {
		if(fCDLCommentScanner == null) {
			fCDLCommentScanner = new CDLCommentScanner(getTextAttributeProvider());
		}
		return fCDLCommentScanner;
	}

	/**
	 * 
	 */
	public MessageConsole getConsole() {
		if(fCDLConsole == null) {
			fCDLConsole = new MessageConsole("CDL Console",null);
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { fCDLConsole });
		}
		return fCDLConsole;
	}
	
	public OutputStream getOutputStreamConsole() {
		if(fCDLout == null) {
		 fCDLout = new DataOutputStream(getConsole().newMessageStream());
		}
		return fCDLout;
	}
	
	public void log(String rmsg) {
		String msg = rmsg;
		if(msg == null)
			msg = "<null>";
		try {
			System.err.println(msg);
			getOutputStreamConsole();
			fCDLout.writeBytes(msg);
			fCDLout.writeBytes("\n");
			fCDLout.flush();
			ConsolePlugin.getDefault().getConsoleManager().warnOfContentChange(fCDLConsole);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showConsole() {
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(fCDLConsole);
	}
	
	public int getDebugLevel() {
		return debuglevel;
	}
	
	public void setDebugLevel(Action act,int level) {
		if(act != action) {
			act.setChecked(true);
			if(action != null)
				action.setChecked(false);
		}
		debuglevel = level;
		this.action = act;
	}

	public void clearConsole() {
		/*getConsole().clearConsole();
		try {
			fCDLout.close();
		} catch (IOException e) {
		}
		fCDLout = null;
		try {
			IOConsoleOutputStream out =getConsole().newOutputStream();
			out.write("CDL");
			out.flush();
			out.close();
			getConsole().partitionerFinished();
			getConsole().getDocument().getNumberOfLines();
		}
		catch(IOException e) {
			
		}*/
		getConsole().getDocument().set("");
	}
}
