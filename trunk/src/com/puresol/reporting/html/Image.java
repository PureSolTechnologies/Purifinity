package com.puresol.reporting.html;

import java.io.File;

public class Image {

	private final File location;
	private int width = 0;
	private int height = 0;
	private int border = 0;
	private boolean relativeSize = false;

	public Image(File location) {
		this.location = location;
	}

	public Image(File location, int width, int height, boolean relativeSize) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.relativeSize = relativeSize;
	}

	public Image(File location, int width, int height, boolean relativeSize,
			int border) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.border = border;
		this.relativeSize = relativeSize;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the border
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            the border to set
	 */
	public void setBorder(int border) {
		this.border = border;
	}

	public String toHTML() {
		String output = "<img src=\"" + location + "\"";
		if (width > 0) {
			if (relativeSize) {
				output += " width=\"" + width + "%\"";
			} else {
				output += " width=\"" + width + "\"";
			}
		}
		if (height > 0) {
			if (relativeSize) {
				output += " height=\"" + height + "%\"";
			} else {
				output += " height=\"" + height + "\"";
			}
		}
		if (border > 0) {
			output += " border=\"" + border + "\"";
		}
		output += "/>";
		return output;
	}
}
