package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.math.MathUtils;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunContentTreeLabelProvider;
import com.puresoltechnologies.purifinity.client.common.branding.ClientImages;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.framework.store.api.FileStore;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreFactory;

public class EvaluationFileTreeLabelProvider implements ITableLabelProvider {

	private final Logger logger = LoggerFactory
			.getLogger(AnalysisRunContentTreeLabelProvider.class);

	private final ISharedImages shareImageManager = PlatformUI.getWorkbench()
			.getSharedImages();
	private final ImageDescriptor errorDecoratorImage = shareImageManager
			.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_ERROR);
	private final ImageDescriptor questionDecoratorImage = shareImageManager
			.getImageDescriptor(ISharedImages.IMG_DEC_FIELD_WARNING);

	private final ImageDescriptor highQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_HIGH_QUALITY_8x8);
	private final ImageDescriptor mediumQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_MEDIUM_QUALITY_8x8);
	private final ImageDescriptor lowQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_LOW_QUALITY_8x8);

	private final ListenerList listeners = new ListenerList();

	private AnalysisRun analysisRun;
	private EvaluatorFactory evaluator = null;

	public void setAnalysisRun(AnalysisRun analysisRun) {
		this.analysisRun = analysisRun;
	}

	public void setEvaluator(EvaluatorFactory evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		try {
			if (analysisRun == null) {
				return "";
			}
			switch (columnIndex) {
			case 0:
				return getFileColumnText(element);
			case 1:
				return getQualityLevelColumnText(element);
			}
			return "";
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			activator.getLog().log(
					new Status(Status.ERROR, activator.getBundle()
							.getSymbolicName(),
							"Could not handle new selection.", e));
			return "";
		}
	}

	private String getFileColumnText(Object element) {
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
					CodeAnalysis analysisResult = fileStore.loadAnalysis(
							analyzedFile.getHashId(), Thread.currentThread()
									.getContextClassLoader());
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

	private String getQualityLevelColumnText(Object element)
			throws EvaluationStoreException {
		QualityLevel qualityLevel = null;
		if (element instanceof String) {
			qualityLevel = getDirectoryQuality(analysisRun.getFileTree());
		} else {
			AnalysisFileTree node = (AnalysisFileTree) element;
			if (node.isFile()) {
				qualityLevel = getFileQuality(node);
			} else {
				qualityLevel = getDirectoryQuality(node);
			}
		}
		if (qualityLevel == null) {
			return "";
		}
		return String.valueOf(MathUtils.round(qualityLevel.getLevel(), 4));
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		try {
			if (columnIndex != 0) {
				return null;
			}
			if (element instanceof String) {
				return createProjectImage();
			}
			AnalysisFileTree input = (AnalysisFileTree) element;
			if (!input.isFile()) {
				return createFolderImage(input);
			}
			return createFileImage(input);
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			activator.getLog().log(
					new Status(Status.ERROR, activator.getBundle()
							.getSymbolicName(),
							"Could not handle new selection.", e));
			return null;
		}
	}

	/**
	 * Creates an image for the project node.
	 * 
	 * @return
	 */
	private Image createProjectImage() {
		return ClientImages.getImage(ClientImages.ANALYSIS_RUN_16x16);
	}

	protected Image createFileImage(AnalysisFileTree node)
			throws EvaluationStoreException {
		Image documentImage = createBaseFileImage(node);
		QualityLevel qualityLevel = getFileQuality(node);
		if (qualityLevel != null) {
			switch (qualityLevel.getQuality()) {
			case HIGH:
				documentImage = new DecorationOverlayIcon(documentImage,
						highQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case MEDIUM:
				documentImage = new DecorationOverlayIcon(documentImage,
						mediumQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case LOW:
				documentImage = new DecorationOverlayIcon(documentImage,
						lowQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case UNSPECIFIED:
			default:
			}
		}
		return documentImage;
	}

	/**
	 * Creates a node for a document node.
	 * 
	 * @param node
	 * @param analyzedFile
	 * @return
	 */
	protected Image createBaseFileImage(AnalysisFileTree node) {
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

	private QualityLevel getFileQuality(AnalysisFileTree node)
			throws EvaluationStoreException {

		try (Evaluators evaluators = Evaluators.createInstance()) {
			EvaluatorStoreFactory storeFactory = EvaluatorStoreFactory
					.getFactory();
			List<EvaluatorFactory> allEvaluators = retrieveEvaluatorsForImage(evaluators);
			QualityLevel qualityLevel = null;
			for (EvaluatorFactory evaluatorFactory : allEvaluators) {
				Class<? extends Evaluator> evaluatorClass = evaluatorFactory
						.getEvaluatorClass();
				EvaluatorStore store = storeFactory
						.createInstance(evaluatorClass);
				MetricFileResults fileResults = store.readFileResults(node
						.getHashId());
				if (fileResults != null) {
					Set<Parameter<?>> parameters = fileResults.getParameters();
					for (Parameter<?> parameter : parameters) {
						if (parameter.equals(SourceCodeQualityParameter
								.getInstance())) {
							for (Map<String, Value<?>> column : fileResults
									.getValues()) {
								Value<?> value = column
										.get(parameter.getName());
								SourceCodeQuality sourceCodeQuality = (SourceCodeQuality) value
										.getValue();
								if (qualityLevel == null) {
									qualityLevel = new QualityLevel(
											sourceCodeQuality);
								} else {
									qualityLevel.add(sourceCodeQuality);
								}
							}
						}
					}
				}
			}
			return qualityLevel;
		}
	}

	protected Image createFolderImage(AnalysisFileTree node)
			throws EvaluationStoreException {
		Image folderImage = ClientImages.getImage(ClientImages.FOLDER_16x16);
		QualityLevel qualityLevel = getDirectoryQuality(node);
		if (qualityLevel != null) {
			switch (qualityLevel.getQuality()) {
			case HIGH:
				folderImage = new DecorationOverlayIcon(folderImage,
						highQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case MEDIUM:
				folderImage = new DecorationOverlayIcon(folderImage,
						mediumQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case LOW:
				folderImage = new DecorationOverlayIcon(folderImage,
						lowQualityDecorator, IDecoration.TOP_LEFT)
						.createImage();
				break;
			case UNSPECIFIED:
			default:
			}
		}
		return folderImage;
	}

	private QualityLevel getDirectoryQuality(AnalysisFileTree node)
			throws EvaluationStoreException {
		try (Evaluators evaluators = Evaluators.createInstance()) {
			QualityLevel qualityLevel = null;
			EvaluatorStoreFactory storeFactory = EvaluatorStoreFactory
					.getFactory();
			List<EvaluatorFactory> allEvaluators = retrieveEvaluatorsForImage(evaluators);
			for (EvaluatorFactory evaluatorFactory : allEvaluators) {
				Class<? extends Evaluator> evaluatorClass = evaluatorFactory
						.getEvaluatorClass();
				EvaluatorStore store = storeFactory
						.createInstance(evaluatorClass);
				MetricDirectoryResults directoryResults = store
						.readDirectoryResults(node.getHashId());
				if (directoryResults != null) {
					QualityLevel childLevel = directoryResults
							.getQualityLevel();
					if (childLevel != null) {
						if (qualityLevel == null) {
							qualityLevel = childLevel;
						} else {
							qualityLevel.add(childLevel);
						}
					}
				}
			}
			return qualityLevel;
		}
	}

	private List<EvaluatorFactory> retrieveEvaluatorsForImage(
			Evaluators evaluators) {
		List<EvaluatorFactory> allEvaluators;
		if (evaluator == null) {
			allEvaluators = evaluators.getAll();
		} else {
			allEvaluators = Arrays.asList(evaluator);
		}
		return allEvaluators;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		listeners.add(listener);
	}

	@Override
	public void dispose() {
		// intentionally left blank
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
	}
}
