package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class AccountStateModelTest {

	@Test
	public void testStartState() {
		AccountStateModel stateModel = new AccountStateModel();
		AccountState startState = stateModel.getStartState();
		assertEquals(AccountState.START, startState);
		Set<AccountTransition> transitions = startState.getTransitions();
		assertTrue("Start state must have one transition!",
				transitions.size() > 0);

	}

	@Test
	public void testEndState() {
		AccountStateModel stateModel = new AccountStateModel();
		Set<AccountState> endStates = stateModel.getEndStates();
		assertEquals(1, endStates.size());
		AccountState endState = endStates.iterator().next();
		assertEquals(AccountState.DELETED, endState);
		Set<AccountTransition> transitions = endState.getTransitions();
		assertEquals("End state must not have any transition!", 0,
				transitions.size());
	}

}
