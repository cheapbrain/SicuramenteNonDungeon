package happypotatoes.slickgame.worldgenerator;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	public static Random r = new Random();
	int seed=314159265;
	public int width=75;
	public int height=75;
	public int corrWidth=2;
	public int nCorr=11;
	public int roomsnumber=25;
	Room rooms[];
	ArrayList<Room> roomList = new ArrayList<Room>();
	ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
	int max=13, min=5; //dimensioni stanze
	public int tempTerrain[][]= new int[width][height];
	public int terrain[][]= new int[width*3][height*3];
	
	public boolean inters(Room a){
		int c=0;
		for(int i=a.x; i<a.x+a.width-1; i++){ 
			if(tempTerrain[i][a.y-1]==0){
				c++;
			}
			if(tempTerrain[i][a.y+a.height]==0){
				c++;
			}
		}
		for(int j=a.y; j<a.y+a.height-1; j++){ 
			if(tempTerrain[a.x-1][j]==0){
				c++;
			}
			if(tempTerrain[a.x+a.width][j]==0){
				c++;
			}
		}
		System.out.print(c+" ");
		if((c>0)&&(c<7)) return false;
		else return true;
	}
	
	public void putRoom(Room a){
		for(int i=a.x; i<a.x+a.width; i++){
			for(int j=a.y; j<a.y+a.height; j++){
				tempTerrain[i][j]=0;
			}
		}
	}
	
	public Generator(){
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				tempTerrain[i][j]=1;
		rooms= new Room[roomsnumber];
	
		r.setSeed(seed);
		
		int h, w;
		for(int i=0; i<roomsnumber; i++){	
			h=(int) (r.nextFloat()*(max-min)+min);
			w=(int) (r.nextFloat()*(max-min)+min);
			rooms[i]= new Room(h, w);
		}
		
		for(int i=0; i<nCorr; i++){
			corridorList.add(new Corridor(100,width,height));
			Corridor a= corridorList.get(corridorList.size()-1);
			for(int j=0; j<a.cells.size(); j++){
				tempTerrain[a.cells.get(j).y][a.cells.get(j).x]=0;
			}
		}
		
		//printAll();
		
		int tries, nstanze=0;
		//sistemare stanze
		for(int i=0; i<roomsnumber; i++){
			tries=0;
			rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
			rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
			while((inters(rooms[i]))&&(tries<100)){
				//System.out.println("RETRY");
				rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
				rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
				tries++;
			}
			if(tries<100){
				//System.out.println("OK");
				putRoom(rooms[i]);
				roomList.add(rooms[i]);
			}
		}
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				terrain[i*corrWidth][j*corrWidth]=tempTerrain[i][j];
				terrain[i*corrWidth+1][j*corrWidth]=tempTerrain[i][j];
				terrain[i*corrWidth][j*corrWidth+1]=tempTerrain[i][j];
				terrain[i*corrWidth+1][j*corrWidth+1]=tempTerrain[i][j];
			}
		}
		
		//printAll();
		
	}
	
	public void printAll(){
		
		for(int i=0; i<height*corrWidth; i++){
			for(int j=0; j<width*corrWidth; j++){
				if(terrain[i][j]==0) System.out.print(" ");
				else System.out.print("#");
			}
			System.out.println();
		}
	}
}
