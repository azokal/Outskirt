package kr.ac.kmu.gameproject.outskirt.screen;

import partition.Partition;
import kr.ac.kmu.gameproject.outskirt.App;

public class Menu implements Screen {

	private App app;
	public Menu(App app) {
		this.app = app;
	}
	
	@Override
	public void draw() {
		app.pushStyle();
		app.background(255);
		app.textAlign(app.CENTER, app.TOP);
		app.fill(0);
		app.text("Press enter to start the game!", app.width / 2, app.height / 2);
		if (app.isPressed(App.ENTER)) {
			Game g = new Game(app);
			g.part = new Partition(app, g, g.pathMaps+"test.xml");
			app.setScreen(g);
		}
		app.popStyle();
	}

	@Override
	public void setup() {
		app.size(app.displayWidth, app.displayHeight);
	}
	
	
	
}
