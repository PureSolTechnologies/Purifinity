package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.commons.utils.statemodel.Transition;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel.AccountState;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel.AccountTransition;

public class AccountStateTest {

	@Test
	public void testNumberOfPossibleStates() {
		assertEquals(6, AccountState.values().length);
	}

	@Test
	public void testStartState() {
		AccountState state = AccountState.START;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(1, transitions.size());
		assertTrue(transitions.contains(AccountTransition.CREATE));
	}

	@Test
	public void testCreatedState() {
		AccountState state = AccountState.CREATED;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(3, transitions.size());
		assertTrue(transitions.contains(AccountTransition.ACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testActivatedState() {
		AccountState state = AccountState.ACTIVATED;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(2, transitions.size());
		assertTrue(transitions.contains(AccountTransition.DEACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
	}

	@Test
	public void testDeactivatedState() {
		AccountState state = AccountState.DEACTIVATED;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(3, transitions.size());
		assertTrue(transitions.contains(AccountTransition.REACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testLockedState() {
		AccountState state = AccountState.LOCKED;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(2, transitions.size());
		assertTrue(transitions.contains(AccountTransition.UNLOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testDeletedState() {
		AccountState state = AccountState.DELETED;
		List<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(0, transitions.size());
	}

}
