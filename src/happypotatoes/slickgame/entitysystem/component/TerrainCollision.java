package happypotatoes.slickgame.entitysystem.component;

import happypotatoes.slickgame.entitysystem.Component;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.world.World;

public class TerrainCollision extends Component {
	public Movement m;
	public HitBox h;
	public TerrainCollision(Entity owner, float priority, Movement m, HitBox h) {
		super(owner, priority);
		this.m=m;
		this.h=h;
	}

	public void update2(World w, long delta) {
		float r = 1.001f;
		if (owner.x>m.oldx)  {
			if (!w.isWalkable(owner.x+h.sizex, m.oldy-h.sizey)||!w.isWalkable(owner.x+h.sizex, m.oldy+h.sizey/2)) {
				owner.x = (float) (Math.floor(owner.x+h.sizex)-h.sizex*r);
			}
		} else if (owner.x<m.oldx) {
			if (!w.isWalkable(owner.x-h.sizex, m.oldy-h.sizey)||!w.isWalkable(owner.x-h.sizex, m.oldy+h.sizey/2)) {
				owner.x = (float) (Math.ceil(owner.x-h.sizex)+h.sizex*r);
			}
		}

		if (owner.y>m.oldy)  {
			if (!w.isWalkable(m.oldx+h.sizex, owner.y+h.sizey/2)||!w.isWalkable(m.oldx-h.sizex, owner.y+h.sizey/2)) {
				owner.y = (float) (Math.floor(owner.y+h.sizey/2)-h.sizey/2*r);
			}
		} else if (owner.y<m.oldy) {
			if (!w.isWalkable(m.oldx+h.sizex, owner.y-h.sizey)||!w.isWalkable(m.oldx-h.sizex, owner.y-h.sizey)) {
				owner.y = (float) (Math.ceil(owner.y-h.sizey)+h.sizey*r);
			}
		}
		
	}
	
	public void update(World w, long delta) {
		boolean c1=false,c2=false;
		boolean moved = false;
		float r = 1.001f;
		float speed = 0.0001f;
		if (owner.x>m.oldx)  {
			c1 = !w.isWalkable(owner.x+h.sizex, m.oldy-h.sizey);
			c2 = !w.isWalkable(owner.x+h.sizex, m.oldy+h.sizey);
			if (c1||c2) {
				owner.x = (float) (Math.floor(owner.x+h.sizex)-h.sizex*r);
				m.speedx = 0;
				if (c1&&owner.y>=m.oldy&&!c2)
					m.speedy += speed*delta;
				if (c2&&owner.y<=m.oldy&&!c1)
					m.speedy -= speed*delta;
				moved = c1||c2;
			}
		} else if (owner.x<m.oldx) {
			c1 = !w.isWalkable(owner.x-h.sizex, m.oldy-h.sizey);
			c2 = !w.isWalkable(owner.x-h.sizex, m.oldy+h.sizey);
			if (c1||c2) {
				owner.x = (float) (Math.ceil(owner.x-h.sizex)+h.sizex*r);
				m.speedx = 0;
				if (c1&&owner.y>=m.oldy&&!c2)
					m.speedy += speed*delta;
				if (c2&&owner.y<=m.oldy&&!c1)
					m.speedy -= speed*delta;
				moved = moved||c1||c2;
			}
		}

		if (owner.y>m.oldy)  {
			c1=!w.isWalkable(m.oldx-h.sizex, owner.y+h.sizey);
			c2=!w.isWalkable(m.oldx+h.sizex, owner.y+h.sizey);
			if (c1||c2) {
				owner.y = (float) (Math.floor(owner.y+h.sizey)-h.sizey*r);
				m.speedy = 0;
				if (c1&&owner.x>=m.oldx&&!c2)
					m.speedx += speed*delta;
				if (c2&&owner.x<=m.oldx&&!c1)
					m.speedx -= speed*delta;
			}
		} else if (owner.y<m.oldy) {
			c1=!w.isWalkable(m.oldx-h.sizex, owner.y-h.sizey);
			c2=!w.isWalkable(m.oldx+h.sizex, owner.y-h.sizey);
			if (c1||c2) {
				owner.y = (float) (Math.ceil(owner.y-h.sizey)+h.sizey*r);
				m.speedy = 0;
				if (c1&&owner.x>=m.oldx&&!c2)
					m.speedx += speed*delta;
				if (c2&&owner.x<=m.oldx&&!c1)
					m.speedx -= speed*delta;
			}
		}
		
	}

}
