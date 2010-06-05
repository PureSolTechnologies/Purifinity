/***************************************************************************
 *
 *   CodeAnalysis.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package apps;

import java.io.File;

import com.puresol.jars.JarLoader;
import com.puresol.utils.ConsoleUtils;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysisStarter {

    public static void main(String[] args) {
	ConsoleUtils.printSystemProperties();
	ConsoleUtils.printSystemProperties();
	JarLoader.loadJarsFromDirectory(new File("lib"), true);
	CodeAnalysis analysis = new CodeAnalysis();
	analysis.run();
    }
}
