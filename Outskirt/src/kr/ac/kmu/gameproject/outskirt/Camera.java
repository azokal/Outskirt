package kr.ac.kmu.gameproject.outskirt;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
		game.addMouseWheelListener(this);
	}

	public void draw() {
		game.translate(position.x + game.displayWidth / 2, position.y + game.displayHeight / 2);
		game.scale(Game.min(game.displayWidth/1980f, game.displayHeight / 1080f) * zoom);
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
		this.zoom = game.min(max, this.zoom);
		this.zoom = game.max(min, this.zoom);
	}
	
	public void addZoom(float zoom) {
		this.zoom += zoom;
		this.zoom = game.min(max, this.zoom);
		this.zoom = game.max(min, this.zoom);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		addZoom(rot / 100f);
	}
	
}
