package com.puresol.reporting.html;

import com.puresol.data.Identifiable;

public enum LinkTarget implements Identifiable {
    DEFAULT {
	@Override
	public String getIdentifier() {
	    return "default";
	}

	@Override
	public String getKeyword() {
	    return "";
	}
    },
    BLANK {
	@Override
	public String getIdentifier() {
	    return "blank";
	}

	@Override
	public String getKeyword() {
	    return "_blank";
	}
    },
    SELF {
	@Override
	public String getIdentifier() {
	    return "self";
	}

	@Override
	public String getKeyword() {
	    return "_self";
	}
    },
    TOP {
	@Override
	public String getIdentifier() {
	    return "top";
	}

	@Override
	public String getKeyword() {
	    return "_top";
	}
    },
    PARENT {
	@Override
	public String getIdentifier() {
	    return "parent";
	}

	@Override
	public String getKeyword() {
	    return "_parent";
	}
    };

    public abstract String getKeyword();

    @Override
    public abstract String getIdentifier();
}
