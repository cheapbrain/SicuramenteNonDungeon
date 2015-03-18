package happypotatoes.slickgame.monsterbuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class tmp {
/*	private File sourceSpriteFile;
	public static final String path = "./res/Mobs/", spritePathDest = "./res/Sprites/Mobs/";
	private JTextField name,  health, healthRegeneration, type, dangerousness, drop, damage, intelligence, mitigation, skills;
	private JButton confirm, spritePathSource;
	public MonsterBuilder(){
		super("Monster Builder");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		spritePathSource = new JButton("select sprite");
		confirm=new JButton("conferma");
		addTextField(name,"name");
		addTextField(health,"health");
		addTextField(healthRegeneration, "healthRegeneration");
		add(spritePathSource);
		add(confirm);
		initListeners();
		initWindow();
				
	}
	private void initWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.pack();
	}
	private void addTextField(JTextField tf, String name){
		add(new JLabel(name+":"));
		tf=new JTextField();
		add(tf);
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
				File f;
				try{
					if(name.getText().equals("")) throw new Exception();
			        f = new File(spritePathSource.getText());
					if (!f.exists()) throw new Exception();
				}catch(Exception exc){
					System.out.println("inserire tutti i dati validi");
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
*/
}
