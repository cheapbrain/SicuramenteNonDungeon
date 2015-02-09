package happypotatoes.slickgame.worldgenerator;

import java.util.ArrayList;

public class Room {
	int x, y;
	int height, width;
	//info varie
	private ArrayList<Cell> corridors = new ArrayList<Cell>();
	private ArrayList<Trap> traps = new ArrayList<Trap>();
	
	public Room(int height, int width){
		this.height=height;
		this.width=width;
	}

	public Room(int x, int y,int height, int width){
		this.x=x;
		this.y=y;
		new Room(height,width);
	}
	
	private void createTraps() {
		int nTraps = Generator.r.nextInt(3);
		for(int i=0; i<nTraps; i++){
			traps.add(new Trap(this));
		}
		for(int i=0; i<corridors.size()-1; i++){
			traps.add(new Trap(corridors.get(i).x,corridors.get(i).y,2));
		}
	}
	
	public void addCorridor(Cell a){
		corridors.add(a);
	}
	public void addCorridor(int a, int b){
		corridors.add(new Cell(a,b));
	}
	public void clearCorridors(){
		corridors.clear();
	}
	public int getCorridorsNumber(){
		return corridors.size();
	}
	
	public boolean intersect(Room b){
		if((this.x+this.width>=b.x-1)&&(this.x-1<=b.x+b.width)&&(this.y+this.height>=b.y-1)&&(this.y-1<=b.y+b.height))
			return true;
		else return false;
	}

	public void generate() {
		createTraps();	
	}
	public ArrayList<Trap> getTraps(){
		return this.traps;
	}

	public void fix() {
		x*=2;
		y*=2;
		width*=2;
		height*=2;
		for(int i=0; i<corridors.size();i++){
			corridors.get(i).x*=2;
			corridors.get(i).y*=2;
		}
	}
}
