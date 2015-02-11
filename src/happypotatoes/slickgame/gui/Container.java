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
		int dx = 0;
		int dy = 0;
		for (Component child:children) 
			if (child.isVisible()){
				dx = child.getX() - dx;
				dy = child.getY() - dy;
				g.translate(dx, dy);
				child.paint(g);
			}
	}
}
