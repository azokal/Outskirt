package kr.ac.kmu.gameproject.outskirt.screen;

import processing.core.PApplet;
import sprites.S4P;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Adventure;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Quit;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Random;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.SelectLevel;

public class EndScreen implements Screen {

	final protected App app;
	final protected Game game;

	final protected GameBackground bg;

	protected MenuItemGroup menuItemList;

	public EndScreen(App app, Game game) {
		this.app = app;
		this.game = game;
		bg = new GameBackground(app);
		app.cursor();

	}

	public void setup() {
		bg.setup();
	}

	@Override
	public void draw() {
		bg.draw();
		app.pushStyle();
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(0, 144, 0);
		app.textSize(32);
		app.text("Game Over\nYour score is:\n", 0, -128);
		app.fill(0, 255, 0);
		app.textSize(128);
		app.text(String.format("%.0f", game.getSpaceSheep().getScore()), 0, 0);
		app.fill(0, 144, 0);
		app.textSize(32);
		app.text("Press enter to go back to menu!", 0, 128);
		if (app.isPressed(App.ENTER)) {
			S4P.resetWorld();
			this.game.unload();
			MainMenu screen = new MainMenu(app);
			screen.setup();
			app.setScreen(screen);
		}
		app.popStyle();
	}

}
