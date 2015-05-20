package happypotatoes.slickgame.entitysystem.component;

import java.util.List;
import java.util.Random;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.entity.ParticleBuilder;
import happypotatoes.slickgame.world.World;

public class Attack extends Component{
	public int animationTime = 0;
	private Walker walker;
	public int animationTotalTime = 0;
	private float damage;
	private Entity focus;
	public float consume = 4f;
	public Attack(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float damage) {
		super(owner, priority);
		this.walker = walker;
		this.damage = damage;
		animationTotalTime = walkerRender.getFrames(2)* walkerRender.getFrameTime();
	}
	
	public void attack(Entity focus) {
		walker.setAttacking();
		this.focus = focus;
	}
	
	public void update(World w, long delta) {
		if(walker.getState()==2){
			Energy thisEnergy=owner.getComponent(Energy.class);
			if(thisEnergy.getEnergy()<consume){
				walker.setStill();
				return;
			}
			animationTime+=delta;
			if(animationTime>=animationTotalTime){
				if (owner.getComponent(AI.class)!=null)
					focus = owner.getComponent(AI.class).focus;
				if(focus!=null)
					owner.getComponent(Walker.class).setFacing(focus.x-owner.x, focus.y-owner.y);
				if(thisEnergy!=null)
					thisEnergy.setEnergy(thisEnergy.getEnergy()-consume);
				if (focus!=null) {
					Health EnemyHp = ((Health) focus.getComponent(Health.class));
					if(focus.getComponent(Defend.class)!=null){
						if(damage*(1f-((Defend)focus.getComponent(Defend.class)).mitigation)>=EnemyHp.getHealth()/100f*5f)
							createBlood();
						EnemyHp.setHealth(EnemyHp.getHealth()-damage*(1f-((Defend)focus.getComponent(Defend.class)).mitigation));
					}	
					else{
						if(damage>=EnemyHp.getHealth()/100f*5f)
							createBlood();
						EnemyHp.setHealth(EnemyHp.getHealth()-damage);
					}
				}
				
				walker.setStill();
				animationTime=0;
			} else{
			}
		}
		else animationTime=0;
	}
	
	public void createBlood(){
		for(int i=0;i<10;i++){
			float angle = (float) (Math.random()+Math.PI/5*i);
			float speedx = (float) Math.cos(angle)*.004f;
			float speedy = (float) Math.sin(angle)*.004f;
			EntitySystem.getInstance().addEntity(ParticleBuilder.create("./res/blood.png", focus.x, focus.y, 1, 500, speedx, speedy, 0, 0.99999f, .1f));
		}
	}
	public boolean isAttacking(){
		if(walker.getState()==2&&animationTime>0) return true;
		else return false;
	}
}
