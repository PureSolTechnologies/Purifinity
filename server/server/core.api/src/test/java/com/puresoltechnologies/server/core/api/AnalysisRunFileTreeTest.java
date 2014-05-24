package com.puresoltechnologies.server.core.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;

public class AnalysisRunFileTreeTest {

	@Test
	public void testSerialization() throws Exception {
		AnalysisRunFileTree root = new AnalysisRunFileTree(null, "root", false);
		AnalysisRunFileTree child1 = new AnalysisRunFileTree(root, "child1",
				true);
		AnalysisRunFileTree child2 = new AnalysisRunFileTree(root, "child2",
				false);
		AnalysisRunFileTree child21 = new AnalysisRunFileTree(child2,
				"child21", true);
		AnalysisRunFileTree child22 = new AnalysisRunFileTree(child2,
				"child22", true);

		String serialized = JSONSerializer.toJSONString(root);
		assertNotNull(serialized);
		assertFalse(serialized.isEmpty());

		AnalysisRunFileTree deserialized = JSONSerializer.fromJSONString(
				serialized, AnalysisRunFileTree.class);
		assertNotNull(deserialized);

		assertEquals(root, deserialized);
	}

}
