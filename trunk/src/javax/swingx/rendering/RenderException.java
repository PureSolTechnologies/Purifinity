package javax.swingx.rendering;

/**
 * This exception is thrown if a renderer is not able to proceed due to
 * incorrect values or other intrinsic circumstances.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RenderException extends Exception {

	private static final long serialVersionUID = -2863927207581717341L;

	public RenderException() {
		super();
	}

	public RenderException(String message, Throwable cause) {
		super(message, cause);
	}

	public RenderException(String message) {
		super(message);
	}

	public RenderException(Throwable cause) {
		super(cause);
	}

}
