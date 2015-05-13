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
		setPosition((int)(k*1132),(int)(k*197));
		setSize((int)(k*bar.getWidth()), (int)(k*bar.getHeight()));
		}
	protected void paintComponent(Graphics g) {
		bar.draw(0,height*(1-energy.getEnergy()/maxValue),width,height, 0, bar.getHeight()*(1-energy.getEnergy()/maxValue), bar.getWidth(), bar.getHeight());
	}
}
