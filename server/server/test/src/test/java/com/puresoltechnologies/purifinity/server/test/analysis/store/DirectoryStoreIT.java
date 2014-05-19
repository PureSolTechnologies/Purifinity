package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;

public class DirectoryStoreIT extends AbstractAnalysisStoreServiceServerTest {

	@Inject
	private DirectoryStore directoryStore;

	@Before
	public void checkPreConditions() {
		assertNotNull(directoryStore);
	}

	@Test
	public void test() {
	}
}
