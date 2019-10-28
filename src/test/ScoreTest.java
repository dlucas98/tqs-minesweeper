package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Score;

public class ScoreTest {

	@Test
	public void testConstructor() {
		Score s = new Score();
		assertEquals(true, s.getScores().isEmpty());
		
		s.addScore("Nom", 0);
		assertEquals(false, s.getScores().isEmpty());
		assertEquals(0, s.getScores().get("Nom"));
	}

}
