package kr.ac.kmu.gameproject.outskirt.gameobject;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class TextObject extends GameObject {
	float timing = 0;
	float endAff = 0;
	float startTime = 0;
	String text;
	boolean isPop;

	public TextObject(Game game, String text, float timing, float endAff) {
		super(game);
		this.timing = timing;
		this.endAff = endAff;
		this.text = text;
	}

	@Override
	public void draw() {
		if (game.pop < timing && isPop == false)
			return;
		else if (isPop == false) {
			isPop = true;
			startTime = game.pop;
		}
		if (game.pop - startTime < endAff)
			game.getApp().text(text, 0, -200);
		else
			kill();
	}

}
