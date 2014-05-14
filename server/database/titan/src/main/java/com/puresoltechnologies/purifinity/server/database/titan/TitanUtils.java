package com.puresoltechnologies.purifinity.server.database.titan;

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

	public static void printEdgeInformation(PrintStream printStream, Edge edge) {
		printStream.println("Edge: id=" + edge.getId());
		printStream.println("==========================");
		printStream.println("Properties:");
		for (String key : edge.getPropertyKeys()) {
			printStream.println("\t" + key + "=" + edge.getProperty(key));
		}
		printStream.println("Vertices:");
		printStream.println("FROM/OUT:");
		printVertexInformation(printStream, edge.getVertex(Direction.OUT));
		printStream.println("TO/IN:");
		printVertexInformation(printStream, edge.getVertex(Direction.IN));
	}
}
