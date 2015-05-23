package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Entity;

import java.lang.reflect.Constructor;

public class AIManager {
	Entity e;
	float priority;
	Walker walker;
	Movement movement;
	AIType type;
	float speed; 
	
	public AIManager(Entity e, float priority, Walker walker, Movement movement, float speed, AIType type) {
		this.e = e;
		this.priority=priority;
		this.walker=walker;
		this.movement=movement;
		this.speed=speed;
		this.type=type;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AI get() { 
		//trova la verità		
		Class c = null;
		Constructor<AI> con = null;
		AI xyz = null;			
		try{
			c = Class.forName("happypotatoes.slickgame.entitysystem.component.AI"+type.name());			
			con = c.getConstructor(Entity.class, float.class, Walker.class, Movement.class, float.class,  AIType.class);
			xyz = (AI) con.newInstance(e, priority, walker, movement, speed, type);	
			
		}
		catch(Exception e){ };			
		return xyz;		
	}
	

}
