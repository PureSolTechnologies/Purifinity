package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.BreakKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CaseKeyword;
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
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.NullKeyword;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.SwitchKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
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
	addTokenDefinition(PackageKeyword.class);
	addTokenDefinition(ImportKeyword.class);

	addTokenDefinition(ClassKeyword.class);
	addTokenDefinition(InterfaceKeyword.class);
	addTokenDefinition(EnumKeyword.class);
	addTokenDefinition(ExtendsKeyword.class);
	addTokenDefinition(ImplementsKeyword.class);
	addTokenDefinition(NewKeyword.class);

	addTokenDefinition(PublicKeyword.class);
	addTokenDefinition(ProtectedKeyword.class);
	addTokenDefinition(PrivateKeyword.class);
	addTokenDefinition(StaticKeyword.class);
	addTokenDefinition(FinalKeyword.class);
	addTokenDefinition(TransientKeyword.class);
	addTokenDefinition(AbstractKeyword.class);
	addTokenDefinition(SynchronizedKeyword.class);

	addTokenDefinition(ReturnKeyword.class);
	addTokenDefinition(BreakKeyword.class);
	addTokenDefinition(ContinueKeyword.class);

	addTokenDefinition(IfKeyword.class);
	addTokenDefinition(ElseKeyword.class);
	addTokenDefinition(WhileKeyword.class);
	addTokenDefinition(DoKeyword.class);
	addTokenDefinition(ForKeyword.class);
	addTokenDefinition(SwitchKeyword.class);
	addTokenDefinition(CaseKeyword.class);

	addTokenDefinition(TryKeyword.class);
	addTokenDefinition(CatchKeyword.class);
	addTokenDefinition(FinallyKeyword.class);
	addTokenDefinition(ThrowsKeyword.class);
	addTokenDefinition(ThrowKeyword.class);

	addTokenDefinition(SuperKeyword.class);
	addTokenDefinition(ThisKeyword.class);
	addTokenDefinition(NullKeyword.class);

	addTokenDefinition(VoidKeyword.class);
	addTokenDefinition(BooleanKeyword.class);
	addTokenDefinition(CharKeyword.class);
	addTokenDefinition(ByteKeyword.class);
	addTokenDefinition(ShortKeyword.class);
	addTokenDefinition(IntKeyword.class);
	addTokenDefinition(LongKeyword.class);
	addTokenDefinition(FloatKeyword.class);
	addTokenDefinition(DoubleKeyword.class);
    }
}
