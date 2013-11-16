package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import sprites.Sprite;

public abstract class GameObject {

	protected Sprite oSprite;
	protected Game game;
	private float angle;
	private float radius;
	private float x;
	private float y;
	private boolean toDelete = false;
	
	
	public GameObject(Game game) {
		this.game = game;
		game.addGameObject(this);
	}

	abstract public void draw();

	public void setPolar(float radius, float angle) {
		x = radius * PApplet.cos(angle) + game.width / 2.0f;
		y = radius * PApplet.sin(angle) + game.height / 2.0f;
		this.radius = radius;
		this.angle = angle;
		oSprite.setXY(x, y);
		oSprite.setRot(angle - Game.PI / 2);
	}
	
	public void setCart(float x, float y) {
		this.x  = x;
		this.y = y;
		this.radius = PApplet.sqrt(x * x + y * y);
		this.angle = PApplet.tan(y / x);
		oSprite.setXY(x, y);
		oSprite.setRot(radius);
	}
	
	public float getRadius() {
		return radius;
	}
	
	public float getAngle() {
		return angle;
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
	
	public void markToDelete() {
		toDelete = true;
	}
	
	public boolean isToDelete() {
		return toDelete;
	}
}
