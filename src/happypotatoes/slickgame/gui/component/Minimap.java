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
		Minimap.explored = new float[150][150];
		Minimap.player = player;
		if (instance!=null) {
			return;
		}
		
		try {
			fog = new Image(world.getSize(),world.getSize());
			gfog = fog.getGraphics();
		} catch(Exception e) {}
		
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
					if (lights[x+1][y]==0) i = 0;
					if (lights[x+1][y+1]==0) i = 0;
					if (lights[x][y+1]==0) i = 0;
					if (i>0) explored[x][y] = .7f;
					i = explored[x][y];
					
					if (i>0) {
						c.a = i;
						gfog.setColor(c);
						gfog.fillRect(x, y, 1, 1);
					}
				}
			
			gfog.flush();
			Graphics.setCurrent(g);
		}
		
		int x = (int)player.x;
		int y = (int)player.y;

		
		int sx = (int) (x-25);
		int sy = (int) (y-25);
		int sw = 50;
		int sh = 50;
		

		fog.getSubImage(sx, sy, sw, sh).draw(0, 0, 200, 200);

		
		g.setColor(Color.red);
		g.fillRect((x-sx)*scale, (y-sy)*scale, scale, scale);
		
		
	}
	public static Minimap getInstance() {
		return instance;
	}
}
