package ModBuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private BufferedImage image;
	public MyPanel(String path, final Class c, final String modPath){
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				if (c.equals(MOBuilder.class)) new MOBuilder(modPath);
				if (c.equals(SkillBuilder.class)) new SkillBuilder();
				if (c.equals(ItemBuilder.class)) new ItemBuilder();
			}
			public void mouseEntered(MouseEvent arg0) {
				setBorder(BorderFactory.createLineBorder(Color.black));
			}
			public void mouseExited(MouseEvent arg0) {
				setBorder(null);
			}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);           
    }
}
