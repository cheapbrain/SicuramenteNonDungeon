package happypotatoes.slickgame.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.gui.component.Button;
import happypotatoes.slickgame.gui.component.EnergyBar;
import happypotatoes.slickgame.gui.component.HealthBar;
import happypotatoes.slickgame.gui.component.Label;
import happypotatoes.slickgame.gui.component.Minimap;

public class GuiSystem {
	private static InventoryWindow iw = null;
	private static Window hud = null, bla = null;
	private static Minimap minimap = null;
	public static void init(UI ui, Entity player){
		iw=inventory(ui, player);
		hud=hud(ui, player);
		ui.add(Minimap.getInstance());
	}
	public static Window getHud(){
		return hud;
	}
	public static InventoryWindow getInventory(){
		return iw;
	}
	private static Window hud(UI ui, Entity player){
		Image image = null;
		try {
			image = new Image("./res/Background.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		int xNuova = ui.width;
		Window hud = new Window("hud", 0, 0, xNuova, xNuova*image.getHeight()/image.getWidth());
		hud.setVerticalAlign(Component.SOUTH);
		hud.setHorizontalAlign(Component.CENTER);
		hud.setBackground(image);
		float k = (float)(ui.width)/image.getWidth();
		hud.add(new HealthBar((Health) player.getComponent(Health.class), k));
		hud.add(new EnergyBar((Energy) player.getComponent(Energy.class), k));
		Component cp = new Component();
		cp.setPriority(10);
		cp.setPosition(hud.x, hud.y);
		cp.setSize(hud.width, hud.height);
		try {
			cp.setBackground(new Image("./res/ForegroundGui.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		hud.add(cp);
		ui.add(hud);	
		
		return hud;
	}
	private static InventoryWindow inventory(UI ui, Entity player){
		InventoryWindow iw = new InventoryWindow("inventario", 0,0, ((Inventory) player.getComponent(Inventory.class)));
		iw.setVerticalAlign(Component.CENTER);
		iw.setHorizontalAlign(Component.EAST);
		iw.setVisible(false);
		ui.add(iw);
		return iw;
	}
}
