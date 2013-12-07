package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Filter.Chain;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JCheckBox;

public class Student_insert extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JCheckBox checkBox_3,checkBox_2,checkBox_1,checkBox;
	private Vector<Integer> order=new Vector<Integer>();
	private final String[] name=new String[]{"班号","学号","姓名","性别","出生日期"};
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_insert frame = new Student_insert();
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
	public Student_insert() {
		setTitle("\u5B66\u751F\u4FE1\u606F\u5BFC\u5165");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ActionListener checkBoxListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s=textField_1.getText();
				if(((JCheckBox)e.getSource()).isSelected()){	
					s+=e.getActionCommand()+",";
				}else{
					s=s.replaceAll(e.getActionCommand()+",","");
				}
				textField_1.setText(s);
			}
		};
		final JCheckBox chckbxNewCheckBox = new JCheckBox("\u51FA\u751F\u65E5\u671F");
		chckbxNewCheckBox.addActionListener(checkBoxListener);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JCheckBox)e.getSource()).isSelected()){
					order.add(new Integer(4));
				}else{
					order.remove(new Integer(4));
				}
				
			}
		});
		chckbxNewCheckBox.setBounds(231, 59, 103, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("\u5BFC\u5165\u6587\u4EF6:");
		lblNewLabel.setBounds(10, 10, 63, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 7, 216, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file=new JFileChooser();
				file.setAcceptAllFileFilterUsed(false); //关闭所有文件筛选器
				file.setMultiSelectionEnabled(false); //关闭多选
				file.setFileFilter(new FileNameExtensionFilter("TXT", "txt")); //添加txt筛选器
				if(file.showDialog(getParent(), "导入")==JFileChooser.APPROVE_OPTION){
					textField.setText(file.getSelectedFile().getPath()); //获得保存路径
				}
				
			}
		});
		
		 checkBox_3 = new JCheckBox("\u6027\u522B");
		checkBox_3.setBounds(174, 59, 54, 23);
		checkBox_3.addActionListener(checkBoxListener);
		checkBox_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JCheckBox)e.getSource()).isSelected()){
					order.add(new Integer(3));
				}else{
					order.remove(new Integer(3));
				}
				
			}
		});
		contentPane.add(checkBox_3);
		button.setBounds(287, 7, 25, 21);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u5BFC\u5165");

		button_1.setBounds(379, 6, 63, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u9884\u89C8");

		button_2.setBounds(315, 6, 63, 23);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("\u5BFC\u5165\u683C\u5F0F:");
		label.setBounds(10, 38, 54, 15);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 35, 216, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		 checkBox = new JCheckBox("\u73ED\u53F7");
		checkBox.setBounds(10, 59, 54, 23);
		checkBox.addActionListener(checkBoxListener);
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JCheckBox)e.getSource()).isSelected()){
					order.add(new Integer(0));
				}else{
					order.remove(new Integer(0));
				}
			}
		});
		
		 checkBox_2 = new JCheckBox("\u59D3\u540D");
		checkBox_2.setBounds(118, 59, 54, 23);
		checkBox_2.addActionListener(checkBoxListener);
		checkBox_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JCheckBox)e.getSource()).isSelected()){
					order.add(new Integer(2));
				}else{
					order.remove(new Integer(2));
				}
				
			}
		});
		contentPane.add(checkBox_2);
		
		 checkBox_1 = new JCheckBox("\u5B66\u53F7");
		checkBox_1.setBounds(63, 59, 54, 23);
		checkBox_1.addActionListener(checkBoxListener);
		checkBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(((JCheckBox)e.getSource()).isSelected()){
					order.add(new Integer(1));
				}else{
					order.remove(new Integer(1));
				}
				
			}
		});
		contentPane.add(checkBox_1);
		contentPane.add(checkBox);
		final JButton button_3 = new JButton("\u9501\u5B9A");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textField_1.getText();
				if(e.getActionCommand()=="锁定"){
					textField_1.setEnabled(false);
					checkBox_3.setEnabled(false);
					checkBox_2.setEnabled(false);
					checkBox_1.setEnabled(false);
					checkBox.setEnabled(false);
					chckbxNewCheckBox.setEnabled(false);
					if(s.endsWith(",")){
						textField_1.setText(s.substring(0,s.length()-1));
					}
					button_3.setText("解锁");
				}else{
					textField_1.setEnabled(true);
					checkBox_3.setEnabled(true);
					checkBox_2.setEnabled(true);
					checkBox_1.setEnabled(true);
					checkBox.setEnabled(true);
					chckbxNewCheckBox.setEnabled(true);
					textField_1.setText(s+",");
					button_3.setText("锁定");
				}
			}
		});
		button_3.setBounds(287, 34, 63, 23);
		contentPane.add(button_3);
		panel = new JPanel();
		panel.setBounds(10, 88, 424, 173);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));//保持边距，不然会超出显示区域
		final JTable table = new JTable();
				/*{
					public boolean isCellEditable(int row, int column){
	                             return false;}//表格不允许被编辑
				};
				*/

		panel.removeAll();
		JScrollPane sPane=new JScrollPane(table);
		sPane.setAutoscrolls(true);
		panel.add(sPane,BorderLayout.CENTER);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Vector<Vector<String>> ans=
						file_insert.get(textField_1.getText(), textField.getText(), name, order);
						//file_insert.get("","C:\\Users\\jxy1\\Documents\\GitHub\\Database-project\\导入数据\\test.txt",name,order);
				final Vector<String> names=new Vector<String>();
				for(String s:name)names.add(s);
				DefaultTableModel dataModel=new DefaultTableModel(ans,names)
				{

					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
				
						return false;
					}
					
					@Override
					public Object getValueAt(int rowIndex, int columnIndex) {
						if(columnIndex==0){
							return rowIndex+1;
						}
				        Vector rowVector = (Vector)dataVector.elementAt(rowIndex);
				        return rowVector.elementAt(columnIndex-1);
					}
					
					@Override
					public String getColumnName(int columnIndex) {
						if(columnIndex==0)return "序号";
						return names.get(columnIndex-1);
					}
					
					@Override
					public int getColumnCount() {
						return names.size()+1;
					}

					
				};
				
				table.setModel(dataModel);
				for(int i=0;i<1;i++){
					TableColumn column = table.getColumnModel().getColumn(i); 
					column.setPreferredWidth(25);
				}
				
				
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel  now=(DefaultTableModel) table.getModel();
				int row=now.getRowCount(),col=now.getColumnCount()-1;
				int rr=0;
				
				for(int i=0;i<row;i++){
					Vector<String> temp=(Vector<String>) now.getDataVector().elementAt(rr);
					if(temp.size()>col)temp.remove(col);
					if(Sql_connetcton.insert(temp,"学生")==true){
						now.removeRow(rr);
					}else{
						rr++;
					}
				}
				
				JOptionPane.showMessageDialog(null,"成功"+(row-rr)+"个,失败"+rr+"个","导入结果", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		
	}
}
