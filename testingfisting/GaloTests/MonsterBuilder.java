package happypotatoes.slickgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MonsterBuilder extends JFrame{
	private JTextField name, health, type, dangerousness, drop, damage, intelligence, mitigation, skills, folder;
	private JButton confirm;
	public MonsterBuilder(){
		super("Monser Builder");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		name=new JTextField();
		health = new JTextField();
		folder = new JTextField();
		confirm=new JButton("conferma");
		add(new JLabel("Name: "));
		add(name);
		add(new JLabel("Health: "));
		add(health);
		add(new JLabel("Type: "));
		add(new JLabel("Dangerousness: "));
		add(new JLabel("Drop: "));
		add(new JLabel("Damage: "));
		add(new JLabel("Intelligence: "));
		add(new JLabel("Mitigation: "));
		add(new JLabel("Skills: "));
		add(new JLabel("Folder: "));
		add(folder);
		add(confirm);
		initListeners();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.pack();		
	}
	private void initListeners() {
		confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int hp;
				String path;
				path=folder.getText();
				File f;
				try{
					if(name.getText().equals("")) throw new Exception();
					hp = Integer.parseInt(health.getText());
			        f = new File("Mobs/"+path);
					if (!f.exists()) throw new Exception();
				}catch(Exception exc){
					System.out.println("inserire un valore di salute valido");
					return;
				}
				if (!folder.getText().equals("")) path=path+"/";
				f = new File("Mobs/"+path+name.getText()+".txt");
				try {
			        Properties props = new Properties();
			        props.setProperty("Name", name.getText());
			        props.setProperty("Health", ""+health.getText());
			        OutputStream out = new FileOutputStream( f );
			        props.store(out, "commento");
			    }
			    catch (Exception e1 ) {
			        e1.printStackTrace();
			    }		        
			}	
		});
		
	}
}
