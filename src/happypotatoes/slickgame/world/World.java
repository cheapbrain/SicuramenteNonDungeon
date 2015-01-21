package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.material.Material;
import happypotatoes.slickgame.material.MaterialManager;
import happypotatoes.slickgame.worldgenerator.WorldGenTest;

public class World {
	private Camera camera;

	private int[][] terrain;
	private List<Entity> entities = new ArrayList<Entity>();
	
	private int size = 100;

	public World(GameContainer container) {
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		WorldGenTest gen = new WorldGenTest();
		
		terrain = gen.terrain;
		
		int x = 0, y = 0;
		for (y=0;y<gen.height;y++)
			for (x=0;x<gen.width;x++)
				if (terrain[x][y]==0)
					break;

		Entity player = new Player();
		System.out.println(x+" "+y);
		player.setPosition(x+.5f, y+.5f);
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

		int sx = (int)camera.getX1();
		int sy = (int)camera.getY1();
		int ex = (int)camera.getX2()+1;
		int ey = (int)camera.getY2()+1;
		if (sx<0) sx = 0;
		if (sy<0) sy = 0;
		if (ex>size) ex = size;
		if (ey>size) ey = size;
		
		for (int y=sy;y<ey;y++)
			for (int x=sx;x<ex;x++)
				if (terrain[x][y]>-1) {
					Material m = MaterialManager.getMaterial(terrain[x][y]);
					m.getTexture().draw(x, y, 1, 1);
				}

		for (Entity e:entities)
			e.render();
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
