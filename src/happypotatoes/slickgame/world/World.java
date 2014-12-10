package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;

public class World {
	private Camera camera;

	private int[][] terrain;
	private List<Entity> entities = new ArrayList<Entity>();

	public World(GameContainer container) {
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		terrain = new int[20][20];
		for (int y=0;y<10;y++)
			for (int x=0;x<10;x++)
				terrain[x][y] = (int) Math.round(Math.random());

		Entity player = new Player();
		camera = new Camera(container.getWidth(), container.getHeight(), 64, player);
		update(container, 0);
		add(player);
	}
	
	public void render(Graphics g) {
		g.scale(camera.getUnit(), camera.getUnit());
		g.translate(-camera.getX1(), -camera.getY1());
		Iterator<Entity> iterator = entities.iterator();
		Entity e = iterator.next();
		boolean checkEntities = true;
		for (int y=0;y<10;y++) {
			for (int x=0;x<10;x++)
				if (terrain[x][y]>0) {
					MaterialManager.getTexture(terrain[x][y]).draw(x, y, 1, 1);
					MaterialManager.getTexture(terrain[x][y]).draw(x, y-camera.getViewAngle(), 1, 1);
				}
				
			while (checkEntities) {
				if (e.getY()<y+1) {
					e.render();
					if (iterator.hasNext())
						e = iterator.next();
					else {
						e =  null;
						checkEntities = false;
					}
				} else
					checkEntities = false;
			}
			checkEntities = e!=null;
		}
	}
	
	public void update(GameContainer container, int delta) {
		
		for (Entity e:entities)
			e.update(container, this, delta);
		
		camera.update(delta);
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
}
