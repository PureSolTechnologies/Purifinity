package com.puresoltechnologies.purifinity.server.test.analysis.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.commons.misc.hash.HashUtilities;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.versioning.Version;

public class FileStoreIT extends AbstractAnalysisStoreServiceServerTest {

	@Inject
	private FileStore fileStore;

	@Before
	public void checkPreConditions() {
		assertNotNull(fileStore);
	}

	@Test
	public void testStoreAndReadRawFile() throws FileStoreException,
			IOException {
		String fileContent = "This is the sample file content to be stored for test...";
		HashId hashId;
		try (ByteArrayInputStream stream = new ByteArrayInputStream(
				fileContent.getBytes())) {
			FileInformation fileInformation = fileStore.storeRawFile(stream);
			assertNotNull(fileInformation);
			hashId = fileInformation.getHashId();
			assertEquals(HashUtilities.getDefaultMessageDigestAlgorithm(),
					hashId.getAlgorithm());
			assertNotNull(hashId.getHash());
		}
		try (InputStream rawFile = fileStore.readRawFile(hashId)) {
			assertNotNull(rawFile);
			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
				IOUtils.copy(rawFile, outputStream);
				String fileConentRead = outputStream.toString();
				assertEquals(fileContent, fileConentRead);
			}
		}
	}

	@Ignore("No correct analysis available. Create one!")
	@Test
	public void testStoreAndReadAnalysis() throws FileStoreException,
			IOException {
		String fileContent = "This is the sample file content to be stored for test...";
		HashId hashId;
		try (ByteArrayInputStream stream = new ByteArrayInputStream(
				fileContent.getBytes())) {
			FileInformation fileInformation = fileStore.storeRawFile(stream);
			assertNotNull(fileInformation);
			hashId = fileInformation.getHashId();
			assertEquals(HashUtilities.getDefaultMessageDigestAlgorithm(),
					hashId.getAlgorithm());
			assertNotNull(hashId.getHash());
		}
		AnalysisInformation analyzedFile = new AnalysisInformation(hashId,
				new Date(), 12345l, true, "language", "1.2.3", "analyzerId",
				Version.valueOf("2.3.4"));
		CodeAnalysis fileAnalysis = new CodeAnalysis(new Date(), 12345l,
				"language", "1.2.3", analyzedFile, new ArrayList<CodeRange>(),
				(UniversalSyntaxTree) null);
		fileStore.storeAnalysis(fileAnalysis);
		List<CodeAnalysis> fileAnalysisRead = fileStore.loadAnalyzes(hashId);
		assertNotNull(fileAnalysisRead);
		assertEquals(1, fileAnalysisRead.size());
		assertNotSame(fileAnalysis, fileAnalysisRead.get(0));
		assertEquals(fileAnalysis, fileAnalysisRead.get(0));
	}
}
