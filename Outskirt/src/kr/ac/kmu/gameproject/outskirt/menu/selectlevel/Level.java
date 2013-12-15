package kr.ac.kmu.gameproject.outskirt.menu.selectlevel;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.SelectedLevelMode;

public class Level extends MenuItem {

	int level;

	public Level(App app, int level) {
		super(app, "Level " + level);
		this.level = level;
	}

	@Override
	public void exec() {
		Game g = new SelectedLevelMode(app, level);
		g.setup();
		app.setScreen(g);
	}
}