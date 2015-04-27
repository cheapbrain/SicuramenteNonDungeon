package happypotatoes.slickgame.gui;

import java.util.LinkedList;
import java.util.List;

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
}
