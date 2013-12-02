package kr.ac.kmu.gameproject.outskirt;

import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class HUD {
	Game game;
	SpaceSheep player;
	
	public HUD(Game game, SpaceSheep player) {
		this.game = game;
		this.player = player;
		/*game.getApp().addMouseWheelListener(this);*/
	}

	void pieChart(float diameter, float[] data) {
		game.getApp().smooth();
		  float lastAngle = 0;
		  for (int i = 0; i < data.length; i++) {
			if (i == 0) {
				game.getApp().fill(0, 255, 0);
			}
			if (i == 1) {
				game.getApp().fill(255, 0, 0);
			}
			if (i == 2) {
				game.getApp().fill(0, 255, 255);
			}
		    game.getApp().arc(600, 400, diameter, diameter, lastAngle, lastAngle+game.getApp().radians(360.0f * data[i] / 100.0f));
		    lastAngle += game.getApp().radians(360.0f * data[i] / 100.0f);
		  }
		  game.getApp().fill(255,255,255);
	}
	
	void comboShow(int x, int y) {
		game.getApp().text("Combo:", x-20, y);
		for (Game.Color c: this.player.combo) {
			if (c == Game.Color.GREEN) {
				game.getApp().fill(0, 255, 0);
			}
			if (c == Game.Color.RED) {
				game.getApp().fill(255, 0, 0);
			}
			if (c == Game.Color.CYAN) {
				game.getApp().fill(0, 255, 255);
			}
			game.getApp().ellipse(x, y+40, 20, 20);
			x += 40;
			game.getApp().noFill();
		}
	}
	
	public void draw() {
		pieChart(200, player.weapon.percentage);
		comboShow(-600, -500);
		game.getApp().getDebug().put("Player power", this.player.weapon.power);
	}
}
