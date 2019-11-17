package core;

public class Score {
	private String name;
	private long time;
	private Board.Dificulty difficulty;
	
	public Score(String name, int time, Board.Dificulty difficulty) {
		while (name.length() < 3)
			name += '-';
		this.name = name.toUpperCase().substring(0, 3);
		
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
	
	public long getTime() {
		return this.time;
	}
	
	public Board.Dificulty getDifficulty() {
		return this.difficulty;
	}
}
