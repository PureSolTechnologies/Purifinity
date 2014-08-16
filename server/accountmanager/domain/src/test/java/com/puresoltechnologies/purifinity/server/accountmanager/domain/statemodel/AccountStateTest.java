package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.puresoltechnologies.purifinity.server.common.utils.statemodel.Transition;

public class AccountStateTest {

	@Test
	public void testNumberOfPossibleStates() {
		assertEquals(6, AccountState.values().length);
	}

	@Test
	public void testStartState() {
		AccountState state = AccountState.START;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(1, transitions.size());
		assertTrue(transitions.contains(AccountTransition.CREATE));
	}

	@Test
	public void testCreatedState() {
		AccountState state = AccountState.CREATED;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(3, transitions.size());
		assertTrue(transitions.contains(AccountTransition.ACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testActivatedState() {
		AccountState state = AccountState.ACTIVATED;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(2, transitions.size());
		assertTrue(transitions.contains(AccountTransition.DEACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
	}

	@Test
	public void testDeactivatedState() {
		AccountState state = AccountState.DEACTIVATED;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(3, transitions.size());
		assertTrue(transitions.contains(AccountTransition.REACTIVATE));
		assertTrue(transitions.contains(AccountTransition.LOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testLockedState() {
		AccountState state = AccountState.LOCKED;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(2, transitions.size());
		assertTrue(transitions.contains(AccountTransition.UNLOCK));
		assertTrue(transitions.contains(AccountTransition.DELETE));
	}

	@Test
	public void testDeletedState() {
		AccountState state = AccountState.DELETED;
		Set<Transition<AccountState>> transitions = state.getTransitions();
		assertEquals(0, transitions.size());
	}

}
