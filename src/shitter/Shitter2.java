package shitter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Shitter2 implements ActionListener{
	JFrame f;
	JFileChooser fc = new JFileChooser();
	JTextField field, frames, name;
	
	String[] dirs = new String[]{
		"UP",
		"UPSX",
		"SX",
		"DOSX",
		"DO",
		"DODX",
		"DX",
		"UPDX",
	};
	
	public static void main(String[] args) {
		new Shitter2();
	}

	public Shitter2() {
		f = new JFrame("Shitter 2000");
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.getContentPane().setPreferredSize(new Dimension(400, 200));
		f.pack();
		
		JButton button = new JButton("File");
		button.setBounds(10, 10, 100, 30);
		button.addActionListener(this);
		f.add(button);
		
		field = new JTextField("");
		field.setBounds(10, 50, 380, 30);
		f.add(field);
		
		JLabel label = new JLabel("Frames");
		label.setBounds(10, 90, 50, 30);
		f.add(label);
		
		frames = new JTextField("12");
		frames.setBounds(60, 90, 60, 30);
		f.add(frames);
		
		label = new JLabel("Name");
		label.setBounds(145, 90, 50, 30);
		f.add(label);
		
		name = new JTextField("");
		name.setBounds(190, 90, 140, 30);
		f.add(name);
		
		button = new JButton("CACCA");
		button.setBounds(10, 130, 100, 30);
		button.addActionListener(this);
		f.add(button);
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String butt = e.getActionCommand();
		if (butt.equals("File")) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (fc.showOpenDialog(f)==JFileChooser.APPROVE_OPTION)
				field.setText(fc.getSelectedFile().getAbsolutePath());
		} else
		if (butt.equals("CACCA")) {
			String path = field.getText()+"\\";
			int frames = Integer.parseInt(this.frames.getText());
			String name  = this.name.getText();
			
			int width = 64;
			int height = 128;

			File[] files = new File(path).listFiles();
			BufferedImage ts;
			try {
				ts = ImageIO.read(files[0]);
				width = ts.getWidth();
				height = ts.getHeight();
			} catch (IOException e2) {}
			
			BufferedImage image = new BufferedImage(frames*width, dirs.length*height, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g = (Graphics2D)image.getGraphics();
			
			g.setBackground(new Color(0,0,0,0));
			g.clearRect(0, 0, image.getWidth(), image.getHeight());
			 
			for (int i=0;i<dirs.length;i++) {
				for (int k=0;k<frames;k++) {
					try {
						BufferedImage sprite;
						if (frames>1)
							sprite = ImageIO.read(new File(path+name+" "+dirs[i]+" ("+(k+1)+").png"));
						else
							sprite = ImageIO.read(new File(path+name+" "+dirs[i]+".png"));
						g.drawImage(sprite, k*width, i*height, null);
					} catch (IOException e1) {
						System.out.println(path+name+dirs[i]+" ("+k+")");
					}
				}
			}
			
			try {
				ImageIO.write(image, "PNG", new File(this.name.getText()+".png"));
				JOptionPane.showMessageDialog(f, "L'ho fatta tutta!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
