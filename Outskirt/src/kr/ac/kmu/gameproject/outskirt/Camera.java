package kr.ac.kmu.gameproject.outskirt;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.core.PApplet;
import processing.core.PVector;

public class Camera implements MouseWheelListener{

	PVector position = new PVector();
	float zoom = 1;
	float max = 10;
	float min = 0;
	Game game;
	
	public Camera(Game game) {
		this(game, 0, 0);
	}

	public Camera(Game game, float x, float y) {
		this.game = game;
		position.x = x;
		position.y = y;
		game.getApp().addMouseWheelListener(this);
	}

	public void draw() {
		game.getApp().translate(position.x + game.getApp().displayWidth / 2, position.y + game.getApp().displayHeight / 2);
		game.getApp().scale(PApplet.min(game.getApp().displayWidth/1980f, game.getApp().displayHeight / 1080f) * zoom);
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.zoom = PApplet.min(max, this.zoom);
		this.zoom = PApplet.max(min, this.zoom);
	}
	
	public void addZoom(float zoom) {
		this.zoom += zoom;
		this.zoom = PApplet.min(max, this.zoom);
		this.zoom = PApplet.max(min, this.zoom);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		addZoom(rot / 100f);
	}
	
}
