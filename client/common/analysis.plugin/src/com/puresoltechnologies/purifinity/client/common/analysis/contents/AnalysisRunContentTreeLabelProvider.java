package com.puresoltechnologies.purifinity.client.common.analysis.contents;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.client.common.branding.ClientImages;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

public class AnalysisRunContentTreeLabelProvider extends LabelProvider {
	private final Logger logger = LoggerFactory
			.getLogger(AnalysisRunContentTreeLabelProvider.class);

	private final ISharedImages shareImageManager = PlatformUI.getWorkbench()
			.getSharedImages();
	private final ImageDescriptor errorDecoratorImage = shareImageManager
			.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private final ImageDescriptor questionDecoratorImage = shareImageManager
			.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);

	private AnalysisRun analysisRun;

	public void setAnalysisRun(AnalysisRun analysisRun) {
		this.analysisRun = analysisRun;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof String) {
			return (String) element;
		}
		AnalysisFileTree input = (AnalysisFileTree) element;
		String text = input.getName();
		File path = input.getPathFile(false);
		AnalysisInformation analyzedFile = analysisRun.findAnalyzedCode(path
				.getPath());
		FileStore fileStore = FileStoreFactory.getFactory().getInstance();
		if ((fileStore != null) && (analyzedFile != null)) {
			if (fileStore.wasAnalyzed(analyzedFile.getHashId())) {
				try {
					CodeAnalysis analysisResult = fileStore
							.loadAnalysis(analyzedFile.getHashId());
					text += " (" + analysisResult.getLanguageName() + " "
							+ analysisResult.getLanguageVersion() + ")";
				} catch (FileStoreException e) {
					logger.warn(
							"Could not load the analysis which was offered by the store.",
							e);
				}
			}
		}
		return text;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof String) {
			return createProjectImage();
		}
		AnalysisFileTree input = (AnalysisFileTree) element;
		if (!input.isFile()) {
			return createFolderImage(input);
		}
		return createFileImage(input);
	}

	/**
	 * Creates an image for the project node.
	 * 
	 * @return
	 */
	private Image createProjectImage() {
		return ClientImages.getImage(ClientImages.ANALYSIS_RUN_16x16);
	}

	protected Image createFolderImage(AnalysisFileTree node) {
		return ClientImages.getImage(ClientImages.FOLDER_16x16);
	}

	/**
	 * Creates a node for a document node.
	 * 
	 * @param node
	 * @param analyzedFile
	 * @return
	 */
	protected Image createFileImage(AnalysisFileTree node) {
		File path = node.getPathFile(false);
		AnalysisInformation analyzedFile = analysisRun.findAnalyzedCode(path
				.getPath());
		Image documentImage = ClientImages
				.getImage(ClientImages.DOCUMENT_EMPTY_16x16);
		if (analyzedFile == null) {
			if (analysisRun.getFailedFiles().contains(node)) {
				/* Analysis failed. */
				return new DecorationOverlayIcon(documentImage,
						errorDecoratorImage, IDecoration.TOP_LEFT)
						.createImage();
			} else {
				/* Unknown file. */
				return new DecorationOverlayIcon(documentImage,
						questionDecoratorImage, IDecoration.TOP_LEFT)
						.createImage();
			}
		}
		return documentImage;
	}

}
