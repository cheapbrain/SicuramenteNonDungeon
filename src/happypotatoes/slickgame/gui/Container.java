package happypotatoes.slickgame.gui;

import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;

public class Container extends Component{
	protected LinkedList<Component> children = new LinkedList<Component>();
	
	public void add(Component child) {
		child.setContainer(this);
		
		if (!children.isEmpty()) {
			float p = child.getPriority();
			int j = 0;
			Iterator<Component> it = children.iterator();
			while(it.hasNext()) {
				Component c = it.next();
				if (c.getPriority()>=p) {
					children.add(j, child);
					return;
				}
				j++;
			}
		}
		children.add(child);
	}

	public void paint(Graphics g) {
		paintComponent(g);
		paintChildren(g);
	}
	
	protected void paintComponent(Graphics g) {
		
	}
	
	protected void paintChildren(Graphics g) {
		int ddx = 0;
		int ddy = 0;
		for (Component child:children) 
			if (child.isVisible()){
				int dx = child.getX() - ddx;
				int dy = child.getY() - ddy;
				g.translate(dx, dy);
				ddx = child.getX();
				ddy = child.getY();
				child.paint(g);
			}
		g.translate(-ddx, -ddy);
	}
	
	public boolean sendMouseEvent(MouseEvent e, int dx, int dy) {
		dx += getX();
		dy += getY();
		if (enabled&&contain(e.x, e.y, dx, dy)) {
			Iterator<Component> it = children.descendingIterator();
			while(it.hasNext())
				if (it.next().sendMouseEvent(e, dx, dy))
					return true;
				
			if (e.action == MouseEvent.PRESSED) {
				this.mousePressed(e.button, e.x-dx, e.y-dy);
			} else {
				this.mouseReleased(e.button, e.x-dx, e.y-dy);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean sendMouseMovementEvent(MouseMovementEvent e, int dx, int dy) {
		dx += getX();
		dy += getY();
		boolean mousewas = contain(e.oldx, e.oldy, dx, dy);
		boolean mouseis = contain(e.x, e.y, dx, dy);
		if (enabled&&(mousewas||mouseis)) {
			
			Iterator<Component> it = children.descendingIterator();
			while(it.hasNext()) {
				if (it.next().sendMouseMovementEvent(e, dx, dy))
					break;
			}

			if (mousewas&&mouseis) {
				if (e.type==MouseMovementEvent.MOVE) {
					mouseMoved(e.oldx-dx, e.oldy-dy, e.x-dx, e.y-dy);
				} 
			} else if (mouseis) {
				mouseEntered();
			} else if (mousewas) {
				mouseLeft();
			}
			return mousewas&&mouseis;
		} else {
			return false;
		}
	}
}
