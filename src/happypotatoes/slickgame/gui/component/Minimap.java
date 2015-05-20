package happypotatoes.slickgame.gui.component;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.LightingBello;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.AI;
import happypotatoes.slickgame.gui.Window;
import happypotatoes.slickgame.world.World;

public class Minimap extends Window{
	private static int scale = 4;
	private static float[][] explored;
	private static Image fog;
	private static Graphics gfog;
	private static Minimap instance;
	private static Entity player;
	private static int worldSize;
	public Minimap(World world, Entity player){
		super("Minimap", 20, 50, 200, 200);
		Minimap.player = player;
		try {
			explored = new float[150][150];
			fog = new Image(world.getSize()*scale+2,world.getSize()*scale+2);
			fog.setFilter(Image.FILTER_LINEAR);
			gfog = fog.getGraphics();
			int a = gfog.MODE_ADD;
			background = new Image(world.getSize()*scale,world.getSize()*scale);
			Graphics g = background.getGraphics();
			g.clearAlphaMap();
			worldSize = world.getSize();
			for(int i=1; i<worldSize-1; i++){
				for(int j=1; j<worldSize-1; j++){
					if(world.isWalkable(i, j)) {
						g.setColor(Color.darkGray);
						g.fillRect(i*scale, j*scale, scale, scale);
					}
					else {
						g.setColor(Color.white);
						if (world.isWalkable(i-1, j)) {
							g.drawLine(i*scale, j*scale, i*scale+1, j*scale+scale);
						}
						if (world.isWalkable(i+1, j)) {
							g.drawLine(i*scale+scale-1, j*scale, i*scale+scale, j*scale+scale);
						}
						if (world.isWalkable(i, j-1)) {
							g.drawLine(i*scale, j*scale, i*scale+scale, j*scale+1);
						}
						if (world.isWalkable(i, j+1)) {
							g.drawLine(i*scale, j*scale+scale-1, i*scale+scale, j*scale+scale);
						}
					}
				}
			}
			g.flush();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setVisible(true);
		instance = this;
	}
	
	long time;
	long delay = 500;
	long oldtime = 0;
	protected void paintComponent(Graphics g) {
		long newtime = System.currentTimeMillis();
		time += newtime-oldtime;
		oldtime = newtime;
		if (time>delay) {
			time = 0;
			Graphics.setCurrent(gfog);
			gfog.clear();
			gfog.clearAlphaMap();
			
			float[][] lights = LightingBello.lighting.lightMap;
			Color c = new Color(1, 1, 1, 1f);

			for (int x=0;x<150;x++) 
				for (int y=0;y<150;y++) {
					float i = lights[x][y];
					if (i>0) explored[x][y] = .7f;
					i = explored[x][y];
					
					if (i>0) {
						c.a = i;
						gfog.setColor(c);
						gfog.fillRect(x*scale, y*scale, scale, scale);
					}
				}
			
			gfog.flush();
			Graphics.setCurrent(g);
		}
		
		float x = player.x;
		float y = player.y;

		
		int sx = (int) (x*scale-100);
		int sy = (int) (y*scale-100);
		int sw = 200;
		int sh = 200;
		

		fog.getSubImage(sx, sy, sw+2, sh+2).draw(-2, -2);
		//g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		//background.getSubImage(sx, sy, sw, sh).draw(0, 0);
		//g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.red);
		g.fillRect(100-2, 100-2, 4, 4);
	}
	public static Minimap getInstance() {
		return instance;
	}
}
