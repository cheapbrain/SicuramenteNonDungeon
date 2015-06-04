package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.gui.Component;

public class HealthBar extends Component{
	private Health health;
	private float maxValue = 0;
	private Image bar = null;
	public HealthBar(Health health, float k) {
		try {
			bar = Loader.image("res/Vita.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setPosition((int)(k*744),(int)(k*197));
		setSize((int)(k*bar.getWidth()), (int)(k*bar.getHeight()));
		this.maxValue = health.getHealth();
		this.health = health;		
	}
	protected void paintComponent(Graphics g) {
		bar.draw(0,height*(1-health.getHealth()/maxValue),width,height, 0, bar.getHeight()*(1-health.getHealth()/maxValue), bar.getWidth(), bar.getHeight());
	}
}
