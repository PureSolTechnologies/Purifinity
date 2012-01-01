package com.puresol.uhura.ust.types;

import javax.i18n4java.Translator;

/**
 * This type is for logical values. This type has different names in different
 * languages:
 * 
 * <pre>
 * 	bool - C++
 *  boolean - Java
 *  logical - Fortran
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LogicalType extends Type {

	private static final long serialVersionUID = -5227270180409950680L;

	private static final Translator translator = Translator
			.getTranslator(LogicalType.class);

	public LogicalType(String originalSymbol) {
		super(originalSymbol);
	}

	@Override
	public String getName() {
		return translator.i18n("Logical Type");
	}

}
