package core;

/**
 * Class used to print string to standard ouptut.
 */
public class Output {
	private String buffer;
	/**
	 * Default constructor.
	 */
	public Output() {
		buffer = "";
	}
	
	/**
	 * Getter for the last string printed to standard output.
	 * @return last String printed
	 */
	public String getBuffer() {
		return buffer;
	}
	
	/**
	 * Prints a String to standard output.
	 * @param s value to print
	 */
	public void print(String s) {
		buffer = s;
		System.out.print(s);
	}
}
