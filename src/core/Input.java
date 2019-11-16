package core;

import java.io.IOException;

public class Input implements interfaces.Input {
	public Input() {
		
	}

	public char readKey() {
		try {
			return Character.toLowerCase((char)System.in.read());
		} catch (IOException e) {
			return ' ';
		}
	}
}
