package happypotatoes.slickgame.gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import org.newdawn.slick.Graphics;

public class Container extends Component{
	protected List<Component> children = new LinkedList<Component>();
	
	public void add(Component child) {
		children.add(child);
		child.setContainer(this);
	}

	public void paint(Graphics g) {
		paintComponent(g);
		paintChildren(g);
		JButton b;
	}
	
	protected void paintComponent(Graphics g) {
		MouseEvent e;
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
	
	public boolean sendMouseEvent(MouseEvent e) {
		if (enabled&&contain(e.x, e.y)) {
			for (Component child:children)
				if (child.sendMouseEvent(e))
					return true;
				
			if (e.action == MouseEvent.PRESSED) {
				this.mousePressed(e.button, e.x, e.y);
			} else {
				this.mouseReleased(e.button, e.x, e.y);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean sendMouseMovementEvent(MouseMovementEvent e) {
		boolean mousewas = contain(e.oldx, e.oldy);
		boolean mouseis = contain(e.x, e.y);
		if (enabled&&(mousewas||mouseis)) {
			for (Component child:children)
				child.sendMouseMovementEvent(e);
			if (mousewas&&mouseis) {
				if (e.type==MouseMovementEvent.MOVE) {
					mouseMoved(e.oldx, e.oldy, e.x, e.y);
				} 
			} else if (mouseis) {
				mouseEntered();
			} else if (mousewas) {
				mouseLeft();
			}
			return true;
		} else {
			return false;
		}
	}
}
