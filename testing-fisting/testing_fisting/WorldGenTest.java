package testing_fisting;

public class WorldGenTest {
	int seed=2511;
	int width=100, height=100;
	int roomsnumber;
	Room rooms[];
	int terrain[][]= new int[100][100];
	
	public WorldGenTest(){
		/*for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				terrain[j][i] = (int) (ImprovedNoise.noise((j)/10d,(i)/10d,(double)seed)+1d);
				if(terrain[j][i]>0) System.out.print("O");
				else System.out.print(" ");
			}
			System.out.println();
		}
		*/
		roomsnumber=((100*100)/1000)+(seed%7)-3;
		rooms= new Room[roomsnumber];
		int h, w;
		
		System.out.println("Numero stanze: "+roomsnumber);
	
		for(int i=1; i<=roomsnumber; i++){	
			h=(((seed<<(i+seed))%(i+seed))/3+2)%1+2;
			w=(((seed<<((i+seed)/2))%(i+seed))/3+2)%1+2;
			h*=10;
			w*=10;
			h+=(((seed<<(i+seed))%(i+seed))/3)%9;
			w+=(((seed<<((i+seed)/2))%(i+seed))/3-10)%9;
			rooms[i-1]= new Room(h, w);
			System.out.println(rooms[i-1].height+"  "+rooms[i-1].width);
			// if(h>32||w>32) System.out.println("seed:"+seed+"  TOO BIG");
			// if(h<11||w<11) System.out.println("seed:"+seed+"  TOO SMALL");
		}
		
		//sistemare stanze
		
		int points[][]; //coordinate
		
		//possibilità 1: punti lontani
		points= new int[][]{
			{0,0},{0,width},{height,width},{height,0},{0,width/2},{height/2,width},{height,width/2},{height/2,0},
			{height/4,width/4},{3*height/4,3*width/4},{height/4,3*width/4},{3*height/4,width/4},{height/2,width/2}
		};
		
		//possibilità 2: offset relativo
		int l=(int) Math.sqrt(roomsnumber);
		points= new int[l][l+1];
		int lastHeight[]=new int[l], lastWidth[]=new int[l+1];
		int totHeight[]=new int[l], totWidth[]=new int[l+1];
		for(int i=0; i<l; i++) totHeight[i]=0;
		for (int j=0; j<l+1; j++) totWidth[j]=0;
		rooms[0].x=0; rooms[0].y=0;
		for(int i=0; i<l; i++){
			for(int j=0; j<l+1; j++){
				if(i*l+j<roomsnumber){
					totHeight[i]+=rooms[i*l+j].height;
					totWidth[j]+=rooms[i*l+j].width;
					lastHeight[i]=rooms[i*l+j].height;
					lastWidth[j]=rooms[i*l+j].width;
					if((i*l+j)%!=0) {
						rooms[i*l+j].x=rooms[i*l+j-1].x+rooms[i*l+j-1].width+(width-lastWidth[j]-totWidth[j])/(roomsnumber-1);
						rooms[i*l+j].y=rooms[i*l+j-1].y+rooms[i*l+j-1].height+(height-lastHeight[i]-totHeight[i])/(roomsnumber-1);
						System.out.print(rooms[i*l+j].x+";"+rooms[i*l+j].y+"  ");
					}		
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
	 new WorldGenTest();
	}
}
