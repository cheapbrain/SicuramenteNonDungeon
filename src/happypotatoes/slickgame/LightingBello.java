package happypotatoes.slickgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightingBello {
	private List<Light> lights = new ArrayList<Light>();
	public static LightingBello lighting;

	int[][] terr;
	private float[][] lightMap;
	
	public LightingBello(int[][] terrain) {
		lighting = this;
		terr = terrain;
		lightMap = new float[terr.length+1][terr[0].length+1];
	}
	
	public float[][] calculate() {
		
		for (float[] row: lightMap)
		    Arrays.fill(row, 0);
		
		for (Light light:lights)
			updateLight(light);
		
		return lightMap;
	}
	
	public void add(Light light) {
		lights.add(light);
	}
	
	public float getColorAt(float x, float y) {
		
		int x0 = (int)x;
		int y0 = (int)y;
		int x1 = x0+1;
		int y1 = y0+1;
		
		float kx1 = x-x0;
		float kx0 = 1-kx1;
		float ky1 = y-y0;
		float ky0 = 1-ky1;
		
		float it0 = lightMap[x0][y0]*kx0+lightMap[x1][y0]*kx1;
		float it1 = lightMap[x0][y1]*kx0+lightMap[x1][y1]*kx1;
		
		return it0*ky0+it1*ky1;
	}
	
	public void updateLight(Light light) {
		float cx = light.getX();
		float cy = light.getY();
		float r = light.r;
		float i = light.i;
		
		int sx = (int)Math.ceil(cx-r);
		int sy = (int)Math.ceil(cy-r);
		int ex = (int)Math.ceil(cx+r);
		int ey = (int)Math.ceil(cy+r);
		
		if (sx<0) sx = 0;
		if (sy<0) sy = 0;
		if (ex>terr.length+1) ex = terr.length+1;
		if (ey>terr[0].length+1) ey = terr[0].length+1;
		
		int count = 0;
		for (int y=sy;y<ey;y++)
			for (int x=sx;x<ex;x++,count++) {
				float d = castRay( x, y,cx, cy);
				if (d!=-1)
					lightMap[x][y] = Math.min(1, lightMap[x][y]+1-d/r);
			}
	}
	
	public float castRay(float x1, float y1, float x2, float y2) {
		float dx = x2-x1;
		float dy = y2-y1;
		float d = (float)Math.sqrt(dx*dx+dy*dy)+0.0000001f;
		
		
		if ((int)x1==(int)x2&&(int)y1==(int)y2)
			return d;
		
		int ix, iy;
		float sdx, sdy;
		if (dx>0) {
			ix = +1;
			sdx = (int)x1+1-x1;
		} else {
			x1 -= 0.00001;
			ix = -1;
			sdx = x1-(int)x1;
		}
		if (dy>0) {
			iy = +1;
			sdy = (int)y1+1-y1;
		} else {
			y1 -= 0.00001;
			iy = -1;
			sdy = y1-(int)y1;
		}

		int x = (int)x1;
		int y = (int)y1;
		
		float stepx = d/Math.abs(dx);
		float stepy = d/Math.abs(dy);
		
		float countx = stepx*sdx;
		float county = stepy*sdy;
		
				
		while(true) {
			if (terr[x][y]==1)
				return -1;
			if (countx>=d&&county>=d)
				return d;
			if (countx<county) {
				x += ix;
				countx += stepx;
			} else {
				y += iy;
				county += stepy;
			}
		}
	}
}
