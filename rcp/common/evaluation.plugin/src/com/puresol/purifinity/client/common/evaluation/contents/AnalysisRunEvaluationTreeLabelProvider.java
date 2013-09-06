package com.puresol.purifinity.client.common.evaluation.contents;

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

	@Override
	protected Image createFolderImage(HashIdFileTree node) {
		Image folderImage = super.createFolderImage(node);
		SourceCodeQuality quality = getDirectoryQuality(node);
		switch (quality) {
		case HIGH:
			folderImage = new DecorationOverlayIcon(folderImage,
					highQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case MEDIUM:
			folderImage = new DecorationOverlayIcon(folderImage,
					mediumQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case LOW:
			folderImage = new DecorationOverlayIcon(folderImage,
					lowQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case UNSPECIFIED:
		default:
		}
		return folderImage;
	}

	private SourceCodeQuality getDirectoryQuality(HashIdFileTree node) {
		Evaluators evaluators = Evaluators.createInstance();
		SourceCodeQuality quality = SourceCodeQuality.UNSPECIFIED;
		try {
			EvaluatorStoreFactory storeFactory = EvaluatorStoreFactory
					.getFactory();
			List<EvaluatorFactory> allEvaluators = evaluators.getAll();
			for (EvaluatorFactory evaluatorFactory : allEvaluators) {
				Class<? extends Evaluator> evaluatorClass = evaluatorFactory
						.getEvaluatorClass();
				EvaluatorStore store = storeFactory
						.createInstance(evaluatorClass);
				MetricDirectoryResults directoryResults = store
						.readDirectoryResults(node.getHashId());
				if (directoryResults != null) {
					Set<Parameter<?>> parameters = directoryResults
							.getParameters();
					for (Parameter<?> parameter : parameters) {
						if (parameter.equals(SourceCodeQualityParameter
								.getInstance())) {
							Map<String, Value<?>> column = directoryResults
									.getValues();
							Value<?> value = column.get(parameter.getName());
							if (value != null) {
								SourceCodeQuality sourceCodeQuality = (SourceCodeQuality) value
										.getValue();
								if (quality == SourceCodeQuality.UNSPECIFIED) {
									quality = sourceCodeQuality;
								} else {
									quality = SourceCodeQuality.getMinimum(
											quality, sourceCodeQuality);
								}
							}
						}
					}
				}
			}
		} finally {
			IOUtils.closeQuietly(evaluators);
		}
		return quality;
	}

	@Override
	protected Image createFileImage(HashIdFileTree node) {
		Image documentImage = super.createFileImage(node);
		SourceCodeQuality quality = getFileQuality(node);
		switch (quality) {
		case HIGH:
			documentImage = new DecorationOverlayIcon(documentImage,
					highQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case MEDIUM:
			documentImage = new DecorationOverlayIcon(documentImage,
					mediumQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case LOW:
			documentImage = new DecorationOverlayIcon(documentImage,
					lowQualityDecorator, IDecoration.TOP_LEFT).createImage();
			break;
		case UNSPECIFIED:
		default:
		}
		return documentImage;
	}

	private SourceCodeQuality getFileQuality(HashIdFileTree node) {
		Evaluators evaluators = Evaluators.createInstance();
		SourceCodeQuality quality = SourceCodeQuality.UNSPECIFIED;
		try {
			EvaluatorStoreFactory storeFactory = EvaluatorStoreFactory
					.getFactory();
			List<EvaluatorFactory> allEvaluators = evaluators.getAll();
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
								if (quality == SourceCodeQuality.UNSPECIFIED) {
									quality = sourceCodeQuality;
								} else {
									quality = SourceCodeQuality.getMinimum(
											quality, sourceCodeQuality);
								}
							}
						}
					}
				}
			}
		} finally {
			IOUtils.closeQuietly(evaluators);
		}
		return quality;
	}
}
