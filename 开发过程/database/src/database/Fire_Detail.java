package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Window.Type;
import java.util.Vector;

import javax.swing.JScrollPane;

public class Fire_Detail extends JDialog {

	/**
	 * Launch the application.
	 */
	private JTable table;
	public static void main(String[] args) {
		try {
			Sql_connetcton.login_s("admin", "admin");
			Vector s=new Vector<>();
			s.add("28");s.add("18");s.add("8");s.add("03051043");
			
			Fire_Detail dialog = new Fire_Detail(Sql_connetcton.killDetail(s),200,100);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String []name=new String[]{"学期","必修未完成学分","限选未完成学分","任选未完成学分"};

	/**
	 * Create the dialog.
	 */
	public Fire_Detail(Vector<Vector> ans,int x,int y) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);  
		setBounds(x,y,400,100);
		//setLocation(x, y);
		getContentPane().setLayout(new BorderLayout());
			Vector<String> names=new Vector<String>();
			for(String s:name)names.add(s);
			TableModel tableModle=new DefaultTableModel(ans, names){
				public boolean isCellEditable(int row, int column){
	                return false;}//表格不允许被编辑
			};
			table=new JTable(tableModle);
			JScrollPane scrollPane = new JScrollPane(table);
			getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//setBounds(scrollPane.getBounds());
	}

}
