package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Tile;

public class TileTest {

	@Test
	public void testConstructor() {
		Tile tile = new Tile(false);
		Tile mine = new Tile(true);
		assertEquals(0, tile.getnMines());
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		assertEquals(false, tile.hasMine());

		assertEquals(0, mine.getnMines());
		assertEquals(Tile.Status.HIDDEN, mine.getStatus());
		assertEquals(true, mine.hasMine());
	}

	@Test
	public void testnMines() {
		Tile tile = new Tile(false);
		tile.setnMines(0);
		assertEquals(0, tile.getnMines());
		tile.setnMines(1);
		assertEquals(1, tile.getnMines());
		tile.setnMines(8);
		assertEquals(8, tile.getnMines());
		tile.setnMines(9);
		assertEquals(8, tile.getnMines());
		tile.setnMines(150);
		assertEquals(8, tile.getnMines());
	}
	
	@Test
	public void testMark() {
		Tile tile = new Tile(false);
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		tile.mark();
		assertEquals(Tile.Status.MARK, tile.getStatus());
	}
	
	@Test
	public void testStatusMine() {
		Tile mine = new Tile(true);
		
		assertEquals(Tile.Status.HIDDEN, mine.getStatus());
		mine.open();
		assertEquals(Tile.Status.MINE, mine.getStatus());
		mine.open();
		assertEquals(Tile.Status.MINE, mine.getStatus());
	}
	
	@Test
	public void testStatus() {
		Tile tile = new Tile(false);
		
		assertEquals(Tile.Status.HIDDEN, tile.getStatus());
		tile.open();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
		tile.open();
		assertEquals(Tile.Status.OPEN, tile.getStatus());
	}
}
