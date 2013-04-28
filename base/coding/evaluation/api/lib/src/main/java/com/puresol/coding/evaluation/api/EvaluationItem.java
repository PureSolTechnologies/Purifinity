package com.puresol.coding.evaluation.api;

import java.io.Serializable;

import com.puresol.coding.analysis.api.CodeRangeType;

/**
 * This class contains the information about the item which was evaluated. This
 * is a file or a part of it.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluationItem implements Serializable {

	private static final long serialVersionUID = -3254432975929722076L;

	private final String fileName;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final int line;
	private final int lineCount;

	public EvaluationItem(String fileName, CodeRangeType codeRangeType,
			String codeRangeName, int line, int lineCount) {
		super();
		this.fileName = fileName;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.line = line;
		this.lineCount = lineCount;
	}

	public String getFileName() {
		return fileName;
	}

	public CodeRangeType getCodeRangeType() {
		return codeRangeType;
	}

	public String getCodeRangeName() {
		return codeRangeName;
	}

	public int getLine() {
		return line;
	}

	public int getLineCount() {
		return lineCount;
	}

}
