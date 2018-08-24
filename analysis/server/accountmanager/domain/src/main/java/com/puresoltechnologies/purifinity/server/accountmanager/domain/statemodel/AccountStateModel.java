package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.statemodel.AbstractStateModel;

public class AccountStateModel extends
		AbstractStateModel<AccountState, AccountTransition> {

	@Override
	public AccountState getStartState() {
		return AccountState.START;
	}

	@Override
	public Set<AccountState> getEndStates() {
		Set<AccountState> endStates = new HashSet<>();
		endStates.add(AccountState.DELETED);
		return endStates;
	}

	@Override
	public Set<AccountState> getVertices() {
		throw new IllegalStateException(
				"This state model does not support graph traversals.");
	}
}
