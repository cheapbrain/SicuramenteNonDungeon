package testing_fisting;

public class Room {
	int x, y;
	int height, width;
	//info varie
	
	public Room(){
		this.width=1;
		this.height=1;
	}
	
	public Room(int height, int width){
		this.height=height;
		this.width=width;
	}
	
	public Room(int x, int y,int height, int width){
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
	}
}
