package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

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
	private int[][] terrain;
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

		terrain = gen.terrain;
		size=terrain.length;
		
		quadtree = new Quadtree(10, 5, null, new Rectangle(0, 0, size, size));
		

		int x = 0, y = 0;
					


		Entity player = new Player();
		player.setPosition(2+.5f, 2+.5f);
		add(player);
		
		int count = 1;
		for (y=0;y<size;y++)
			for (x=0;x<size;x++) 
				if (terrain[x][y]==0){
					if (Math.random()>.90) {
						Entity dummy = new Meuwse();
						dummy.setPosition(x+.5f, y+.5f);
						add(dummy);
						count++;
					}
				}
		System.out.println(count);
		System.out.println(count*count/2);

		//add trappole
		for(int i=0; i<gen.allTraps.size(); i++){
			add(gen.allTraps.get(i));
		}
		
		
		camera = new Camera(container.getWidth(), container.getHeight(), 64, player);
		update(container, 0);
	}
	
	boolean renderquad = false;
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
		
		if (renderquad)
			quadtree.render(g);

		List<Entity> renderizable = new ArrayList<Entity>();
		quadtree.getRenderizable(camera.getRekt(), renderizable);
		for(Entity e:renderizable)
			e.render();
	}
	
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_UP))
			camera.scale(1.5f);
		if (input.isKeyPressed(Input.KEY_DOWN))
			camera.scale(1/1.5f);
		if (input.isKeyPressed(Input.KEY_TAB))
			renderquad = !renderquad;
		
		if (delta>maxdelay) delta = maxdelay;
		
		quadtree.collisions(null);

		quadtree.update(container, this, delta);
		
		EntityCommand c;
		while((c = eCommands.poll())!=null) {
			switch(c.action) {
				case EntityCommand.ADD:
					quadtree.add(c.e);
					break;
				case EntityCommand.REMOVE:
					quadtree.remove(c.e);
					break;
				case EntityCommand.REFRESH:
					break;
					
			}
		}
		
		
		camera.update(delta);
	}
	
	public void add(Entity e) {
		eCommands.add(new EntityCommand(e, EntityCommand.ADD));
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
