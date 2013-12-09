package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class AventureMode extends Game{
	int level;
	
	public AventureMode(App app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	void init() {
		level = 1;
		part = new Partition(app, this, pathMaps+"level1.xml");
	}
	

	@Override
	void endLevel() {
		if (pop >= endGame) {
			if (level != 3) {
				level++;
				this.tpop.reset();
				part = new Partition(app, this, pathMaps+"level"+level+".xml");
			}
			else {
				app.setScreen(new EndScreen(app, this));
			}
		}
	}
}
