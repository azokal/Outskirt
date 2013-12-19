package kr.ac.kmu.gameproject.outskirt;

import kr.ac.kmu.gameproject.outskirt.boss.BasicBoss;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class HUD {
	Game game;
	SpaceSheep player;

	public HUD(Game game, SpaceSheep player) {
		this.game = game;
		this.player = player;
		/* game.getApp().addMouseWheelListener(this); */
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
			game.getApp().arc(535, 425, diameter, diameter, lastAngle,
					lastAngle + App.radians(360.0f * data[i] / 100.0f));
			lastAngle += App.radians(360.0f * data[i] / 100.0f);
		}
		game.getApp().popStyle();
	}

	void comboShow(int x, int y) {
		game.getApp().pushStyle();
		game.getApp().textSize(24);
		game.getApp().text("Combo:", x + 40, y);
		for (Game.Color c : this.player.combo) {
			if (c == Game.Color.GREEN) {
				game.getApp().fill(0, 255, 0);
			}
			if (c == Game.Color.RED) {
				game.getApp().fill(255, 0, 0);
			}
			if (c == Game.Color.CYAN) {
				game.getApp().fill(0, 255, 255);
			}
			game.getApp().ellipse(x, y + 48, 20, 20);
			x += 40;
		}
		game.getApp().popStyle();
	}

	void verticalBar(int x, int y, int size, String label, float value,
			float valueMax) {
		game.getApp().pushStyle();
		game.getApp().textSize(24);
		game.getApp().text(label, x - 8, y + size + 32);
		game.getApp().fill(255 - 255 * value / valueMax,
				255 * value / valueMax, 0);
		game.getApp().rect(x - 16, y + size, 20, -size * value / valueMax);
		game.getApp().popStyle();
	}

	void bossLifeShow(int x, int y) {
		game.getApp().pushStyle();
		game.getApp().smooth();
		game.getApp().textSize(24);
		BasicBoss boss = null;
		boolean isFind = false;
		for (GameObject obj : game.gameObjectList) {
			if (obj instanceof BasicBoss
					&& game.tpop.getTotalTime() >= ((BasicBoss) obj)
							.getTiming()) {
				boss = (BasicBoss) obj;
				isFind = true;
			}
		}
		if (isFind == false)
			boss = null;
		if (boss != null) {
			game.getApp().text("Boss Life:", x + 175, y - 38);
			game.getApp().fill(
					255 - 255 * boss.getCurrentLife() / boss.getLifeMax(),
					255 * boss.getCurrentLife() / boss.getLifeMax(), 0);
			game.getApp().rect(x, y,
					400 * boss.getCurrentLife() / boss.getLifeMax(), 20);
		}
		game.getApp().popStyle();
	}

	void printMult(int x, int y) {
		game.getApp().pushStyle();
		game.getApp().textSize(24);
		game.getApp().text("Mult: " + player.getMult() + "x", x, y);
		game.getApp().popStyle();
	}

	void printScore(int x, int y) {
		game.getApp().pushStyle();
		game.getApp().textSize(36);
		game.getApp()
				.text("Score:\n"
						+ String.format("%.0f", game.getSpaceSheep().getScore()),
						x, y);
		game.getApp().popStyle();
	}

	public void draw() {
		pieChart(200, player.getCurrentWeapon().percentage);
		comboShow(490, 260);
		bossLifeShow(-200, -500);
		verticalBar(500, -200, 400, "Life", player.getCurrentLife(),
				player.getLifeMax());
		verticalBar(580, -200, 400, "Power",
				this.player.getCurrentWeapon().power,
				this.player.getCurrentWeapon().powerMax);
		printMult(500, -250);
		printScore(0, 460);
		game.getApp().getDebug()
				.put("Player power", this.player.getCurrentWeapon().power);
	}
}
