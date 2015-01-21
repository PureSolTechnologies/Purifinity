package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.statemodel.State;

public enum AccountState implements State<AccountState, AccountTransition> {

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
		public Set<AccountTransition> getTransitions() {
			Set<AccountTransition> transitions = new HashSet<>();
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
		public Set<AccountTransition> getTransitions() {
			Set<AccountTransition> transitions = new HashSet<AccountTransition>();
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
		public Set<AccountTransition> getTransitions() {
			Set<AccountTransition> transitions = new HashSet<>();
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
		public Set<AccountTransition> getTransitions() {
			Set<AccountTransition> transitions = new HashSet<>();
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
		public Set<AccountTransition> getTransitions() {
			Set<AccountTransition> transitions = new HashSet<>();
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
		public Set<AccountTransition> getTransitions() {
			return new HashSet<AccountTransition>();
		}

		@Override
		public Set<AccountTransition> getEdges() {
			// TODO Auto-generated method stub
			return null;
		}
	},
	;

	public abstract String getDescription();

	@Override
	public Set<AccountTransition> getEdges() {
		throw new IllegalStateException(
				"This state model does not support graph traversals.");
	}
}
