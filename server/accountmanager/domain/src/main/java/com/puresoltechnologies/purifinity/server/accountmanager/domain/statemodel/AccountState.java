package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.misc.statemodel.State;
import com.puresoltechnologies.commons.misc.statemodel.Transition;

public enum AccountState implements State<AccountState> {

	START {

		@Override
		public String getName() {
			return "start";
		}

		@Override
		public String getDescription() {
			return "This is the start state for the account. The account is not created, yet.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			Set<Transition<AccountState>> transitions = new HashSet<Transition<AccountState>>();
			transitions.add(AccountTransition.CREATE);
			return transitions;
		}
	},
	CREATED {

		@Override
		public String getName() {
			return "created";
		}

		@Override
		public String getDescription() {
			return "The account is created, but not activated, yet.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			Set<Transition<AccountState>> transitions = new HashSet<Transition<AccountState>>();
			transitions.add(AccountTransition.ACTIVATE);
			transitions.add(AccountTransition.LOCK);
			transitions.add(AccountTransition.DELETE);
			return transitions;
		}
	},
	ACTIVATED {

		@Override
		public String getName() {
			return "activated";
		}

		@Override
		public String getDescription() {
			return "The account is created and activated.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			Set<Transition<AccountState>> transitions = new HashSet<Transition<AccountState>>();
			transitions.add(AccountTransition.DEACTIVATE);
			transitions.add(AccountTransition.LOCK);
			return transitions;
		}
	},
	DEACTIVATED {

		@Override
		public String getName() {
			return "deactivated";
		}

		@Override
		public String getDescription() {
			return "The account is deactivated and cannot be used. It can be activated again if requested.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			Set<Transition<AccountState>> transitions = new HashSet<Transition<AccountState>>();
			transitions.add(AccountTransition.REACTIVATE);
			transitions.add(AccountTransition.LOCK);
			transitions.add(AccountTransition.DELETE);
			return transitions;
		}
	},
	LOCKED {

		@Override
		public String getName() {
			return "locked";
		}

		@Override
		public String getDescription() {
			return "The account is locked by the administration authority. It is not a user request and the user needs to contact the administrator to settle an issue to get the possibility to reactivate the account.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			Set<Transition<AccountState>> transitions = new HashSet<Transition<AccountState>>();
			transitions.add(AccountTransition.UNLOCK);
			transitions.add(AccountTransition.DELETE);
			return transitions;
		}
	},
	DELETED {

		@Override
		public String getName() {
			return "deleted";
		}

		@Override
		public String getDescription() {
			return "The account is deleted. This cannot be undone.";
		}

		@Override
		public Set<Transition<AccountState>> getTransitions() {
			return new HashSet<Transition<AccountState>>();
		}
	},
	;

}
