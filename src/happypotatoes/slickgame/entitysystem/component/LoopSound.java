package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.Camera;
import happypotatoes.slickgame.Sounds;
import happypotatoes.slickgame.entitysystem.Entity;

public class LoopSound extends StateSound{
	int wait;
	int time;
	boolean play = false;

	public LoopSound(Entity owner, int state, int wait, String ... sounds) {
		super(owner, state, 0, sounds);
		this.wait = wait;
		time = wait;
	}
	
	public void trigger(int state, int event) {
		if (state==this.state&&event==StateSound.ENTER) {
			play = true;
		} else if (state==this.state&&event==StateSound.LEAVE) {
			play = false;
		}
	}
	
	
	public void update(long delta) {
		if (play) {
			time += delta;
			if (time>=wait) {
				time -= wait;
				float dx = Math.abs(Camera.camera.getXC()-owner.x);
				float dy = Math.abs(Camera.camera.getYC()-owner.y);
				if (dx<10&&dy<10)
					Sounds.getInstance().playSound(sounds[(int)(Math.random()*sounds.length)], dx, dy);
			}
		} else {
			time = wait;
		}
	}
}
