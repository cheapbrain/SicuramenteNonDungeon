package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Sounds;
import happypotatoes.slickgame.entitysystem.Entity;

public class StateSound {
	public static final int ENTER = 0;
	public static final int LEAVE = 1;
	Entity owner;
	String[] sounds;
	int state;
	int event;
	
	public StateSound(Entity owner, int state, int event, String ... sounds) {
		this.owner = owner;
		this.sounds = sounds;
		this.state = state;
		this.event = event;
	}
	
	public void trigger(int state, int event) {
		if (this.state == state&&this.event == event) {
			float dx = Math.abs(Camera.camera.getXC()-owner.x);
			float dy = Math.abs(Camera.camera.getYC()-owner.y);
			if (dx<10&&dy<10)
				Sounds.getInstance().playSound(sounds[(int)(Math.random()*sounds.length)], dx, dy);
		}
	}
	
	public void setSound(String ... sound) {
		this.sounds = sounds;
	}

	public void update(long delta) {
		
	}
}
