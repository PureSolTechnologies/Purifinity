package com.puresoltechnologies.toolshed.jpcap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PackageCaptureTest {

    @Test
    public void test() {
	PackageCapture instance = PackageCapture.getInstance();
	assertNotNull(instance);
    }

}
