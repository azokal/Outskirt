package kr.ac.kmu.gameproject.outskirt;

import javax.xml.soap.Text;

import processing.core.PApplet;
import processing.core.PImage;


public class Game extends PApplet{
	
	public PImage bg;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}
	
	public void setup() {
		size(1920, 1080);
		bg = loadImage("stars.jpg");
	}
	
	public void draw() {
		background(bg);
		ellipse(1920/2, 1080/2, 800, 800);
	}
	
}
