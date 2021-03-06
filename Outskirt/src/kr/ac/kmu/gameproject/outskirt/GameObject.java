package kr.ac.kmu.gameproject.outskirt;

import kr.ac.kmu.gameproject.outskirt.screen.Game;
import sprites.S4P;
import sprites.Sprite;

public abstract class GameObject {

	public Sprite oSprite;
	protected Game game;
	private float angle;
	private float radius;
	private float x;
	private float y;
	protected Game.Color color;

	public GameObject(Game game) {
		this.game = game;
		game.addGameObject(this);
	}

	public void draw() {
	};

	public void pause() {
	};

	public void resume() {
	};

	public void setPolar(float radius, float angle) {
		x = App.toX(radius, angle);
		y = App.toY(radius, angle);
		this.radius = radius;
		this.angle = angle;
		oSprite.setXY(x, y);
		oSprite.setRot(angle - App.PI / 2);
	}

	public void setCart(float x, float y) {
		this.x = x;
		this.y = y;
		this.radius = App.toRadius(x, y);
		this.angle = App.toAngle(x, y);
		oSprite.setXY(x, y);
		oSprite.setRot(radius);
	}

	public void kill() {
		game.delGameObject(this);
		if (oSprite != null) {
			oSprite.setDead(true);
			S4P.deregisterSprite(oSprite);
		}
	}

	public float getRadius() {
		return radius;
	}

	public float getAngle() {
		return angle;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setAngle(float angle) {
		setPolar(radius, angle);
	}

	public void addAngle(float angle) {
		setPolar(radius, this.angle + angle);
	}

	public void addRadius(float radius) {
		setPolar(this.radius + radius, this.angle);
	}

	public void setRadius(float radius) {
		setPolar(radius, angle);
	}

	public boolean collide(GameObject obj) {
		if (this.oSprite != null && obj.oSprite != null) {
			return this.oSprite.bb_collision(obj.oSprite);
		}
		return false;
	}

	public void onCollide(GameObject obj) {

	}

}
