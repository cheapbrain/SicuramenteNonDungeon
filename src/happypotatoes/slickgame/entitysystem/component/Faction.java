package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class Faction extends Component{
	public static final int good=1, bad=2, neutral=3;
	private int faction;
	public Faction(Entity owner, float priority, int faction) {
		super(owner, priority);
		this.faction=faction;
	}
	
	@Override
	public void update(World w, long delta) {
	}
	
	public boolean isGood(){
		return (faction==good);
	}
	public boolean isBad(){
		return (faction==bad);
	}
	public boolean isNeutral(){
		return (faction==neutral);
	}
	public boolean enemyOf(Entity e){
		Faction enemyFaction = e.getComponent(Faction.class);
		if(enemyFaction!=null)
			if(faction!=enemyFaction.faction) 
				return true;
		return false;
	}
}
