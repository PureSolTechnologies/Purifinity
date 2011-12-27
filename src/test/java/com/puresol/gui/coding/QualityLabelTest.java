package com.puresol.gui.coding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}
