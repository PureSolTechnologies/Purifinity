// This is a file header. An end-of-line comment starting at first position.
package pkg1;

/**
 * This is a sample class for SLOC metric testing. Here all kind and
 * combinations of commens, blank lines and productive lines should be found.
 * 
 * This is a class comment;
 * 
 * @author Rick-Rainer Ludwig
 */
public class SLOCTestSample {

	/*
	 * This is a block comment not starting at the first line position.
	 */

	/**
	 * The constructor. An API block comment not starting at the first line
	 * position.
	 */
	public SLOCTestSample() {
		super(); // End of line comment with productive line part.
	}

	/**
	 * This method is used to show embedded block comments.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public double multiply(double a, double b) {
		return a /* left operant */* /* operation */b; // right operant.
	}
}
/*
 * This is a block comment at the first position of the line and at the end of
 * the file. (NO ENTER AFTER THIS COMMENT!!!
 */