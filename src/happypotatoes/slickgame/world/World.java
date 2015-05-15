package happypotatoes.slickgame.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.CustomRender;
import happypotatoes.slickgame.Light;
import happypotatoes.slickgame.LightingBello;
import happypotatoes.slickgame.LightingBrutto;
import happypotatoes.slickgame.material.Material;
import happypotatoes.slickgame.material.MaterialManager;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntityRenderer;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.RenderComponent;
import happypotatoes.slickgame.entitysystem.entity.ParticleBuilder;
import happypotatoes.slickgame.entitysystem.entity.StupidEntity;
import happypotatoes.slickgame.geom.Rectangle;
import happypotatoes.slickgame.worldgenerator.Generator;
import happypotatoes.slickgame.worldgenerator.Room;

public class World {
	public GameContainer container;
	private Camera camera;
	private LightingBello lighting;
	private int[][] terrainType;
	private int[][] terrain;
	private int[][] walls;
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
		
		List<Entity> mobs = gen.getAllMobs();
		for (Entity e:mobs)
			es.addEntity(e);
		
		int[][] terrain = gen.getTerrain();
		
		size=terrain.length;
		terrainType = terrain;
		this.terrain = new int[size][size];
		this.walls = new int[size][size];
		
		lighting = new LightingBello(terrainType);
		
		for (y=size-1;y>-1;y--)
			for (x=0;x<size;x++) {
				this.terrain[x][y] = x%3+(y%3)*3+MaterialManager.FLOOR+(int)Math.round(Math.random())*9;
				if(terrain[x][y]==0) {
					this.walls[x][y] = 0;
				} else 
					if(x==0||y==0||x==size-1||y==size-1)
						this.walls[x][y] = 0;
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
								value += 7;
							else if (terrain[x+1][y-1]==0)
								value += 8;
							else if (terrain[x-1][y+1]==0)
								value += 5;
							else if (terrain[x+1][y+1]==0)
								value += 6;
						}
						
						this.walls[x][y] = value+MaterialManager.WALLS;
					}		
				}			
		terrain = this.terrain;
		
		

		update(container, 0);
	}
		
	Queue<RenderComponent> renderqueue = new LinkedBlockingQueue<RenderComponent>();
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
		if (ex>=size) ex = size-1;
		if (ey>=size) ey = size-1;
		
		float[][] lightMap = lighting.calculate();
		
		for (int y=sy;y<ey;y++)
			for (int x=sx;x<ex;x++)
				if (terrain[x][y]>0) {
					Material m = MaterialManager.getMaterial(terrain[x][y]);
					if (m.getOffset()==0) {
						CustomRender.draw(m.getTexture(), x, y+m.getOffset(), 1, m.getHeight(),
								lightMap[x][y],
								lightMap[x][y+1],
								lightMap[x+1][y+1],
								lightMap[x+1][y]);
					}
				}
		
		
		List<RenderComponent> sprites = EntityRenderer.getTaskes(g);
		renderqueue.clear();
		for (RenderComponent sprite:sprites) {
			renderqueue.add(sprite);
		}
		
		for (int y=sy;y<ey;y++) {
			while (!renderqueue.isEmpty()) {
				RenderComponent sprite = renderqueue.peek();
				if (sprite.depth< y+1) {
					EntityRenderer.renderTask(sprite);
					renderqueue.poll();
				} else 
					break;
			}
			
			for (int x=sx;x<ex;x++) 
				if (walls[x][y]>0) {
					Material m = MaterialManager.getMaterial(walls[x][y]);
					if (m.getOffset()<0) {
						if (m.getHeight()==1) {
							CustomRender.draw(m.getTexture(), x, y+m.getOffset(), 1, m.getHeight(),
									lightMap[x][y],
									lightMap[x][y+1],
									lightMap[x+1][y+1],
									lightMap[x+1][y]);
						} else {
							CustomRender.draw(m.getTexture().getSubImage(0, 0, 64, 64), x, y+m.getOffset(), 1, 1,
									lightMap[x][y],
									lightMap[x][y+1],
									lightMap[x+1][y+1],
									lightMap[x+1][y]);
							CustomRender.draw(m.getTexture().getSubImage(0, 64, 64, 64), x, y+m.getOffset()+1, 1, 1,
									lightMap[x][y+1],
									lightMap[x][y+1],
									lightMap[x+1][y+1],
									lightMap[x+1][y+1]);
						}
					}
					
				}
		}
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
	
	public boolean castRay(float x1, float y1, float x2, float y2) {
		if ((int)x1==(int)x2&&(int)y1==(int)y2)
			return true;
		float dx = x2-x1;
		float dy = y2-y1;
		float d = (float)Math.sqrt(dx*dx+dy*dy)+0.0000001f;
		
		int ix, iy;
		float sdx, sdy;
		if (dx>0) {
			ix = +1;
			sdx = (int)x1+1-x1;
		} else {
			ix = -1;
			sdx = x1-(int)x1;
		}
		if (dy>0) {
			iy = +1;
			sdy = (int)y1+1-y1;
		} else {
			iy = -1;
			sdy = y1-(int)y1;
		}
		
		float stepx = d/Math.abs(dx);
		float stepy = d/Math.abs(dy);
		
		float countx = stepx*sdx;
		float county = stepy*sdy;
		
		int x = (int)x1;
		int y = (int)y1;
				
		while(true) {
			if (terrainType[x][y]==1)
				return false;
			if (countx>=d&&county>=d)
				return true;
			
			if (countx<county) {
				x += ix;
				countx += stepx;
			} else {
				y += iy;
				county += stepy;
			}
		}
	}
	
	public boolean isWalkable(float x, float y) {
		return terrainType[(int)x][(int)y]==0;
	}

	public void add(Entity e) {
		es.addEntity(e);
	}

	public void remove(Entity e) {
		es.removeEntity(e);
	}
}
