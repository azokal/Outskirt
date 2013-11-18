package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.App;

public class PauseScreen implements Screen, KeyListener {

	App app;
	Game game;
	
	public PauseScreen(App app, Game game) {
		this.app = app;
		this.game = game;
	}
	
	@Override
	public void draw() {
		//game.draw();
		app.pushStyle();
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(255);
		app.textSize(42);
		app.text("Pause", app.width / 2, app.height / 2);
		app.popStyle();
	}

	@Override
	public void setup() {
		app.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'p') {
			app.resumeScreen(game);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
