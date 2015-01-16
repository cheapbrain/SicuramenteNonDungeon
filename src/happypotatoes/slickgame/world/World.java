package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import testingfisting.WorldGenTest;
import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.material.Material;
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
		
<<<<<<< HEAD
		terrain[6][4] = 1;

=======
>>>>>>> origin/master
		Entity player = new Player();
		camera = new Camera(container.getWidth(), container.getHeight(), 64, player);
		update(container, 0);
		add(player);
	}
	
	public void render(Graphics g) {
		float unit = camera.getUnit();
		float cx = (int)(camera.getX1()*unit)/unit;
		float cy = (int)(camera.getY1()*unit)/unit;
		g.scale(unit, unit);
		g.translate(-cx, -cy);
		Iterator<Entity> iterator = entities.iterator();
		Entity e = iterator.next();
		boolean checkEntities = true;
		for (int y=0;y<size;y++) {
			for (int x=0;x<size;x++)
				if (terrain[x][y]>-1) {
					Material m = MaterialManager.getMaterial(terrain[x][y]);
					m.getTexture().draw(x, y+m.getOffset(), 1, m.getHeight());
					
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
		if (entities.isEmpty())
			entities.add(e);
		else {
			float y = e.getY();
			for (int i=0;i<entities.size();i++)
				if (entities.get(i).getY()>y) {
					entities.add(i, e);
					return;
				}
			entities.add(e);
		}
	}
	
	public void move(Entity e) {
		
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public boolean isWalkable(float x, float y) {
		return MaterialManager.getMaterial(terrain[(int)x][(int)y]).isWalkable();
	}
}
