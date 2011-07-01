package com.puresol.opencl;

import com.sun.jna.Library;

public interface OpenCL extends Library {

	public int clGetPlatformIDs(int num, int platforms[],
			int ret_num_platforms[]);

	public int clGetDeviceIDs(int platformId, long deviceType, int num,
			int deviceIds[], int deviceNum[]);

}
