package happypotatoes.slickgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class CustomRender {
	protected static SGL GL = Renderer.get();

	public static Color color = new Color(0,0,0,1);
	public static void draw(Image image, float x, float y, float w, float h, float i0, float i1, float i2, float i3) {

		float sx0 = image.getTextureOffsetX();
		float sy0 = image.getTextureOffsetY();
		float sx1 = sx0+image.getTextureWidth();
		float sy1 = sy0+image.getTextureHeight();
		
		image.bind();
        GL.glBegin(SGL.GL_QUADS); 
        
        setColor(i0);
		GL.glTexCoord2f(sx0, sy0);
		GL.glVertex3f(x,y, 0.0f);

        setColor(i1);
		GL.glTexCoord2f(sx0, sy1);
		GL.glVertex3f(x,(y + h), 0.0f);

        setColor(i2);
		GL.glTexCoord2f(sx1, sy1);
		GL.glVertex3f((x + w),(y + h), 0.0f);

        setColor(i3);
		GL.glTexCoord2f(sx1, sy0);
		GL.glVertex3f((x + w),y, 0.0f);
		
        GL.glEnd(); 
	}
	
	public static void fill(float x, float y, float w, float h, float i0, float i1, float i2, float i3) {
		//GL.glDisable(GL.GL_TEXTURE_2D);
		
        GL.glBegin(SGL.GL_QUADS); 
        
        setColor(i0);
		GL.glVertex3f(x,y, 0.0f);

        setColor(i1);
		GL.glVertex3f(x,(y + h), 0.0f);

        setColor(i2);
		GL.glVertex3f((x + w),(y + h), 0.0f);

        setColor(i3);
		GL.glVertex3f((x + w),y, 0.0f);
		
        GL.glEnd(); 
	}
	
	public static void setColor(float i) {
		color.r = i;
		color.g = i;
		color.b = i;
		color.a = 1;
		color.bind();
	}
}
