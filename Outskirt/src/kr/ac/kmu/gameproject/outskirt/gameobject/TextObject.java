package kr.ac.kmu.gameproject.outskirt.gameobject;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public class TextObject extends GameObject {
	float timing = 0;
	float endAff = 0;
	float startTime = 0;
	int size = 0;
	String text;
	boolean isPop;

	public TextObject(Game game, String text, int size, float timing,
			float endAff) {
		super(game);
		this.timing = timing;
		this.endAff = endAff;
		this.text = text;
		this.size = size;
	}

	@Override
	public void draw() {
		if (game.tpop.getTotalTime() < timing && isPop == false)
			return;
		else if (isPop == false) {
			isPop = true;
			startTime = game.pop;
		}
		if (game.tpop.getTotalTime() - startTime < endAff) {
			game.getApp().pushStyle();
			game.getApp().textSize(size);
			game.getApp().text(text, 0, -200);
			game.getApp().popStyle();
		} else
			kill();
	}

}
