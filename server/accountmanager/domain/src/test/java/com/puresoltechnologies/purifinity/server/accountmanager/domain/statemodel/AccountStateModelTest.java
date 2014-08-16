package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.statemodel.Transition;

public class AccountStateModelTest {

	@Test
	public void testStartState() {
		AccountStateModel stateModel = new AccountStateModel();
		AccountState startState = stateModel.getStartState();
		assertEquals(AccountState.START, startState);
		Set<Transition<AccountState>> transitions = startState.getTransitions();
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
		Set<Transition<AccountState>> transitions = endState.getTransitions();
		assertEquals("End state must not have any transition!", 0,
				transitions.size());
	}

}
