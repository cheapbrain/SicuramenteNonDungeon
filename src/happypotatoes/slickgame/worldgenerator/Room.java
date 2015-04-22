package happypotatoes.slickgame.worldgenerator;


import java.util.ArrayList;
import java.util.Collection;

public class Room {
	public int x, y;
	public int height, width;
	//info varie
	private ArrayList<Cell> corridors = new ArrayList<Cell>();
	//private ArrayList<StaticEntity> staticEntities = new ArrayList<StaticEntity>();
	
	public Room(int width, int height){
		this.width=width;
		this.height=height;
	}

	public Room(int x, int y, int width, int height){
		this(width,height);
		this.x=x;
		this.y=y;
	}
	
	/*
	private void createPlates() {
		int nTraps = Generator.getR().nextInt(3);
		for(int i=0; i<nTraps; i++){
			staticEntities.add(new Plate(this));
		}
	}
	*/
	
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
	
	public boolean attempt(int[][] tempTerrain, ArrayList<Room> roomList){
		for(int i=this.x; i<this.x+this.width-1; i++){ 
			if(tempTerrain[i][this.y-1]==0) this.addCorridor(i, this.y-1);
			if(tempTerrain[i][this.y+this.height]==0) this.addCorridor(i, this.y+this.height);
		}
		for(int j=this.y; j<this.y+this.height-1; j++){ 
			if(tempTerrain[this.x-1][j]==0) this.addCorridor(this.x-1, j);
			if(tempTerrain[this.x+this.width][j]==0) this.addCorridor(this.x+this.width,j);
		}
		//intersezione tra stanze o angolo con corridoio
		for(int i=0; i<roomList.size(); i++){
			Room b = roomList.get(i);
			if(this.intersect(b))
				this.clearCorridors();
			if((tempTerrain[this.x-1][this.y-1]==0)||(tempTerrain[this.x-1][this.y+this.height]==0)||
					(tempTerrain[this.x+this.width][this.y-1]==0)||(tempTerrain[this.x+this.width][this.y+this.height]==0))
				this.clearCorridors();
		}	
		if((this.getCorridorsNumber()>0)&&(this.getCorridorsNumber()<5)) return false;
		else return true;
	}
	
	public boolean intersect(Room b){
		if((this.x+this.width>=b.x-1)&&(this.x-1<=b.x+b.width)&&(this.y+this.height>=b.y-1)&&(this.y-1<=b.y+b.height))
			return true;
		else return false;
	}
	
	/*
	public void generate() {
		createPlates();	
	}
	public ArrayList<StaticEntity> getTraps(){
		return this.staticEntities;
	}
	*/

	public void fix() {
		x*=2;
		y*=2;
		width*=2;
		height*=2;
		for(int i=0; i<corridors.size();i++){
			corridors.get(i).setX(corridors.get(i).getX() * 2);
			corridors.get(i).setY(corridors.get(i).getY() * 2);
		}
	}

	/*
	public void addStairs() {
		staticEntities.add(new Stairs(this));	
	}
	*/

}
