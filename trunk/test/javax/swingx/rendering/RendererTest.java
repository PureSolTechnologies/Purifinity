package javax.swingx.rendering;

import static org.junit.Assert.*;

import java.awt.GraphicsEnvironment;

import org.junit.Before;
import org.junit.Test;

/**
 * This class does not check the Renderer interface, but some behavior around
 * its functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RendererTest {

	private GraphicsEnvironment graphicsEnvironment;

	@Before
	public void setup() {
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	}

	@Test
	public void testGraphicsEnvironment() {
		String[] names = graphicsEnvironment.getAvailableFontFamilyNames();
		assertNotNull(names);
		for (String name : names) {
			System.out.println(name);
		}
	}

}
