/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.tamago.eclipse.cdl.editor.util.CDLDebugLevel;
import org.tamago.eclipse.cdl.editor.util.CDLEssai;
import org.tamago.eclipse.cdl.editor.util.CDLGenericAction;
import org.tamago.eclipse.cdl.editor.util.CompileCDLAction;
import org.tamago.eclipse.cdl.editor.util.TamagoCompileContract;
import org.tamago.eclipse.cdl.editor.util.TamagoCompileContractAndSkeleton;
import org.tamago.eclipse.cdl.editor.util.TamagoCompileTest;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLActionBarContributor extends EditorActionBarContributor {

	private static final String CDL_COMPILATION_ID = "CDL_COMPILATION_ID";
	private static final String CDL_COMPILATION_ID2 = "CDL_COMPILATION_ID2";
	class CDLMenuListener implements IMenuListener {

		/**
		 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
		 */
		public void menuAboutToShow(IMenuManager manager) {
			//CDLEditorPlugin.getDefault().log("ACTION!!!");
		}
		
	}

	protected IAction compileCDL = new CompileCDLAction();
	protected IAction debug0 = new CDLDebugLevel("Debug - silent","No information",0,true);
	protected IAction debug3 = new CDLDebugLevel("Debug - normal","Give just big step or situation",3,false);
	protected IAction debug6 = new CDLDebugLevel("Debug - verbose","Fournit beaucoup d'information (utile pour comprendre le parsing du xml)",6,false);
	
	
	/**
	 * 
	 */
	public CDLActionBarContributor() {
		// TODO Auto-generated constructor stub
	}

	public void contributeToMenu(IMenuManager menuManager) {
		//CDLEditorPlugin.getDefault().log("Contribute to Menu");

        IMenuManager submenuManager = new MenuManager("TamagoCDL", CDL_COMPILATION_ID ); //$NON-NLS-1$ //$NON-NLS-2$
        
        menuManager.insertAfter(IWorkbenchActionConstants.MB_ADDITIONS, submenuManager); //$NON-NLS-1$
        submenuManager.addMenuListener(new CDLMenuListener());
        submenuManager.add(new Separator());
        submenuManager.add(compileCDL);
        submenuManager.add(new Separator());
        submenuManager.add(debug0);
        submenuManager.add(debug3);
        submenuManager.add(debug6);
        submenuManager.add(new Separator());
        submenuManager.add(new CDLGenericAction("Génération des Conteneurs/Interfaces","Regénère les conteneurs pour la vérification dynamique des contrats.(nécéssite la génération des fichiers XML)",new TamagoCompileContract()));
        submenuManager.add(new CDLGenericAction("Génération des Conteneurs/Interfaces+ Squelette","Regénère les conteneurs ainsi que les squelettes. ATTENTION: vous pouvez perdre les modifications que vous avez réalisé jusqu'à présent",new TamagoCompileContractAndSkeleton()));
        submenuManager.add(new Separator());
        submenuManager.add(new CDLGenericAction("Génération de Test","Génére des fichiers de test JUnit éprouvant le contrat fonctionnel sélectionné",new TamagoCompileTest()));
        submenuManager.add(new Separator());
        submenuManager.add(new CDLEssai("Print Project"));
        
        //submenuManager.add(new Separator());
        //submenuManager.add(new CDLGenericAction("Génération de testcase","Génère un fichier de test avec les dernieres informations enregistre",new TamagoTestActionGen()));
        
        
    }
}
