package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountTransitionTest {

	@Test
	public void testNumberOfTransitions() {
		assertEquals(7, AccountTransition.values().length);
	}

	@Test
	public void testCreateTransitions() {
		AccountTransition transition = AccountTransition.CREATE;
		assertEquals(AccountState.CREATED, transition.getTargetState());
	}

	@Test
	public void testActivateTransitions() {
		AccountTransition transition = AccountTransition.ACTIVATE;
		assertEquals(AccountState.ACTIVATED, transition.getTargetState());
	}

	@Test
	public void testDeactivateTransitions() {
		AccountTransition transition = AccountTransition.DEACTIVATE;
		assertEquals(AccountState.DEACTIVATED, transition.getTargetState());
	}

	@Test
	public void testReactivateTransitions() {
		AccountTransition transition = AccountTransition.REACTIVATE;
		assertEquals(AccountState.ACTIVATED, transition.getTargetState());
	}

	@Test
	public void testLockTransitions() {
		AccountTransition transition = AccountTransition.LOCK;
		assertEquals(AccountState.LOCKED, transition.getTargetState());
	}

	@Test
	public void testUnlockTransitions() {
		AccountTransition transition = AccountTransition.UNLOCK;
		assertEquals(AccountState.DEACTIVATED, transition.getTargetState());
	}

	@Test
	public void testDeleteTransitions() {
		AccountTransition transition = AccountTransition.DELETE;
		assertEquals(AccountState.DELETED, transition.getTargetState());
	}

}
