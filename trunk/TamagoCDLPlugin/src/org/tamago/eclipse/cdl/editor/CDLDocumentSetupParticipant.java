/**
 * 
 */
package org.tamago.eclipse.cdl.editor;

import org.eclipse.core.filebuffers.IDocumentSetupParticipant;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLDocumentSetupParticipant implements IDocumentSetupParticipant {

	/**
	 * @see org.eclipse.core.filebuffers.IDocumentSetupParticipant#setup(org.eclipse.jface.text.IDocument)
	 */
	public void setup(IDocument document) {
		//CDLEditorPlugin.getDefault().log("SETUP:"+document);
			// Crée le partitioner
			IDocumentPartitioner partitioner= new FastPartitioner(
						CDLEditorPlugin.getDefault().getCDLPartitionScanner(),
						CDLPartitionScanner.CDL_PARTITION_TYPES);
			// Attache le partitioner au document.
			document.setDocumentPartitioner(partitioner);
			// Attache le document au partitioner.
			partitioner.connect(document);
	}

}
