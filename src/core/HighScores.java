package core;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Stores lists of Scores.
 * It's used in the Minesweeper class.
 */
public class HighScores implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Score> easyScores;
	private LinkedList<Score> intermediateScores;
	private LinkedList<Score> hardScores;
	
	/**
	 * Default constructor
	 */
	public HighScores() {
		easyScores = new LinkedList<Score>();
		intermediateScores = new LinkedList<Score>();
		hardScores = new LinkedList<Score>();
	}
	
	/**
	 * Adds a Score to a list
	 * @param s: Score to add
	 */
	public void addScore(Score s) {
		boolean added = false;
		switch (s.getDifficulty()) {
		case EASY:
			for (int i = 0; i < easyScores.size(); i++) {
				if(easyScores.get(i).getTime() > s.getTime()) {
					easyScores.add(i, s);
					added = true;
					break;
				}
			}
			if(!added)
				easyScores.add(s);
			break;
		case MEDIUM:
			for (int i = 0; i < intermediateScores.size(); i++) {
				if(intermediateScores.get(i).getTime() > s.getTime()) {
					intermediateScores.add(i, s);
					added = true;
					break;
				}
			}
			if(!added)
				intermediateScores.add(s);
			break;
		case HARD:
			for (int i = 0; i < hardScores.size(); i++) {
				if(hardScores.get(i).getTime() > s.getTime()) {
					hardScores.add(i, s);
					added = true;
					break;
				}
			}
			if(!added)
				hardScores.add(s);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Getter for easy Score list
	 */
	public LinkedList<Score> getEasyScores() {
		return easyScores;
	}
	
	/**
	 * Getter for intermediate Score list
	 */
	public LinkedList<Score> getIntermediateScores() {
		return intermediateScores;
	}
	
	/**
	 * Getter for hard Score list
	 */
	public LinkedList<Score> getHardScores() {
		return hardScores;
	}
	
	/**
	 * Print method.
	 * @return String to be printed to console.
	 */
	public String printScores() {
		String ret = "EASY\n";
		for (int i = 0; i < 5; i++) {
			if(i < easyScores.size())
				ret += i+1 + "-" + easyScores.get(i).getName() + "\t" + easyScores.get(i).getTime() + "\n";
			else
				ret += i+1 + "-\n";
		}
		
		ret += "INTERMEDIATE\n";
		for (int i = 0; i < 5; i++) {
			if(i < intermediateScores.size())
				ret += i+1 + "-" + intermediateScores.get(i).getName() + "\t" + intermediateScores.get(i).getTime() + "\n";
			else
				ret += i+1 + "-\n";
		}
		
		ret += "HARD\n";
		for (int i = 0; i < 5; i++) {
			if(i < hardScores.size())
				ret += i+1 + "-" + hardScores.get(i).getName() + "\t" + hardScores.get(i).getTime() + "\n";
			else
				ret += i+1 + "-\n";
		}
		
		return ret;
	}
}
