package testing_fisting;

import java.util.Random;

public class WorldGenTest {
	int seed=137;
	int width=100, height=100;
	int roomsnumber=100;
	Room rooms[];
	int max=12, min=7; //dimensioni stanze
	int terrain[][]= new int[width][height];
	
	public boolean inters(Room a, int[][] terrain){
		for(int i=a.x-a.leftSpace; i<a.x+a.width+a.rightSpace; i++){
			for(int j=a.y-a.topSpace; j<a.y+a.height+a.downSpace; j++){
				if((i>0)&&(i<width)&&(j>0)&&(j<height)) if(terrain[i][j]==0) return true;
			}
		}
		return false;
	}
	
	public void putRoom(Room a){
		for(int i=a.x; i<a.x+a.width; i++){
			for(int j=a.y; j<a.y+a.height; j++){
				terrain[i][j]=0;
			}
		}
	}
	
	public WorldGenTest(){
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				terrain[i][j]=1;
		rooms= new Room[roomsnumber];
		int h, w;
		
		System.out.println("Numero stanze: "+roomsnumber);
	
		Random r= new Random();
		r.setSeed(seed);
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
		int tries;
		//sistemare stanze
		for(int i=0; i<roomsnumber; i++){
			tries=0;
			rooms[i].x=(int) (r.nextFloat()*(width-rooms[i].width-2)+1);
			rooms[i].y=(int) (r.nextFloat()*(height-rooms[i].height-2)+1);
			while((inters(rooms[i], terrain))&&(tries<100)){
				rooms[i].x=(int) (r.nextFloat()*(width-rooms[i].width-2)+1);
				rooms[i].y=(int) (r.nextFloat()*(height-rooms[i].height-2)+1);
				tries++;
			}
			if(tries<100){
				putRoom(rooms[i]);
				rooms[i].corridornumb=(int) (r.nextFloat()*Math.sqrt(rooms[i].width*rooms[i].height)/2)+1;
				//System.out.println(rooms[i].width+" "+rooms[i].height+" "+rooms[i].corridornumb);
			}
		}
		
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++)
				if(terrain[i][j]==0) System.out.print(" ");
				else System.out.print("#");
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	 new WorldGenTest();
	}
}
