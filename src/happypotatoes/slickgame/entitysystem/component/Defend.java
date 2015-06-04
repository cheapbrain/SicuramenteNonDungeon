package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.inventory.EquipSlot;
import happypotatoes.slickgame.items.ItemList;
import happypotatoes.slickgame.items.ItemType;
import happypotatoes.slickgame.items.Shield;
import happypotatoes.slickgame.world.World;

public class Defend extends Component{
	public float animationTime = 0;
	private Walker walker;
	public float animationTotalTime;
	public float baseMitigation;
	public float mitigation;
	public float consumeSecond=3f;
	
	public Defend(Entity owner, float priority, Walker walker, WalkerRender walkerRender, float baseMitigation) {
		super(owner, priority);
		this.walker = walker;
		this.baseMitigation = baseMitigation;
		animationTotalTime = walkerRender.getFrames(4)* walkerRender.getFrameTime();
	}
	
	public void update(World w, long delta) {
		if(walker.getState()==4){
			Energy thisEnergy=owner.getComponent(Energy.class);
			if(thisEnergy.getEnergy()<consumeSecond/4f){
				walker.setStill();
				mitigation=0f;
				return;
			}
			if(animationTime>animationTotalTime){
				//controlla se ha uno scudo
				Equip equip = owner.getComponent(Equip.class);
				EquipSlot slot = null;
				Shield shield = null;
				if(equip!=null){
					 slot = equip.get(ItemType.secondhand);
					 if(slot!=null){
						 shield = (Shield) ItemList.getItemForId(slot.getItemId());
					 }
				}
				if(shield!=null){
					mitigation=baseMitigation+shield.getMitigation();
					if(thisEnergy!=null)
						thisEnergy.setEnergy(thisEnergy.getEnergy()-((consumeSecond+shield.getWeight())/1000*delta));
				}
				else{
					mitigation=baseMitigation;
					thisEnergy.setEnergy(thisEnergy.getEnergy()-((consumeSecond)/1000*delta));
				}
				
			} else{
				animationTime+=delta;	
			}
		}
		else{
			animationTime=0;
			mitigation=0f;
		}
		
	}
	
	public boolean isDefending(){
		if(animationTime>animationTotalTime) return true; 
		else return false;
	}
}
