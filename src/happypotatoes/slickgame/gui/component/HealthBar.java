package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.gui.Component;

public class HealthBar extends Component{
	private Health health;
	private float maxValue = 0;
	public HealthBar(Health health, int x, int y, int width, int height) {
		setPosition(x, y);
		setSize(width, height);
		this.maxValue = health.getHealth();
		this.health = health;
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, health.getHealth()*width/maxValue, height);
	}
}
