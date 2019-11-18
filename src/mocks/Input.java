package mocks;

public class Input implements interfaces.Input {
	private char key;
	public Input() {
		this.key = ' ';
	}
	
	public char readKey() {
		return Character.toLowerCase(this.key);
	}
	/**
	 * Emulates a keyboard stroke
	 * @param key key to be emulated
	 */
	public void sendKey(char key) {
		this.key = key;
	}
}
