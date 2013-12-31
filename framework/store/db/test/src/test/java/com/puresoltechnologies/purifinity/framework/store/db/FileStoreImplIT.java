package com.puresoltechnologies.purifinity.framework.store.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.framework.store.api.FileStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.analysis.FileStoreImpl;

public class FileStoreImplIT extends AbstractDbStoreTest {

	private final FileStoreImpl fileStore = new FileStoreImpl();

	@Test
	public void testStoreAndReadRawFile() throws FileStoreException,
			IOException {
		String fileContent = "This is the sample file content to be stored for test...";
		HashId hashId;
		try (ByteArrayInputStream stream = new ByteArrayInputStream(
				fileContent.getBytes())) {
			hashId = fileStore.storeRawFile(stream);
			assertNotNull(hashId);
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

	@Test
	public void testStoreAndReadAnalysis() throws FileStoreException,
			IOException {
		String fileContent = "This is the sample file content to be stored for test...";
		HashId hashId;
		try (ByteArrayInputStream stream = new ByteArrayInputStream(
				fileContent.getBytes())) {
			hashId = fileStore.storeRawFile(stream);
			assertNotNull(hashId);
			assertEquals(HashUtilities.getDefaultMessageDigestAlgorithm(),
					hashId.getAlgorithm());
			assertNotNull(hashId.getHash());
		}
		AnalysisInformation analyzedFile = new AnalysisInformation(hashId,
				new Date(), 12345l, true, "language", "1.2.3");
		CodeAnalysis fileAnalysis = new CodeAnalysis(new Date(), 12345l,
				"language", "1.2.3", analyzedFile, new ArrayList<CodeRange>(),
				(UniversalSyntaxTree) null);
		fileStore.storeAnalysis(hashId, fileAnalysis);
		CodeAnalysis fileAnalysisRead = fileStore.loadAnalysis(hashId);
		assertNotSame(fileAnalysis, fileAnalysisRead);
		assertEquals(fileAnalysis, fileAnalysisRead);
	}
}
