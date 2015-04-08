package ModBuilder;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class PanelSelectDrop extends JPanel{

	private FileTree items;
	private JTable drop;
	private final String[] columnItems = {"Item", "Prob. Drop"};
	private JScrollPane dropPane;
	public PanelSelectDrop(String path){
		setLayout(new GridLayout(1,0,0,0));
		items = new FileTree(new File(path));
		drop = new JTable(new MyTableModel(columnItems));
		dropPane = new JScrollPane(drop);
		dropPane.setPreferredSize(new Dimension(200,350));
	    dropPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(items);
		add(dropPane);
	}
	public String getSelectedItem(){
		return items.getTree().getSelectionPath().getLastPathComponent().toString();
	}
	public JTable getTable(){
		return drop;
	}
}
