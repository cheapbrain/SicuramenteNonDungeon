package happypotatoes.slickgame.gui;

import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.component.Energy;
import happypotatoes.slickgame.entitysystem.component.Health;
import happypotatoes.slickgame.entitysystem.component.Inventory;
import happypotatoes.slickgame.gui.component.Button;
import happypotatoes.slickgame.gui.component.EnergyBar;
import happypotatoes.slickgame.gui.component.HealthBar;
import happypotatoes.slickgame.gui.component.Label;

public class GuiSystem {
	private static InventoryWindow iw = null;
	private static Window hud = null;
	public static void init(UI ui, Entity player){
		iw=inventory(ui, player);
		hud=hud(ui, player);
	}
	public static Window getHud(){
		return hud;
	}
	public static InventoryWindow getInventory(){
		return iw;
	}
	private static Window hud(UI ui, Entity player){
		Window hud = new Window("hud", 0, 0, 400, 50);
		hud.setVerticalAlign(Component.SOUTH);
		hud.setHorizontalAlign(Component.CENTER);
		hud.add(new Label("ebola", 0, 0, 100, 50));
		hud.add(new Button("omg", 100, 0, 50, 30));
		hud.add(new HealthBar((Health) player.getComponent(Health.class), 200,0,100,20));
		hud.add(new EnergyBar((Energy) player.getComponent(Energy.class), 200,25,100,20));
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
