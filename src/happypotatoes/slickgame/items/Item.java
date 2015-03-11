package happypotatoes.slickgame.items;

public class Item {
	private int id;
	private int maxStackSize;
	private int maxDamage;
	private boolean hasSubtypes;
	private String name;
	
	public Item(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}

	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public boolean isHasSubtypes() {
		return hasSubtypes;
	}

	public void setHasSubtypes(boolean hasSubtypes) {
		this.hasSubtypes = hasSubtypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
