package happypotatoes.slickgame.items;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Item {
	private int id;
	private int type;
	private int maxStack;
	private String name, description;
	private Image texture;

	
	public Item(int id, String name){
		setId(id);
		setName(name);
		try {
			texture = new Image("res/MyMod/Sprites/Items/"+name+".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type=type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public int getMaxStack() {
		return maxStack;
	}

	public String getDescription() {
		return description;
	}

	public Image getTexture() {
		return texture;
	}
}
