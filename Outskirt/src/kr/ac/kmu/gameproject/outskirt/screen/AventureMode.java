package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class AventureMode extends Game{
	int level;
	
	public AventureMode(App app) {
		super(app);
	}
	
	@Override
	void init() {
		level = 1;
		part = new Partition(app, this, pathMaps+"level1.xml");
		spaceSheep = new SpaceSheep(this, false);
		this.hud = new HUD(this, spaceSheep);
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
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
	}
}
