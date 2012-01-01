/***************************************************************************
 *
 *   Clone4Testing.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.testing;


public class Clone4Testing implements Cloneable {

    private int intVar;
    private boolean booleanVar;
    private String stringVar;
    private Boolean booleanVar2;
    private Double doubleVar;
    private short shortVar;

    public int getIntVar() {
	return intVar;
    }

    public void setIntVar(int intVar) {
	this.intVar = intVar;
    }

    public boolean isBooleanVar() {
	return booleanVar;
    }

    public void setBooleanVar(boolean booleanVar) {
	this.booleanVar = booleanVar;
    }

    public String getStringVar() {
	return stringVar;
    }

    public void setStringVar(String stringVar) {
	this.stringVar = stringVar;
    }

    public Boolean getBooleanVar2() {
	return booleanVar2;
    }

    public void setBooleanVar2(Boolean booleanVar2) {
	this.booleanVar2 = booleanVar2;
    }

    public Double getDoubleVar() {
	return doubleVar;
    }

    public void setDoubleVar(Double doubleVar) {
	this.doubleVar = doubleVar;
    }

    public short getShortVar() {
	return shortVar;
    }

    public void setShortVar(short shortVar) {
	this.shortVar = shortVar;
    }

    @Override
    public Object clone() {
	try {
	    Clone4Testing cloned = (Clone4Testing) super.clone();
	    cloned.intVar = this.intVar;
	    cloned.booleanVar = this.booleanVar;
	    cloned.stringVar = new String(this.stringVar);
	    cloned.booleanVar2 = new Boolean(this.booleanVar2);
	    cloned.doubleVar = new Double(this.doubleVar);
	    cloned.shortVar = this.shortVar;
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new RuntimeException(e);
	}
    }
}
