package happypotatoes.slickgame.world;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entity2.Entity;
import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Light;
import happypotatoes.slickgame.LightingBrutto;
import happypotatoes.slickgame.material.Material;
import happypotatoes.slickgame.material.MaterialManager;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.entity.Player;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.worldgenerator.Generator;

public class World {
	public GameContainer container;
	private Camera camera;
	private LightingBrutto lighting;
	private int[][] terrainType;
	private int[][] terrain;
	//private Quadtree quadtree;
	private Queue<EntityCommand> eCommands = new LinkedBlockingQueue<EntityCommand>();
	
	private int size;
	private int maxdelay = 30;
	
	private Entity player;

	public World(GameContainer container) {
		this.container = container;
		System.out.println(Math.atan2(.1, -1));
		System.out.println(Math.atan2(1, .1));
		System.out.println(Math.atan2(.1, 1));
		System.out.println(Math.atan2(-1, .1));
		int x = 0, y = 0;
		
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Generator gen = new Generator();

		camera = new Camera(container.getWidth(), container.getHeight(), 64, null);
		
		int[][] terrain = gen.getTerrain();
		
		size=terrain.length;
		terrainType = terrain;
		this.terrain = new int[size][size];
		
		for (y=size-1;y>-1;y--)
			for (x=0;x<size;x++) 
				if(terrain[x][y]==0)
					this.terrain[x][y] = x%3+(y%3)*3+MaterialManager.FLOOR+(int)Math.round(Math.random())*9;
				else 
					if(x==0||y==0||x==size-1||y==size-1)
						this.terrain[x][y] = 0;
					else {
						int value = 0;
						if (terrain[x-1][y]==1)
							if (terrain[x+1][y]==0)
								value += 2;
							else
								value++;
						
						if (terrain[x][y-1]==1)
							if (terrain[x][y+1]==0)
								value += 6;
							else
								value += 3;
						
						if (value==4) {
							if (terrain[x-1][y-1]==0)
								value += 8;
							else if (terrain[x+1][y-1]==0)
								value += 9;
							else if (terrain[x-1][y+1]==0)
								value += 10;
							else if (terrain[x+1][y+1]==0)
								value += 11;
							else
								value = -MaterialManager.WALLS;
						}
						
						this.terrain[x][y] = value+MaterialManager.WALLS;
					}
					
		terrain = this.terrain;
		lighting = new LightingBrutto();
				
		player = Player.create();
		player.x = 2.5f;
		player.y = 2.5f;
				
		camera.setTarget(player);
		lighting.add(new Light(player, 0, 0, 10, 1f));
		lighting.add(new Light(2, 2, 10, 1f));
		lighting.add(new Light(15, 2, 10, 1f));


		update(container, 0);
	}
	
	boolean renderquad = false;
	public void render(Graphics g) {

		g.pushTransform();
		camera.applyTrasform(g);
		
		Rectangle rect = camera.getRekt();

		int sx = (int)rect.x0;
		int sy = (int)rect.y0;
		int ex = (int)Math.ceil(rect.x1);
		int ey = (int)Math.ceil(rect.y1);
		int cw = ex-sx;
		int ch = ey-sy;
		if (sx<0) sx = 0;
		if (sy<0) sy = 0;
		if (ex>size) ex = size;
		if (ey>size) ey = size;
		
		float[][] lightMap = lighting.calculateLights(terrainType, cw, ch, sx, sy, ex, ey);
		
		for (int y=sy;y<ey;y++)
			for (int x=sx;x<ex;x++)
				if (terrain[x][y]>0) {
					Material m = MaterialManager.getMaterial(terrain[x][y]);
					m.getTexture().draw(x, y, 1, 1, new Color(1f, 1f, 1f, lightMap[x-sx][y-sy]));
				}

		EntityRenderer.render();

		g.popTransform();
	}
	
	public void update(GameContainer container, int delta) {
				
		if (delta>maxdelay) delta = maxdelay;
		player.update(this, delta);
		
		EntityCommand c;
		while((c = eCommands.poll())!=null) {
			switch(c.action) {
				case EntityCommand.ADD:
					break;
				case EntityCommand.REMOVE:
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
