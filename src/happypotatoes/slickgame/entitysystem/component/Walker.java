package happypotatoes.slickgame.entitysystem.component;

import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Walker extends Component{
	public int directions;
	public int states;
	
	public int facing = 0;
	private int state = 0;
	
	public Walker(Entity owner, float priority, int directions, int states) {
		super(owner, priority);
		this.directions = directions;
		this.states = states;
	}
	
	public Walker(Entity owner, float priority, int directions, int states, int facing) {
		super(owner, priority);
		this.directions = directions;
		this.states = states;
		this.facing = facing;
	}

	@Override
	public void update(World w, long delta) {
		
	}
	
	public void setFacing(float dx, float dy) {
		float angle = (float)(Math.atan2(dx, dy)/Math.PI/2+1);
		facing = (int)Math.round(angle*directions+4)%directions;
	}
	
	public void setState(int state){
		this.state=state;
	}
	public int getState(){
		return state;
	}
	
	public void setStill(){
		this.state=0;
	}
	public void setWalking(){
		this.state=1;
	}
	public void setAttacking(){
		this.state=2;
		if(this.states>5){
			switch((new Random()).nextInt(2)){
			case 0: this.state=5;
			}
		}
	}
	public void setDead(){
		if(owner.getComponent(AI.class)!=null){
			owner.getComponent(AI.class).focus=null;
		}
		if(owner.getComponent(Energy.class)!=null){
			owner.getComponent(Energy.class).setEnergy(0);
		}
		this.state=3;
	}
	public void setDefending(){
		Energy thisEnergy=owner.getComponent(Energy.class);
		Defend thisDefend=owner.getComponent(Defend.class);
		if(thisEnergy.getEnergy()>thisDefend.consumeSecond)
			this.state=4;
	}
	

}
