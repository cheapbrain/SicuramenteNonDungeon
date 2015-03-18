package happypotatoes.slickgame;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Lighting {
	private GameContainer container;
	private Image render;
	private Graphics offg;
	public static int[][] terrain;
	private Image lightShape;
	
	List<Light> lights = new ArrayList<Light>();
	
	public Lighting(GameContainer container, int[][] terrain) {
		this.container = container;
		this.terrain = terrain;
		try {
			lightShape = new Image("res/light.png");
			lightShape.setFilter(Image.FILTER_LINEAR);
	        render = new Image(container.getWidth(), container.getHeight());
	        render.setFilter(Image.FILTER_LINEAR);
	        offg = render.getGraphics();
		} catch (SlickException e) {
			e.printStackTrace();
		}		
	}
	
	public void generate() {
		Graphics.setCurrent(offg);
		Camera.camera.applyTrasform(offg);
		offg.setDrawMode(Graphics.MODE_ADD);
		offg.setBackground(Color.gray);
        offg.clear();
		Color.white.bind();
		lightShape.bind();
        offg.flush();
	}
	
	public void render(Graphics g) {
        Graphics.setCurrent(g);
		GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_COLOR);
		render.draw(0, 0, container.getWidth(), container.getHeight());
		
		g.setDrawMode(Graphics.MODE_NORMAL);
	}
	
	public void addLight(Light light) {
		lights.add(light);
	}
}
