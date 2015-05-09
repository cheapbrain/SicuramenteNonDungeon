package happypotatoes.slickgame.worldgenerator;


import happypotatoes.slickgame.entitysystem.Entity;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
	private static Random r = new Random();
	private int seed=19921;
	private int width;
	private int height;
	private int corrWidth=2;
	private int nCorr, nRooms;
	private Room rooms[];
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
	private ArrayList<Entity> allMobs = new ArrayList<Entity>();
	private int maxW=13, minW=5, maxH=13, minH=5; //dimensioni stanze
	private int tempTerrain[][];
	private int terrain[][];
	
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
			for(int j=0; j<a.getCells().size(); j++){
				tempTerrain[a.getCells().get(j).getY()][a.getCells().get(j).getX()]=0;
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
		//roomList.get(r.nextInt(roomList.size()-1)).addStairs();
		
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
			allMobs.addAll(roomList.get(i).getMobs());
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

	public static Random getR() {
		return r;
	}

	public static void setR(Random r) {
		Generator.r = r;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCorrWidth() {
		return corrWidth;
	}

	public void setCorrWidth(int corrWidth) {
		this.corrWidth = corrWidth;
	}

	public int getnCorr() {
		return nCorr;
	}

	public void setnCorr(int nCorr) {
		this.nCorr = nCorr;
	}

	public int getnRooms() {
		return nRooms;
	}

	public void setnRooms(int nRooms) {
		this.nRooms = nRooms;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}

	public ArrayList<Corridor> getCorridorList() {
		return corridorList;
	}

	public void setCorridorList(ArrayList<Corridor> corridorList) {
		this.corridorList = corridorList;
	}

	public ArrayList<Entity> getAllMobs() {
		return allMobs;
	}

	public void setAllMobs(ArrayList<Entity> allMobs) {
		this.allMobs = allMobs;
	}
	
	public int getMaxW() {
		return maxW;
	}

	public void setMaxW(int maxW) {
		this.maxW = maxW;
	}

	public int getMinW() {
		return minW;
	}

	public void setMinW(int minW) {
		this.minW = minW;
	}

	public int getMaxH() {
		return maxH;
	}

	public void setMaxH(int maxH) {
		this.maxH = maxH;
	}

	public int getMinH() {
		return minH;
	}

	public void setMinH(int minH) {
		this.minH = minH;
	}

	public int[][] getTempTerrain() {
		return tempTerrain;
	}

	public void setTempTerrain(int[][] tempTerrain) {
		this.tempTerrain = tempTerrain;
	}

	public int[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(int[][] terrain) {
		this.terrain = terrain;
	}
}
