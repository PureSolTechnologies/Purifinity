package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.genesis.transformation.spi.TransformationSequence;
import com.puresoltechnologies.genesis.transformation.spi.Transformator;

public class PasswordStoreTransformator implements Transformator {

	public static final String TARGET_NAME = "PasswordStore";

	@Override
	public String getTargetName() {
		return TARGET_NAME;
	}

	@Override
	public Set<TransformationSequence> getSequences() {
		Set<TransformationSequence> sequences = new HashSet<>();
		sequences.add(new PasswordStoreSequence100());
		return sequences;
	}

}
