package happypotatoes.slickgame.gui;

import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;

import org.newdawn.slick.Graphics;

public class InventoryWindow extends PopUpWindow{
	private final static int slotDim = 64;
	public InventoryWindow(String title, int x, int y, Inventory inventory) {
		super(title, x, y, slotDim*inventory.width, slotDim*inventory.height);
		for(int i=0;i<inventory.width;i++)
			for(int j=0;j<inventory.height;j++)
				add(new InventorySlot(i*slotDim+borderOffset/2,j*slotDim+borderOffset/2,slotDim, inventory.getSlot(i,j)));
	}
	
	public InventoryWindow(String title, int x, int y, Equip equip) {
		super(title, x, y, slotDim*equip.width, slotDim*equip.height);
		for(int i=0;i<equip.width;i++)
			for(int j=0;j<equip.height;j++){
				add(new InventorySlot(i*slotDim+borderOffset/2,j*slotDim+borderOffset/2,slotDim, equip.getSlot(i,j)));
			}
	}
	
	public boolean isOpen(){
		return isEnabled();
	}
	public void setOpen(boolean b){
		setEnabled(b);		
		setVisible(b);		
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}