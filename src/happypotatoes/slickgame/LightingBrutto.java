package happypotatoes.slickgame;

import java.util.ArrayList;
import java.util.List;

public class LightingBrutto {
	private float[][] lightMap = new float[1][1];
	
	private List<Light> lights = new ArrayList<Light>();
	
	int[][] terr;

	public float[][] calculateLights(
			int[][] terrain, 
			int w, int h, 
			int sx, int sy, int ex, int ey) {
		
		terr = terrain;
		
		if (lightMap.length!=h||lightMap[0].length!=w)
			lightMap = new float[w+1][h+1];
		
		int tx, ty;
		
		for (tx=sx;tx<ex;tx++)			
			for (ty=sy;ty<ey;ty++) {
					lightMap[tx-sx][ty-sy] = lightValue(tx, ty);
			}
		
		return lightMap;
	}
	
	public float lightValue(int tx, int ty) {
		float out = 0;
		for (Light light:lights) {
			float r = light.getR();
			float sx = light.getX();
			float sy = light.getY();
			float lx = tx-sx;
			float ly = ty-sy;
			float l = (float)Math.sqrt(lx*lx+ly*ly);
			if (r<l)
				continue;
				
			float stepx = l/lx;
			float stepy = l/ly;

			float dx = Math.round(sx)-sx;
			float dy = Math.round(sy)-sy;
			
			out += 1-(l/r)*(l/r);
		}
		return out;
	}
	
	public void add(Light light) {
		lights.add(light);
	}
}
