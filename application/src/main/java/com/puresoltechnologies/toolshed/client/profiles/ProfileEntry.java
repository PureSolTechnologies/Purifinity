package com.puresoltechnologies.toolshed.client.profiles;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedByte;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedLong;

public class ProfileEntry {

    private final byte type;
    private final String className;
    private final String methodName;
    private final long totalTime;
    private final long invocations;

    @BinaryCreator
    public ProfileEntry( //
	    @BinarySignedByte("type") byte type, //
	    @BinaryNulTerminateString("className") String className, //
	    @BinaryNulTerminateString("methodName") String methodName, //
	    @BinarySignedLong("totalTime") long totalTime, //
	    @BinarySignedLong("invocations") long invocations //
    ) {
	super();
	this.type = type;
	this.className = className;
	this.methodName = methodName;
	this.totalTime = totalTime;
	this.invocations = invocations;
    }

    public byte getType() {
	return type;
    }

    public String getClassName() {
	return className;
    }

    public String getMethodName() {
	return methodName;
    }

    public long getTotalTime() {
	return totalTime;
    }

    public long getInvocations() {
	return invocations;
    }

}
