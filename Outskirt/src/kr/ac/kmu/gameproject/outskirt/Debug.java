package kr.ac.kmu.gameproject.outskirt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Debug extends LinkedHashMap<String, Object> implements KeyListener {

	Game game;
	boolean on = true;

	public Debug(Game game) {
		this.game = game;
		game.addKeyListener(this);
	}
	
	public void draw() {
		if (on) {
			StringBuilder str = new StringBuilder();
			str.append("DEBUG CONSOLE" + System.lineSeparator());
			for (Entry<String, Object> line : this.entrySet()) {
				str.append(line.getKey() + ":" + line.getValue() + System.lineSeparator());
			}
			game.pushStyle();
			game.color(255);
			game.textAlign(game.LEFT, game.TOP);
			game.text(str.toString(), 0, 0);
			game.popStyle();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'd') {
			on = !on;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}
