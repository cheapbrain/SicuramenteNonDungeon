package happypotatoes.slickgame.gui;


import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.inventory.Slot;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;

public class InventorySlot extends Component{
	private Image img;
	private Slot slot;
	public InventorySlot(int x, int y, int dim, Slot slot){
		setSize(dim, dim);
		setPosition(x, y);
		this.slot = slot;
		try {
			img = new Image("./res/popup/Frame.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g) {
		if(!slot.getItemId().equals("")){
			ItemSystem.getTexture(slot.getItemId()).draw(0,0,width,height);
		}
		int x = getAbsoluteX();
		int y = getAbsoluteY();	
		int mx = UI.mx;
		int my = UI.my;
		if (mx>x&&mx<x+width&&my>y&&my<y+height)
			if (UI.mb0)
				slot.getOwner().takeOut(slot);
		img.draw(0,0,width,height);
	}
}
