package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.Score;

public class ScoreTest {

	@Test
	public void testConstructor() {
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
		assertEquals(Integer.MAX_VALUE, s3.getTime());
		assertEquals(Board.Dificulty.MEDIUM, s3.getDifficulty());
		
		Score s4 = new Score("ñ1", 0, Board.Dificulty.HARD);
		assertEquals("Ñ1-", s4.getName());
		assertEquals(0, s4.getTime());
		assertEquals(Board.Dificulty.HARD, s4.getDifficulty());
	}

}
