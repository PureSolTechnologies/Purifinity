package com.puresol.coding.lang.fortran;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractSourceCodeParser;

abstract public class AbstractFortranParser extends AbstractSourceCodeParser {

    private static final long serialVersionUID = -2927266642904908599L;

    @Override
    public ProgrammingLanguage getLanguage() {
	return Fortran.getInstance();
    }
}
