package happypotatoes.slickgame.worldgenerator;

public class Room {
	int x, y;
	int height, width;
	int bind[];
	//info varie
	
	public Room(){
		this.width=1;
		this.height=1;
		bind= new int[10];
	}
	
	public Room(int height, int width){
		this.height=height;
		this.width=width;
		bind= new int[10];
	}
	
	public Room(int x, int y,int height, int width){
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		bind= new int[10];
	}
}
