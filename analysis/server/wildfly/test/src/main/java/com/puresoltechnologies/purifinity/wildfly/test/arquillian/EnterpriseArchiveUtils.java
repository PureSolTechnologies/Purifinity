package com.puresoltechnologies.purifinity.wildfly.test.arquillian;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class EnterpriseArchiveUtils {

	/**
	 * This constant contains the name of the application.xml.
	 */
	public static final String APPLICATION_XML_FILE_NAME = "application.xml";

	/**
	 * This constant contains the path to the application.xml within an
	 * {@link EnterpriseArchive}.f
	 */
	public static final String APPLICATION_XML_PATH = "META-INF/"
			+ APPLICATION_XML_FILE_NAME;

	/**
	 * This constant contains the filename of the JBoss deployment structure
	 * file.
	 */
	public static final String JBOSS_DEPLOYMENT_STRUCTURE_XML_FILE_NAME = "jboss-deployment-structure.xml";

	/**
	 * This constant contains the path to the JBoss deployment structure file.
	 */
	public static final String JBOSS_DEPLOYMENT_STRUCTURE_XML_PATH = "META-INF/"
			+ JBOSS_DEPLOYMENT_STRUCTURE_XML_FILE_NAME;

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private EnterpriseArchiveUtils() {
	}

	/**
	 * This method reads the file /META/INF/application.xml from the enterprise
	 * archive provided.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be searched for the
	 *            application.xml.
	 * @return The application.xml is returned as {@link String}.
	 * @throws FileNotFoundException
	 *             is thrown in case of the application.xml was not found in
	 *             directory /META-INF.
	 * @throws IOException
	 *             is thrown if the application.xml could not be read.
	 */
	public static String getApplicationXml(EnterpriseArchive enterpriseArchive)
			throws FileNotFoundException, IOException {
		Node node = enterpriseArchive.get(APPLICATION_XML_PATH);
		if (node == null) {
			throw new FileNotFoundException("File '" + APPLICATION_XML_PATH
					+ "' was not found in enterprise archive '"
					+ enterpriseArchive.getName());
		}
		InputStream applicationXmlStream = node.getAsset().openStream();
		if (applicationXmlStream == null) {
			throw new IOException("File '" + APPLICATION_XML_PATH
					+ "' seems to be a directory in enterprise archive '"
					+ enterpriseArchive.getName());
		}
		try {
			String resultString = IOUtils.toString(applicationXmlStream);
			return resultString;
		} finally {
			applicationXmlStream.close();
		}
	}

	/**
	 * This modules removes a module from an enterprise archive.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be searched for a module
	 *            to be removed.
	 * @param moduleName
	 *            is the name of the module to be removed.
	 * @throws Exception
	 *             is thrown if the modules could not be removed.
	 */
	public static void removeModule(EnterpriseArchive enterpriseArchive,
			String moduleName) throws Exception {
		enterpriseArchive.delete("/" + moduleName);
		removeModuleFromApplicationXml(enterpriseArchive, moduleName);
		removeModuleFromJBossDeploymentStructureXml(enterpriseArchive,
				moduleName);
	}

	/**
	 * This method removes a module from the application.xml within an
	 * enterprise archive.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be edited.
	 * @param moduleName
	 *            is the module name of the module to be removed.
	 * @throws Exception
	 *             is thrown if the module was not removed.
	 */
	private static void removeModuleFromApplicationXml(
			EnterpriseArchive enterpriseArchive, String moduleName)
			throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Node applicationXmlNode = enterpriseArchive.get(APPLICATION_XML_PATH);
		Document applicationXmlDoc = db.parse(applicationXmlNode.getAsset()
				.openStream());
		Element applicationXmlRootElement = applicationXmlDoc
				.getDocumentElement();
		NodeList moduleList = applicationXmlRootElement
				.getElementsByTagName("module");
		if (moduleList != null && moduleList.getLength() > 0) {
			for (int i = 0; i < moduleList.getLength(); i++) {
				Element moduleElement = (Element) moduleList.item(i);
				NodeList nl = moduleElement.getElementsByTagName("web-uri");
				if (nl != null && nl.getLength() > 0) {
					Element webEl = (Element) nl.item(0);
					String textVal = webEl.getFirstChild().getNodeValue();
					if (textVal.equals(moduleName)) {
						moduleElement.getParentNode()
								.removeChild(moduleElement);
					}
				}
			}
		}
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		StringWriter sw = new StringWriter();
		transformer.transform(new DOMSource(applicationXmlDoc),
				new StreamResult(sw));
		setApplicationXml(enterpriseArchive, sw.getBuffer().toString());
	}

	/**
	 * This methods removes a provided module from the JBoss deployment
	 * structure.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be edited.
	 * @param moduleName
	 *            is the moduleName of the module to be removed.
	 * @throws Exception
	 *             is thrown if the module was not removed.
	 */
	private static void removeModuleFromJBossDeploymentStructureXml(
			EnterpriseArchive enterpriseArchive, String moduleName)
			throws Exception {
		Node node = enterpriseArchive.get(JBOSS_DEPLOYMENT_STRUCTURE_XML_PATH);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document deploymentStructureDoc = db
				.parse(node.getAsset().openStream());
		Element rootElement = deploymentStructureDoc.getDocumentElement();
		NodeList subDeploymentList = rootElement
				.getElementsByTagName("sub-deployment");

		if (subDeploymentList != null && subDeploymentList.getLength() > 0) {
			for (int i = 0; i < subDeploymentList.getLength(); i++) {
				Element subDeploymentElement = (Element) subDeploymentList
						.item(i);
				String subDeployment = subDeploymentElement
						.getAttribute("name");
				if (moduleName.equals(subDeployment)) {
					rootElement.removeChild(subDeploymentElement);
				}
			}
		}
		StringWriter sw = new StringWriter();
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(new DOMSource(deploymentStructureDoc),
				new StreamResult(sw));
		setJBossDeploymentStructureXml(enterpriseArchive, sw.getBuffer()
				.toString());
	}

	/**
	 * This method sets a new JBoss deployment structure for an enterprise
	 * archive.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be edited.
	 * @param jbossDeploymentStructure
	 *            is the content of the new deployment structure.
	 * @throws IOException
	 *             No application.xml found
	 */
	public static void setJBossDeploymentStructureXml(
			EnterpriseArchive enterpriseArchive, String jbossDeploymentStructure)
			throws IOException {
		File tempFile = File.createTempFile(
				JBOSS_DEPLOYMENT_STRUCTURE_XML_FILE_NAME, ".temp");
		tempFile.deleteOnExit();
		IOUtils.write(jbossDeploymentStructure, new FileOutputStream(tempFile));
		// delete the old descriptor
		enterpriseArchive.delete(JBOSS_DEPLOYMENT_STRUCTURE_XML_PATH);
		// set the new descriptor
		enterpriseArchive.addAsManifestResource(tempFile,
				JBOSS_DEPLOYMENT_STRUCTURE_XML_FILE_NAME);
	}

	/**
	 * This method sets a new application.xml for the provided enterprise
	 * archive.
	 * 
	 * @param enterpriseArchive
	 *            is the {@link EnterpriseArchive} to be edited.
	 * @param applicationXML
	 *            is the content of the application.xml as {@link String}.
	 * @throws IOException
	 *             is thrown if the new application.xml was not set.
	 */
	public static void setApplicationXml(EnterpriseArchive enterpriseArchive,
			String applicationXML) throws IOException {
		File tempApplicationXmlFile = File.createTempFile(
				APPLICATION_XML_FILE_NAME, ".temp");
		tempApplicationXmlFile.deleteOnExit();
		IOUtils.write(applicationXML, new FileOutputStream(
				tempApplicationXmlFile));

		// delete old application.xml
		enterpriseArchive.delete(APPLICATION_XML_PATH);

		// set the new application.xml
		enterpriseArchive.setApplicationXML(tempApplicationXmlFile);
	}

}
