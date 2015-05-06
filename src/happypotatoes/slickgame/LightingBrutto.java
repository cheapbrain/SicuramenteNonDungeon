package happypotatoes.slickgame;

import happypotatoes.slickgame.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class LightingBrutto {
	public static LightingBrutto lighting;
	public float[][] finalMap;
	public float[][] lightMap;
	public float[][] tempMap;
	
	private List<Light> lights = new ArrayList<Light>();
	
	int[][] terr;
	int ssx,ssy;
	int w,h;
	
	public LightingBrutto() {
		Rectangle rect = Camera.camera.getRekt();
		int w = (int) rect.w+3;
		int h = (int) rect.h+3;
		finalMap = new float[w][h];
		lightMap = new float[w][h];
		tempMap = new float[w][h];
		lighting = this;
	}

	public float[][] calculateLights(
			int[][] terrain, 
			int w, int h, 
			int sx, int sy, int ex, int ey) {
		
		this.w = w;
		this.h = h;
		ssx = sx;
		ssy = sy;
		
		terr = terrain;
		
		int tx, ty;

		for (tx=0;tx<=w;tx++)			
			for (ty=0;ty<=h;ty++) {
				lightMap[tx][ty] = 0;
				finalMap[tx][ty] = 0;
			}
		

		for (Light light:lights) {
			for (tx=0;tx<=w;tx++)			
				for (ty=0;ty<=h;ty++)
					tempMap[tx][ty] = 0;
			for (tx=sx;tx<ex;tx++) {
				drawLine(light, tx, sy);
				drawLine(light, tx, ey-1);
			}
	
			for (ty=sy+1;ty<ey-1;ty++) {
				drawLine(light, sx, ty);
				drawLine(light, ex-1, ty);
			}

			for (tx=0;tx<=w;tx++)			
				for (ty=0;ty<=h;ty++)
					lightMap[tx][ty] = Math.min(1, lightMap[tx][ty]+tempMap[tx][ty]);
		}
		
		for (tx=0;tx<w;tx++)			
			for (ty=0;ty<h;ty++) {
				
			}
				
		
		
		return lightMap;
	}

	public void drawLine(Light light, int x2, int y2) {
		float r = light.r*light.r;
		int x1 = (int)light.getX();
		int y1 = (int)light.getY();
		float x0 = light.getX();
		float y0 = light.getY();

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;

		int err = dx - dy;

		while (true) {

		    if (terr[x1][y1]!=0) {
		    	break;
		    }
		    float l = (x1-x0+.5f)*(x1-x0+.5f)+(y1-y0+.5f)*(y1-y0+.5f);
			    
		    int xl = x1-ssx;
		    int yl = y1-ssy;
			    
		    if (xl>=0&&xl<w&&yl>=0&&yl<h)
		    	tempMap[xl][yl] =  Math.max((1-(l/r))*light.i,tempMap[xl][yl]);

		    if (x1 == x2 && y1 == y2) {
		        break;
		    }

		    int e2 = 2 * err;

		    if (e2 > -dy) {
		        err = err - dy;
		        x1 = x1 + sx;
		    }

		    if (e2 < dx) {
		        err = err + dx;
		        y1 = y1 + sy;
		    }
		}
			
	}
		
	public void add(Light light) {
		lights.add(light);
	}
}
