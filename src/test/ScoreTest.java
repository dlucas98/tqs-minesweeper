package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.Score;

public class ScoreTest {

	@Test
	public void testConstructor() {
		// Testing the constructor is working correctly in each difficulty level. 
		// Also testing the player name input. The only format accepted is AAA, if it is different,
		// the game itself cuts off the name.
		Score s = new Score("Player1", 100, Board.Dificulty.EASY);
		assertEquals("PLA", s.getName());
		assertEquals(100, s.getTime());
		assertEquals(Board.Dificulty.EASY, s.getDifficulty());

		Score s2 = new Score("David", -1, null);
		assertEquals("DAV", s2.getName());
		assertEquals(0, s2.getTime());
		assertEquals(Board.Dificulty.EASY, s2.getDifficulty());

		Score s3 = new Score("", Integer.MAX_VALUE + 1, Board.Dificulty.MEDIUM);
		assertEquals("---", s3.getName());
		assertEquals(0, s3.getTime());
		assertEquals(Board.Dificulty.MEDIUM, s3.getDifficulty());
		
		Score s4 = new Score("a", 0, Board.Dificulty.HARD);
		assertEquals("A--", s4.getName());
		assertEquals(0, s4.getTime());
		assertEquals(Board.Dificulty.HARD, s4.getDifficulty());
		
		Score s5 = new Score("a", 0, Board.Dificulty.CUSTOM);
		assertEquals("Ah-", s4.getName());
		assertEquals(0, s5.getTime());
		assertEquals(Board.Dificulty.CUSTOM, s5.getDifficulty());
	}

}
