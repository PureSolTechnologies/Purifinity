package com.puresol.opencl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jna.Native;

public class JNATest {

	@Test
	public void test() {
		OpenCL openCL = (OpenCL) Native.loadLibrary("OpenCL", OpenCL.class);
		int num = 1;
		int platforms[] = new int[1];
		int ret_num_platforms[] = new int[1];
		int result = openCL.clGetPlatformIDs(num, platforms, ret_num_platforms);
		assertEquals(0, result);
		assertEquals(1, ret_num_platforms[0]);
		System.out.print(platforms[0]);
		int deviceIds[] = new int[1];
		int deviceNum[] = new int[1];
		result = openCL
				.clGetDeviceIDs(platforms[0], 0, 4, deviceIds, deviceNum);
		assertEquals(0, result);
	}
}
