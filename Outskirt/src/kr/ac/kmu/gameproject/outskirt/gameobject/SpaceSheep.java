package kr.ac.kmu.gameproject.outskirt.gameobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import kr.ac.kmu.gameproject.outskirt.Game;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.Game.Color;

import processing.core.PApplet;
import processing.core.PVector;

public class SpaceSheep extends GameObject implements MouseMotionListener{

	//Set<Bullet> bullets = new HashSet<Bullet>();
	Game.Color color = Game.Color.GREEN;
	float cooldownShoot = 100f; //100ms
	float lastShoot = 0.0f;
	
	
	public SpaceSheep(Game game) {
		super(game);
		oSprite = new sprites.Sprite(game, "playerGrid.png", 3, 1, 10);
		oSprite.setScale(1.4f);
		setPolar(450, 0);
		game.addMouseMotionListener(this);
		game.noCursor();
	}

	public void draw() {
		
		if (game.isPressed(PApplet.LEFT)) {
			addAngle(0.08f);
		}
		if (game.isPressed(PApplet.RIGHT)) {
			addAngle(-0.08f);
		}
		if (game.isPressed(' ')) {
			if (game.timer.getTotalTime() - lastShoot > cooldownShoot)
			{
				Bullet tmp = new Bullet(game, this.getRadius(), this.getAngle(), 4, color);
				//bullets.add(tmp);
				lastShoot = game.timer.getTotalTime();
			}
		}
		if (game.isPressed('z')) {
			color = Game.Color.GREEN;
			oSprite.setFrame(0);
		}
		if (game.isPressed('x')) {
			color = Game.Color.RED;
			oSprite.setFrame(1);
		}
		if (game.isPressed('c')) {
			color = Game.Color.CYAN;
			oSprite.setFrame(2);
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
		float normX = mouseX / norme;
		float normY = mouseY / norme;
		setPolar(450, Game.atan2(normY, normX));
		int newMouseX = (int) (normX * 100) + (game.displayWidth / 2);
		int newMouseY = (int) (normY * 100) + (game.displayHeight / 2);
		game.robot.mouseMove(newMouseX, newMouseY);
	}
}
