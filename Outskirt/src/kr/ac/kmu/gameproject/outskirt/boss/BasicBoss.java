package kr.ac.kmu.gameproject.outskirt.boss;

import java.util.ArrayList;

import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.life.Life;
import kr.ac.kmu.gameproject.outskirt.screen.Game;

public abstract class BasicBoss extends Life {
	GameObject[] bossPart;
	
	public BasicBoss(Game game, float maxLife) {
		super(game, maxLife);
		// TODO Auto-generated constructor stub
	}
}


