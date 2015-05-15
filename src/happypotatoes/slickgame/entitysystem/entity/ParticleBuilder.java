package happypotatoes.slickgame.entitysystem.entity;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.component.Particle;
import happypotatoes.slickgame.entitysystem.component.Physics;
import happypotatoes.slickgame.entitysystem.component.RenderParticle;

public class ParticleBuilder {
	public static Entity create(String path, float x, float y, float z, long ttl, float speedx, float speedy, float speedz, float friction, float size){
		Entity particle = new Entity(EntitySystem.getInstance().getFreeID(), "particella");
		particle.x=x;
		particle.y=y;
		Physics fisica = new Physics(particle, 0, speedx, speedy, speedz, z,  friction);
		Particle particella = new Particle(particle,0, ttl);
		RenderParticle rp = new RenderParticle(particle, fisica, particella, path, size);
		return particle;
	}
}
