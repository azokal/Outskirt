package kr.ac.kmu.gameproject.outskirt;

import processing.core.PApplet;

public class EndScreen implements Screen {

	App app;
	Game game;
	
	public EndScreen(App app, Game game) {
		this.app = app;
		this.game = game;
	}
	
	@Override
	public void draw() {
		app.pushStyle();
		app.background(255);
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(0);
		app.text("Your score is:" + game.getSpaceSheep().getScore() + " Press enter to start the game!", app.width / 2, app.height / 2);
		if (app.isPressed(App.ENTER)) {
			app.setScreen(new Game(app));
		}
		app.popStyle();
	}

	@Override
	public void setup() {
		app.size(app.displayWidth, app.displayHeight);
	}
}
