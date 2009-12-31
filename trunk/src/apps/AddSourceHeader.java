/***************************************************************************
 *
 *   TemplateHeaderUtility.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package apps;

import java.io.File;

import javax.source.SourceHeader;

public class AddSourceHeader {

    public static void main(String[] args) {
	SourceHeader.addHeaderToFiles(new File(
	"res/config/SourceFileHeader.template"), new File(
	"res/config/about"), new File("src"), "**/*.java");
	SourceHeader.addHeaderToFiles(new File(
		"res/config/SourceFileHeader.template"), new File(
		"res/config/about"), new File("test"), "**/*.java");
    }
}
