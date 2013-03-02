package com.puresol.coding.client.application.content;

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

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.ClientImages;

public class AnalysisRunContentTreeLabelProvider extends LabelProvider {

    private final Logger logger = LoggerFactory
	    .getLogger(AnalysisRunContentTreeLabelProvider.class);

    private final Image folderImage = Activator.getDefault().getImageRegistry()
	    .get(ClientImages.FOLDER_16x16);
    private final Image documentImage = Activator.getDefault()
	    .getImageRegistry().get(ClientImages.DOCUMENT_EMPTY_16x16);
    private final Image analysisRunImage = Activator.getDefault()
	    .getImageRegistry().get(ClientImages.ANALYSIS_RUN_16x16);

    private final ISharedImages shareImageManager = PlatformUI.getWorkbench()
	    .getSharedImages();
    private final ImageDescriptor errorDecoratorImage = shareImageManager
	    .getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
    private final ImageDescriptor questionDecoratorImage = shareImageManager
	    .getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);

    private final CodeStore fileStore = CodeStoreFactory.getFactory()
	    .getInstance();

    private AnalysisRun analysisRun;

    public void setAnalysisRun(AnalysisRun analysisRun) {
	this.analysisRun = analysisRun;
    }

    @Override
    public String getText(Object element) {
	if (element instanceof String) {
	    return (String) element;
	}
	HashIdFileTree input = (HashIdFileTree) element;
	String text = input.getName();
	File path = input.getPathFile(false);
	AnalyzedCode analyzedFile = analysisRun
		.findAnalyzedCode(path.getPath());
	if (analyzedFile != null) {
	    if (fileStore.wasAnalyzed(analyzedFile.getHashId())) {
		try {
		    CodeAnalysis analysisResult = fileStore
			    .loadAnalysis(analyzedFile.getHashId());
		    text += " (" + analysisResult.getLanguageName() + " "
			    + analysisResult.getLanguageVersion() + ")";
		} catch (CodeStoreException e) {
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
	    return analysisRunImage;
	}
	HashIdFileTree input = (HashIdFileTree) element;
	File path = input.getPathFile(false);
	AnalyzedCode analyzedFile = analysisRun
		.findAnalyzedCode(path.getPath());
	if (!input.isFile()) {
	    return folderImage;
	}
	if (analyzedFile == null) {
	    if (analysisRun.getFailedCodes().contains(input)) {
		return new DecorationOverlayIcon(documentImage,
			errorDecoratorImage, IDecoration.TOP_LEFT)
			.createImage();
	    } else {
		return new DecorationOverlayIcon(documentImage,
			questionDecoratorImage, IDecoration.TOP_LEFT)
			.createImage();
	    }
	}
	return documentImage;
    }
}
