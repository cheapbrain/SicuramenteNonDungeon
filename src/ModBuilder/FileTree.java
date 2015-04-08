package ModBuilder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTree extends JPanel {
	private JTree tree;
	public FileTree(File dir) {
	    setLayout(new BorderLayout());
	    tree = new JTree(addNodes(null, dir));
	    JScrollPane scrollpane = new JScrollPane();
	    scrollpane.getViewport().add(tree);
	    add(BorderLayout.CENTER, scrollpane); 
	    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
	    String curPath = dir.getPath();
	    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath.substring(curPath.lastIndexOf(File.separator)+1));
	    if (curTop != null) { 
	      curTop.add(curDir);
	    }
	    Vector ol = new Vector();
	    String[] tmp = dir.list();
	    for (int i = 0; i < tmp.length; i++)
	      ol.addElement(tmp[i]);
	    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
	    File f;
	    ArrayList<String> files = new ArrayList<String>();
	    for (int i = 0; i < ol.size(); i++) {
	      String thisObject = (String) ol.elementAt(i);
	      String newPath;
	      if (curPath.equals("."))
	        newPath = thisObject;
	      else
	        newPath = curPath + File.separator + thisObject;
	      if ((f = new File(newPath)).isDirectory())
	        addNodes(curDir, f);
	      else
	        files.add(thisObject);
	    }
	    for (int fnum = 0; fnum < files.size(); fnum++){
	      curDir.add(new DefaultMutableTreeNode(files.get(fnum)));
	    }
	    return curDir;
	}

 	public Dimension getMinimumSize() {
 		return new Dimension(200, 350);
	}
 	public JTree getTree(){
 		return tree;
 	}
	public Dimension getPreferredSize() {
		return new Dimension(200, 350);
	}
}
