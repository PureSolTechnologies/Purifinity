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
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreException;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.ClientImages;
import com.puresol.trees.FileTree;

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

    private final FileStore fileStore = FileStoreFactory.getInstance();

    private AnalysisRun analysisRun;

    public void setAnalysisRun(AnalysisRun analysisRun) {
	this.analysisRun = analysisRun;
    }

    @Override
    public String getText(Object element) {
	if (element instanceof String) {
	    return (String) element;
	}
	FileTree input = (FileTree) element;
	String text = input.getName();
	File path = input.getPathFile(false);
	AnalyzedFile analyzedFile = analysisRun.findAnalyzedFile(path);
	if (analyzedFile != null) {
	    try {
		FileAnalysis analysisResult = fileStore
			.loadAnalysis(analyzedFile.getHashId());
		text += " (" + analysisResult.getLanguage().getName() + " "
			+ analysisResult.getLanguage().getVersion() + ")";
	    } catch (FileStoreException e) {
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
	FileTree input = (FileTree) element;
	File path = input.getPathFile(false);
	AnalyzedFile analyzedFile = analysisRun.findAnalyzedFile(path);
	if (analyzedFile != null) {
	    return documentImage;
	}
	if (analysisRun.getFailedFiles().contains(path)) {
	    return new DecorationOverlayIcon(documentImage,
		    errorDecoratorImage, IDecoration.TOP_LEFT).createImage();
	}
	return folderImage;
    }
}
