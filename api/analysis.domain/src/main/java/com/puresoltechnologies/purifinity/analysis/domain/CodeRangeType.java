package com.puresoltechnologies.purifinity.analysis.domain;

public enum CodeRangeType {

	DIRECTORY {
		@Override
		public String getName() {
			return "directory";
		}
	},
	FILE {
		@Override
		public String getName() {
			return "file";
		}
	},
	CLASS {
		@Override
		public String getName() {
			return "class";
		}
	},
	INTERFACE {
		@Override
		public String getName() {
			return "interface";
		}
	},
	ENUMERATION {
		@Override
		public String getName() {
			return "enumeration";
		}
	},
	ANNOTATION {
		@Override
		public String getName() {
			return "annotation";
		}
	},
	MODULE {
		@Override
		public String getName() {
			return "module";
		}
	},
	CONSTRUCTOR {
		@Override
		public String getName() {
			return "constructor";
		}
	},
	METHOD {
		@Override
		public String getName() {
			return "method";
		}
	},
	PROGRAM {
		@Override
		public String getName() {
			return "program";
		}
	},
	FUNCTION {
		@Override
		public String getName() {
			return "function";
		}
	},
	SUBROUTINE {
		@Override
		public String getName() {
			return "subroutine";
		}
	};

	public abstract String getName();

	@Override
	public String toString() {
		return getName();
	}

}
