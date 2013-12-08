package com.puresoltechnologies.purifinity.coding.evaluation.api;

import java.io.Serializable;

/**
 * This is the base interface for all evaluation results. The main purpose for
 * Evaluators are the check of source code and providing feedback about the
 * quality. This interface reflects this and provides base methods for the main
 * purposes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface EvaluationResult extends Serializable {

	/**
	 * <p>
	 * This method returns the source code quality of the directory or file
	 * which was evaluated. This source quality is the aggregate value if the
	 * evaluator has different quality result. Multiple quality results are
	 * possible in file evaluatos where the file is evaluated in different parts
	 * like classes, methods, and other sub parts of the source.
	 * </p>
	 * <p>
	 * <b>Attention:</b> There is a contract here! Have a look to
	 * {@link #getQualityLevel()} API documentation.
	 * </p>
	 * 
	 * @return A {@link SourceCodeQuality} object is returned. If not quality is
	 *         specified, {@link SourceCodeQuality#UNSPECIFIED} is allowed to be
	 *         returned. This method <b>must not</b> return <code>null</code>!
	 *         The return value should be {@link #getQualityLevel()}
	 *         .getQuality().
	 */
	public SourceCodeQuality getSourceQuality();

	/**
	 * <p>
	 * This method returns the source code quality as a statistical value which
	 * can be used for further calculations.
	 * </p>
	 * <p>
	 * <b>Attention:</b> There is a contract here! The method
	 * {@link #getSourceQuality()} and {@link #getQualityLevel()} have a defined
	 * behavior as follows:
	 * 
	 * <ol>
	 * <li>If a {@link SourceCodeQuality} is specified in
	 * {@link SourceCodeQuality} (the quality is not
	 * {@link SourceCodeQuality#UNSPECIFIED}). This method must return a
	 * non-null value. The result must reflect the same source code quality or
	 * in other words: {@link #getQualityLevel()} needs to return
	 * {@link QualityLevel#getQuality()} of the result of this method.</li>
	 * <li>If the source code quality is not specified in
	 * {@link #getSourceQuality()}, this method must not return a value.
	 * <code>null</code> must be returned.</li>
	 * </ol>
	 * </p>
	 * 
	 * @return A {@link QualityLevel} object is returned.
	 */
	public QualityLevel getQualityLevel();
}
