package ModBuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ModBuilder extends JFrame{
	private final int whidth=900, height=300;
	private String modPath = null;
	private JPanel MOBuilder, ITEMBuilder, SKILLBuilder;
	public ModBuilder(){
		super("ModBuilder");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION){
            modPath=fc.getSelectedFile().getPath();
		}
		this.setSize(new Dimension(whidth,height));
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		MOBuilder = new MyPanel("res/ICON_1.png", MOBuilder.class, modPath);
		ITEMBuilder = new MyPanel("res/ICON_2.png",ItemBuilder.class, modPath);
		SKILLBuilder = new MyPanel("res/ICON_3.png",SkillBuilder.class, modPath);
		add(MOBuilder);
		add(ITEMBuilder);
		add(SKILLBuilder);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String args[]){
		ModBuilder m = new ModBuilder();
	}
}