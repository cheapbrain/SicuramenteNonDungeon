package happypotatoes.slickgame.worldgenerator;


import happypotatoes.slickgame.entitysystem.Entity;
import happypotatoes.slickgame.entitysystem.entity.Chest;
import happypotatoes.slickgame.entitysystem.entity.FighterEntity;
import happypotatoes.slickgame.entitysystem.entity.Mowse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Room {
	public int x, y;
	public int height, width;
	//info varie
	private ArrayList<Cell> corridors = new ArrayList<Cell>();
	private ArrayList<Entity> mobs = new ArrayList<Entity>();
	
	public Room(int width, int height){
		this.width=width;
		this.height=height;
	}

	public Room(int x, int y, int width, int height){
		this(width,height);
		this.x=x;
		this.y=y;
	}
	
	private void createMobs() {
		Random r = new Random();
		int nMobs = r.nextInt(6)+1;
		for(int i=0; i<nMobs; i++){
			float x = r.nextInt(width)+this.x+.5f;
			float y = r.nextInt(height)+this.y+.5f;
			Entity e;
			if(r.nextInt(2)==0) e = Mowse.create(x, y);
			else e = FighterEntity.create(x, y);
			mobs.add(e);
		}
		int nChests = r.nextInt(2)+1;
		float x=this.x; float y=this.y; int f=0;
		for(int i=0; i<nChests; i++){
			int wall = r.nextInt(3);
			switch(wall){
			case 0: 
				x = this.x+.5f;
				y = r.nextInt(height-1)+this.y+.5f;
				f = 6;
				if(y==this.y+.5f) f=5;
				break;
			case 1:
				x = r.nextInt(width-1)+this.x+.1f;
				y = this.y+.5f;
				f = 4;
				if(x==this.x+.5f) f=5;
				else if(x==width+this.x-1f) f=3;
				break;
			case 2:
				x = width+this.x-.5f;
				y = r.nextInt(height-1)+this.y+.5f;
				f = 2;
				if(y==this.y+.5f) f=3;
				break;
			}
			Entity e = Chest.create(x,y,f);
			mobs.add(e);
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
	
	public void generate() {
		createMobs();	
	}
	public ArrayList<Entity> getMobs(){
		return this.mobs;
	}

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
