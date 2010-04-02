package com.puresol.coding.reporting;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.reporting.html.HTMLStandards;

public class HTMLConverter {

	public static String convertCodeRangeToHTML(CodeRange codeRange) {
		String output = codeRange.getType().getIdentifier() + " \""
				+ codeRange.getName() + "\"<br/>\n";
		output += "(" + codeRange.getFile() + ":" + codeRange.getStart() + "-"
				+ codeRange.getStop();
		output += HTMLStandards.convertSourceCodeToHTML(codeRange.getText());
		return output;
	}

}
