package com.puresol.parser;

import java.util.ArrayList;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public abstract class AbstractTokenDefinitionGroup implements
	TokenDefinitionGroup {

    public final ArrayList<TokenDefinition> definitions =
	    new ArrayList<TokenDefinition>();

    public AbstractTokenDefinitionGroup() {
	initialize();
    }

    protected abstract void initialize();

    @Override
    public final void addTokenDefinition(
	    Class<? extends TokenDefinition> keyword)
	    throws TokenException {
	try {
	    definitions.add(Instances.createInstance(keyword));
	} catch (ClassInstantiationException e) {
	    throw new TokenException(e.getMessage());
	}
    }

    @Override
    public final void addTokenDefinition(TokenDefinition keyword) {
	definitions.add(keyword);
    }

    @Override
    public final void addTokenDefinitions(
	    Class<? extends TokenDefinitionGroup> definitions)
	    throws TokenException {
	try {
	    TokenDefinitionGroup definitionsInstance =
		    Instances.createInstance(definitions);
	    this.definitions.addAll(definitionsInstance
		    .getTokenDefinitions());
	} catch (ClassInstantiationException e) {
	    throw new TokenException(e.getMessage());
	}
    }

    @Override
    public final void addTokenDefinitions(TokenDefinitionGroup definitions) {
	this.definitions.addAll(definitions.getTokenDefinitions());
    }

    @Override
    public final ArrayList<TokenDefinition> getTokenDefinitions() {
	return definitions;
    }
}
