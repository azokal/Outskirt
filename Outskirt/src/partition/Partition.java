package partition;

import kr.ac.kmu.gameproject.outskirt.App;
import kr.ac.kmu.gameproject.outskirt.gameobject.Enemy;
import kr.ac.kmu.gameproject.outskirt.gameobject.bullet.BasicBullet;
import kr.ac.kmu.gameproject.outskirt.screen.Game;
import processing.data.XML;

public class Partition {
	XML xml;
	Game game;
	
	Partition(App app, Game game, String name) {
	
		this.game = game;
		xml = app.loadXML(name);
		XML monsters = xml.getChild("monsters");
//		XML bullets = xml.getChild("bullets");
		
				
		XML[] children = monsters.getChildren("monster");
		for (int i = 0; i < children.length; i++) {
			new Enemy(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("life")], 
					children[i].getFloat("life"), children[i].getFloat("timing"));
		}

//		children = bullets.getChildren("bullet");
//		for (int i = 0; i < children.length; i++) {
//			new BasicBullet(game, children[i].getFloat("angle"), Game.Color.values()[children[i].getInt("life")], 
//					children[i].getFloat("life"), children[i].getFloat("timing"));
//		}
	}
	
}
