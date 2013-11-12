package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;
import processing.core.PVector;
import sprites.Sprite;
import sprites.maths.Vector2D;

public class GameObject {

	protected Sprite oSprite;
	protected Game game;
	private float angle;
	private float radius;
	private float x;
	private float y;
	
	public void setPolar(float radius, float angle) {
		x = radius * PApplet.cos(angle) + game.width / 2;
		y = radius * PApplet.sin(angle) + game.height / 2;
		this.radius = radius;
		this.angle = angle;
		oSprite.setXY(x, y);
	}
	
	public void setCart(float x, float y) {
		this.x  = x;
		this.y = y;
		this.radius = PApplet.sqrt(x * x + y * y);
		this.angle = PApplet.tan(y / x);
		oSprite.setXY(x, y);
	}
	public void addAngle(float angle) {
		setPolar(radius, this.angle + angle);
	}
	public void addRadius(float radius) {
		setPolar(this.radius + radius, this.angle);
	}
	public GameObject(Game game) {
		this.game = game;
	}
}
