package kr.ac.kmu.gameproject.outskirt.menu.mainmenu;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.screen.SelectLevelMenu;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;

public class SelectLevel extends MenuItem {

	Screen bg;
	
	public SelectLevel(App app, Screen bg) {
		super(app, "Select level");
		this.bg = bg;
	}

	@Override
	public void exec() {
		SelectLevelMenu g = new SelectLevelMenu(app, bg);
		app.setScreen(g);
	}

}