package core;

import java.util.Date;

public class Score {
	private String name;
	private int time;
	private Board.Dificulty difficulty;
	
	public Score(String name, int time, Board.Dificulty difficulty) {
		this.name = name.toUpperCase().substring(0, 3);
		while (this.name.length() < 3)
			this.name += '-';
		
		if (time < 0)
			this.time = 0;
		else
			this.time = time;
		
		if (difficulty != null)
			this.difficulty = difficulty;
		else
			this.difficulty = Board.Dificulty.EASY;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public Board.Dificulty getDifficulty() {
		return this.difficulty;
	}
}
