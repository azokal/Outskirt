package kr.ac.kmu.gameproject.outskirt.screen;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;

public class SelectedLevelMode extends Game{
	int level;
	
	public SelectedLevelMode(App app, int level) {
		super(app);
		// TODO Auto-generated constructor stub
		this.level = level;
	}

	
	@Override
	void init() {
		part = new Partition(app, this, pathMaps+"level"+level+".xml");
		spaceSheep = new SpaceSheep(this, false);
		this.hud = new HUD(this, spaceSheep);
	}
}