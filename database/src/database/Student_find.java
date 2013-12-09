package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.awt.Choice;
import java.awt.Label;
import java.awt.Window.Type;
import java.awt.TextField;
import java.awt.Button;

import javax.swing.JTable;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Student_find extends JFrame {

	private JPanel contentPane;
	private TextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin","admin");
					Student_find frame = new Student_find();
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
	public Student_find() {
		setTitle("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		 textField = new TextField();
		 textField.setBounds(180, 10, 161, 23);
		 contentPane.add(textField);

		
				final Choice choice = new Choice();
		choice.setBounds(58, 10, 60, 21);
		choice.add("班号");
		choice.add("学号");
		choice.add("姓名");
		choice.add("性别");contentPane.add(choice);
		final JButton button = new JButton("\u67E5\u8BE2");
		button.setBounds(347, 10, 76, 23);
		contentPane.add(button);
		
				final JPanel panel = new JPanel();
		panel.setBounds(10, 39, 414, 212);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		this.getRootPane().setDefaultButton(button);
		Label label_1 = new Label("\u67E5\u8BE2\u5185\u5BB9:");
		label_1.setBounds(124, 10, 69, 23);
		contentPane.add(label_1);
		
		Label label = new Label("\u67E5\u8BE2\u9879:");
		label.setBounds(10, 10, 69, 23);
		contentPane.add(label);
		
		
		final Object name[]={"班号","学号","姓名","性别","出生日期"};
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [][]a=Sql_connetcton.find_student(choice.getSelectedIndex(),textField.getText());
				JTable table = new JTable(a, name){
					public boolean isCellEditable(int row, int column){
	                             return false;}//表格不允许被编辑
					
				};
				table.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getClickCount() == 2){
							int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
							String num=String.valueOf(((JTable)e.getSource()).getValueAt(row, 1));
							StudentDetial now=new StudentDetial(num);
							now.setVisible(true);
						} 
						else return; 
					}
				});
				panel.removeAll();
				JScrollPane sPane=new JScrollPane(table);
				sPane.setAutoscrolls(true);
				panel.add(sPane,BorderLayout.CENTER);
				validate();
			}
		});
		 textField.addActionListener(new ActionListener() {
			 	
			 	@Override
			 	public void actionPerformed(ActionEvent e) {
			 		
			 		button.doClick();
			 		 
			 	}
			 });

		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
