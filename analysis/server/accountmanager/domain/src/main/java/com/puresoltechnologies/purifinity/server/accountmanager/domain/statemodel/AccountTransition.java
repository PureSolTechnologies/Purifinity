package com.puresoltechnologies.purifinity.server.accountmanager.domain.statemodel;

import com.puresoltechnologies.graph.Pair;
import com.puresoltechnologies.statemodel.Transition;

public enum AccountTransition implements
		Transition<AccountState, AccountTransition> {

	CREATE {

		@Override
		public String getName() {
			return "create";
		}

		@Override
		public String getDescription() {
			return "This action creates the account and sets some default values.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.CREATED;
		}
	},
	ACTIVATE {

		@Override
		public String getName() {
			return "activate";
		}

		@Override
		public String getDescription() {
			return "This action activates the account. Afterwards, the account is fully usable. This is a user request.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.ACTIVATED;
		}
	},
	DEACTIVATE {

		@Override
		public String getName() {
			return "deactivate";
		}

		@Override
		public String getDescription() {
			return "This action deactivates the account. The account cannot be used until re-activated. This is a user request.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.DEACTIVATED;
		}
	},
	REACTIVATE {

		@Override
		public String getName() {
			return "reactivate";
		}

		@Override
		public String getDescription() {
			return "This action reactivates an former activated account.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.ACTIVATED;
		}
	},
	LOCK {

		@Override
		public String getName() {
			return "lock";
		}

		@Override
		public String getDescription() {
			return "This action locks the account. This can only be done from administration authority due to some issues. The user needs to contact the authority to get the account unlocked.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.LOCKED;
		}
	},
	UNLOCK {

		@Override
		public String getName() {
			return "unlock";
		}

		@Override
		public String getDescription() {
			return "This action unlocks the account. The user can reactivate the account afterwards.";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.DEACTIVATED;
		}
	},
	DELETE {

		@Override
		public String getName() {
			return "delete";
		}

		@Override
		public String getDescription() {
			return "This action deletes the account. It cannot be undone and is final decision!";
		}

		@Override
		public AccountState getTargetState() {
			return AccountState.DELETED;
		}

	},
	;

	public abstract String getDescription();

	@Override
	public Pair<AccountState> getVertices() {
		throw new IllegalStateException(
				"This state model does not support graph traversals.");
	}
}
