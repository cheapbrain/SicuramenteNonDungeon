package happypotatoes.slickgame.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.LightingBrutto;
import happypotatoes.slickgame.material.Material;
import happypotatoes.slickgame.material.MaterialManager;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.StupidEntity;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.worldgenerator.Generator;

public class World {
	public GameContainer container;
	private Camera camera;
	private LightingBrutto lighting;
	private int[][] terrainType;
	private int[][] terrain;
	private int size;
	private int maxdelay = 30;
	EntitySystem es = EntitySystem.getInstance();

	public int getSize() {
		return size;
	}
	
	public World(GameContainer container) {
		camera = Camera.camera;
		this.container = container;
		
		int x = 0, y = 0;
		
		try {
			MaterialManager.init();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Generator gen = new Generator();
		
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
		
		
				
		update(container, 0);
		System.out.println(es.getAll().size());
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
		
		lighting.calculateLights(terrainType, cw, ch, sx, sy, ex, ey);
		
		for (int y=sy;y<ey;y++)
			for (int x=sx;x<ex;x++)
				if (terrain[x][y]>0) {
					Material m = MaterialManager.getMaterial(terrain[x][y]);
					m.getTexture().draw(x, y, 1, 1, new Color(1f, 1f, 1f, lighting.lightMap[x-sx][y-sy]));
				}

		EntityRenderer.render(g);

		g.popTransform();
	}
	
	public void update(GameContainer container, int delta) {
		if (delta>maxdelay) delta = maxdelay;
		
		EntityRenderer.clear();
		es.update(this, delta);
		
		
		camera.update(delta);
	}

	public Entity getNearest(Entity e){
		Entity near = null;
		float dist = 1000;
		for(Entity tmp: es.getAll()){
			if(e.getDist(tmp)<dist && !tmp.equals(e)){
				dist = e.getDist(tmp);
				near = tmp;
			}
		}
		return near;
	}
		
	public void setLighting(LightingBrutto light){
		lighting = light;
	}
	public boolean isWalkable(float x, float y) {
		return MaterialManager.getMaterial(terrain[(int)x][(int)y]).isWalkable();
	}

	public void add(Entity e) {
		es.addEntity(e);
	}

	public void remove(Entity e) {
		es.removeEntity(e);
	}
}
