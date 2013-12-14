package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.ChooseLevel;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;

public class SelectLevel extends MenuItem {

	Screen bg;
	
	public SelectLevel(App app, Screen bg) {
		super(app, "Select level");
		this.bg = bg;
	}

	@Override
	public void exec() {
		Screen g = new ChooseLevel(app, bg);
		g.setup();
		app.setScreen(g);
	}

}