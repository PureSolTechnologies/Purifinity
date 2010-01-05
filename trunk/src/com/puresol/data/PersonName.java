package com.puresol.data;

import java.util.ArrayList;

public class PersonName {

    private ArrayList<PersonNamePart> nameParts = null;

    public PersonName(ArrayList<PersonNamePart> nameParts)
	    throws IllegalPersonNameException {
	this.nameParts = nameParts;
	check();
    }

    private void check() throws IllegalPersonNameException {
	if (nameParts == null) {
	    throw new IllegalPersonNameException(this);
	}
    }

    public String toString() {
	String result = nameParts.get(0).toString();
	for (int index = 1; index < nameParts.size(); index++) {
	    result += " " + nameParts.get(index).toString();
	}
	return result;
    }

    public String getName() {
	String result = nameParts.get(0).getName();
	for (int index = 1; index < nameParts.size(); index++) {
	    result += " " + nameParts.get(index).getName();
	}
	return result;
    }
}
