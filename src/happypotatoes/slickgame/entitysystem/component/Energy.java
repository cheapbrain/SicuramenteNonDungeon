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
		if(owner.getComponent(Walker.class).getState()==0){
			timer += delta;
			if(timer>2000){
				if(getEnergy()+getEnergyRegeneration()*delta/1000f<=maxEnergy){
				setEnergy(getEnergy()+getEnergyRegeneration()*delta/1000f);
				}
				else setEnergy(maxEnergy);
			}
		}
		else timer=0;
	}
	public float getEnergy() {
		return energy;
	}
	public void setEnergy(float energy) {
		if(energy>=0) this.energy = energy;
		else this.energy = 0f;
	}
	public float getEnergyRegeneration() {
		return energyRegeneration;
	}
	public void setEnergyRegeneration(float energyRegeneration) {
		this.energyRegeneration = energyRegeneration;
	}
	public void charge(float i) {
		energy += i;
		if (energy>maxEnergy) energy = maxEnergy;
	}
}
