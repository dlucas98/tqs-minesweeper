package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.Cursor;
import core.Cursor.Direction;
import mocks.Map;

public class CursorTest {

	@Test
	public void testConstructor() {
		// Testing that the constructor sets the parameters correctly, starting in the position 0,0
		Cursor c = new Cursor(null);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
	}
	
	@Test
	public void testMove() {
		// Creating a Map in order to get a board. Also created a Cursor to be able to make moves.
		Map m = new Map();
		Board b = m.getBoard();
		Cursor c = new Cursor(b);
		
		// Trying to move to the top being in the position 0,0. Not possible.
		c.move(Direction.TOP);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
		
		// Move once to the top and check the coordinates have changed correctly.		
		c.move(Direction.BOTTOM);
		assertEquals(0, c.getX());
		assertEquals(1, c.getY());
		
		// Return to 0,0 and try to move left (out of the board). Not possible
		c.move(Direction.TOP);
		c.move(Direction.LEFT);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
		
		// Move once to the right and check the coordinates have changed correctly.	
		c.move(Direction.RIGHT);
		assertEquals(1, c.getX());
		assertEquals(0, c.getY());

		// Move to the right until the board ends. Check that it is not possible to get out of the board.
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		assertEquals(8, c.getX());
		assertEquals(0, c.getY());

		// Move to the bottom until the board ends. Check that it is not possible to get out of the board.
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		c.move(Direction.BOTTOM);
		assertEquals(8, c.getX());
		assertEquals(8, c.getY());
	}

}
