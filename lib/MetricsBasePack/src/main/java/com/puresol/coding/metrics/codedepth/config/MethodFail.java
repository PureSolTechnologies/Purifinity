package com.puresol.coding.metrics.codedepth.config;

import com.puresol.config.PropertyDescription;

/**
 * This class is a configurator PropertyDescription for CodeDepth. The following
 * code range types have configurations for LOW, MEDIUM and HIGH quality:
 * 
 * <pre>
 * FILE
 * CLASS
 * INTERFACE
 * ENUMERATION
 * ANNOTATION
 * MODULE 
 * CONSTRUCTOR
 * METHOD
 * PROGRAM
 * SUBROUTINE
 * FUNCTION
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MethodFail implements PropertyDescription<Integer> {

    @Override
    public String getPropertyName() {
	return "codedepth.methods.fail";
    }

    @Override
    public String getDisplayName() {
	return "CodeDepth for Methods for Low Quality";
    }

    @Override
    public String getDescription() {
	return "Specify here the codepth for methods to fail.";
    }

    @Override
    public Class<Integer> getType() {
	return Integer.class;
    }

    @Override
    public Integer getDefaultValue() {
	return 7;
    }

}
