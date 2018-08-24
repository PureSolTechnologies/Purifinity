package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTreeMetaData;

public class FoundGoto {

    private final UniversalSyntaxTree gotoStmt;

    public FoundGoto(UniversalSyntaxTree gotoStmt) {
	this.gotoStmt = gotoStmt;
    }

    public UniversalSyntaxTreeMetaData getMetaData() {
	return gotoStmt.getMetaData();
    }

}
