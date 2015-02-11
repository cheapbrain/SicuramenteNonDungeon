package happypotatoes.slickgame.world;

import java.util.LinkedList;
import java.util.List;

import happypotatoes.slickgame.entity.Entity;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Quadtree {
	private int maxEntities;
	private int maxDepth;
	private int depth;
	
	private List<Entity> entities = new LinkedList<Entity>();
	
	private Rectangle area;
	
	private Quadtree parent;
	private Quadtree[] nodes; 
	//  0 1
	//  2 3
	
	public Quadtree(int maxEntities, int maxDepth, Quadtree parent, Rectangle area) {
		nodes = null;
		this.maxEntities = maxEntities;
		this.maxDepth = maxDepth;
		this.area = area;
		
		this.parent = parent;
		
		if (parent!=null)
			depth = parent.getDepth()+1;
		else
			depth = 1;
	}
	
	
	
	public int getDepth() {
		return depth;
	}
	
	private void genChildren() {
		nodes = new Quadtree[4];
		float x = area.getX();
		float y = area.getY();
		float w = area.getWidth();
		float h = area.getHeight();
		float w1 = (int)(w/2);
		float h1 = (int)(h/2);
		float w2 = w-w1;
		float h2 = h-h1;
		
		Rectangle a0 = new Rectangle(x, y, w1, h1);
		Rectangle a1 = new Rectangle(w1, y, w2, h1);
		Rectangle a2 = new Rectangle(x, h1, w1, h2);
		Rectangle a3 = new Rectangle(w1, h1, w2, h2);
		
		nodes[0] = new Quadtree(maxEntities, maxDepth, this, a0);
		nodes[1] = new Quadtree(maxEntities, maxDepth, this, a1);
		nodes[2] = new Quadtree(maxEntities, maxDepth, this, a2);
		nodes[3] = new Quadtree(maxEntities, maxDepth, this, a3);
	}
	
	public Rectangle getArea() {
		return area;
	}
	
	public void add(Entity entity) {
		if (nodes!=null) {
			Shape shape = entity.getShape();
			boolean added = false;
			for (int i=0;i<nodes.length;i++)
				if (nodes[i].area.contains(shape)) {
					nodes[i].add(entity);
					added = true;
					break;
				}
			if (!added)
				addSort(entity);
			
		} else
			addSort(entity);
	}
	
	private void addSort(Entity entity) {
		entities.add(entity);
	}
	
	
	
}
