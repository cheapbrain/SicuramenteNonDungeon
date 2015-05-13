package happypotatoes.slickgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;

public class FogEmitter implements ParticleEmitter{
	
	private int x;
	private int y;
	private int ttl = 5000;
	private int interval = 100;
	private int timer;
	private float size = 800;

	public FogEmitter(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void update(ParticleSystem system, int delta) {
		timer -= delta;
		if (timer <= 0) {
			timer = interval;
			Particle p = system.getNewParticle(this, ttl);
			
			float minc = 0.2f;
			float rangec = 0.4f;
			float c = (float) (Math.random()*rangec+minc);
			
			p.setColor(c, c, c, 0f);
			int x = (int) (this.x*Math.random());
			int y = (int) (this.y-Math.random()*size/4+100);
			
			p.setPosition(x, y);
			p.setSize(size*(float)(Math.random()/4+.75));
			float vx = (float) (0);
			float vy = (float) (0);
			p.setVelocity(vx,vy,0);
		}
	}

	@Override
	public void updateParticle(Particle particle, int delta) {
		float c = 1.5f/ttl * delta;
		if (particle.getLife() > ttl*2/3) {
			particle.adjustColor(0,0,0,+c/2);
		} else {
			particle.adjustColor(0,0,0,-c/4);
		}
		particle.adjustPosition(0, -.03f*delta);
	}

	@Override
	public boolean completed() {
		return false;
	}

	@Override
	public void wrapUp() {
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public boolean useAdditive() {
		return false;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public boolean isOriented() {
		return false;
	}

	@Override
	public boolean usePoints(ParticleSystem system) {
		return false;
	}

	@Override
	public void resetState() {
	}

}
