package com.puresol.gui.uhura.rendering;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class loads and keeps all properites needed for rendering Uhura grammar
 * schematics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RenderProperties {

	private static final Logger logger = Logger
			.getLogger(RenderProperties.class);

	private static final Properties properties = new Properties();
	static {
		try {
			properties.load(RenderProperties.class
					.getResourceAsStream("/config/UhuraRender.properties"));
		} catch (IOException e) {
			logger.fatal(e.getMessage(), e);
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

}
