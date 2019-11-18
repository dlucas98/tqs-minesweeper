package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class HighScores implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<Score> easyScores;
	private LinkedList<Score> intermediateScores;
	private LinkedList<Score> hardScores;
	
	public HighScores() {
		easyScores = new LinkedList<Score>();
		intermediateScores = new LinkedList<Score>();
		hardScores = new LinkedList<Score>();
	}
	
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
	
	public LinkedList<Score> getEasyScores() {
		return easyScores;
	}
		
	public LinkedList<Score> getIntermediateScores() {
		return intermediateScores;
	}
	
	public LinkedList<Score> getHardScores() {
		return hardScores;
	}
	
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
