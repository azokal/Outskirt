package kr.ac.kmu.gameproject.outskirt.menu;

import kr.ac.kmu.gameproject.outskirt.App;
import processing.core.PApplet;

abstract public class MenuItem {

	protected String text;
	protected boolean selected;
	protected App app;

	public MenuItem(App app, String text, boolean selected) {
		this.selected = selected;
		this.text = text;
		this.app = app;
	}

	public MenuItem(App app, String text) {
		this(app, text, false);
	}

	public abstract void exec();

	public void draw() {
		app.pushStyle();
		app.textAlign(PApplet.CENTER, PApplet.TOP);
		// app.setFont(f);
		app.textSize(75);
		if (selected) {
			app.fill(0, 255, 0);
		} else {
			app.fill(0, 144, 0);
		}
		app.text(text, 0, 0);
		app.popStyle();
	}

	public boolean isSelected() {
		return selected;
	}

	public void select() {
		selected = true;
	}

	public void unselect() {
		selected = false;
	}

}
