package com.puresol.accountmanager.domain.statemodel;

import com.puresol.commons.utils.statemodel.AbstractStateModel;

public class AccountStateModel extends AbstractStateModel<AccountState> {

	@Override
	public AccountState getStartState() {
		return AccountState.START;
	}

	@Override
	public AccountState getEndState() {
		return AccountState.DELETED;
	}
}
