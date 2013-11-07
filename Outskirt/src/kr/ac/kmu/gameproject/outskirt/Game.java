package kr.ac.kmu.gameproject.outskirt;

import javax.xml.soap.Text;

import processing.core.PApplet;

public class Game extends PApplet{

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		text("Hello world" , 10, 10);
	}
	
}
