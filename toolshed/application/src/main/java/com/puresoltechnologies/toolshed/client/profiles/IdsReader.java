package com.puresoltechnologies.toolshed.client.profiles;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.BiConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.streaming.binary.BinaryInputStream;
import com.puresoltechnologies.streaming.binary.mapper.BinaryMapper;
import com.puresoltechnologies.streaming.binary.mapper.BinaryMappingException;
import com.puresoltechnologies.streaming.streams.OptimizedFileInputStream;
import com.puresoltechnologies.streaming.streams.PositionInputStream;
import com.puresoltechnologies.toolshed.client.profiles.graph.ClassVertex;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraph;
import com.puresoltechnologies.toolshed.client.profiles.graph.ImplementsEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.InvokesEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;

public class IdsReader implements Closeable {

    private static final Logger logger = LoggerFactory.getLogger(IdsReader.class);

    private final File file;
    private final CodeGraph codeGraph;
    private final PositionInputStream positionInputStream;
    private final BinaryInputStream binaryInputStream;
    private final BinaryMapper binaryMapper = new BinaryMapper(Charset.defaultCharset());
    private ClassVertex lastClassVertex = null;
    private MethodVertex lastMethodVertex = null;
    private BiConsumer<Long, Long> progressObserver = null;

    public IdsReader(File file, CodeGraph codeGraph) throws FileNotFoundException {
	this.file = file;
	this.codeGraph = codeGraph;
	OptimizedFileInputStream fileInputStream = new OptimizedFileInputStream(file);
	positionInputStream = new PositionInputStream(fileInputStream);
	binaryInputStream = new BinaryInputStream(positionInputStream, LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	binaryInputStream.close();
    }

    public CodeGraph getCodeGraph() {
	return codeGraph;
    }

    public long getPosition() {
	return positionInputStream.getPosition();
    }

    public void setProgressObserver(BiConsumer<Long, Long> progressObserver) {
	this.progressObserver = progressObserver;
    }

    public void read() {
	codeGraph.clear();
	try {
	    while (true) {
		int code = binaryInputStream.readUnsignedByte();
		switch (code) {
		case 1:
		    readClassDeclaration();
		    break;
		case 2:
		    readMethodDeclaration();
		    break;
		case 3:
		    readMethodInvokation();
		    break;
		default:
		    throw new IllegalStateException(
			    "Unknown section id " + code + " found. Position: " + positionInputStream.getPosition());
		}
		if (progressObserver != null) {
		    progressObserver.accept(getPosition(), file.length());
		}
	    }
	} catch (BinaryMappingException | IOException e) {
	    logger.warn("Could not read ids file.", e);
	}
    }

    private void readClassDeclaration() throws BinaryMappingException, IOException {
	ClassDeclarationEntry classDeclaration = binaryMapper.read(binaryInputStream, ClassDeclarationEntry.class);
	String className = classDeclaration.getClassName();
	logger.debug("Found class '" + className + "'.");
	ClassVertex classVertex = codeGraph.findClass(className);
	if (classVertex == null) {
	    classVertex = new ClassVertex(className);
	    codeGraph.addVertex(classVertex);
	}
	lastClassVertex = classVertex;
    }

    private void readMethodDeclaration() throws BinaryMappingException, IOException {
	try {
	    MethodDeclarationEntry methodDeclaration = binaryMapper.read(binaryInputStream,
		    MethodDeclarationEntry.class);
	    String methodName = methodDeclaration.getMethodName();
	    String descriptor = methodDeclaration.getDescriptor();
	    logger.trace("Found method '" + methodName + descriptor + "'.");
	    MethodVertex methodVertex = new MethodVertex(lastClassVertex.getClassName(), methodName, descriptor);
	    lastMethodVertex = methodVertex;
	    codeGraph.addVertex(methodVertex);
	    ImplementsEdge implementsEdge = new ImplementsEdge(methodVertex);
	    lastClassVertex.addImplementation(implementsEdge);
	} catch (NullPointerException e) {
	    logger.error("Could not read ids file.");
	    throw e;
	}
    }

    private void readMethodInvokation() throws BinaryMappingException, IOException {
	MethodInvocationEntry methodInvocation = binaryMapper.read(binaryInputStream, MethodInvocationEntry.class);
	String className = methodInvocation.getClassName();
	String methodName = methodInvocation.getMethodName();
	String descriptor = methodInvocation.getDescriptor();
	logger.trace("Found method invokation '" + className + "#" + methodName + descriptor + "'.");
	ClassVertex classVertex = codeGraph.findClass(className);
	if (classVertex == null) {
	    classVertex = new ClassVertex(className);
	    codeGraph.addVertex(classVertex);
	}
	MethodVertex methodVertex = codeGraph.findMethod(className, methodName, descriptor);
	if (methodVertex == null) {
	    methodVertex = new MethodVertex(className, methodName, descriptor);
	    codeGraph.addVertex(methodVertex);
	}
	InvokesEdge invokesEdge = new InvokesEdge(methodVertex);
	lastMethodVertex.addInvokation(invokesEdge);
    }

}
