package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Ranking {
	private String fileName;
	private List<Score> easyRanking;
	private List<Score> mediumRanking;
	private List<Score> hardRanking;
	
	public Ranking(String fileName) {
		if (fileName != null)
			this.fileName = fileName;
		else
			this.fileName = "mine-save";

		easyRanking = new ArrayList<Score>();
		mediumRanking = new ArrayList<Score>();
		hardRanking = new ArrayList<Score>();
	}
	
	public void addScore(Score s) {
		try {
			readScores();
		} catch (Exception e) {}
		switch (s.getDifficulty()) {
			case EASY:
				easyRanking.add(s);
				break;
			case MEDIUM:
				mediumRanking.add(s);
				break;
			case HARD:
				hardRanking.add(s);
				break;
			default:
				break;
		}
		try {
			writeScores();
		} catch (Exception e) {}
	}

	public List<Score> getEasyRanking() {
		return this.easyRanking;
	}

	public List<Score> getMediumRanking() {
		return this.mediumRanking;
	}

	public List<Score> getHardRanking() {
		return this.hardRanking;
	}
	
	public String getFileName() {
		return this.fileName;
	}

	private void setEasyRanking(List<Score> l) {
		this.easyRanking = l;
	}
	
	private void setMediumRanking(List<Score> l) {
		this.mediumRanking = l;
	}
	
	private void setHardRanking(List<Score> l) {
		this.hardRanking = l;
	}
	
	public void print() {
		
	}
	
	private void readScores() throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream(new File(fileName));
		ObjectInputStream oi = new ObjectInputStream(fi);
		Ranking r = (Ranking) oi.readObject();
		oi.close();
		fi.close();
		
		setEasyRanking(r.getEasyRanking());
		setMediumRanking(r.getMediumRanking());
		setHardRanking(r.getHardRanking());
	}
	
	private void writeScores() throws IOException {
		FileOutputStream f = new FileOutputStream(new File(fileName));
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(this);
		o.close();
		f.close();
	}
}
