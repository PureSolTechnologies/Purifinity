package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.statemodel.AbstractStateModel;

public class AccountStateModel extends AbstractStateModel<AccountState> {

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
}
