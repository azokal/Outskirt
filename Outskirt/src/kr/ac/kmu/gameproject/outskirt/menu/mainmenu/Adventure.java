package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.AventureMode;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class Adventure extends MenuItem {

	public Adventure(App app) {
		super(app, "Adventure");
	}

	@Override
	public void exec() {
		Game g = new AventureMode(app);
		g.setup();
		app.setScreen(g);
	}

}
