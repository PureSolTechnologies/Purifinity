package com.puresol.purifinity.uhura.ust.modifiers;

import com.puresol.purifinity.uhura.ust.USTNode;

public abstract class AbstractModifier extends USTNode {

	private static final long serialVersionUID = 6970210094744093023L;

	public AbstractModifier(String name, String originalSymbol) {
		super(name, originalSymbol);
	}
}
