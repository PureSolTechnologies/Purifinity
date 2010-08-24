package com.puresol.uhura.parser.parsetable;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Production;

public interface Item {

	public Production getProduction();

	public void incPosition();

	public int getPosition();

	public Construction getNext();

	public boolean hasNext();

	public Construction get2ndNext();

	public boolean has2ndNext();

}
