package com.puresoltechnologies.purifinity.server.test.analysis;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.os.HashAlgorithm;
import com.puresoltechnologies.commons.os.HashCodeGenerator;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.commons.os.HashUtilities;
import com.puresoltechnologies.parsers.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.server.common.utils.io.FileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;

public class TreeTestUtils {

	public static AnalysisRunFileTree convertToHashIdFileTree(FileTree fileTree)
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
	private static AnalysisRunFileTree convertToAnalysisFileTree(
			Map<FileTree, HashId> hashes, FileTree node,
			AnalysisRunFileTree parent) throws IOException {
		String name = node.getName();
		HashId hashId = hashes.get(node);
		File file = node.getPathFile(true);
		boolean isFile = file.isFile();
		AnalysisRunFileTree currentNode = new AnalysisRunFileTree(parent, name,
				isFile, new UnspecifiedSourceCodeLocation(), hashId);
		for (FileTree child : node.getChildren()) {
			convertToAnalysisFileTree(hashes, child, currentNode);
		}
		return currentNode;
	}

}
