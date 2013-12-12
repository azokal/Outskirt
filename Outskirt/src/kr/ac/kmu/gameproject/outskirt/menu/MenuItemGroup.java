package kr.ac.kmu.gameproject.outskirt.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.screen.Screen;

import processing.core.PApplet;

public class MenuItemGroup extends Vector<MenuItem> implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int selectedIndex = -1;
	
	private App app;
	
	public MenuItemGroup(App app) {
		this.app = app;
		app.addMouseListener(this);
		app.addMouseMotionListener(this);
	}
	
	public MenuItem getSelectedItem() {
		return get(selectedIndex);
	}
	
	public void draw() {
		int nbItem = this.size();
		int x;
		int y;
		float circlepart = App.PI * 2 / nbItem;
		for (int i = 0; i < nbItem; i++) {
			x = (int) (App.toX(250, circlepart * i - App.PI / 2));
			y = (int) (App.toY(250, circlepart * i - App.PI / 2));
			app.pushMatrix();
			app.translate(x, y);
			this.get(i).draw();
			app.popMatrix();
		}
	}

	public void selectAll() {
		for (MenuItem item : this){
			item.select();
		}
	}
	
	public void unselectAll() {
		for (MenuItem item : this){
			item.unselect();
		}
	}
	
	public void select(int index) {
		unselectAll();
		selectedIndex = index;
		this.get(index).select();
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		float mouseX = e.getX() - app.displayWidth / 2;
		float mouseY = e.getY() - app.displayHeight / 2;
		float angle = App.toAngle(mouseX, mouseY);

		float circlepart = App.PI * 2 / this.size();
		int indexMenuSelected = (int)(((angle + App.PI) * App.RAD_TO_DEG) / (circlepart * App.RAD_TO_DEG));
		app.getDebug().put("rad", (angle + App.PI) * App.RAD_TO_DEG + "/" + circlepart * App.RAD_TO_DEG);
		app.getDebug().put("index", indexMenuSelected);
		this.select(indexMenuSelected);

	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if (app.mousePressed && app.mouseButton == PApplet.LEFT) {
			this.getSelectedItem().exec();
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
			
}
