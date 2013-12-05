package kr.ac.kmu.gameproject.outskirt.life;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public abstract class Life extends GameObject {
	protected float life;
	protected float maxLife;
	
	public Life(Game game, float maxLife) {
		super(game);
		life = maxLife;
		this.maxLife = life;
	}

	abstract public void looseLife(BasicBullet coll);
	
	public float getLifeMax() {
		return maxLife;
	}
	public float getCurrentLife() {
		return life;
	}
}
