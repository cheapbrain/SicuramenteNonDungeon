package happypotatoes.slickgame.world;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.entity.Dummy;
import happypotatoes.slickgame.entity.Entity;
import happypotatoes.slickgame.entity.Meuwse;
import happypotatoes.slickgame.entity.NyanCat;
import happypotatoes.slickgame.entity.Player;
import happypotatoes.slickgame.material.Material;
import happypotatoes.slickgame.material.MaterialManager;
import happypotatoes.slickgame.worldgenerator.Generator;

public class World {
	private Camera camera;
	private int scale=1;
	private int[][] terrain;
	private List<Entity> entities = new LinkedList<Entity>();
	private Quadtree quadtree;
	private Queue<EntityCommand> eCommands = new LinkedBlockingQueue<EntityCommand>();
	
	private int size;
	private int maxdelay = 30;

	public World(GameContainer container) {
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Generator gen = new Generator();
		size=gen.height*gen.corrWidth;
		
		terrain = gen.terrain;
		
		int x = 0, y = 0;
		for (y=0;y<gen.height;y++)
			for (x=0;x<gen.width;x++)
				if (terrain[x][y]==0)
					break;

		System.out.println(x+" "+y);

		Entity player = new Player();
		player.setPosition(x+.5f, y+.5f);
		add(player);
				
		Entity cat = new NyanCat();
		cat.setPosition(x+.5f, y+.5f);
		add(cat);
		
		Entity dummy = new Dummy();
		dummy.setPosition(x+.5f, y+2.5f);
		add(dummy);

		dummy = new Dummy();
		dummy.setPosition(x+.5f, y+1.5f);
		add(dummy);

		dummy = new Dummy();
		dummy.setPosition(x+.5f, y+3.5f);
		add(dummy);

		Entity mouse = new Meuwse();
		mouse.setPosition(x+.5f, y-1f);
		add(mouse);
		

		camera = new Camera(container.getWidth(), container.getHeight(), 64/scale, player);
		update(container, 0);
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
		if (delta>maxdelay) delta = maxdelay;
		
		for (Entity e:entities)
			e.update(container, this, delta);
		
		EntityCommand c;
		while((c = eCommands.poll())!=null) {
			switch(c.action) {
				case EntityCommand.ADD:
					addToList(c.e);
					break;
				case EntityCommand.REMOVE:
					entities.remove(c.e);
					break;
				case EntityCommand.REFRESH:
					int i = entities.indexOf(c.e);
					float y = c.e.getY();
					float y1 = i+1<entities.size()?entities.get(i+1).getY():Integer.MAX_VALUE;
					float y2 = i>0?entities.get(i-1).getY():0;
					if (y>y1||y<y2) {
						entities.remove(c.e);
						addToList(c.e);
					}
					
			}
		}
		
		
		camera.update(delta);
	}
	
	public void add(Entity e) {
		eCommands.add(new EntityCommand(e, EntityCommand.ADD));
	}
	
	private void addToList(Entity e) {
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
		eCommands.add(new EntityCommand(e, EntityCommand.REFRESH));
	}
	
	public void remove(Entity e) {
		eCommands.add(new EntityCommand(e, EntityCommand.REMOVE));
	}
	
	public boolean isWalkable(float x, float y) {
		return MaterialManager.getMaterial(terrain[(int)x][(int)y]).isWalkable();
	}
}
