package com.puresoltechnologies.toolshed.server.impl.system.linux;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinuxProcesses {

    private static final Logger logger = LoggerFactory.getLogger(LinuxProcesses.class);

    public static String readCmdline(int pid) {
	File cmdlineFile = new File("/proc/" + pid + "/cmdline");
	try (FileInputStream fileInputStream = new FileInputStream(cmdlineFile)) {
	    String cmdline = IOUtils.toString(fileInputStream, Charset.defaultCharset());
	    return cmdline.trim();
	} catch (IOException e) {
	    logger.error("Could not read cmdline for proces '" + pid + "'.", e);
	    return "";
	}
    }

    public static String readComm(int pid) {
	File cmdlineFile = new File("/proc/" + pid + "/comm");
	try (FileInputStream fileInputStream = new FileInputStream(cmdlineFile)) {
	    String cmdline = IOUtils.toString(fileInputStream, Charset.defaultCharset());
	    return cmdline.trim();
	} catch (IOException e) {
	    logger.error("Could not read cmdline for proces '" + pid + "'.", e);
	    return "";
	}
    }

}
