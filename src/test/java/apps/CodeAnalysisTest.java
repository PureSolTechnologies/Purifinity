package apps;

import static org.junit.Assert.*;

import org.junit.Test;

public class CodeAnalysisTest {

	@Test
	public void testInstance() {
		CodeAnalysis codeAnalysis = new CodeAnalysis();
		assertNotNull(codeAnalysis);
	}

}
