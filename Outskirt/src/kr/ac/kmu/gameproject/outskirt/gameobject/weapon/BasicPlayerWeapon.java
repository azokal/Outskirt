package kr.ac.kmu.gameproject.outskirt.gameobject.weapon;

import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class BasicPlayerWeapon extends BasicWeapon {

	public BasicPlayerWeapon(Game game, SpaceSheep owner, Game.Color color) {
		super(game, owner, color);
	}

	public void draw() {
		if (game.getApp().isPressed('q')) {
			color = Game.Color.GREEN;
			((SpaceSheep) owner).setGreen();
		}
		if (game.getApp().isPressed('w')) {
			color = Game.Color.RED;
			((SpaceSheep) owner).setRed();
		}
		if (game.getApp().isPressed('e')) {
			color = Game.Color.CYAN;
			((SpaceSheep) owner).setBlue();
		}
	}
}
