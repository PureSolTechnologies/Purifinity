package com.puresoltechnologies.purifinity.client.common.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evaluators implements AutoCloseable {

	public static Evaluators createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EvaluatorFactory> getAllMetrics() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	public List<EvaluatorFactory> getAll() {
		return Collections.emptyList();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	public List<EvaluatorFactory> getAllSortedByDependency() {
		// TODO Auto-generated method stub
		return null;
	}

}
