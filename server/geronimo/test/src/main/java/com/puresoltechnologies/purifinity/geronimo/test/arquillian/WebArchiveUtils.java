package com.puresoltechnologies.purifinity.geronimo.test.arquillian;

import java.io.IOException;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * This class contains some utility methods to handle {@link WebArchive}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class WebArchiveUtils {

	/**
	 * Private default constructor to avoid instantiation.
	 */
	private WebArchiveUtils() {
	}

	/**
	 * Creates an empty WAR with a standard web.xml
	 * 
	 * @param archiveName
	 *            is the name of the war file.
	 * @return Returns the created WAR file.
	 * @throws IOException
	 *             War can not be created
	 */
	public static WebArchive createWebArchiveWithBeanXML(String archiveName)
			throws IOException {
		WebArchive war = ShrinkWrap.create(WebArchive.class, archiveName);
		war.setWebXML(WebArchiveUtils.class
				.getResource("/com/puresol/jboss/test/standard_web.xml"));
		return war;
	}

}
