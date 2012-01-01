package com.puresol.document.convert.html;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.puresol.document.Chart;

public class HTMLChart {

	public static StringBuffer convert(HTMLConverter converter, Chart chart)
			throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div><img src=\"" + chart.getName() + ".png"
				+ "\"/><br/>" + chart.getCaption() + "</div>\n");
		BufferedImage image = chart.getChartRenderer().getChart()
				.createBufferedImage(640, 480);
		FileOutputStream outStream;
		outStream = new FileOutputStream(new File(converter.getImageFolder(),
				chart.getName() + ".png"));
		try {
			ImageIO.write(image, "png", outStream);
		} finally {
			outStream.close();
		}
		converter.convertChildren(buffer, chart.getChildren());
		return buffer;
	}
}
