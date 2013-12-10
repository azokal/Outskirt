package kr.ac.kmu.gameproject.outskirt.partition;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.SAXParseException;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.GameObject;
import kr.ac.kmu.gameproject.outskirt.enemy.Enemy;
import kr.ac.kmu.gameproject.outskirt.enemy.SquareEnemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import kr.ac.kmu.gameproject.outskirt.screen.Game.Color;
import kr.ac.kmu.gameproject.outskirt.screen.Game.EnemyType;
import processing.data.XML;

public class Partition {
	XML xml;
	Game game;
	XML export = new XML("OutSkirt");
	XML monsters = new XML("monsters");
	Map<String, XML> patterns;
	
	public Partition(App app, Game game, String name) {
	
		this.game = game;
		patterns = new HashMap<String, XML>();
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
				XML[] pattern = xml.getChildren("pattern");
				for (int i = 0; i < pattern.length; i++) {
					patterns.put(pattern[i].getString("id"), pattern[i]);
				}
				
				XML level = xml.getChild("level");

				pattern = level.getChildren("pattern");
				for (int i = 0; i < pattern.length; i++) {
					TreatXML(patterns.get(pattern[i].getString("id")), pattern[i].getFloat("timing"));
				}
				
			TreatXML(level, 0);
			game.endGame = level.getChild("endgame").getFloat("timing");
//				XML bullets = xml.getChild("bullets");
						
				
//				children = bullets.getChildren("bullet");
//				for (int i = 0; i < children.length; i++) {
//					new BasicBullet(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("life")], 
//							children[i].getFloat("life"), children[i].getFloat("timing"));
//				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void TreatXML(XML level, float timing) {
		XML[] children = level.getChild("monsters").getChildren("monster");
		for (int i = 0; i < children.length; i++) {
			game.EnnemyFactory(EnemyType.values()[children[i].getInt("type")], children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("color")],
					children[i].getFloat("life"), children[i].getFloat("timing") + timing);
		}
		children = level.getChild("monsters").getChildren("boss");
		for (int i = 0; i < children.length; i++) {	
			game.BossFactory(Game.BossType.values()[children[i].getInt("type")], children[i].getFloat("life"), children[i].getFloat("timing") + timing);
		}
		game.endGame = 0;
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
