package kr.ac.kmu.gameproject.outskirt.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.event.MouseInputListener;

import processing.core.PApplet;
import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.HUD;
import kr.ac.kmu.gameproject.outskirt.gameobject.SpaceSheep;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItem;
import kr.ac.kmu.gameproject.outskirt.menu.MenuItemGroup;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Quit;
import kr.ac.kmu.gameproject.outskirt.menu.mainmenu.Adventure;
import kr.ac.kmu.gameproject.outskirt.partition.Partition;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import kr.ac.kmu.gameproject.outskirt.screen.Game.EnemyType;

public class MainMenu implements Screen {

	final protected App app;
	
	final protected Screen bg;
	
	protected MenuItemGroup menuItemList;
	
	public MainMenu(App app) {
		this.app = app;
		bg = new GameBackground(app);
		menuItemList = new MenuItemGroup(app);
		//app.noCursor();
		menuItemList.add(new Adventure(app));
		menuItemList.add(new Quit(app));
		menuItemList.select(1);
		
	}


	@Override
	public void setup() {
		bg.setup();
	}
	
	@Override
	public void draw() {

		bg.draw();
		app.textAlign(app.CENTER, app.TOP);
		app.fill(255, 255, 255);
		app.text("Let's play !", 0, 0);
		menuItemList.draw();				
	}

	
}
