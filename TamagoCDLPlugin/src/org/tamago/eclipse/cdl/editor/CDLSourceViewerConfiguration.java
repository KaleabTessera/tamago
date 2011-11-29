package org.tamago.eclipse.cdl.editor;

import org.eclipse.jface.text.IAutoEditStrategy;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextSourceViewerConfiguration;
import org.tamago.eclipse.cdl.CDLEditorPlugin;

;/**
 * Classe param�trant les diff�rentes fonctionnalit�s de l'�diteur.
 * @author Hakim Belhaouari
 */
public class CDLSourceViewerConfiguration extends TextSourceViewerConfiguration {

	/**
	 * Met en place la coloration syntaxique.
	 * Attribue des damagers et des repairers � chaque type de partition.
	 * @param sourceViewer SourceViewer pour lequel on configure le reconciler.
	 * @return Le reconciler � utiliser avec les damager et les repairers configur�s.
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getPresentationReconciler(org.eclipse.jface.text.source.ISourceViewer)
	 */
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler= new PresentationReconciler();
		reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		
		// Cr�e le damager/repairer pour le code
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(CDLEditorPlugin.getDefault().getCDLCodeScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		DefaultDamagerRepairer dr2 = new DefaultDamagerRepairer(CDLEditorPlugin.getDefault().getCDLCommentScanner());
		reconciler.setDamager(dr2, CDLPartitionScanner.CDL_COMMENT);
		reconciler.setRepairer(dr2, CDLPartitionScanner.CDL_COMMENT);
		
		return reconciler;
	}
	
	/**
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getAutoEditStrategies(org.eclipse.jface.text.source.ISourceViewer, java.lang.String)
	 */
	@Override
	public IAutoEditStrategy[] getAutoEditStrategies(
			ISourceViewer sourceViewer, String contentType) {
		if(contentType == IDocument.DEFAULT_CONTENT_TYPE)
			return new IAutoEditStrategy[] { new CDLAutoIndentStrategy() };
		else
			return super.getAutoEditStrategies(sourceViewer, contentType);
	}
	
	/**
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentFormatter(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
		ContentFormatter cf = new ContentFormatter();
		cf.setFormattingStrategy(new CDLFormattingStrategy(sourceViewer), IDocument.DEFAULT_CONTENT_TYPE);
		cf.enablePartitionAwareFormatting(false);
		return cf;
	}
	
	
	/**
	 * @see org.eclipse.jface.text.source.SourceViewerConfiguration#getContentAssistant(org.eclipse.jface.text.source.ISourceViewer)
	 */
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant ca = new ContentAssistant();
		ca.setContentAssistProcessor(new CDLCompletionProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		// ---
		ca.enableAutoActivation(true);
		ca.setAutoActivationDelay(500);
		
		ca.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
		ca.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);
		//ca.setContextInformationPopupBackground(JavaEditorEnvironment.getJavaColorProvider().getColor(new RGB(150, 150, 0)));


		return ca;
		// return super.getContentAssistant(sourceViewer);
	}
	
}
