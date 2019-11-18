package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Minesweeper;

public class OutputTest {

	@Test
	public void test() {
		//Test if getBuffer() method works correctly
		Minesweeper m = new Minesweeper();
		
		m.getOutput().print("TEXT");
		assertEquals("TEXT", m.getOutput().getBuffer());
		
		m.getOutput().print("");
		assertEquals("", m.getOutput().getBuffer());
	}

}
