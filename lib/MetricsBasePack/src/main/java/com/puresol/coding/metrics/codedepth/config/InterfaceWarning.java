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
public class InterfaceWarning implements PropertyDescription<Integer> {

    @Override
    public String getPropertyName() {
	return "codedepth.interfaces.warning";
    }

    @Override
    public String getDisplayName() {
	return "CodeDepth for Interface Classes for Medium Quality";
    }

    @Override
    public String getDescription() {
	return "Specify here the codepth for interface classes to raise a warning.";
    }

    @Override
    public Class<Integer> getType() {
	return Integer.class;
    }

    @Override
    public Integer getDefaultValue() {
	return 2;
    }

}
