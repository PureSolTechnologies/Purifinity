/***************************************************************************
 *
 *   ProjectAnalyserTest.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class ProjectAnalyserTest extends TestCase {

	private static final File WORKSPACE_DIRECTORY = new File(
			ProjectAnalyserTest.class.getSimpleName() + "workspace");

	@Test
	public void testSerialization() {
		try {
			ProjectAnalyzer projectAnalyser = TestProjectAnalysers.MINIMAL_PROJECT_ANALYSER;

			Persistence.persist(projectAnalyser, new File("test/persist.test"));
			ProjectAnalyzer restored = (ProjectAnalyzer) Persistence
					.restore(new File("test/persist.test"));

			Assert.assertNotSame(projectAnalyser, restored);
			Assert.assertEquals(projectAnalyser, restored);
		} catch (PersistenceException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testFortran() {
		ProjectAnalyzer analyser = ProjectAnalyzer.create(new File(
				"/usr/src/compile/ATLAS/src/blas/f77reference/zgerc.f"),
				WORKSPACE_DIRECTORY);
		analyser.run();
		for (File file : analyser.getFiles()) {
			List<CodeRange> ranges = analyser.getAnalyzer(file)
					.getNonFragmentCodeRangesRecursively();
			for (CodeRange range : ranges) {
				TokenStream stream = range.getTokenStream();
				for (int index = 0; index < stream.getSize(); index++) {
					Token token = stream.get(index);
					if (token.getPublicity() == TokenPublicity.HIDDEN) {
						continue;
					}
					System.out.print("'");
					System.out.print(token.getText());
					System.out.print("'");
					System.out.println("\t(" + token.getStartLine() + " / "
							+ token.getDefinition().toString() + ")");
				}
			}
		}
		WORKSPACE_DIRECTORY.delete();
	}
}
