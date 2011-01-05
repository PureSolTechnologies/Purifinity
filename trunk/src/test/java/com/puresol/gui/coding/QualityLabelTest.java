package com.puresol.gui.coding;

import static org.junit.Assert.*;

import java.awt.BorderLayout;

import javax.swingx.Dialog;

import org.junit.Test;

import com.puresol.coding.quality.SourceCodeQuality;

public class QualityLabelTest {

	@Test
	public void testInstance() {
		assertNotNull(new QualityLabel());
	}

	@Test
	public void testDefaultValues() {
		QualityLabel label = new QualityLabel();
		assertEquals(SourceCodeQuality.UNSPECIFIED, label.getQuality());
	}

	public static void main(String args[]) {
		Dialog dlg = new Dialog("TEST", true);
		dlg.setLayout(new BorderLayout());
		dlg.getContentPane().add(new QualityLabel(SourceCodeQuality.HIGH));
		dlg.run();
	}

}
