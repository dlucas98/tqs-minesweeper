package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HighScores {
	private ArrayList<Score> easyScores;
	private ArrayList<Score> intermediateScores;
	private ArrayList<Score> hardScores;
	
	public HighScores() {
		easyScores = new ArrayList<Score>();
		intermediateScores = new ArrayList<Score>();
		hardScores = new ArrayList<Score>();
	}
	
	private void readScores() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scores"));
			HighScores obj = (HighScores)ois.readObject();
			
			for (Score score : obj.getEasyScores())
				easyScores.add(score);
			for (Score score : obj.getIntermediateScores())
				easyScores.add(score);
			for (Score score : obj.getHardScores())
				easyScores.add(score);
			ois.close();
		} catch (Exception e) {
			
		}
	}
	
	private void writeScores() {
		try {
			FileOutputStream f = new FileOutputStream(new File("scores"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(this);
			o.close();
		} catch (Exception e) {
			
		}
	}
	
	public void addScore(Score s) {
		readScores();
		switch (s.getDifficulty()) {
		case EASY:
			easyScores.add(s);
			break;
		case MEDIUM:
			intermediateScores.add(s);
			break;
		case HARD:
			hardScores.add(s);
			break;
		default:
			break;
		}
		writeScores();
	}
	
	public ArrayList<Score> getEasyScores() {
		return easyScores;
	}
		
	public ArrayList<Score> getIntermediateScores() {
		return intermediateScores;
	}
	
	public ArrayList<Score> getHardScores() {
		return hardScores;
	}
	
	public String printScores() {
		String ret = "EASY\n";
		for (int i = 0; i < 5; i++) {
			if(i <= easyScores.size())
				ret += i + "-" + easyScores.get(i).getName() + "\t" + easyScores.get(i).getTime() + "\n";
			else
				ret += i + "-\n";
		}
		
		ret += "INTERMEDIATE\n";
		for (int i = 0; i < 5; i++) {
			if(i <= intermediateScores.size())
				ret += i + "-" + intermediateScores.get(i).getName() + "\t" + intermediateScores.get(i).getTime() + "\n";
			else
				ret += i + "-\n";
		}
		
		ret += "HARD\n";
		for (int i = 0; i < 5; i++) {
			if(i <= hardScores.size())
				ret += i + "-" + hardScores.get(i).getName() + "\t" + hardScores.get(i).getTime() + "\n";
			else
				ret += i + "-\n";
		}
		
		return ret;
	}
}
