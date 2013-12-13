package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;

public class ChooseLevel implements Screen {

	App app;
	
	private Screen bg;
	
	public ChooseLevel(App app, Screen bg) {
		this.app = app;
		this.bg = bg;
	}

	@Override
	public void setup() {
	}
	
	@Override
	public void draw() {
		//Play !
		bg.draw();
		app.textAlign(app.CENTER, app.TOP);
		app.fill(255, 255, 255);
		app.text("Choose your level !", 0, 0);
		if (app.isPressed(App.ENTER)) {
			Game g = new AventureMode(app);
			app.setScreen(g);
		}
	}

}
