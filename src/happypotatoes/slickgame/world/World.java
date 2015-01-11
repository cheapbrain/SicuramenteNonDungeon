package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import testingfisting.WorldGenTest;
import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.material.MaterialManager;

public class World {
	private Camera camera;

	private int[][] terrain;
	private List<Entity> entities = new ArrayList<Entity>();
	
	private int size = 10;

	public World(GameContainer container) {
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		terrain = new int[size][size];
		for (int y=0;y<size;y++)
			for (int x=0;x<size;x++)
				if (x==0||y==0||x==size-1||y==size-1)
					terrain[x][y] = 1;
		
		Entity player = new Player();
		camera = new Camera(container.getWidth(), container.getHeight(), 48, player);
		update(container, 0);
		add(player);
	}
	
	public void render(Graphics g) {
		g.scale(camera.getUnit(), camera.getUnit());
		g.translate(-camera.getX1(), -camera.getY1());
		Iterator<Entity> iterator = entities.iterator();
		Entity e = iterator.next();
		boolean checkEntities = true;
		for (int y=0;y<size;y++) {
			for (int x=0;x<size;x++)
				if (terrain[x][y]>0) {
					MaterialManager.getTexture(terrain[x][y]).draw(x, y, 1, 1);
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
	
	public boolean isWalkable(float x, float y) {
		return MaterialManager.getMaterial(terrain[(int)x][(int)y]).isWalkable();
	}
}
