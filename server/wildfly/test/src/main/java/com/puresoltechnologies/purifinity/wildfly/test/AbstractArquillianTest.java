package com.puresoltechnologies.purifinity.wildfly.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * This class contains all common settings and functionality used for Arquillian
 * client and server tests.
 * 
 * To select WARs for testing, have a look to post <a
 * href="http://arquillian.org/blog/2012/07/25/arquillian-core-1-0-2-Final"
 * >http://arquillian.org/blog/2012/07/25/arquillian-core-1-0-2-Final</a>.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractArquillianTest {

    public static final File APPLICATION_DIRECTORY = new File("../app/target");
    public static final File PLUGIN_DIRECTORY = new File("../plugin/target");
    private static final String EAR_EXTENSION = ".ear";
    private static final String APPLICATION_XML_PATH = "/META-INF/application.xml";

    protected static File findApplicationEARFile() {
	return findEARFileInDirectory(APPLICATION_DIRECTORY);
    }

    protected static File findPluginEARFile() {
	return findEARFileInDirectory(PLUGIN_DIRECTORY);
    }

    private static File findEARFileInDirectory(File directory) {
	if (!directory.exists()) {
	    throw new IllegalStateException(
		    "The directory '"
			    + directory
			    + "' does not exist! "
			    + "There is maybe an issue with the project setup or do you need to build with Maven?");
	}
	File[] earFiles = directory.listFiles(new FilenameSuffixFilter(
		EAR_EXTENSION));
	if (earFiles.length == 0) {
	    throw new IllegalStateException(
		    "Cannot find EAR file! Application needs to be built.");
	}
	if (earFiles.length > 1) {
	    throw new IllegalStateException(
		    "Multiple EAR files were found! There is only on EAR file allowed. "
			    + "Maybe this is an issue with the project setup.");
	}
	return earFiles[0];
    }

    protected static void removeWAR(EnterpriseArchive archive, String warName)
	    throws Exception {
	// Delete war file
	archive.delete("/" + warName);
	// now we remove the module entry in application.xml
	Node node = archive.get(APPLICATION_XML_PATH);
	DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
		.newDocumentBuilder();
	try (InputStream stream = node.getAsset().openStream()) {
	    Document document = documentBuilder.parse(stream);
	    NodeList elements = document.getElementsByTagName("web-uri");
	    for (int index = 0; index < elements.getLength(); index++) {
		org.w3c.dom.Node item = elements.item(index);
		if (item.getTextContent().equals(warName)) {
		    org.w3c.dom.Node webNode = item.getParentNode();
		    org.w3c.dom.Node moduleNode = webNode.getParentNode();
		    moduleNode.getParentNode().removeChild(moduleNode);
		}
	    }
	    document.normalize();
	    archive.delete(APPLICATION_XML_PATH);

	    Transformer transformer = TransformerFactory.newInstance()
		    .newTransformer();
	    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
		transformer.transform(new DOMSource(document),
			new StreamResult(byteArrayOutputStream));
		archive.add(
			new ByteArrayAsset(byteArrayOutputStream.toByteArray()),
			APPLICATION_XML_PATH);
	    }
	}
    }

}
