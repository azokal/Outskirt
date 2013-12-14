package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Adventure;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Quit;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Random;

public class MainMenu implements Screen {

	final protected App app;
	
	final protected Screen bg;
	
	protected MenuItemGroup menuItemList;
	
	public MainMenu(App app) {
		this.app = app;
		bg = new GameBackground(app);
		menuItemList = new MenuItemGroup(app);
		app.cursor();
		menuItemList.add(new Quit(app));
		menuItemList.add(new Adventure(app));
		menuItemList.add(new Random(app));
		menuItemList.select(1);
	}


	@Override
	public void setup() {
		bg.setup();
	}
	
	@Override
	public void draw() {
		bg.draw();
		app.textAlign(App.CENTER, App.TOP);
		app.fill(255, 255, 255);
		app.text("Let's play !", 0, 0);
		menuItemList.draw();				
	}

	
}
