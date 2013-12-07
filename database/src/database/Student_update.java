package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student_update extends JFrame {

	private JPanel contentPane;
	private JTextField classcode;
	private JTextField num;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtYyyymmdd;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin","admin");
					Student_update frame = new Student_update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Student_update() {
		setTitle("\u5B66\u751F\u4FE1\u606F\u5F55\u5165");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u73ED\u53F7:");
		label.setBounds(21, 10, 54, 15);
		contentPane.add(label);
		
		classcode = new JTextField();
		classcode.setBounds(56, 7, 66, 21);
		contentPane.add(classcode);
		classcode.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7:");
		label_1.setBounds(144, 10, 54, 15);
		contentPane.add(label_1);
		
		num = new JTextField();
		num.setBounds(180, 7, 66, 21);
		contentPane.add(num);
		num.setColumns(10);
		
		JLabel label_2 = new JLabel("\u59D3\u540D:");
		label_2.setBounds(21, 38, 54, 15);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(56, 35, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6027\u522B:");
		label_3.setBounds(144, 38, 54, 15);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 35, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_4 = new JLabel("\u51FA\u751F\u65E5\u671F:");
		label_4.setBounds(256, 38, 54, 15);
		contentPane.add(label_4);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("yyyy.mm.dd");
		txtYyyymmdd.setBounds(313, 35, 95, 21);
		contentPane.add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JButton button = new JButton("\u5F55\u5165");
		button.setBounds(294, 6, 93, 23);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 69, 434, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("\u6279\u91CF\u4FEE\u6539:");
		label_5.setBounds(10, 10, 72, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u73ED\u53F7:");
		label_6.setBounds(115, 10, 54, 15);
		panel.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(148, 7, 66, 21);
		panel.add(textField_2);
		final JTable table = new JTable();
		JScrollPane span=new JScrollPane(table);
		span.setBounds(10, 35, 414, 147);
		panel.add(span);
		String []name=new String[]{"全选","班号","学号","姓名","性别"};
		Vector<Vector> ans=Sql_connetcton.getAllStudent();
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定修改？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
					Vector data=tableModel.getDataVector();
					int rows=tableModel.getRowCount();
					for(int i=0;i<rows;i++){
						
					}
			}
		});
		button_1.setBounds(300, 6, 93, 23);
		panel.add(button_1);
	}

}
