package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.AventureMode;
import kr.ac.kmu.gameproject.outskirt.screen.ChooseLevel;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;

public class Adventure extends MenuItem {

	public Adventure(App app) {
		super(app, "Adventure");
	}

	@Override
	public void exec() {
		app.setScreen(new AventureMode(app));
	}

}
