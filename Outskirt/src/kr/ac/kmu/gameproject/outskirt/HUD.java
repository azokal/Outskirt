package kr.ac.kmu.gameproject.outskirt;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import kr.ac.kmu.gameproject.outskirt.boss.BasicBoss;
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
		game.getApp().pushStyle();
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
		game.getApp().popStyle();
	}
	
	void comboShow(int x, int y) {
		game.getApp().text("Combo:", x+20, y);
		game.getApp().pushStyle();
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
			game.getApp().ellipse(x, y+20, 20, 20);
			x += 40;
		}
		game.getApp().popStyle();
	}
	
	void bossLifeShow(int x, int y) {
		game.getApp().pushStyle();
		game.getApp().smooth();
		BasicBoss boss = null;
		for (GameObject obj: game.gameObjectList)
			if (obj instanceof BasicBoss && game.pop > ((BasicBoss)obj).getTiming()) {
				boss = (BasicBoss)obj;
			}
		if (boss != null) {
			game.getApp().text("Boss Life:", x+175, y-20);
			game.getApp().fill(255-255 * boss.getCurrentLife()/boss.getLifeMax(), 255 * boss.getCurrentLife()/boss.getLifeMax(), 0);
			game.getApp().rect(x, y, 400 * boss.getCurrentLife()/boss.getLifeMax(), 20);
		}
		game.getApp().popStyle();
	}
	
	public void draw() {
		pieChart(200, player.getCurrentWeapon().percentage);
		comboShow(560, 250);
		bossLifeShow(-200, -500);
		game.getApp().getDebug().put("Player power", this.player.getCurrentWeapon().power);
	}
}
