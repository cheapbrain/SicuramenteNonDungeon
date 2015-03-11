package entity2;

import happypotatoes.slickgame.world.World;

public class TerrainCollision extends Component {
	public Movement m;
	public HitBox h;
	public TerrainCollision(Entity owner, float priority, Movement m, HitBox h) {
		super(owner, priority);
		this.m=m;
		this.h=h;
	}

	@Override
	public void update(World w, long delta) {
		float r = 1.001f;
		if (owner.x>m.oldx)  {
			if (!w.isWalkable(owner.x+h.sizex, m.oldy-h.sizey)||!w.isWalkable(owner.x+h.sizex, m.oldy+h.sizey/2)) {
				owner.x = (float) (Math.floor(owner.x+h.sizex)-h.sizex*r);
				m.speedx = 0;
			}
		} else if (owner.x<m.oldx) {
			if (!w.isWalkable(owner.x-h.sizex, m.oldy-h.sizey)||!w.isWalkable(owner.x-h.sizex, m.oldy+h.sizey/2)) {
				owner.x = (float) (Math.ceil(owner.x-h.sizex)+h.sizex*r);
				m.speedx = 0;
			}
		}

		if (owner.y>m.oldy)  {
			if (!w.isWalkable(m.oldx+h.sizex, owner.y+h.sizey/2)||!w.isWalkable(m.oldx-h.sizex, owner.y+h.sizey/2)) {
				owner.y = (float) (Math.floor(owner.y+h.sizey/2)-h.sizey/2*r);
				m.speedy = 0;
			}
		} else if (owner.y<m.oldy) {
			if (!w.isWalkable(m.oldx+h.sizex, owner.y-h.sizey)||!w.isWalkable(m.oldx-h.sizex, owner.y-h.sizey)) {
				owner.y = (float) (Math.ceil(owner.y-h.sizey)+h.sizey*r);
				m.speedy = 0;
			}
		}
		
	}

}
