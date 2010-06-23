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
import com.puresol.utils.DirectoryUtilities;

/**
 * This is PureSolTechnologies' code analysis tool for automated source code
 * validation and evaluation.
 * 
 * @author Rick-Rainer Ludwig
 */
public class CodeAnalysisStarter {

	public static void main(String[] args) {
		ConsoleUtils.printSystemProperties();
		ConsoleUtils.printEnvironment();
		JarLoader.loadJarsFromDirectory(new File("lib"), true);
		File installDir = DirectoryUtilities.getInstallationDirectory(
				CodeAnalysisStarter.class, true);
		System.out.println(installDir);
		CodeAnalysis analysis = new CodeAnalysis();
		analysis.run();
	}
}
