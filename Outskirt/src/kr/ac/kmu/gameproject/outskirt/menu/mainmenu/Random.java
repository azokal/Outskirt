package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.AventureMode;
import kr.ac.kmu.gameproject.outskirt.screen.ChooseLevel;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.RandomMode;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;

public class Random extends MenuItem {

	public Random(App app) {
		super(app, "Random");
	}

	@Override
	public void exec() {
		Game g = new RandomMode(app);
		app.setScreen(g);
	}

}
