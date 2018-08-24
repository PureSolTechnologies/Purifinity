package com.puresoltechnologies.toolshed.client.profiles;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedInt;

public class ClassDeclarationEntry {

    private final String className;
    private final int classId;

    @BinaryCreator
    public ClassDeclarationEntry( //
	    @BinaryNulTerminateString("className") String className, //
	    @BinarySignedInt("classId") int classId //
    ) {
	this.className = className;
	this.classId = classId;
    }

    public String getClassName() {
	return className;
    }

    public int getClassId() {
	return classId;
    }

}
