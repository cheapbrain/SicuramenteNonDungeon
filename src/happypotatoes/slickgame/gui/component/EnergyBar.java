package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.gui.Component;

public class EnergyBar extends Component{
	private Energy energy;
	private float maxValue = 0;
	private Image bar = null;
	public EnergyBar(Energy energy, float k) {
		this.maxValue = energy.getEnergy();
		this.energy = energy;
		try {
			bar = new Image("./res/Energia.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setPosition(480,10);
		setSize((int)(k*bar.getWidth()), (int)(k*bar.getHeight()));
		}
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0,0,0,1));
		g.fillRect(0, 0, energy.getEnergy()*width/maxValue, height);
		bar.draw(0,0,width, height);
	}
}
