package com.puresoltechnologies.parsers.impl.ust;

import java.util.List;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;

/**
 * This class is used in cases where no special production can be used for
 * example in transition productions.
 * 
 * @author Rick-Rainer Ludwig
 */
public class UnspecialistProduction extends AbstractProduction {

	private static final long serialVersionUID = -820255465228987814L;

	public UnspecialistProduction(String name, String originalName,
			List<UniversalSyntaxTree> children) {
		super(name, originalName, children);
	}

}