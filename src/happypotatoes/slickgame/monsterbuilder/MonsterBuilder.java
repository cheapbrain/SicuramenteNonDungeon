package happypotatoes.slickgame.monsterbuilder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class MonsterBuilder extends JFrame{
	private JPanel pnl1, pnl2, pnl3;
	private JTextField name, health, healthRegeneration, damage;
	private JButton done, sprite, folder;
	private JLabel output;
	public MonsterBuilder(){
		super("MOBuilder");
		initGraphic();
		initListeners();
		initWindowSettings();				
	}
	private void initListeners() {
		folder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(folder);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            folder.setText(fc.getSelectedFile().getPath());
		       }
			}
		});
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				File f;
				String relSpritePath = "";
				//controllo di tutti i dati inseriti
				try{
					if (!name.getText().matches("[a-zA-Z]+")) throw new Exception();
					Float.parseFloat(health.getText());
					Float.parseFloat(healthRegeneration.getText());
					f = new File(folder.getText()+"\\Mobs");
					if(!f.exists()) f.mkdir();
					f = new File(folder.getText()+"\\Sprites");
					if(!f.exists()) f.mkdir();
					f = new File(folder.getText()+"\\Sprites\\"+name.getText()+sprite.getText().substring(sprite.getText().lastIndexOf('.')));
					if (!f.exists()) copyFile(new File(sprite.getText()), f);
					relSpritePath = folder.getText().split("\\\\")[folder.getText().split("\\\\").length-1];
					relSpritePath += "\\Sprites\\"+name.getText()+sprite.getText().substring(sprite.getText().lastIndexOf('.'));
					System.out.println(relSpritePath);
				}catch(Exception e){
					output.setText("Inserire correttamente  tutti i dati");
					return;
				} 
				//creazione file .mob
				try {
					f = new File(folder.getText()+"\\Mobs\\"+name.getText()+".mob");
					OutputStream out = new FileOutputStream(f);
					Properties props = new Properties();
					props.setProperty("Name", name.getText());
					props.setProperty("Health", health.getText());
					props.setProperty("Sprite", relSpritePath);
					props.store(out, "commento");
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				output.setText("done!");
			}
		});
		sprite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(sprite);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sprite.setText(fc.getSelectedFile().getPath());
				}	        
			}
			});
		}
	private void initGraphic() {
		addInfoMenu();
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		pnl1 = new JPanel();
		pnl2 = new JPanel();
		pnl3 = new JPanel();
		//panel1
		pnl1.setLayout(new BoxLayout(pnl1, BoxLayout.Y_AXIS));	
		name = addTextField(pnl1, "Name");
		health = addTextField(pnl1, "Health");
		healthRegeneration = addTextField(pnl1, "Health Regeneration");
		damage = addTextField(pnl1, "Damage");
		//panel2
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.Y_AXIS));
		//panel3
		folder = addButton(pnl3, "Folder");
		sprite = addButton(pnl3, "Sprite");
		done = addButton(pnl3, "Done");
		output = new JLabel();
		pnl3.add(output);
		pnl3.setLayout(new BoxLayout(pnl3, BoxLayout.Y_AXIS));
		add(pnl1);
		add(new JSeparator(JSeparator.VERTICAL));
		add(pnl2);
		add(new JSeparator(JSeparator.VERTICAL));
		add(pnl3);
	}
	private void addInfoMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu info = new JMenu("Info");
		JMenuItem help = new JMenuItem("Help");
		info.add(help);
		menu.add(info);
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFrame helpFrame = new JFrame("Help");
				helpFrame.setLayout(new BoxLayout(helpFrame.getContentPane(), BoxLayout.Y_AXIS));
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader("./res/Help.txt"));
					String tmp = br.readLine();
					while(tmp!=null){
						helpFrame.add(new JLabel(tmp));
						tmp = br.readLine();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				helpFrame.setVisible(true);
				helpFrame.pack();
			}
		});
		setJMenuBar(menu);
	}
	private JButton addButton(JPanel pnl, String caption){
		JButton btn = new JButton(caption);
		pnl.add(new JLabel("  "));
		pnl.add(btn);
		return btn;
	}
	private void copyFile(File source, File dest) throws IOException {
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
	private JTextField addTextField(JPanel pnl, String name){
		pnl.add(new JLabel(name+":"));
		JTextField tf=new JTextField();
		pnl.add(tf);
		return tf;
	}
	private void initWindowSettings() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.pack();
	}
}