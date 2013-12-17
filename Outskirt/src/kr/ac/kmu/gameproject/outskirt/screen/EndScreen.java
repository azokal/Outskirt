package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import processing.core.PApplet;
import sprites.S4P;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Adventure;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Quit;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Random;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.SelectLevel;

public class EndScreen implements Screen, MouseListener {

	final protected App app;
	final protected Game game;

	final protected GameBackground bg;
	boolean isRelease = false;

	public EndScreen(App app, Game game) {
		this.app = app;
		this.game = game;
		bg = new GameBackground(app);
		this.game.getSpaceSheep().unregisterEvent();
		app.cursor();
		app.addMouseListener(this);
	}

	public void setup() {
		bg.setup();
	}

	@Override
	public void draw() {
		bg.draw();
		app.pushStyle();
		app.textAlign(PApplet.CENTER, PApplet.CENTER);
		app.fill(0, 144, 0);
		app.textSize(32);
		app.text("Game Over\nYour score is:\n", 0, -128);
		app.fill(0, 255, 0);
		app.textSize(128);
		app.text(String.format("%.0f", game.getSpaceSheep().getScore()), 0, 0);
		app.fill(0, 144, 0);
		// app.textSize(32);
		// app.text("Press enter to go back to menu!", 0, 128);
		if (app.keyPressed || isRelease == true) {
			this.unload();
			MainMenu screen = new MainMenu(app);
			screen.setup();
			app.setScreen(screen);
		}
		app.popStyle();
	}

	@Override
	public void unload() {
		S4P.resetWorld();
		this.game.unload();
		this.bg.unload();
		app.removeMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isRelease = true;
	}

}
