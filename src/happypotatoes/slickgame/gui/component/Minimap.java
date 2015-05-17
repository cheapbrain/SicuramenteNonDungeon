package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.gui.Window;
import happypotatoes.slickgame.world.World;

public class Minimap extends Window{
	private static Minimap instance;
	private static Entity player;
	private static int worldSize;
	public Minimap(World world, Entity player){
		super("Minimap", 0, 50, 200, 200);
		this.player = player;
		try {
			background = new Image(world.getSize(),world.getSize());
			Graphics g = background.getGraphics();
			worldSize = world.getSize();
			for(int i=0; i<worldSize; i++){
				for(int j=0; j<worldSize; j++){
					if(world.isWalkable(i, j))
						g.setColor(Color.white);
					else
						g.setColor(Color.red);
					g.fillRect(i, j, 1, 1);
				}
			}
			g.flush();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setVisible(true);
		instance = this;
	}
	protected void paintComponent(Graphics g) {
		int x = (int) player.x*width/worldSize;
		int y = (int) player.y*height/worldSize;
		background.draw(0,0,width,height);
		g.setColor(Color.blue);
		g.drawRect(x, y+1, .5f, .5f);
	}
	public static Minimap getInstance() {
		return instance;
	}
}
