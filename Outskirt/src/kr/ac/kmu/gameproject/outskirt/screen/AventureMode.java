package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.gameobject.TextObject;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class AventureMode extends Game {
	int level;
	int loopNb;

	public AventureMode(App app) {
		super(app);
		activatePause();
	}

	@Override
	void init() {
		loopNb = 0;
		level = 1;
		new TextObject(this, "Level 1", 100, 0, 1000);
		part = new Partition(app, this, pathMaps + "level1.xml", loopNb);
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
				if (loopNb == 0)
					new TextObject(this, "Level " + level +"\nNew Weapon!", 100, 1000, 1000);
				else
					new TextObject(this, "Level " + level, 100, 1000, 1000);
				part = new Partition(app, this, pathMaps + "level" + level
						+ ".xml", loopNb);
			} else {
				if (spaceSheep.getScore() >= 1000000 * (loopNb + 1)) {
					loopNb++;
					level = 1;
					this.tpop.reset();
					new TextObject(this, "Loop "+ (loopNb + 1), 100, 0, 1000);
					new TextObject(this, "Level 1", 100, 1000, 1000);
					part = new Partition(app, this, pathMaps + "level" + level
							+ ".xml", loopNb);
				} else {
					isPausable = false;
					EndScreen screen = new EndScreen(app, this);
					screen.setup();
					app.setScreen(screen);
				}
			}
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
}
