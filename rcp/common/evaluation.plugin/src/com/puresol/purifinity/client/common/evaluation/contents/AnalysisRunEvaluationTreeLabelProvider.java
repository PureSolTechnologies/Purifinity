package com.puresol.purifinity.client.common.evaluation.contents;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;

import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.Value;
import com.puresol.purifinity.client.common.analysis.contents.AnalysisRunContentTreeLabelProvider;
import com.puresol.purifinity.client.common.branding.ClientImages;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.Evaluator;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluators;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;

public class AnalysisRunEvaluationTreeLabelProvider extends
		AnalysisRunContentTreeLabelProvider {

	private final ImageDescriptor highQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_HIGH_QUALITY_8x8);
	private final ImageDescriptor mediumQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_MEDIUM_QUALITY_8x8);
	private final ImageDescriptor lowQualityDecorator = ClientImages
			.getImageDescriptor(ClientImages.DEC_LOW_QUALITY_8x8);

	private EvaluatorFactory evaluator = null;

	public void setEvaluator(EvaluatorFactory evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	protected Image createFolderImage(HashIdFileTree node) {
		Image folderImage = super.createFolderImage(node);
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

	private QualityLevel getDirectoryQuality(HashIdFileTree node) {
		Evaluators evaluators = Evaluators.createInstance();
		try {
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
		} finally {
			IOUtils.closeQuietly(evaluators);
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
	protected Image createFileImage(HashIdFileTree node) {
		Image documentImage = super.createFileImage(node);
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

	private QualityLevel getFileQuality(HashIdFileTree node) {
		Evaluators evaluators = Evaluators.createInstance();
		try {
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
		} finally {
			IOUtils.closeQuietly(evaluators);
		}
	}
}
