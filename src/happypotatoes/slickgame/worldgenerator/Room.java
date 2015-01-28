package happypotatoes.slickgame.worldgenerator;

import java.util.ArrayList;

public class Room {
	int x, y;
	int height, width;
	//info varie
	ArrayList<Trap> traps = new ArrayList<Trap>();
	
	public Room(int height, int width){
		this.height=height;
		this.width=width;
		createTraps();
	}

	public Room(int x, int y,int height, int width){
		this.x=x;
		this.y=y;
		new Room(height,width);
	}
	
	private void createTraps() {
		int nTraps;
	}
}
