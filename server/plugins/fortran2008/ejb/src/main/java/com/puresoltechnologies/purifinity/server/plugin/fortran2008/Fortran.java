package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginActivatedParameter;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.CodeDepthMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.HalsteadMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.McCabeMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.SLOCMetricImpl;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(ProgrammingLanguageAnalyzer.class)
public class Fortran extends AbstractProgrammingLanguageAnalyzer {

    public static final String ID = Fortran.class.getName();
    public static final String NAME = "Fortran";
    public static final String VERSION = "2008";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);

    public static final String[] FILE_SUFFIXES = { ".f", ".f77", ".f90",
	    ".f95", ".for" };

    private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();
    static {
	configurationParameters.add(new PluginActivatedParameter());
    }

    private SourceForm sourceForm = SourceForm.FREE_FORM;

    public Fortran() {
	super(NAME, VERSION);
    }

    @Override
    protected String[] getValidFileSuffixes() {
	return FILE_SUFFIXES;
    }

    @Override
    public Set<ConfigurationParameter<?>> getConfigurationParameters() {
	return configurationParameters;
    }

    @Override
    public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
	try {
	    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
		    file));
	    try {
		return (CodeAnalyzer) ois.readObject();
	    } finally {
		ois.close();
	    }
	} catch (ClassNotFoundException e) {
	    /*
	     * XXX This needs to be null to go on with the language try out...
	     * :-(
	     */
	    return null;
	}
    }

    @Override
    public CodeAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
	return new FortranAnalyzer(sourceCodeLocation);
    }

    public void setSourceForm(SourceForm sourceForm) {
	this.sourceForm = sourceForm;
    }

    public SourceForm getSourceForm() {
	return sourceForm;
    }

    @Override
    public LanguageGrammar getGrammar() {
	return FortranGrammar.getInstance();
    }

    @Override
    public SLOCType getType(AbstractTerminal token) {
	return new SLOCMetricImpl().getType(token);
    }

    @Override
    public boolean cascadingNode(UniversalSyntaxTree node) {
	return new CodeDepthMetricImpl().cascadingNode(node);
    }

    @Override
    public int increasesCyclomaticComplexityBy(AbstractProduction production) {
	return new McCabeMetricImpl()
		.increasesCyclomaticComplexityBy(production);
    }

    @Override
    public HalsteadSymbol getHalsteadResult(AbstractTerminal node) {
	return new HalsteadMetricImpl().getHalsteadResult(node);
    }
}
