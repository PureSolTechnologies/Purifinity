package com.puresoltechnologies.toolshed.client.utils;

import com.puresoltechnologies.javafx.utils.FXDefaultFonts;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Fonts {

    public static final Font titleFont = Font.font(FXDefaultFonts.defaultFamily, FontWeight.BOLD,
	    FXDefaultFonts.defaultSize * 1.3);
    public static final Font boldFont = Font.font(FXDefaultFonts.defaultFamily, FontWeight.BOLD,
	    FXDefaultFonts.defaultFont.getSize());

}
