package kr.ac.kmu.gameproject.outskirt.boss;

import java.util.Random;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.enemy.Enemy;
import kr.ac.kmu.gameproject.outskirt.enemy.SpiralEnemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.FreeMoveBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;

public class SpiralBossMob extends SpiralEnemy {

	public SpiralBossMob(Game game, float startAngle, float radius, Color color, float life,
			float timing) {
		super(game, startAngle, color, life, timing);
		setRadius(radius);
	}

	@Override
	public void move() {
		localAngle += PApplet.PI / 30.f;
		oSprite.setRot(localAngle + PApplet.PI / -2.f);
		oSprite.setScale(1.0);
	}

	@Override
	public void looseLife(BasicBullet coll) {
	}
}
