package com.puresoltechnologies.purifinity.database.titan.utils;

import java.io.PrintStream;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public class TitanUtils {

	public static void printVertexInformation(PrintStream printStream,
			Vertex vertex) {
		printStream.println("Vertex: id=" + vertex.getId());
		printStream.println("==========================");
		printStream.println("Properties:");
		for (String key : vertex.getPropertyKeys()) {
			printStream.println("\t" + key + "=" + vertex.getProperty(key));
		}
		printStream.println("Edges:");
		for (Edge edge : vertex.getEdges(Direction.BOTH)) {
			printStream.println("\t" + edge.getLabel());
			for (String key : edge.getPropertyKeys()) {
				printStream.println("\t\t" + key + "=" + edge.getProperty(key));
			}
		}
	}
}
