package com.puresol.gui.uhura.rendering;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class loads and keeps all properites needed for rendering Uhura grammar
 * schematics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UhuraRenderProperties {

    private static final Logger logger = LoggerFactory
	    .getLogger(UhuraRenderProperties.class);

    private static final Properties properties = new Properties();
    static {
	try {
	    properties.load(UhuraRenderProperties.class
		    .getResourceAsStream("/config/UhuraRenderer.properties"));
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
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
