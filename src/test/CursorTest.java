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
		Cursor c = new Cursor(null);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
	}
	
	public void testMove() {
		Map m = new Map();
		Board b = m.getBoard();
		Cursor c = new Cursor(b);
		
		c.move(Direction.TOP);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
		
		c.move(Direction.BOTTOM);
		assertEquals(0, c.getX());
		assertEquals(1, c.getY());
		
		c.move(Direction.TOP);
		c.move(Direction.LEFT);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
		
		c.move(Direction.RIGHT);
		assertEquals(1, c.getX());
		assertEquals(0, c.getY());

		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		c.move(Direction.RIGHT);
		assertEquals(9, c.getX());
		assertEquals(0, c.getY());

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
		assertEquals(9, c.getX());
		assertEquals(9, c.getY());
	}

}
