package ModBuilder;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class MOBuilder extends JFrame{
	private JPanel pnl1, pnl2, pnl3;
	private JTextField name, title, health, healthRegeneration, damage;
	private JButton done, sprite;
	private JMenu folder;
	private JMenuItem change;
	private String modPath;
	private String[] types = {"Bestia", "Costrutto", "Umanoide", "Demone", "Non-morto", "Elementale", "Xenomorfo"};
	private String[] dangerLevels = {"Normale", "Forte", "Elite", "Elite-Master", "Nemesis"};
	private JComboBox type, dangerousness;
	private JLabel output;
	private PanelSelectDrop drop;
	private SelectNavigator itemNavigator;
	private PanelSelectDrop skill;
	private JTextField attSpeed;
	public MOBuilder(String tmp){
		super("MOBuilder");
		modPath=tmp;
		initGraphic();
		initListeners();
		initWindowSettings();
	}
	private void initListeners() {
		done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				File f;
				//controllo di tutti i dati inseriti
				try{
					if (!name.getText().matches("[a-zA-Z]+")) throw new Exception();
					Float.parseFloat(health.getText());
					Float.parseFloat(healthRegeneration.getText());
					Float.parseFloat(damage.getText());
					Float.parseFloat(attSpeed.getText());
					f = new File(change.getText()+"\\Mobs");
					if(!f.exists()) f.mkdir();
					f = new File(change.getText()+"\\Sprites");
					if(!f.exists()){
						f.mkdir();
						f = new File(change.getText()+"\\Sprites\\Mobs");
						f.mkdir();
					}
					f = new File(change.getText()+"\\Sprites\\Mobs\\"+name.getText());
					if (!f.exists()) copyFolder(new File(sprite.getText()), f);
					
				}catch(Exception e){
					output.setText("Inserire correttamente  tutti i dati");
					return;
				} 
				//creazione file .mob
				try {
					f = new File(change.getText()+"\\Mobs\\"+name.getText()+".mob");
					OutputStream out = new FileOutputStream(f);
					Properties props = new Properties();
					props.setProperty("Name", name.getText());
					props.setProperty("Health", health.getText());
					props.setProperty("Title", title.getText());
					props.setProperty("AttackSpeed", attSpeed.getText());
					props.setProperty("Damage", damage.getText());
					props.setProperty("Type", (String)type.getSelectedItem());
					props.setProperty("Dangerousness", (String)dangerousness.getSelectedItem());
					props.setProperty("HealthRegeneration", healthRegeneration.getText());
					int count=0;
					for(Object[] tmp: ((MyTableModel) drop.getTable().getModel()).getData()){
						props.setProperty("Drop"+count, tmp[0]+"");
						props.setProperty("DropProb"+count, tmp[1]+"");
						count++;
					}
					count=0;
					for(Object[] tmp: ((MyTableModel) skill.getTable().getModel()).getData()){
						props.setProperty("Skill"+count, tmp[0]+"");
						props.setProperty("SkillProb"+count, tmp[1]+"");
						count++;
					}
					props.store(out, "");
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
			public void actionPerformed(ActionEvent arg0){
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(sprite);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sprite.setText(fc.getSelectedFile().getPath());
				}
				pack();
			}
			});
	}
	private void initGraphic() {
		addInfoMenu();
		setLayout(new FlowLayout());
		pnl1 = new JPanel();
		pnl2 = new JPanel();
		pnl3 = new JPanel();
		//panel1
		pnl1.setLayout(new GridLayout(0,1,0,0));
		name = addTextField(pnl1, "Name");
		title = addTextField(pnl1, "Title");
		type = addComboBox(pnl1, types, "Type");
		dangerousness = addComboBox(pnl1, dangerLevels, "Dangerousness");
		health = addTextField(pnl1, "Health");
		healthRegeneration = addTextField(pnl1, "Health Regeneration");
		damage = addTextField(pnl1, "Damage");
		attSpeed = addTextField(pnl1, "Attack Speed");
		//panel2(Items)
		File tmp = new File(change.getText()+"/Skill");
		if(!tmp.exists())
			tmp.mkdir();
		tmp = new File(change.getText()+"/Items");
		if(!tmp.exists())
			tmp.mkdir();
		pnl2.setLayout(new BoxLayout(pnl2, BoxLayout.X_AXIS));
		drop = new PanelSelectDrop(change.getText()+"/Items");
		skill = new PanelSelectDrop(change.getText()+"/Skills");
		pnl2.add(newMyPanel(drop));
		JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		sep.setPreferredSize(new Dimension(10,400));
		pnl2.add(sep);
		pnl2.add(newMyPanel(skill));
		//panel3
		pnl3.setLayout(new GridLayout(0,1,0,0));
		sprite = addButton(pnl3, "Sprite Folder");
		done = addButton(pnl3, "Done");
		output = new JLabel();
		pnl3.add(output);
		//frame
		add(pnl1);
		sep = new JSeparator(JSeparator.VERTICAL);
		sep.setPreferredSize(new Dimension(1,400));
		add(sep);
		add(pnl2);
		sep = new JSeparator(JSeparator.VERTICAL);
		sep.setPreferredSize(new Dimension(1,400));
		add(sep);
		add(pnl3);
	}
	private JComboBox addComboBox(JPanel p, String[] s, String name){
		JComboBox cb = new JComboBox(s);
		p.add(new JLabel(name));
		p.add(cb);
		return cb;
	}
	private void addInfoMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu info = new JMenu("Info");
		folder = new JMenu("Folder");
		JMenuItem help = new JMenuItem("Help");
		change = new JMenuItem(modPath);
		folder.add(change);
		info.add(help);
		menu.add(info);
		menu.add(folder);
		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(folder);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            change.setText(fc.getSelectedFile().getPath());
		       }	
			}
		});
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
	public static void copyFolder(File src, File dest)
	    	throws IOException{
	 
	    	if(src.isDirectory()){
	 
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Directory copied from " 
	                              + src + "  to " + dest);
	    		}
	 
	    		//list all the directory contents
	    		String files[] = src.list();
	 
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	 
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	 
	    	        byte[] buffer = new byte[1024];
	 
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	        System.out.println("File copied from " + src + " to " + dest);
	    	}
	    }
	private JTextField addTextField(JPanel pnl, String name){
		pnl.add(new JLabel(name+":"));
		JTextField tf=new JTextField();
		pnl.add(tf);		
		return tf;
	}
	private void initWindowSettings() {
		setVisible(true);
		setResizable(false);
		this.pack();
	}
	private JPanel newMyPanel(PanelSelectDrop pannelo){
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add(pannelo);
		itemNavigator = new SelectNavigator(pannelo);
		myPanel.add(itemNavigator);
		return myPanel;
	}
}