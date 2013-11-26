package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.Weapon;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;

public class SpaceSheep extends GameObject implements MouseMotionListener{

	//Set<Bullet> bullets = new HashSet<Bullet>();
	int	score = 0;
	public BasicWeapon weapon;
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game.getApp(), game.pathSprites+"playerGrid.png", 3, 1, 10);
		oSprite.setScale(1.4f);
		setPolar(450, 0);
		color = Game.Color.GREEN;
		weapon = new BasicWeapon(game, this, color);
		game.getApp().addMouseMotionListener(this);
		game.getApp().noCursor();
		oSprite.setCollisionRadius(oSprite.getCollisionRadius()/10.0f);
	}

	public void draw() {
		game.getApp().getDebug().put("Score", score);
		oSprite.setScale(getRadius() / 300f);
		if (game.getApp().isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.getApp().isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}
		if (game.getApp().isPressed(' ') || (game.getApp().mousePressed && game.getApp().mouseButton == PApplet.LEFT)) {
			weapon.shoot();
		}
		pieChart(200, weapon.percentage);
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
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float mouseX = e.getX() - game.getApp().displayWidth / 2;
		float mouseY = e.getY() - game.getApp().displayHeight / 2;
		float norme = PApplet.sqrt(mouseX * mouseX + mouseY * mouseY);
		
		//Free fly
		if (norme > 450) {
			norme = 450;
		}
		setPolar(norme, PApplet.atan2(mouseY, mouseX));
		
		// Circular fly
		/*
		float normX = mouseX / norme;
		float normY = mouseY / norme;
		setPolar(450, Game.atan2(normY, normX));
		int newMouseX = (int) (normX * 100) + (game.displayWidth / 2);
		int newMouseY = (int) (normY * 100) + (game.displayHeight / 2);
		game.robot.mouseMove(newMouseX, newMouseY);
		*/
	}

	public void addScore(int i) {
		score += i;
	}
	
	public int getScore() {
		return score;
	}
	
	public Game.Color getColor() {
		return color;
	}

	public void setRed() {
		color = Game.Color.RED;
		oSprite.setFrame(1);
	}
	
	public void setGreen() {
		color = Game.Color.GREEN;
		oSprite.setFrame(0);
	}
	
	public void setBlue() {
		color = Game.Color.CYAN;
		oSprite.setFrame(2);
	}
	
}
