package happypotatoes.slickgame.gui;


import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.inventory.EquipSlot;
import happypotatoes.slickgame.inventory.Slot;
import happypotatoes.slickgame.items.ItemList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class InventorySlot extends Component{
	private Image img;
	private Slot slot=null;
	private EquipSlot equipSlot=null;
	
	public InventorySlot(int x, int y, int dim, Slot slot){
		setSize(dim, dim);
		setPosition(x, y);
		this.slot = slot;
		try {
			img = Loader.image("res/popup/Frame.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public InventorySlot(int x, int y, int dim, EquipSlot slot){
		setSize(dim, dim);
		setPosition(x, y);
		this.equipSlot = slot;
		try {
			img = Loader.image("res/popup/Frame.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		int id = 0;
		if(slot!=null) id = slot.getItemId();
		else id = equipSlot.getItemId();
		if(id!=0){
			ItemList.getItemForId(id).getTexture().draw(0,0,width,height);
		}
		int x = getAbsoluteX();
		int y = getAbsoluteY();	
		int mx = UI.mx;
		int my = UI.my;
		if (mx>x&&mx<x+width&&my>y&&my<y+height)
			if (UI.mb0)
				if(slot!=null)slot.getOwner().takeOut(slot);
				else equipSlot.getOwner().takeOut(equipSlot);
		img.draw(0,0,width,height);
		int n=0;
		if(slot!=null) n = slot.HowManyStacked();
		g.setColor(Color.black);
		g.setFont(font);
		if(n>0)	g.drawString(slot.HowManyStacked()+"",0,0);
	}
}
