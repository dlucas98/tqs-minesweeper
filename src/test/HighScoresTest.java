package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Board;
import core.HighScores;
import core.Score;

public class HighScoresTest {

	@Test
	public void testConstructor() {
		HighScores hs = new HighScores();
		assertEquals(0, hs.getEasyScores().size());
		
		hs.addScore(new Score("Player", 10, Board.Dificulty.EASY));
		assertEquals(1, hs.getEasyScores().size());

		String s = "EASY\n"
				+ "1-PLA\t10\n"
				+ "2-\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n"
				+ "INTERMEDIATE\n"
				+ "1-\n"
				+ "2-\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n"
				+ "HARD\n"
				+ "1-\n"
				+ "2-\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n";
		assertEquals(s, hs.printScores());
		

		hs.addScore(new Score("Pl2", 9, Board.Dificulty.EASY));
		assertEquals(2, hs.getEasyScores().size());
		assertEquals(0, hs.getIntermediateScores().size());
		assertEquals(0, hs.getHardScores().size());
		
		hs.addScore(new Score("MED", 100, Board.Dificulty.MEDIUM));
		assertEquals(2, hs.getEasyScores().size());
		assertEquals(1, hs.getIntermediateScores().size());
		assertEquals(0, hs.getHardScores().size());
		
		hs.addScore(new Score("har", 10, Board.Dificulty.HARD));
		assertEquals(2, hs.getEasyScores().size());
		assertEquals(1, hs.getIntermediateScores().size());
		assertEquals(1, hs.getHardScores().size());
		
		String s2 = "EASY\n"
				+ "1-PL2\t9\n"
				+ "2-PLA\t10\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n"
				+ "INTERMEDIATE\n"
				+ "1-MED\t100\n"
				+ "2-\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n"
				+ "HARD\n"
				+ "1-HAR\t10\n"
				+ "2-\n"
				+ "3-\n"
				+ "4-\n"
				+ "5-\n";
		assertEquals(s2, hs.printScores());
	}

}
