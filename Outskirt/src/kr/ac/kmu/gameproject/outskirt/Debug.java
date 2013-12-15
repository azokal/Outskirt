package kr.ac.kmu.gameproject.outskirt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Debug extends LinkedHashMap<String, Object> implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	App app;
	boolean on = true;

	public Debug(App app) {
		this.app = app;
		app.addKeyListener(this);
	}

	public void draw() {
		if (on) {
			StringBuilder str = new StringBuilder();
			str.append("DEBUG CONSOLE" + System.lineSeparator());
			for (Entry<String, Object> line : this.entrySet()) {
				str.append(line.getKey() + ":" + line.getValue()
						+ System.lineSeparator());
			}
			app.pushStyle();
			app.color(255);
			app.textAlign(App.LEFT, App.TOP);
			app.text(str.toString(), 0, 0);
			app.popStyle();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == 'd') {
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
