package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import partition.Partition;
import processing.core.PApplet;
import sprites.S4P;

public class EndScreen implements Screen {

	App app;
	Game game;
	boolean toto;
	
	public EndScreen(App app, Game game) {
		this.app = app;
		this.game = game;
		toto = false;
	}
	
	@Override
	public void draw() {
	
		if (toto == false){
			game.part.exportMap("new.xml", app);
			toto = true;
		}
		app.pushStyle();
		app.background(255);
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(0);
		app.text("Your score is:" + game.getSpaceSheep().getScore() + " Press enter to start the game!", app.width / 2, app.height / 2);
		if (app.isPressed(App.ENTER)) {
			S4P.resetWorld();
			this.game.unload();
			app.setScreen(new Game(app));
		}
		app.popStyle();
	}

	@Override
	public void setup() {
		app.size(app.displayWidth, app.displayHeight);
	}
}