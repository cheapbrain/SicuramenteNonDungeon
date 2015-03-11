package happypotatoes.slickgame;

public class TriLight {
	
	private static float zero = 0.0001f;
	
	boolean type; //true h -- false v
	float dx1;
	float dy1;
	float dx2;
	float dy2;
	float a1, a2;

	public TriLight(float dx1, float dy1, float dx2, float dy2, boolean type) {
		if (dx1==0) this.dx1 = zero;
		else 
			this.dx1 = dx1;
		if (dy1==0) this.dy1 = zero;
		else 
			this.dy1 = dy1;
		if (dx2==0) this.dx2 = zero;
		else 
			this.dx2 = dx2;
		if (dy2==0) this.dy2 = zero;
		else 
			this.dy2 = dy2;
		
		a1 = (float) Math.atan2(dx1, dy1);
		a2 = (float) Math.atan2(dx2, dy2);
		System.out.println(a1);
		System.out.println(a2);
		System.out.println();
	}
	
	public String toString() {
		return String.format("x1 %f y1 %f\nx2 %f y2%f", dx1, dy1, dx2, dy2);
	}
}
