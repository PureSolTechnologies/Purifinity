package com.puresol.coding.client.content;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.CodeAnalysis;
import com.puresol.coding.analysis.api.CodeStoreException;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.analysis.api.CodeStore;
import com.puresol.coding.analysis.api.CodeStoreFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.ClientImages;

public class AnalysisContentTreeLabelProvider extends LabelProvider {

    private final Image folderImage = Activator.getDefault().getImageRegistry()
	    .get(ClientImages.FOLDER_16x16);
    private final Image documentImage = Activator.getDefault()
	    .getImageRegistry().get(ClientImages.DOCUMENT_EMPTY_16x16);
    private final Image analysisRunImage = Activator.getDefault()
	    .getImageRegistry().get(ClientImages.ANALYSIS_RUN_16x16);

    private final ImageDescriptor errorDecoratorImage = PlatformUI
	    .getWorkbench().getSharedImages()
	    .getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);

    private final CodeStore fileStore = CodeStoreFactory
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
	AnalyzedCode analyzedFile = analysisRun.findAnalyzedCode(path);
	if (analyzedFile != null) {
	    try {
		CodeAnalysis analysisResult = fileStore
			.loadAnalysis(analyzedFile.getHashId());
		text += " (" + analysisResult.getLanguageName() + " "
			+ analysisResult.getLanguageVersion() + ")";
	    } catch (CodeStoreException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
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
	AnalyzedCode analyzedFile = analysisRun.findAnalyzedCode(path);
	if (analyzedFile != null) {
	    return documentImage;
	}
	if (analysisRun.getFailedCodeLocations().contains(path)) {
	    return new DecorationOverlayIcon(documentImage,
		    errorDecoratorImage, IDecoration.TOP_LEFT).createImage();
	}
	return folderImage;
    }
}
