package happypotatoes.slickgame.worldgenerator;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenTest {
	int seed=17817;
	public int width=50, height=50;
	int roomsnumber=20;
	Room rooms[];
	ArrayList<Room> roomList = new ArrayList<Room>();
	ArrayList<Corridor> corridorList = new ArrayList<Corridor>();
	int max=10, min=4; //dimensioni stanze
	public int tempTerrain[][]= new int[width][height];
	public int terrain[][]= new int[width*2][height*2];
	
	public boolean inters(Room a, int[][] tempTerrain){
		for(int i=a.x-a.leftSpace; i<a.x+a.width+a.rightSpace; i++){
			for(int j=a.y-a.topSpace; j<a.y+a.height+a.downSpace; j++){
				if((i>0)&&(i<width)&&(j>0)&&(j<height)) if(tempTerrain[i][j]==0) return true;
			}
		}
		return false;
	}
	
	public void putRoom(Room a){
		for(int i=a.x; i<a.x+a.width; i++){
			for(int j=a.y; j<a.y+a.height; j++){
				tempTerrain[i][j]=0;
			}
		}
	}
	
	public WorldGenTest(){
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				tempTerrain[i][j]=1;
		rooms= new Room[roomsnumber];
		
		Random r= new Random();
		r.setSeed(seed);
		
		  int h, w;
		for(int i=0; i<roomsnumber; i++){	
			h=(int) (r.nextFloat()*(max-min)+min);
			w=(int) (r.nextFloat()*(max-min)+min);
			rooms[i]= new Room(h, w);
			rooms[i].topSpace=(int) (r.nextFloat()*(6)+1);
			rooms[i].rightSpace=(int) (r.nextFloat()*(6)+1);
			rooms[i].downSpace=(int) (r.nextFloat()*(6)+1);
			rooms[i].leftSpace=(int) (r.nextFloat()*(6)+1);
		}
		
		System.out.println();
		int tries, nstanze=0;
		//sistemare stanze
		for(int i=0; i<roomsnumber; i++){
			tries=0;
			rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
			rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
			while((inters(rooms[i], tempTerrain))&&(tries<100)){
				rooms[i].x=2*((int) (0.5*(r.nextFloat()*(width-rooms[i].width-4)+2)));
				rooms[i].y=2*((int) (0.5*(r.nextFloat()*(height-rooms[i].height-4)+2)));
				tries++;
			}
			if(tries<100){
				putRoom(rooms[i]);
				roomList.add(rooms[i]);
			}
		}
		
		/*
		//bind dei corridoi
		System.out.println("Numero stanze: "+roomList.size());		
		int n =(int) (Math.floor(Math.log(roomList.size())/Math.log(2)));		 		
		for(int j=1; j<=n; j++)
			for(int i=0; i<Math.floor(roomList.size()/Math.pow(2, j)); i++){
				roomList.get((int) (i*Math.pow(2, j)+Math.floor(Math.pow(2, j-1)/2))).bind[j]=(int) (i*Math.pow(2, j)+Math.pow(2, j-1)+Math.floor(Math.pow(2, j-1)/2));
				roomList.get((int) (i*Math.pow(2, j)+Math.pow(2, j-1)+Math.floor(Math.pow(2, j-1)/2))).bind[j]=(int) (i*Math.pow(2, j-1)+Math.floor(Math.pow(2, j-1)/2));				
				System.out.println((int) (i*Math.pow(2, j)+Math.floor(Math.pow(2, j-1)/2))+" bind with "+(int) (i*Math.pow(2, j)+Math.pow(2, j-1)+Math.floor(Math.pow(2, j-1)/2)));
			}
		//ultima stanza, se dispari
		if(roomList.size()%2!=0){
			roomList.get(0).bind[1]=roomList.size()-1;
			roomList.get(roomList.size()-1).bind[0]=0;
		}
		*/
		
		for(int i=0; i<5; i++){
			corridorList.add(new Corridor(r,100));
			Corridor a= corridorList.get(corridorList.size()-1);
			for(int j=0; j<a.cells.size(); j++){	
				tempTerrain[a.cells.get(j).y][a.cells.get(j).x]=0;
			}
		}
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				terrain[i*2][j*2]=tempTerrain[i][j];
				terrain[i*2+1][j*2]=tempTerrain[i][j];
				terrain[i*2][j*2+1]=tempTerrain[i][j];
				terrain[i*2+1][j*2+1]=tempTerrain[i][j];
			}
		}
		for(int i=0; i<height*2; i++){
			for(int j=0; j<width*2; j++){
				if(terrain[i][j]==0) System.out.print(" ");
				else System.out.print("#");
			}
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	 new WorldGenTest();
	}
}
