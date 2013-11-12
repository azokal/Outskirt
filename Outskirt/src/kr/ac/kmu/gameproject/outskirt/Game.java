package kr.ac.kmu.gameproject.outskirt;

import javax.xml.soap.Text;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.S4P;

public class Game extends PApplet{
	
	public PImage bg;
	public Timer sw;
	public SpaceSheep spaceSheep;
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}

	public void setup() {
		size(1920, 1080);
		bg = loadImage("bg.png");
		sw = new Timer();
		sw.start();
		spaceSheep = new SpaceSheep(this);
		registerMethod("keyEvent", this.spaceSheep);
	}

	public void draw() {
		background(bg);
		/*ellipse(1920/2, 1080/2, 900, 900);*/
		spaceSheep.draw();
		float elapsedTime = (float)sw.getElapsedTime();
		S4P.updateSprites(elapsedTime);
		S4P.drawSprites();
	}
	
}
