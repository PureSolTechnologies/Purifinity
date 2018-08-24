package com.puresoltechnologies.purifinity.server.ddl;

import org.junit.Test;

import com.puresoltechnologies.genesis.controller.GenesisController;

public class MigrationIT {

	@Test
	public void test() throws Exception {
		GenesisController.main(new String[] { "--drop", "--migrate" });
	}

}
