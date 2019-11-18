package core;

import java.time.Instant;

import core.Board.Status;
import core.Cursor.Direction;

public class Minesweeper {
	public enum MenuStatus {
		MAIN_MENU,
		CHOOSE_DIFFICULTY,
		BOARD,
		HIGH_SCORES,
		WON,
		LOST
	}
	private Board board;
	private HighScores highScores;
	private interfaces.Input input;
	private interfaces.Map map;
	private Output output;
	private MenuStatus status;
	private String playername;
	
	public Minesweeper() {
		this.highScores = new HighScores();
		this.input = new Input();
		this.output = new Output();
		this.status = MenuStatus.MAIN_MENU;
		this.playername = "";
		this.map = new Map();
	}
	
	public Minesweeper(interfaces.Input input, interfaces.Map map) {
		this.input = input;
		this.map = map;
		this.status = MenuStatus.MAIN_MENU;
		this.playername = "";
		
		this.highScores = new HighScores();
		this.output = new Output();
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public HighScores getHighScores() {
		return this.highScores;
	}
	
	public interfaces.Input getInput() {
		return this.input;
	}
	
	public Output getOutput() {
		return this.output;
	}
	
	public MenuStatus getActualMenu() {
		return this.status;
	}
	
	private void printMenu() {
		output.print("1-Play\n2-HighScores\n0-Exit");
	}
	
	private void printDifficultyMenu() {
		output.print("1-EASY\n2-INTERMEDIATE\n3-HARD\n");
	}
	
	private void printHighScores() {
		output.print(getHighScores().printScores());
	}
	
	private void printBoard() {
		output.print(board.print());
	}
	
	private void printWon() {
		output.print(String.format("YOU WON!\n\nEnter your name:\n%s", playername));
	}
	
	private void printLost() {
		output.print("GAME OVER");
	}
	
	public void updateFrame() {
		char c = getInput().readKey();
		if(getActualMenu() == MenuStatus.WON) {
			if(playername.length() < 3) {
				playername += String.valueOf(c);
			}
			if(playername.length() >= 3) {
				Instant instant = Instant.now();
				long time = instant.getEpochSecond();
				getHighScores().addScore(new Score(playername, time - board.getTime(), board.getDifficulty()));
				status = MenuStatus.HIGH_SCORES;
			}
		} else {
			switch(c) {
			case '1':
				if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					//Map m = new Map(Board.Dificulty.EASY);
					this.board = map.getBoard(Board.Dificulty.EASY);
				} else if(getActualMenu() == MenuStatus.MAIN_MENU)
					status = MenuStatus.CHOOSE_DIFFICULTY;
				break;
			case '2':
				if(getActualMenu() == MenuStatus.MAIN_MENU)
					status = MenuStatus.HIGH_SCORES;
				else if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					this.board = map.getBoard(Board.Dificulty.MEDIUM);
				}
				break;
			case '3':
				if(getActualMenu() == MenuStatus.CHOOSE_DIFFICULTY) {
					status = MenuStatus.BOARD;
					this.board = map.getBoard(Board.Dificulty.HARD);
				}
				break;
			case '0':
				if(getActualMenu() == MenuStatus.MAIN_MENU) {
					output.print("Good bye!");
					System.exit(0);
				} else {
					status = MenuStatus.MAIN_MENU;
				}
				break;
			case 'w':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().moveCursor(Direction.TOP);
				break;
			case 'a':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().moveCursor(Direction.LEFT);
				break;
			case 's':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().moveCursor(Direction.BOTTOM);
				break;
			case 'd':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().moveCursor(Direction.RIGHT);
				break;
			case 'o':
				if(getActualMenu() == MenuStatus.BOARD) {
					getBoard().openTile();
					if(getBoard().checkStatus() == Status.WIN)
						status = MenuStatus.WON;
					if(getBoard().checkStatus() == Status.LOST)
						status = MenuStatus.LOST;
				}
				break;
			case 'm':
				if(getActualMenu() == MenuStatus.BOARD)
					getBoard().markTile();
				break;
			}
		}
		
		switch(status) {
		case MAIN_MENU:
			printMenu();
			break;
		case HIGH_SCORES:
			printHighScores();
			break;
		case CHOOSE_DIFFICULTY:
			printDifficultyMenu();
			break;
		case BOARD:
			printBoard();
			break;
		case WON:
			printWon();
			break;
		case LOST:
			printLost();
			break;
		}
	}
	
	public void main() {
		while(true) {
			updateFrame();
		}
	}
}