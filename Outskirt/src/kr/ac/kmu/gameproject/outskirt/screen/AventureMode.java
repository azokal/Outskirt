package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.TextObject;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class AventureMode extends Game {
	int level;

	public AventureMode(App app) {
		super(app);
		activatePause();
	}

	@Override
	void init() {
		level = 1;
		new TextObject(this, "Level 1", 0, 1000);
		part = new Partition(app, this, pathMaps + "level1.xml");
		spaceSheep = new SpaceSheep(this, true);
		this.hud = new HUD(this, spaceSheep);
	}

	@Override
	void endLevel() {
		if (pop >= endGame) {
			if (level != 3) {
				spaceSheep.activateWeapon(level);
				level++;
				this.tpop.reset();
				new TextObject(this, "Level " + level, 1000, 1000);
				part = new Partition(app, this, pathMaps + "level" + level
						+ ".xml");
			} else {
				isPausable = false;
				EndScreen screen = new EndScreen(app, this);
				screen.setup();
				app.setScreen(screen);
			}
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
}
