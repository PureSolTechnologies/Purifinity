package com.puresoltechnologies.purifinity.client.common.analysis.grammar;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;

import com.puresoltechnologies.purifinity.client.common.analysis.Activator;

/**
 * This class loads and keeps all properites needed for rendering Uhura grammar
 * schematics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UhuraRenderProperties {

	private static final String UHURA_RENDERER_PROPERTIES = "/config/UhuraRenderer.properties";

	private static final ILog logger = Activator.getDefault().getLog();

	private static final Properties properties = new Properties();
	static {
		try {
			InputStream resourceAsStream = UhuraRenderProperties.class
					.getResourceAsStream(UHURA_RENDERER_PROPERTIES);
			if (resourceAsStream == null) {
				throw new RuntimeException("Could not open properties file '"
						+ UHURA_RENDERER_PROPERTIES + "'!");
			}
			properties.load(resourceAsStream);
		} catch (IOException e) {
			logger.log(new Status(Status.ERROR, UhuraRenderProperties.class
					.getName(),
					"Could not load rendering properties for grammar."));
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static int getBoxPadding() {
		return Integer.valueOf(getProperty("box.padding"));
	}

	public static int getBoxArrowLength() {
		return Integer.valueOf(getProperty("box.arrow.length"));
	}

	public static int getBoxArrowMargin() {
		return Integer.valueOf(getProperty("box.arrow.margin"));
	}

	public static int getArrowTipLength() {
		return Integer.valueOf(getProperty("arrow.tip.length"));
	}

	public static int getArrowTipAngle() {
		return Integer.valueOf(getProperty("arrow.tip.angle"));
	}

	public static String getIdentifierFontFamily() {
		return getProperty("identifier.font.family");
	}

	public static int getIdentifierFontSize() {
		return Integer.valueOf(getProperty("identifier.font.size"));
	}

	public static String getLiteralFontFamily() {
		return getProperty("literal.font.family");
	}

	public static int getLiteralFontSize() {
		return Integer.valueOf(getProperty("literal.font.size"));
	}

	public static Dimension getAssumedFontSize() {
		return new Dimension(8, 12);
	}

}
