package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;

public class Quit extends MenuItem {

	public Quit(App app) {
		super(app, "Quit");
	}

	@Override
	public void exec() {
		app.exit();
	}
	
}
