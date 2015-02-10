package happypotatoes.slickgame.monsterbuilder;

import happypotatoes.slickgame.entity.Mob;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MonsterBuilder extends JFrame{
	private File sourceSpriteFile;
	public static final String path = "./res/Mobs/", spritePathDest = "./res/Sprites/Mobs/";
	private JTextField name,  health, type, dangerousness, drop, damage, intelligence, mitigation, skills;
	private JButton confirm, spritePathSource;
	public MonsterBuilder(){
		super("Monster Builder");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		name=new JTextField();
		health = new JTextField();
		spritePathSource = new JButton("select sprite");
		confirm=new JButton("conferma");
		add(new JLabel("Name: "));
		add(name);
		add(new JLabel("Health: "));
		add(health);
		add(spritePathSource);
		add(new JLabel("Type: "));
		add(new JLabel("Dangerousness: "));
		add(new JLabel("Drop: "));
		add(new JLabel("Damage: "));
		add(new JLabel("Intelligence: "));
		add(new JLabel("Mitigation: "));
		add(new JLabel("Skills: "));
		add(new JLabel("Folder: "));
		add(confirm);
		initListeners();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.pack();		
	}
	private void initListeners() {
		spritePathSource.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				int returnVal = fc.showOpenDialog(spritePathSource);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            sourceSpriteFile = fc.getSelectedFile();
		            spritePathSource.setText(sourceSpriteFile.getPath());
		        }
			}
		});
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int hp;
				File f;
				try{
					if(name.getText().equals("")) throw new Exception();
					hp = Integer.parseInt(health.getText());
			        f = new File(spritePathSource.getText());
					if (!f.exists()) throw new Exception();
				}catch(Exception exc){
					System.out.println("inserire un valore di salute valido");
					return;
				}
				f = new File(path+name.getText()+".mob");
				File f1 = null;
				try {
					f1=new File(spritePathDest+name.getText()+sourceSpriteFile.getName().substring(sourceSpriteFile.getName().lastIndexOf('.')));
					copyFileUsingFileChannels(sourceSpriteFile,f1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
			        Properties props = new Properties();
			        props.setProperty("Name", name.getText());
			        props.setProperty("Health", ""+health.getText());
			        props.setProperty("SpritePath",f1.getPath());
			        OutputStream out = new FileOutputStream( f );
			        props.store(out, "commento");
			    }
			    catch (Exception e1 ) {
			        e1.printStackTrace();
			    }	
			}	
		});
		
	}
	private void copyFileUsingFileChannels(File source, File dest)
			throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
}
