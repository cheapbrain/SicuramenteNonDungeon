package happypotatoes.slickgame.gui;

import happypotatoes.slickgame.entitysystem.component.Inventory;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class InventoryWindow extends Window{
	private final static int slotDim = 50;
	public InventoryWindow(String title, int x, int y, Inventory inventory) {
		super(title, x, y, slotDim*4, slotDim*4);
		for(int i=0;i<inventory.getDim();i++){
			add(new InventorySlot((i%4)*slotDim,((int)(i/4))*slotDim,slotDim, inventory.getSlot(i)));
		}
	}
	public boolean isOpen(){
		return isVisible();
	}
	public void setOpen(boolean b){
		setVisible(b);		
	}
	protected void paintComponent(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(0, 0, width, height);
	}
}