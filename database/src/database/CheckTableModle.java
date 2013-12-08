package database;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class CheckTableModle extends DefaultTableModel {
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex==0)return true;
		return false;
	}
    public CheckTableModle(Vector data, Vector columnNames) {
        super(data, columnNames);
       
    }
    public CheckTableModle(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
       
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void selectAllOrNull(boolean value) {
        for (int i = 0; i < getRowCount(); i++) {
            this.setValueAt(value, i, 0);
        }
    }

}