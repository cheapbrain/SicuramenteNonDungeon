package happypotatoes.slickgame.entitysystem.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.geom.Rectangle;

public class RenderParticle extends RenderComponent{
	private Rectangle rekt;
	private Image img;
	private Physics physics;
	private Particle particle;
	private Color color;
	public RenderParticle(Entity owner, Physics fisica, Particle particle, String path) {
		super(owner, false);
		physics = fisica;
		this.particle=particle;
		color = new Color(1,1,1,1f);
		try {
			img = new Image(path);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		rekt = new Rectangle(owner.x-.1f, owner.y-.1f-physics.z/2, .2f, .2f);
		
	}

	@Override
	public void render(float i) {
		color.a=(1-(float)particle.time/particle.totalTime)*i;
		img.draw(rekt.x0,rekt.y0, rekt.w, rekt.h,color);
		rekt.setPosition(owner.x-.1f, owner.y-.1f-physics.z/2);
		depth=owner.y;
	}

	@Override
	public Rectangle getRect() {
		return rekt;
	}

	@Override
	public Color getPixel(float selectx, float selecty) {
		return null;
	}

}
