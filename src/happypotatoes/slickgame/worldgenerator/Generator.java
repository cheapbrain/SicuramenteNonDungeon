package happypotatoes.slickgame.worldgenerator;

import happypotatoes.slickgame.entity.StaticEntity;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	public static Random r = new Random();
	public int seed=19921;
	public int width;
	public int height;
	public int corrWidth=2;
	public int nCorr, nRooms;
	public Room rooms[];
	public ArrayList<Room> roomList = new ArrayList<Room>();
	public ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
	public ArrayList<StaticEntity> allTraps = new ArrayList<StaticEntity>();
	public int maxW=13, minW=5, maxH=13, minH=5; //dimensioni stanze
	public int tempTerrain[][];
	public int terrain[][];
	
	public void putRoom(Room a){
		for(int i=a.x; i<a.x+a.width; i++){
			for(int j=a.y; j<a.y+a.height; j++){
				tempTerrain[i][j]=0;
			}
		}
	}
	
	public Generator(int w, int h, int complexity){
		width=w;
		height=h;
		complexity=Math.abs(complexity%11);
		nRooms=2*complexity+2;
		nCorr=complexity+1;
		
		tempTerrain= new int[width][height];
		terrain = new int[width*corrWidth][height*corrWidth];
		
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				tempTerrain[i][j]=1;
		rooms= new Room[nRooms];
	
		r.setSeed(seed);
		
		int W, H;
		for(int i=0; i<nRooms; i++){	
			H=(int) (r.nextFloat()*(maxH-minH)+minH);
			W=(int) (r.nextFloat()*(maxW-minW)+minW);
			rooms[i]= new Room(W, H);
		}
		
		//sistemare corridoi
		for(int i=0; i<nCorr; i++){
			corridorList.add(new Corridor(100,width,height));
			Corridor a= corridorList.get(corridorList.size()-1);
			for(int j=0; j<a.cells.size(); j++){
				tempTerrain[a.cells.get(j).y][a.cells.get(j).x]=0;
			}
		}
		
		//sistemare stanze
		int tries;
		for(int i=0; i<nRooms; i++){
			tries=0;
			rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
			rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
			while((rooms[i].attempt(tempTerrain, roomList))&&(tries<100)){
				rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
				rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
				tries++;
			}
			if(tries<100){
				putRoom(rooms[i]);
				roomList.add(rooms[i]);
			}
		}
		roomList.get(r.nextInt(roomList.size()-1)).addStairs();
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				terrain[i*corrWidth][j*corrWidth]=tempTerrain[i][j];
				terrain[i*corrWidth+1][j*corrWidth]=tempTerrain[i][j];
				terrain[i*corrWidth][j*corrWidth+1]=tempTerrain[i][j];
				terrain[i*corrWidth+1][j*corrWidth+1]=tempTerrain[i][j];
			}
		}		
		
		for(int i=0; i<roomList.size()-1; i++){
			roomList.get(i).fix();
			roomList.get(i).generate();
			allTraps.addAll(roomList.get(i).getTraps());
		}
	}
	
	public Generator(){
		this(75,75,10);
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
