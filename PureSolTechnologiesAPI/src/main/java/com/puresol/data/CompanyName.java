package com.puresol.data;


public class CompanyName implements Cloneable {

    private String name = "";
    private String organizationalForm = "";

    public CompanyName(String name, String organizationalForm)
	    throws IllegalCompanyNameException {
	this.name = name;
	this.organizationalForm = organizationalForm;
	check();
    }

    private void check() throws IllegalCompanyNameException {
	if (name == null) {
	    throw new IllegalCompanyNameException(this);
	}
	if (name.isEmpty()) {
	    throw new IllegalCompanyNameException(this);
	}
	if (organizationalForm == null) {
	    throw new IllegalCompanyNameException(this);
	}
    }

    @Override
    public String toString() {
	return name + " " + organizationalForm;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime
		* result
		+ ((organizationalForm == null) ? 0 : organizationalForm
			.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CompanyName other = (CompanyName) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (organizationalForm == null) {
	    if (other.organizationalForm != null)
		return false;
	} else if (!organizationalForm.equals(other.organizationalForm))
	    return false;
	return true;
    }

    @Override
    public CompanyName clone() {
	try {
	    CompanyName cloned = (CompanyName) super.clone();
	    cloned.name = this.name;
	    cloned.organizationalForm = this.organizationalForm;
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new RuntimeException(e);
	}
    }
}
