package com.puresol.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.puresol.commons.utils.statemodel.Transition;

public class AccountStateModelTest {

	@Test
	public void testStartState() {
		AccountStateModel stateModel = new AccountStateModel();
		AccountState startState = stateModel.getStartState();
		assertEquals(AccountState.START, startState);
		List<Transition<AccountState>> transitions = startState
				.getTransitions();
		assertTrue("Start state must have one transition!",
				transitions.size() > 0);

	}

	@Test
	public void testEndState() {
		AccountStateModel stateModel = new AccountStateModel();
		AccountState endState = stateModel.getEndState();
		assertEquals(AccountState.DELETED, endState);
		List<Transition<AccountState>> transitions = endState.getTransitions();
		assertEquals("End state must not have any transition!", 0,
				transitions.size());
	}

}
