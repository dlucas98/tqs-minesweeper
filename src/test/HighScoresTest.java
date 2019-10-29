package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.HighScores;
import core.Score;

public class HighScoresTest {

	@Test
	public void testConstructor() {
		HighScores hs = new HighScores();
		assertEquals(0, hs.getScores().getSize());
		
		hs.addScore(new Score("Player", 0));
		assertEquals(1, hs.getScores().getSize());
		assertEquals()
	}

}
