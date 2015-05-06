package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.gui.Component;

public class HealthBar extends Component{
	private Health health;
	private float maxValue = 0;
	private Image bar = null;
	public HealthBar(Health health, float k) {
		try {
			bar = new Image("./res/Vita.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setPosition(165,10);
		setSize((int)(k*bar.getWidth()), (int)(k*bar.getHeight()));
		this.maxValue = health.getHealth();
		this.health = health;		
	}
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0,1));
		g.fillRect(0, 0, health.getHealth()*width/maxValue, height);
		bar.draw(0,0,width, height);
	}
}
