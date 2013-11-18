package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import kr.ac.kmu.gameproject.outskirt.Game;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.BasicWeapon;
import kr.ac.kmu.gameproject.outskirt.gameobject.weapon.Weapon;
import processing.core.PApplet;

public class SpaceSheep extends GameObject implements MouseMotionListener{

	//Set<Bullet> bullets = new HashSet<Bullet>();
	int	score = 0;
	Weapon weapon = new BasicWeapon(game, this);
	
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game, "playerGrid.png", 3, 1, 10);
		oSprite.setScale(1.4f);
		setPolar(450, 0);
		game.addMouseMotionListener(this);
		game.noCursor();
	}

	public void draw() {
		game.debug.put("Score", score);
		oSprite.setScale(getRadius() / 300f);
		if (game.isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}
		if (game.isPressed(' ')) {
			weapon.shoot();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		float mouseX = e.getX() - game.displayWidth / 2;
		float mouseY = e.getY() - game.displayHeight / 2;
		float norme = Game.sqrt(mouseX * mouseX + mouseY * mouseY);
		
		//Free fly
		if (norme > 450) {
			norme = 450;
		}
		setPolar(norme, Game.atan2(mouseY, mouseX));
		
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
}
