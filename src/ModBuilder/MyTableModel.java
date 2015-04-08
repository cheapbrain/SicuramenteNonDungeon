package ModBuilder;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class MyTableModel extends AbstractTableModel{
	private String[] columnNames;
	private List<Object[]> data = new ArrayList<Object[]>();
	public MyTableModel(String[] c){
		columnNames = c;		
		
	}
	public List<Object[]> getData(){
		return data;
	}
	public int getColumnCount() {
		return columnNames.length;
	}
	public int getRowCount() {
		return data.size();
	}
	public String getColumnName(int index) {
	    return columnNames[index];
	}
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}
	public void setValueAt(Object value, int row, int col){
		try{
		Object[] tmp = data.get(row);
		if((Integer.parseInt((String) value)>=0)&&(Integer.parseInt((String) value)<=100))
			tmp[col] = value;
		data.set(row, tmp);
		fireTableCellUpdated(row, col);
		} catch(Exception e){
			
		}
	}
	public void addRow(String value){
		Object[] tmp = new Object[2];
		tmp[0]=value;
		tmp[1]="0";
		data.add(tmp);
		fireTableDataChanged();
	}
	
	public void dropRow(int row){
		data.remove(row);
		fireTableDataChanged();
	}
	public boolean isCellEditable(int row, int col) {
	     switch (col) {
	         case 0: return false;
	         case 1: return true;
	         default:
	             return false;
	      }
	}
}
