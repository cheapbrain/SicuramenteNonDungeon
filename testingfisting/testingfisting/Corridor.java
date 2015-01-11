package testingfisting;

import java.util.ArrayList;
import java.util.Random;


public class Corridor {
	public ArrayList<Cell> cells= new ArrayList<Cell>();
	int d,rot,c;
	Random r;
	
	public Corridor(Random random,int lenght){
		r=random;
		cells.add(new Cell(r.nextInt(99)+1,r.nextInt(99)+1));
		d=r.nextInt(4);
		System.out.println(d);
		int n;
		for(int i=0; i<lenght; i++){
			rot=0;
			n=r.nextInt(30);
			if((n>1)&&(n<4)){
				rot=1;
			}
			if(n<2){	
				rot=2;
			}
			c=1;
			if(!place(rot)) break;
		}
	}
	
	public boolean straight(){	
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.y-1>0)&&(!touchItself(t.x,t.y-1))){ cells.add(new Cell(t.x,t.y-1)); return true; } else break;
		case 1: if((t.x+1<100)&&(!touchItself(t.x+1,t.y))){ cells.add(new Cell(t.x+1,t.y)); return true; } else break;
		case 2: if((t.y+1<100)&&(!touchItself(t.x,t.y+1))){ cells.add(new Cell(t.x,t.y+1)); return true; } else break;
		case 3: if((t.x-1>0)&&(!touchItself(t.x-1,t.y))){ cells.add(new Cell(t.x-1,t.y)); return true; } else break;
		}
		return false;
	}

	public boolean right(){
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.x+1<100)&&(!touchItself(t.x+1,t.y))){ cells.add(new Cell(t.x+1,t.y)); d=(d+1)%4; return true; } else break;
		case 1: if((t.y+1<100)&&(!touchItself(t.x,t.y+1))){ cells.add(new Cell(t.x,t.y+1)); d=(d+1)%4;  return true; } else break;
		case 2: if((t.x-1>0)&&(!touchItself(t.x-1,t.y))){ cells.add(new Cell(t.x-1,t.y)); d=(d+1)%4; return true; } else break;
		case 3: if((t.y-1>0)&&(!touchItself(t.x,t.y-1))){ cells.add(new Cell(t.x,t.y-1)); d=(d+1)%4; return true; } else break;
		}
		return false;
	}
	
	public boolean left(){	
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.x-1>0)&&(!touchItself(t.x-1,t.y))){ cells.add(new Cell(t.x-1,t.y)); d--; if(d<0) d=3; return true; } else break;
		case 1: if((t.y-1>0)&&(!touchItself(t.x,t.y-1))){ cells.add(new Cell(t.x,t.y-1)); d--; if(d<0) d=3; return true; } else break;
		case 2: if((t.x+1<100)&&(!touchItself(t.x+1,t.y))){ cells.add(new Cell(t.x+1,t.y)); d--; if(d<0) d=3; return true; } else break;
		case 3: if((t.y+1<100)&&(!touchItself(t.x,t.y+1))){ cells.add(new Cell(t.x,t.y+1)); d--; if(d<0) d=3;  return true; } else break;
		}
		return false;
	}
	
	private boolean place(int x){
		switch(x){
		case 0: System.out.println("STRAIGHT");
				if(!straight()){
					if(c<3){
						c++;
						rot=r.nextInt(2)+1;
						place(rot);
					}
					else return false;
				}
				break; //al contrario, quindi priorità all'ultima
		case 1: System.out.println("RIGHT");
				if(!right()){
					if(c<3){
						c++;
						if(c==2) rot=0; else rot=2;
						place(rot);
					}
					else return false;
				}
				break;
		case 2: System.out.println("LEFT");
				if(!left()){
					if(c<3){
						c++;
						if(c==2) rot=0; else rot=1;
						place(rot);
					}
					else return false;
				}
				break;
		}
		return true;
	}

	private boolean touchItself(int x, int y){
		for(int i=0; i<cells.size()-2; i++) 
			if (((x>=cells.get(i).x-1)&&(x<=cells.get(i).x+1)&&(y==cells.get(i).y))||
				((x==cells.get(i).x)&&(y>=cells.get(i).y-1)&&(y<=cells.get(i).y+1)))
			{
			//if((x==cells.get(i).x)&&(y==cells.get(i).y)){
			return true;
			}
		return false;
	}
}
