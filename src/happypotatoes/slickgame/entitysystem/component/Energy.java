package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Energy extends Component{
	private float energy, energyRegeneration, maxEnergy;
	private int timer = 0;
	public Energy(Entity owner, float priority, float energy, float energyRegeneration ) {
		super(owner, priority);
		maxEnergy=energy;
		this.setEnergy(energy);
		this.energyRegeneration=energyRegeneration;
	}
	public void update(World w, long delta) {
		timer += delta;
		if(timer>1000 && energy+energyRegeneration<=maxEnergy){
			timer=0;
			setEnergy(getEnergy() + energyRegeneration);
		}
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		if(energy>=0) this.energy = energy;
	}
	public float getHealthRegeneration() {
		return energyRegeneration;
	}
	public void setEnergyRegeneration(float energyRegeneration) {
		this.energyRegeneration = energyRegeneration;
	}
}
