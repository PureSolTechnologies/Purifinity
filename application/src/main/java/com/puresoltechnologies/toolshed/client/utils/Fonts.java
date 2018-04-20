package com.puresoltechnologies.toolshed.client.utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Fonts {

    private static final Font defaultFont = Font.getDefault();
    public static final String defaultFamily = defaultFont.getFamily();
    public static final double defaultSize = defaultFont.getSize();
    public static final Font titleFont = Font.font(defaultFamily, FontWeight.BOLD, defaultSize * 1.3);
    public static final Font boldFont = Font.font(defaultFamily, FontWeight.BOLD, defaultFont.getSize());

}
