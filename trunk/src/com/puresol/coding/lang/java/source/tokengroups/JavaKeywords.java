package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.java.source.keywords.AssertKeyword;
import com.puresol.coding.lang.java.source.keywords.BooleanKeyword;
import com.puresol.coding.lang.java.source.keywords.BreakKeyword;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CaseKeyword;
import com.puresol.coding.lang.java.source.keywords.CatchKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ConstKeyword;
import com.puresol.coding.lang.java.source.keywords.ContinueKeyword;
import com.puresol.coding.lang.java.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.java.source.keywords.DoKeyword;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.ElseKeyword;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.keywords.FinalKeyword;
import com.puresol.coding.lang.java.source.keywords.FinallyKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.keywords.GotoKeyword;
import com.puresol.coding.lang.java.source.keywords.IfKeyword;
import com.puresol.coding.lang.java.source.keywords.ImplementsKeyword;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.keywords.InstanceofKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.NativeKeyword;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.PackageKeyword;
import com.puresol.coding.lang.java.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.java.source.keywords.ProtectedKeyword;
import com.puresol.coding.lang.java.source.keywords.PublicKeyword;
import com.puresol.coding.lang.java.source.keywords.ReturnKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.coding.lang.java.source.keywords.StrictfpKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.SwitchKeyword;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowKeyword;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.coding.lang.java.source.keywords.TransientKeyword;
import com.puresol.coding.lang.java.source.keywords.TryKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.keywords.VolatileKeyword;
import com.puresol.coding.lang.java.source.keywords.WhileKeyword;
import com.puresol.parser.TokenDefinition;

/**
 * @author Rick-Rainer Ludwigs
 * 
 */
public class JavaKeywords {

	public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

	static {
		DEFINITIONS.add(PackageKeyword.class);
		DEFINITIONS.add(ImportKeyword.class);

		DEFINITIONS.add(ClassKeyword.class);
		DEFINITIONS.add(InterfaceKeyword.class);
		DEFINITIONS.add(EnumKeyword.class);
		DEFINITIONS.add(ExtendsKeyword.class);
		DEFINITIONS.add(ImplementsKeyword.class);
		DEFINITIONS.add(NewKeyword.class);

		DEFINITIONS.add(PublicKeyword.class);
		DEFINITIONS.add(ProtectedKeyword.class);
		DEFINITIONS.add(PrivateKeyword.class);
		DEFINITIONS.add(StaticKeyword.class);
		DEFINITIONS.add(FinalKeyword.class);
		DEFINITIONS.add(ConstKeyword.class);
		DEFINITIONS.add(NativeKeyword.class);
		DEFINITIONS.add(TransientKeyword.class);
		DEFINITIONS.add(VolatileKeyword.class);
		DEFINITIONS.add(AbstractKeyword.class);
		DEFINITIONS.add(SynchronizedKeyword.class);
		DEFINITIONS.add(StrictfpKeyword.class);

		DEFINITIONS.add(ReturnKeyword.class);
		DEFINITIONS.add(BreakKeyword.class);
		DEFINITIONS.add(ContinueKeyword.class);

		DEFINITIONS.add(IfKeyword.class);
		DEFINITIONS.add(ElseKeyword.class);
		DEFINITIONS.add(WhileKeyword.class);
		DEFINITIONS.add(DoKeyword.class);
		DEFINITIONS.add(ForKeyword.class);
		DEFINITIONS.add(SwitchKeyword.class);
		DEFINITIONS.add(CaseKeyword.class);
		DEFINITIONS.add(DefaultKeyword.class);

		DEFINITIONS.add(TryKeyword.class);
		DEFINITIONS.add(CatchKeyword.class);
		DEFINITIONS.add(FinallyKeyword.class);
		DEFINITIONS.add(ThrowsKeyword.class);
		DEFINITIONS.add(ThrowKeyword.class);

		DEFINITIONS.add(InstanceofKeyword.class);
		DEFINITIONS.add(SuperKeyword.class);
		DEFINITIONS.add(ThisKeyword.class);

		DEFINITIONS.add(AssertKeyword.class);
		DEFINITIONS.add(GotoKeyword.class);

		DEFINITIONS.add(VoidKeyword.class);
		DEFINITIONS.add(BooleanKeyword.class);
		DEFINITIONS.add(CharKeyword.class);
		DEFINITIONS.add(ByteKeyword.class);
		DEFINITIONS.add(ShortKeyword.class);
		DEFINITIONS.add(IntKeyword.class);
		DEFINITIONS.add(LongKeyword.class);
		DEFINITIONS.add(FloatKeyword.class);
		DEFINITIONS.add(DoubleKeyword.class);
	}
}
