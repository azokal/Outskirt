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
				app.getDebug().put("child lenght :", children.length);
				for (int i = 0; i < children.length; i++) {
					new Enemy(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("color")], 
							children[i].getFloat("life"), children[i].getFloat("timing"));
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
  		child.addChild("angle");
  		child.getChild("angle").setFloatContent(en.getAngle());
  		child.addChild("color");
  		child.getChild("color").setIntContent(en.getColor());
  		child.addChild("life");
  		child.getChild("life").setFloatContent(en.getLife());
  		child.addChild("timing");
  		child.getChild("timing").setFloatContent(en.getTiming());
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
