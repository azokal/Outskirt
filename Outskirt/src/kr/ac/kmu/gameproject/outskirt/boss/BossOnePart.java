package kr.ac.kmu.gameproject.outskirt.boss;

import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BossOnePart extends Life{
	Game.Color color;
	BossOne owner;
	
	public BossOnePart(Game game, float maxLife, BossOne owner, Game.Color color) {
		super(game, maxLife);
		this.color = color;
		this.owner = owner;
		oSprite = new sprites.Sprite(game.getApp(),game.pathSprites+"squareGrid.png", 3, 1, 10);
		oSprite.setScale(3);
		this.setPolar(50, 0);
		if (color == Game.Color.RED) {
			oSprite.setFrame(1);
			this.setPolar(50, 120);
		}
		if (color == Game.Color.CYAN) {
			oSprite.setFrame(2);
			this.setPolar(50, 240);
		}
	}

	@Override
	public void looseLife(BasicBullet coll) {
		if (color == coll.color)
			coll.power /= 2;
		this.owner.looseLife(coll);
	}

	@Override
	public void draw() {
		
	}

}
