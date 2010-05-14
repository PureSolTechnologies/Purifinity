package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.packages.ImportDeclarations;
import com.puresol.coding.lang.java.source.grammar.packages.PackageDeclaration;
import com.puresol.coding.lang.java.source.grammar.packages.TypeDeclarations;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * compilationUnit : ( (annotations )? packageDeclaration )? (importDeclaration
 * )* (typeDeclaration )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CompilationUnit extends AbstractJavaParser {

    private static final long serialVersionUID = -5271390812159304045L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(PackageDeclaration.class);
	acceptPart(ImportDeclarations.class);
	acceptPart(TypeDeclarations.class);
	finish(getFile().getName());
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FILE;
    }
}
