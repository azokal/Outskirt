package kr.ac.kmu.gameproject.outskirt.life;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public abstract class Life extends GameObject {
	protected float life;
	
	public Life(Game game, float maxLife) {
		super(game);
		life = maxLife;
	}

	abstract public void looseLife(BasicBullet coll);
}
