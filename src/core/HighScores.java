package core;

import java.awt.List;
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
		
	}
	
	public ArrayList<Score> getEasyScores() {
		
	}
		
	public ArrayList<Score> getIntermediateScores() {
		
	}
	
	public ArrayList<Score> getHardScores() {
		
	}
	
	public String printScores() {
		
	}
}
