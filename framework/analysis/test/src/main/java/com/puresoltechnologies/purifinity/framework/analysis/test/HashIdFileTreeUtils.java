package com.puresoltechnologies.purifinity.framework.analysis.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.HashUtilities;
import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileTree;
import com.puresoltechnologies.purifinity.framework.commons.utils.data.HashCodeGenerator;

public class HashIdFileTreeUtils {

	public static HashIdFileTree convertToHashIdFileTree(FileTree fileTree)
			throws IOException {
		Map<FileTree, HashId> hashes = new HashMap<>();
		calculateHashes(hashes, fileTree);
		return convertToHashIdFileTree(hashes, fileTree, null);
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
	private static HashIdFileTree convertToHashIdFileTree(
			Map<FileTree, HashId> hashes, FileTree node,
			HashIdFileTree parent) throws IOException {
		String name = node.getName();
		HashId hashId = hashes.get(node);
		File file = node.getPathFile(true);
		HashIdFileTree currentNode = new HashIdFileTree(parent, name, hashId,
				file.isFile());
		for (FileTree child : node.getChildren()) {
			convertToHashIdFileTree(hashes, child, currentNode);
		}
		return currentNode;
	}

}
