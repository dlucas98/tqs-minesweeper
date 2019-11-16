package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mocks.Input;

public class InputTest {

	@Test
	public void test() {
		//Test init
		Input i = new Input();
		assertEquals(' ', i.readKey());
		
		i.sendKey('w');
		assertEquals('w', i.readKey());
		//Test to lower
		i.sendKey('W');
		assertEquals('w', i.readKey());

		i.sendKey('a');
		assertEquals('a', i.readKey());
		i.sendKey('s');
		assertEquals('s', i.readKey());
		i.sendKey('d');
		assertEquals('d', i.readKey());
		i.sendKey('m');
		assertEquals('m', i.readKey());
		i.sendKey('o');
		assertEquals('o', i.readKey());
		
		//Test special chars
		i.sendKey(' ');
		assertEquals(' ', i.readKey());
		i.sendKey('\\');
		assertEquals('\\', i.readKey());
	}

}
