package happypotatoes.slickgame.worldgenerator;

import java.util.ArrayList;

import java.util.Random; 

public class Corridor {
	private ArrayList<Cell> cells= new ArrayList<Cell>();
	private int d,rot,c;
	private int w,h;
	private int turnProb=24;
	
	public Corridor(int lenght,int w,int h){
		cells.add(new Cell(Generator.getR().nextInt(w-2)+1,Generator.getR().nextInt(h-2)+1));
		d=Generator.getR().nextInt(4);
		int n;
		for(int i=0; i<lenght; i++){
			rot=0;
			n=Generator.getR().nextInt(100);
			if(n<turnProb/2){
				rot=1;
			}
			if(n>100-(turnProb/2)){	
				rot=2;
			}
			c=1;
			if(!place(rot)) break;
		}
	}
	
	public boolean straight(){	
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.getY()-1>0)&&(!touchItself(t.getX(),t.getY()-1))){ cells.add(new Cell(t.getX(),t.getY()-1)); return true; } else break;
		case 1: if((t.getX()+1<w-1)&&(!touchItself(t.getX()+1,t.getY()))){ cells.add(new Cell(t.getX()+1,t.getY())); return true; } else break;
		case 2: if((t.getY()+1<h-1)&&(!touchItself(t.getX(),t.getY()+1))){ cells.add(new Cell(t.getX(),t.getY()+1)); return true; } else break;
		case 3: if((t.getX()-1>0)&&(!touchItself(t.getX()-1,t.getY()))){ cells.add(new Cell(t.getX()-1,t.getY())); return true; } else break;
		}
		return false;
	}

	public boolean right(){
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.getX()+1<w-1)&&(!touchItself(t.getX()+1,t.getY()))){ cells.add(new Cell(t.getX()+1,t.getY())); d=(d+1)%4; return true; } else break;
		case 1: if((t.getY()+1<h-1)&&(!touchItself(t.getX(),t.getY()+1))){ cells.add(new Cell(t.getX(),t.getY()+1)); d=(d+1)%4;  return true; } else break;
		case 2: if((t.getX()-1>0)&&(!touchItself(t.getX()-1,t.getY()))){ cells.add(new Cell(t.getX()-1,t.getY())); d=(d+1)%4; return true; } else break;
		case 3: if((t.getY()-1>0)&&(!touchItself(t.getX(),t.getY()-1))){ cells.add(new Cell(t.getX(),t.getY()-1)); d=(d+1)%4; return true; } else break;
		}
		return false;
	}
	
	public boolean left(){	
		Cell t=cells.get(cells.size()-1);
		switch(d){
		case 0: if((t.getX()-1>0)&&(!touchItself(t.getX()-1,t.getY()))){ cells.add(new Cell(t.getX()-1,t.getY())); d--; if(d<0) d=3; return true; } else break;
		case 1: if((t.getY()-1>0)&&(!touchItself(t.getX(),t.getY()-1))){ cells.add(new Cell(t.getX(),t.getY()-1)); d--; if(d<0) d=3; return true; } else break;
		case 2: if((t.getX()+1<w-1)&&(!touchItself(t.getX()+1,t.getY()))){ cells.add(new Cell(t.getX()+1,t.getY())); d--; if(d<0) d=3; return true; } else break;
		case 3: if((t.getY()+1<h-1)&&(!touchItself(t.getX(),t.getY()+1))){ cells.add(new Cell(t.getX(),t.getY()+1)); d--; if(d<0) d=3;  return true; } else break;
		}
		return false;
	}
	
	private boolean place(int x){
		switch(x){
		case 0: 
				if(!straight()){
					if(c<3){
						c++;
						rot=Generator.getR().nextInt(2)+1;
						place(rot);
					}
					else return false;
				}
				break; //al contrario, quindi priorità all'ultima
		case 1:
				if(!right()){
					if(c<3){
						c++;
						if(c==2) rot=0; else rot=2;
						place(rot);
					}
					else return false;
				}
				break;
		case 2:
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
			if (((x>=cells.get(i).getX()-1)&&(x<=cells.get(i).getX()+1)&&(y>=cells.get(i).getY()-1)&&(y<=cells.get(i).getY()+1)))
			return true;
		return false;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getRot() {
		return rot;
	}

	public void setRot(int rot) {
		this.rot = rot;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getTurnProb() {
		return turnProb;
	}

	public void setTurnProb(int turnProb) {
		this.turnProb = turnProb;
	}
}
