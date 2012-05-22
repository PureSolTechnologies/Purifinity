package com.puresol.coding.client.charting;

/**
 * This is an interface for a single chart title.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Title {

    private final TitleType type;
    private String text = "";
    private int size = 12;

    public Title(TitleType position) {
	super();
	this.type = position;
    }

    public Title(TitleType position, String text) {
	super();
	this.type = position;
	this.text = text;
    }

    public Title(TitleType position, String text, int size) {
	super();
	this.type = position;
	this.text = text;
	this.size = size;
    }

    /**
     * This method set the text for the title.
     * 
     * @param text
     */
    public void setText(String text) {
	this.text = text;
    }

    /**
     * This method returns the title's text.
     * 
     * @return
     */
    public String getText() {
	return text;
    }

    /**
     * This method sets the size of the title to be drawn.
     * 
     * @param size
     */
    public void setSize(int size) {
	this.size = size;
    }

    /**
     * This method returns the size of the title font.
     * 
     * @return
     */
    public int getSize() {
	return size;
    }

    public TitleType getType() {
	return type;
    }
}
