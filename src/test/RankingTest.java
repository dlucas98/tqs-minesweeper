package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import core.Board;
import core.Ranking;
import core.Score;

public class RankingTest {

	@Test
	public void constructorTest() {
		Ranking r = new Ranking("D:/mine-save");
		assertEquals("D:/mine-save", r.getFileName());
	}
	
	@Test
	public void addTest() {
		Ranking r = new Ranking("D:/mine-save");
		
		//Easy
		Score s = new Score("David", 900, Board.Dificulty.EASY);
		r.addScore(s);
		assertEquals(true, r.getEasyRanking().contains(s));
		assertEquals(1, r.getEasyRanking().size());
		assertEquals(0, r.getMediumRanking().size());
		assertEquals(0, r.getHardRanking().size());
		
		//Medium
		Score s2 = new Score("Albert", 20, Board.Dificulty.MEDIUM);
		r.addScore(s2);
		assertEquals(1, r.getEasyRanking().size());
		assertEquals(true, r.getMediumRanking().contains(s2));
		assertEquals(1, r.getMediumRanking().size());
		assertEquals(0, r.getHardRanking().size());
		
		//Hard
		Score s3 = new Score("Otazu", 0, Board.Dificulty.HARD);
		r.addScore(s3);
		assertEquals(1, r.getEasyRanking().size());
		assertEquals(1, r.getMediumRanking().size());
		assertEquals(true, r.getHardRanking().contains(s3));
		assertEquals(1, r.getHardRanking().size());
		
		//Custom
		Score s4 = new Score("Master", 0, Board.Dificulty.CUSTOM);
		r.addScore(s4);
		assertEquals(1, r.getEasyRanking().size());
		assertEquals(1, r.getMediumRanking().size());
		assertEquals(1, r.getHardRanking().size());
	}

}
