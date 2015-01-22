package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashId;

public class AnalysisStoreServiceBeanTest {

	@Test
	public void testCalculateDirectoryHashIdWithEmptyList() {
		List<HashId> hashIds = new ArrayList<>();
		HashId hashId = AnalysisStoreServiceBean
				.calculateDirectoryHashId(hashIds);
		assertNotNull(hashId);
		assertEquals(
				"SHA-256:e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
				hashId.toString());
	}

	@Test
	public void testCalculateDirectoryHashId() {
		List<HashId> hashIds = new ArrayList<>();
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000001"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000002"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000003"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000004"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000005"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000006"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000007"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000008"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000009"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"000000000000000000000000000000000000000000000000000000000000000A"));
		HashId hashId = AnalysisStoreServiceBean
				.calculateDirectoryHashId(hashIds);
		assertNotNull(hashId);
		assertEquals(
				"SHA-256:ac683da0a7a486cbbc81c4f207e1f5665be6f3cd9792ff96a666a828296a49f5",
				hashId.toString());
	}

	@Test
	public void testCalculateDirectoryHashIdShuffeled() {
		List<HashId> hashIds = new ArrayList<>();
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000009"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000002"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000001"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000005"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000006"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000008"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000004"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000007"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"0000000000000000000000000000000000000000000000000000000000000003"));
		hashIds.add(new HashId(HashAlgorithm.SHA256,
				"000000000000000000000000000000000000000000000000000000000000000A"));
		HashId hashId = AnalysisStoreServiceBean
				.calculateDirectoryHashId(hashIds);
		assertNotNull(hashId);
		assertEquals(
				"SHA-256:ac683da0a7a486cbbc81c4f207e1f5665be6f3cd9792ff96a666a828296a49f5",
				hashId.toString());
	}

}
