package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

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
	public ArrayList<Game.Color> combo;
	
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
		combo = new ArrayList<Game.Color>();
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
		
		if (norme < 100) {
			norme = 100;
		}
		
		setPolar(norme, PApplet.atan2(mouseY, mouseX));
		game.getApp().getDebug().put("old mouse x", e.getX());
		game.getApp().getDebug().put("old mouse y", e.getY());
		
		int newMouseX = (int) (getX() + game.getApp().displayWidth / 2); 
		int newMouseY = (int) (getY() + game.getApp().displayHeight / 2);

		game.getApp().getDebug().put("new mouse x", newMouseX);
		game.getApp().getDebug().put("new mouse y", newMouseY);

		game.getApp().robot.mouseMove(newMouseX, newMouseY);
	}

	public void addCombo(Game.Color c) {
		combo.add(c);
		if (combo.size() >= 3)
		{
			if (combo.size() == 4) {
				combo.remove(0);
			}
			if (combo.get(0) == combo.get(1) && combo.get(0) == combo.get(2)) {
				score += 1000;
			}
			if (combo.get(0) != combo.get(1) && combo.get(0) != combo.get(2) && combo.get(1) != combo.get(2)) {
				weapon.power += 5;
			}
		}
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
