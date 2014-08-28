package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Local;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerServiceManager extends AnalyzerServiceManagerCommon {

	public boolean isActive(String analyzerId);

	public void setActive(String analyzerId, boolean active);

}
