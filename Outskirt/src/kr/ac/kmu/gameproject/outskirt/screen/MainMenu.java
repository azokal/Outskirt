package kr.ac.kmu.gameproject.outskirt.screen;

import processing.core.PApplet;
import sprites.S4P;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Adventure;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Quit;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Random;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.SelectLevel;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Tutorial;

public class MainMenu implements Screen {

	final protected App app;

	final protected GameBackground bg;

	protected MenuItemGroup menuItemList;

	public MainMenu(App app) {
		this.app = app;
		bg = new GameBackground(app);
		menuItemList = new MenuItemGroup(app);
		app.cursor();
		menuItemList.add(new Adventure(app));
		menuItemList.add(new SelectLevel(app, bg));
		menuItemList.add(new Quit(app));
		menuItemList.add(new Tutorial(app, bg));
		menuItemList.add(new Random(app));
		menuItemList.select(1);
	}

	public void setup() {
		bg.setup();
	}

	@Override
	public void draw() {
		bg.draw();
		app.textAlign(App.CENTER, App.TOP);
		app.fill(0, 144, 0);
		app.textSize(100);
		app.text("OutSkirt", 0, -550);
		menuItemList.draw();
	}

	@Override
	public void unload() {
		S4P.resetWorld();
		this.bg.unload();
		menuItemList.unregisterEvent();
	}

}
