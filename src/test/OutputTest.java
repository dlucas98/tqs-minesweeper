package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Minesweeper;
import mocks.Input;

public class OutputTest {

	@Test
	public void test() {
		Minesweeper m = new Minesweeper(new Input());
		Input i = (Input)m.getInput();
		
		String s =  "1-Play\n"+
					"2-HighScores\n"+
					"0-Exit";
		
		assertEquals(s, m.getOutput().getBuffer());
		
		String s2 = "EASY\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n" + 
					"INTERMEDIATE\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n" + 
					"HARD\n" + 
					"1-\n" + 
					"2-\n" + 
					"3-\n" + 
					"4-\n" + 
					"5-\n";
		i.sendKey('2');
		assertEquals(s2, m.getOutput().getBuffer());
		
		String s3 = "1-EASY\n" + 
					"2-INTERMEDIATE\n" + 
					"3-HARD\n";;
		i.sendKey('1');
					
		String s4 = "[#]# # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n" +
					" # # # # # # # # # \n";
		assertEquals(s4, m.getOutput().getBuffer());
		
		i.sendKey('o');
		
		String s5 = "[ ]        2 # # # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s5, m.getOutput().getBuffer());
		
		i.sendKey('d');
		String s6 = "  [ ]      2 # # # \n" +
					"           3 # # # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s6, m.getOutput().getBuffer());
		
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('s');

		String s7 = "           2 # # # \n" +
					"           3 #[#]# \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s7, m.getOutput().getBuffer());

		i.sendKey('o');
		String s8 = "           2 # # # \n" +
					"           3 #[8]# \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s8, m.getOutput().getBuffer());

		i.sendKey('a');
		i.sendKey('m');
		String s9 = "           2 # # # \n" +
					"           3[X]8 # \n" +
					"           2 # # # \n" +
					"           1 2 3 2 \n" +
					" 2 3 3 2 1         \n" +
					" # # # # 2         \n" +
					" # # # # 3         \n" +
					" # # # # 3         \n" +
					" # # # # 2         \n";
		assertEquals(s9, m.getOutput().getBuffer());

		i.sendKey('a');
		i.sendKey('a');
		i.sendKey('a');
		i.sendKey('a');
		i.sendKey('a');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('d');
		i.sendKey('o');
		/*String s10 = "           2 # # # \n" +
					 "           3 X 8 # \n" +
					 "           2 # # # \n" +
					 "           1 2 3 2 \n" +
					 " 2 3 3 2 1         \n" +
					 " # # # # 2         \n" +
					 " # # # # 3         \n" +
					 " 2[5]# # 3         \n" +
					 "   2 # # 2         \n";*/
		String s10 = "YOU WON!\n" +
					 "\n" + 
					 "Enter your name: \n";
		assertEquals(s10, m.getOutput().getBuffer());
		i.sendKey('D');
		String s11 = "YOU WON!\n" +
					 "\n" + 
					 "Enter your name: \n" + 
					 "D";
		assertEquals(s11, m.getOutput().getBuffer());
		i.sendKey('A');
		i.sendKey('V');
		
		assertEquals(s, m.getOutput().getBuffer());
	}

}
