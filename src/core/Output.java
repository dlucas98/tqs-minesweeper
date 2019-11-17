package core;

public class Output {
	private String buffer;
	public Output() {
		buffer = "";
	}
	
	public String getBuffer() {
		return buffer;
	}
	
	public void print(String s) {
		buffer = s;
		System.out.print(s);
	}
}
