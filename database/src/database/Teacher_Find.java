package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teacher_Find extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin","admin");
					Teacher_Find frame = new Teacher_Find();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String[] name=new String[]{"序号","课程名","任课教师"};
	private void refresh(){
		
		Vector<Vector> ans=Sql_connetcton.teacherFind(textField.getText());
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		TableModel tableModle=new DefaultTableModel(ans, names){
			public boolean isCellEditable(int row, int column){
                return false;}//表格不允许被编辑
		};
		table.setModel(tableModle);
		TableColumn column = table.getColumnModel().getColumn(1); 
		column.setPreferredWidth(350);
		TableColumn column1 = table.getColumnModel().getColumn(0); 
		column1.setPreferredWidth(45);
	}
	/**
	 * Create the frame.
	 */
	public Teacher_Find() {
		setTitle("\u4EFB\u8BFE\u6559\u5E08\u67E5\u8BE2");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		textField = new JTextField();
		textField.setBounds(81, 10, 96, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u5B66\u53F7:");
		label.setBounds(43, 13, 54, 15);
		contentPane.add(label);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(235, 9, 93, 23);
		contentPane.add(button);
		
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 37, 414, 214);
		contentPane.add(scrollPane);
	}

}
