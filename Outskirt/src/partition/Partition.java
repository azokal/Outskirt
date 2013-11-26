package partition;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.xml.sax.SAXParseException;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import processing.data.XML;

public class Partition {
	XML xml;
	Game game;
	XML export = new XML("OutSkirt");
	XML monsters = new XML("monsters");
	
	public Partition(App app, Game game, String name) {
	
		this.game = game;
			  	File file = new File(name);
			  	if (file.exists() == false) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
			  	}
		try {
				xml = app.loadXML(name);
				XML monsters = xml.getChild("monsters");
//				XML bullets = xml.getChild("bullets");
				
						
				XML[] children = monsters.getChildren("monster");
				for (int i = 0; i < children.length; i++) {	
					Enemy e = new Enemy(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("color")], 
							children[i].getFloat("life"), children[i].getFloat("timing"));
	//				e.get
				}

//				children = bullets.getChildren("bullet");
//				for (int i = 0; i < children.length; i++) {
//					new BasicBullet(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("life")], 
//							children[i].getFloat("life"), children[i].getFloat("timing"));
//				}
		} catch (Exception e) {
		}
	}
	
	public void addEnemy(Enemy en) {
		XML child = new XML("monster");
  		child.setFloat("angle", en.getAngle());
  		child.setInt("color", en.getColor());
  		child.setFloat("life", en.getLife());
  		child.setFloat("timing", en.getTiming());
  		monsters.addChild(child);
	}
	
	public void exportMap(String name, App app) {
	  	File file = new File(name);
	  	if (file.exists() == true) {
			file.delete();
	  	}
		export.addChild(monsters);
  		app.saveXML(export, name);
	}
}
