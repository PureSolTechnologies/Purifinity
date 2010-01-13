package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.BreakKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CatchKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.java.source.keywords.DoKeyword;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.ElseKeyword;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.FinallyKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.keywords.IfKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.NullKeyword;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.coding.lang.java.source.keywords.TryKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.keywords.WhileKeyword;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class JavaKeywords extends AbstractTokenDefinitionGroup {

	@Override
	protected void initialize() {
		addKeyword(PackageKeyword.class);
		addKeyword(ImportKeyword.class);

		addKeyword(ClassKeyword.class);
		addKeyword(InterfaceKeyword.class);
		addKeyword(EnumKeyword.class);
		addKeyword(ExtendsKeyword.class);
		addKeyword(ImplementsKeyword.class);

		addKeyword(PublicKeyword.class);
		addKeyword(ProtectedKeyword.class);
		addKeyword(PrivateKeyword.class);
		addKeyword(StaticKeyword.class);
		addKeyword(FinalKeyword.class);
		addKeyword(TransientKeyword.class);

		addKeyword(ReturnKeyword.class);
		addKeyword(BreakKeyword.class);
		addKeyword(ContinueKeyword.class);

		addKeyword(IfKeyword.class);
		addKeyword(ElseKeyword.class);
		addKeyword(WhileKeyword.class);
		addKeyword(DoKeyword.class);
		addKeyword(ForKeyword.class);

		addKeyword(TryKeyword.class);
		addKeyword(CatchKeyword.class);
		addKeyword(FinallyKeyword.class);
		addKeyword(ThrowsKeyword.class);
		addKeyword(ThrowKeyword.class);

		addKeyword(SuperKeyword.class);
		addKeyword(ThisKeyword.class);
		addKeyword(NullKeyword.class);

		addKeyword(VoidKeyword.class);
		addKeyword(BooleanKeyword.class);
		addKeyword(CharKeyword.class);
		addKeyword(ByteKeyword.class);
		addKeyword(ShortKeyword.class);
		addKeyword(IntKeyword.class);
		addKeyword(LongKeyword.class);
		addKeyword(FloatKeyword.class);
		addKeyword(DoubleKeyword.class);
	}
}
