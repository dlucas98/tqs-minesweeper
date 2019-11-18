package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Tile;

public class TileTest {

	@Test
	public void testConstructor() {		
		Tile tile = new Tile(false);	// Creating a tile without mine
		Tile mine = new Tile(true);		// Creating a tile which contains a mine
		
		// Testing that once the Tile has been created has the default parameters and hasMine = false
		assertEquals(0, tile.getnMines());
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		assertEquals(false, tile.hasMine());
		
		// Testing that once the Tile has been created has the default parameters and hasMine = true
		assertEquals(0, mine.getnMines());
		assertEquals(Tile.Status.HIDDEN, mine.getStatus());
		assertEquals(true, mine.hasMine());
	}

	@Test
	public void testnMines() {
		Tile tile = new Tile(false);	// Creating a tile without mine
		
		// Testing the setter and getter of nMines. Using limit values (0,8) and boundary values (-1,1,7,9).
		tile.setnMines(0);
		assertEquals(0, tile.getnMines());
		tile.setnMines(1);
		assertEquals(1, tile.getnMines());
		tile.setnMines(7);
		assertEquals(7, tile.getnMines());
		tile.setnMines(8);
		assertEquals(8, tile.getnMines());
		tile.setnMines(9);
		assertEquals(8, tile.getnMines());
		tile.setnMines(150);
		assertEquals(8, tile.getnMines());
		tile.setnMines(-1);
		assertEquals(0, tile.getnMines());
		tile.setnMines(-100);
		assertEquals(0, tile.getnMines());
	}
	
	@Test
	public void testMark() {
		Tile tile = new Tile(false);	// Create a Tile without mine
		Tile tmine = new Tile(true);	// Create a Tile with a mine inside
		
		// Testing that if a hidden tile is marked twice, is still hidden
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		tile.mark();
		assertEquals(Tile.Status.MARK, tile.getStatus());
		tile.mark();
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		
		// Testing that if a tile is open, it cannot be marked
		tile.open();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
		tile.mark();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
		
		// Testing that an open tile (with a mine) cannot be marked
		tmine.open();
		assertEquals(Tile.Status.MINE, tmine.getStatus());
		tmine.mark();
		assertEquals(Tile.Status.MINE, tmine.getStatus());
	}
	
	@Test
	public void testStatusMine() {
		Tile mine = new Tile(true);	// Create a Tile with mine
		
		// Testing that if a tile gets open and has a mine, it cannot be opened again
		assertEquals(Tile.Status.HIDDEN, mine.getStatus());
		mine.open();
		assertEquals(Tile.Status.MINE, mine.getStatus());
		mine.open();
		assertEquals(Tile.Status.MINE, mine.getStatus());
	}
	
	@Test
	public void testStatus() {
		Tile tile = new Tile(false);	// Create a Tile without mine
		
		// Testing that if a tile gets open and does not have a mine, it cannot be opened again
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		tile.open();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
		assertEquals(false, tile.hasMine());
		tile.open();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
		assertEquals(false, tile.hasMine());
		
		//Testing that if a tile is marked, its status cannot change
		Tile marked = new Tile(false);
		marked.mark();
		assertEquals(Tile.Status.MARK, marked.getStatus());
		marked.mark();
		assertEquals(Tile.Status.HIDDEN, marked.getStatus());	
	}
	
	@Test
	public void testCursor() {
		Tile tile = new Tile(false);	// Create a Tile without mine
		
		// Testing that the setter and getter of the cursor is working
		assertEquals(false, tile.hasCursor());
		tile.setHasCursor(true);
		assertEquals(true, tile.hasCursor());
		tile.setHasCursor(false);
		assertEquals(false, tile.hasCursor());
	}
}
