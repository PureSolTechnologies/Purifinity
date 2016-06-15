package com.puresoltechnologies.purifinity.evaluation.domain.issues;

/**
 * This enum contains constants to classify issues into different categories.
 * 
 * @author Rick-Rainer Ludwig
 */
public enum Classification {

    /**
     * A style issue is an issue within the code and is not affecting the result
     * itself. Style issues are violations to the code or naming convention,
     * statements and block which are difficult to read or analyze and
     * everything else which effects maintainability in a bad way.
     */
    STYLE_ISSUE,
    /**
     * Defects are programmatic issues which do or might lead into a runtime
     * issue like a crash, wrong results or unexpecged behavior.
     */
    DEFECT,
    /**
     * Architecture issues are wrong library usages, wrong dependencies and
     * everything else which leads to an disadvantage architecture.
     */
    ARCHITECTURE_ISSUE,
    /**
     * Design issues are issues in code which lead to correct results, but which
     * are not optimal for source evolution like empty catch blocks, log and
     * re-throw issues and the like.
     */
    DESIGN_ISSUE,;

}
