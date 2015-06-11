package happypotatoes.slickgame.gui;


import happypotatoes.slickgame.Loader;
import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.EntitySystem;
import happypotatoes.slickgame.entitysystem.ItemSystem;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.entitysystem.component.PlayerInput;
import happypotatoes.slickgame.entitysystem.component.equip.Equip;
import happypotatoes.slickgame.entitysystem.entity.ItemEntity;
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
			ItemList.getItemForId(id).getTexture().draw(0,0,getWidth(),getHeight());
		}
		
		img.draw(0,0,getWidth(),getHeight());
		int n=0;
		if(slot!=null) n = slot.HowManyStacked();
		
		//g.setColor(Color.white);
		//if(n>0)	g.fillRect(0, getHeight()-24, 20, 20);
		//g.setColor(Color.black);
		//g.setFont(font);
		//if(n>0)	g.drawString(slot.HowManyStacked()+"",0,getHeight()-27);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		switch(button) {
		case MouseEvent.BUTTON0:
			//inventory o chest
			if(slot!=null) {
				int id = slot.getItemId();
				if (id!=0) {
					Equip equip = slot.getOwner().owner.getComponent(Equip.class);
					//inventory
					if (equip!=null) {
						//equipaggia
						if (equip.add(id)) {
							slot.getOwner().takeOut(slot);
						//usa
						} else {
							if (ItemList.getItemForId(id).use(slot.getOwner().owner)) {
								slot.getOwner().takeOut(slot);
							} else {
								Notification.showMessage("Can't use this item!", 2000);
							}
						}
					//chest
					} else {
						if (EntitySystem.getInstance().getEntities(PlayerInput.class).get(0).getComponent(Inventory.class).add(id)) {
							slot.getOwner().takeOut(slot);
						} else {
							Notification.showMessage("Full inventory!", 2000);
						}
					}
				}
			//equip
			} else {
				int id = equipSlot.getItemId();

				if (id!=0) {
					Inventory inv = equipSlot.getOwner().owner.getComponent(Inventory.class);
					if (inv!=null) {
						if (inv.add(id)) {
							equipSlot.getOwner().takeOut(equipSlot);
						} else {
							Notification.showMessage("Full inventory!", 2000);
						}
					}
				}
			}
			break;
		case MouseEvent.BUTTON1:
			if(slot!=null) {
				Entity o = slot.getOwner().owner;
				if (o.getComponent(Equip.class)!=null) {
					int id = slot.getOwner().takeOut(slot);
					if (id!=0)
						EntitySystem.getInstance().addEntity(ItemEntity.create(id, o.x, o.y));
				}
			} else {
				int id = equipSlot.getOwner().takeOut(equipSlot);
				Entity o = equipSlot.getOwner().owner;
				if (id!=0)
					EntitySystem.getInstance().addEntity(ItemEntity.create(id, o.x, o.y));
			}
			break;
		}
	}
}
