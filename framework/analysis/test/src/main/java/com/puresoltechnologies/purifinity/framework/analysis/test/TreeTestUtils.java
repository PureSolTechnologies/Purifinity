package com.puresoltechnologies.purifinity.framework.analysis.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileTree;
import com.puresoltechnologies.purifinity.framework.commons.utils.data.HashCodeGenerator;

public class TreeTestUtils {

	public static AnalysisFileTree convertToHashIdFileTree(FileTree fileTree)
			throws IOException {
		Map<FileTree, HashId> hashes = new HashMap<>();
		calculateHashes(hashes, fileTree);
		return convertToAnalysisFileTree(hashes, fileTree, null);
	}

	private static void calculateHashes(Map<FileTree, HashId> hashes,
			FileTree node) throws IOException {
		if (node.getPathFile(true).isFile()) {
			HashId hashId = HashUtilities.createHashId(node.getPathFile(true));
			hashes.put(node, hashId);
		} else {
			for (FileTree child : node.getChildren()) {
				calculateHashes(hashes, child);
			}
			StringBuilder builder = new StringBuilder();
			for (FileTree child : node.getChildren()) {
				if (builder.length() > 0) {
					builder.append(",");
				}
				HashId hashId = hashes.get(child);
				builder.append(hashId);
			}
			HashAlgorithm algorithm = HashUtilities
					.getDefaultMessageDigestAlgorithm();
			String hash = HashCodeGenerator.get(algorithm, builder.toString());
			HashId hashId = new HashId(algorithm, hash);
			hashes.put(node, hashId);
		}
	}

	/**
	 * ATTENTION!!!: This method is a fake implementation for testing only!
	 * 
	 * @param node
	 * @param parent
	 * @return
	 * @throws IOException
	 */
	private static AnalysisFileTree convertToAnalysisFileTree(
			Map<FileTree, HashId> hashes, FileTree node, AnalysisFileTree parent)
			throws IOException {
		String name = node.getName();
		HashId hashId = hashes.get(node);
		File file = node.getPathFile(true);
		boolean isFile = file.isFile();
		AnalysisFileTree currentNode = new AnalysisFileTree(parent, name,
				hashId, isFile, new UnspecifiedSourceCodeLocation(),
				isFile ? new ArrayList<AnalysisInformation>() : null);
		for (FileTree child : node.getChildren()) {
			convertToAnalysisFileTree(hashes, child, currentNode);
		}
		return currentNode;
	}

}
