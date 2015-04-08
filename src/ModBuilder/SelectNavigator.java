package ModBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
public class SelectNavigator extends JPanel{
	private JButton addItem;
	private JPanel partner;
	private JButton dropItem;
	public SelectNavigator(final JPanel partner){
		this.partner = partner;
		addItem = new JButton("Add");
		addItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String tmp;
				tmp = ((PanelSelectDrop) partner).getSelectedItem();
				try{
					if((!tmp.substring(tmp.lastIndexOf('.')).equals(".item"))&&(!tmp.substring(tmp.lastIndexOf('.')).equals(".skill"))) throw new Exception();
					((MyTableModel) ((PanelSelectDrop) partner).getTable().getModel()).addRow(tmp);
				} catch(Exception e){	
				}
			}
		});
		add(addItem);
		dropItem = new JButton("Delete");
		dropItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = ((PanelSelectDrop) partner).getTable().getSelectedRow();
				((MyTableModel) ((PanelSelectDrop) partner).getTable().getModel()).dropRow(selectedRow);
			}
		});
		add(dropItem);
		
	}
}
