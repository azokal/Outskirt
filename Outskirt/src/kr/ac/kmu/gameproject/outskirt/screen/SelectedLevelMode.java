package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.TextObject;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class SelectedLevelMode extends Game {
	int level;

	public SelectedLevelMode(App app, int level) {
		super(app);
		this.level = level;
		activatePause();
	}

	@Override
	void init() {
		part = new Partition(app, this, pathMaps + "level" + level + ".xml", 0);
		spaceSheep = new SpaceSheep(this, true);
		this.hud = new HUD(this, spaceSheep);
		if (level >= 2 || level == 0) {
			spaceSheep.activateWeapon(1);
		}
		if (level >= 3 || level == 0) {
			spaceSheep.activateWeapon(2);
		}
		if (level != 0)
			new TextObject(this, "Level " + level, 100, 0, 1000);
	}
}
