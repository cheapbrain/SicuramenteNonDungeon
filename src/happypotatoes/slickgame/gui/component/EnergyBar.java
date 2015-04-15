package happypotatoes.slickgame.gui.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.gui.Component;

public class EnergyBar extends Component{
	private Energy energy;
	private float maxValue = 0;
	public EnergyBar(Energy energy, int x, int y, int width, int height) {
		setPosition(x, y);
		setSize(width, height);
		this.maxValue = energy.getEnergy();
		this.energy = energy;
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, energy.getEnergy()*width/maxValue, height);
	}
}
