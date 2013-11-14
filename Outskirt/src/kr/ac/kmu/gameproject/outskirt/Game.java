package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.S4P;

public class Game extends PApplet{
	
	public PImage bg;
	public Timer timer;
	public SpaceSheep spaceSheep;
	public Enemy enemy;
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				"kr.ac.kmu.gameproject.outskirt.Game" });
	}

	public void setup() {
		size(1920, 1080);
		bg = loadImage("bg.png");
		timer = new Timer();
		spaceSheep = new SpaceSheep(this);
		enemy = new Enemy(this);
	}

	public void draw() {
		timer.updateTime();
		background(bg);
		/*ellipse(1920/2, 1080/2, 900, 900);*/
		spaceSheep.draw();
		enemy.draw();
		S4P.updateSprites(timer.getTotalTime());
		S4P.drawSprites();
	}
	
}
