package com.puresoltechnologies.purifinity.framework.lang.fortran2008;

/**
 * This enum specifies the source form of fortran code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SourceForm {

	/**
	 * Source code in free form is only free form. All other source form will
	 * lead to warnings.
	 */
	FREE_FORM,
	/**
	 * This source code form is in fixed form. All other source forms will lead
	 * to warnings.
	 */
	FIXED_FORM,
	/**
	 * This form is not specified in the Fortran Language Specification, but is
	 * used to indicate, that the source form may vary within the project and
	 * maybe in one source file itself.
	 */
	MIXED_FORM;

}
