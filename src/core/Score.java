package core;

import java.io.Serializable;
/**
 * Stores information about the score of a game.
 * 
 */
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private long time;
	private Board.Dificulty difficulty;
	
	/**
	 * Default constructor.
	 * @param name Name of the player. Restricted to 3 upper case characters.
	 * @param time Score. Lower is better.
	 * @param difficulty Difficulty of the game.
	 */
	public Score(String name, long time, Board.Dificulty difficulty) {
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
	
	/**
	 * Getter for name
	 * @return Player name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for score
	 * @return Score puntuation. Lower is better.
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Getter for difficulty
	 * @return Difficulty
	 */
	public Board.Dificulty getDifficulty() {
		return this.difficulty;
	}
}
