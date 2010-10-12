package com.puresol.uhura.parser.items;

import java.io.Serializable;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;

public interface Item extends Serializable {

	public Production getProduction();

	public int getPosition();

	public Construction getNext();

	public boolean hasNext();

	public Construction get2ndNext();

	public boolean has2ndNext();

}
