package com.puresoltechnologies.toolshed.client.profiles;

import java.util.Objects;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedByte;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedLong;

public class ProfileEntry {

    private final byte type;
    private final String className;
    private final String methodName;
    private final String descriptor;
    private final long totalTime;
    private final long selfTime;
    private final long invocations;
    private final int hash;

    @BinaryCreator
    public ProfileEntry( //
	    @BinarySignedByte("type") byte type, //
	    @BinaryNulTerminateString("className") String className, //
	    @BinaryNulTerminateString("methodName") String methodName, //
	    @BinaryNulTerminateString("descriptor") String descriptor, //
	    @BinarySignedLong("totalTime") long totalTime, //
	    @BinarySignedLong("selfTime") long selfTime, //
	    @BinarySignedLong("invocations") long invocations //
    ) {
	super();
	this.type = type;
	this.className = className;
	this.methodName = methodName;
	this.descriptor = descriptor;
	this.totalTime = totalTime;
	this.selfTime = selfTime;
	this.invocations = invocations;
	this.hash = Objects.hash(type, className, methodName, totalTime, invocations);
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

    public String getDescriptor() {
	return descriptor;
    }

    public long getTotalTime() {
	return totalTime;
    }

    public long getSelfTime() {
	return selfTime;
    }

    public long getInvocations() {
	return invocations;
    }

    @Override
    public String toString() {
	return className + "#" + methodName + descriptor + ": " + invocations + "x " + totalTime + "ms";
    }

    @Override
    public int hashCode() {
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ProfileEntry other = (ProfileEntry) obj;
	if (hash != other.hash) {
	    return false;
	}
	if (className == null) {
	    if (other.className != null)
		return false;
	} else if (!className.equals(other.className))
	    return false;
	if (invocations != other.invocations)
	    return false;
	if (methodName == null) {
	    if (other.methodName != null)
		return false;
	} else if (!methodName.equals(other.methodName))
	    return false;
	if (totalTime != other.totalTime)
	    return false;
	if (type != other.type)
	    return false;
	return true;
    }

}
