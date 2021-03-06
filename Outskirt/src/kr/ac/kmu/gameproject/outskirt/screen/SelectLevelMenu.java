package kr.ac.kmu.gameproject.outskirt.screen;

import sprites.S4P;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.selectlevel.Level;

public class SelectLevelMenu implements Screen {

	App app;

	private Screen bg;

	protected MenuItemGroup menuItemList;

	public SelectLevelMenu(App app, Screen bg) {
		this.app = app;
		this.bg = bg;
		menuItemList = new MenuItemGroup(app);
		app.cursor();
		for (int i = 0; i < Game.MAX_LEVEL; i++) {
			menuItemList.add(new Level(app, i + 1));
		}
		menuItemList.select(1);
	}

	@Override
	public void draw() {
		// Play !
		bg.draw();
		app.pushStyle();
		app.textAlign(App.CENTER, App.TOP);
		app.fill(0, 144, 0);
		app.textSize(100);
		app.text("Choose your level !", 0, -550);
		app.popStyle();
		menuItemList.draw();
	}

	@Override
	public void unload() {
		S4P.resetWorld();
		this.bg.unload();
		menuItemList.unregisterEvent();
	}

}
