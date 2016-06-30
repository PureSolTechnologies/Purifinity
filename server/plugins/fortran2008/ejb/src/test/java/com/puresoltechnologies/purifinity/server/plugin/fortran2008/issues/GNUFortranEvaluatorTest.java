package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class GNUFortranEvaluatorTest {

    @Test
    public void testCompileLinePattern() {
	Pattern pattern = GNUFortranEvaluator.START_COMPILE_LINE_PATTERN;
	Matcher matcher = pattern
		.matcher("cd /home/ludwig/git/dyn3d/build/DYN3D2GNeutronKinetics/aggregation/modlib && "
			+ "/usr/bin/f95    -g -cpp -finit-local-zero -Wall -J../../../../DYN3D2GNeutronKinetics/aggregation/modules   -c /home/ludwig/git/dyn3d/DYN3D2GNeutronKinetics/aggregation/modlib/cb_mix.f -o CMakeFiles/MOD2G.dir/cb_mix.f.o");
	assertTrue(matcher.find());
	assertEquals("/home/ludwig/git/dyn3d/DYN3D2GNeutronKinetics/aggregation/modlib/cb_mix.f", matcher.group(1));
    }

}
