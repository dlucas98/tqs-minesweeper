package mocks;

public class Input implements interfaces.Input {
	private char key;
	public Input() {
		this.key = ' ';
	}
	
	public char readKey() {
		return Character.toLowerCase(this.key);
	}
	
	public void sendKey(char key) {
		this.key = key;
	}
}
