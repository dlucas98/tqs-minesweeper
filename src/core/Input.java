package core;

import java.io.IOException;
/**
 * Class to capture all key strokes.
 * Used in Minesweeper.
 */
public class Input implements interfaces.Input {
	/**
	 * Default constructor.
	 */
	public Input() {
		
	}

	/**
	 * Returns a char read.
	 */
	public char readKey() {
		try {
			return Character.toLowerCase((char)System.in.read());
		} catch (IOException e) {
			return ' ';
		}
	}
}
