package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.SelectLevelMenu;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;
import kr.ac.kmu.gameproject.outskirt.screen.SelectedLevelMode;

public class Tutorial extends MenuItem {

	Screen bg;

	public Tutorial(App app, Screen bg) {
		super(app, "Tutorial");
		this.bg = bg;
	}

	@Override
	public void exec() {
		Game g = new SelectedLevelMode(app, 0);
		g.setup();
		app.setScreen(g);
	}

}