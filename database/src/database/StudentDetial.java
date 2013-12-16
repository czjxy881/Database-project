package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.ColorModel;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentDetial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sql_connetcton.login_s("admin","admin");
			StudentDetial dialog = new StudentDetial("03051002");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	final String []name=new String[]{"专业名","学期","课程号","课程名","课程类型","学分","成绩"};
	public StudentDetial(String num) {
		Vector<String> detail=Sql_connetcton.getPersons(num);
		Vector<Vector<String>> ans=Sql_connetcton.getScoreDetail2(num);
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u770B");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 444, 270);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u59D3\u540D:");
			label.setBounds(120, 4, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel name = new JLabel(detail.get(1));
			name.setBounds(158, 4, 70, 15);
			contentPanel.add(name);
			
		}
		{
			JLabel label = new JLabel("\u5B66\u53F7:");
			label.setBounds(10, 4, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel number = new JLabel(detail.get(0));
			number.setBounds(40, 4, 70, 15);
			contentPanel.add(number);
		}
		{
			JLabel label = new JLabel("\u6027\u522B:");
			label.setBounds(252, 4, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel sex = new JLabel(detail.get(2));
			sex.setBounds(287,4, 54, 15);
			contentPanel.add(sex);
		}
		{
			JLabel label = new JLabel("\u603B\u5E73\u5747\u5206:");
			label.setBounds(10, 21, 69, 15);
			contentPanel.add(label);
		}
		{
			JLabel ave = new JLabel(detail.get(3));
			ave.setBounds(65, 21, 54, 15);
			contentPanel.add(ave);
		}
		{
			JLabel label = new JLabel("\u5FC5\u4FEE\u9650\u9009\u5E73\u5747\u5206:");
			label.setBounds(120, 21, 100, 15);
			contentPanel.add(label);
		}
		{
			JLabel have = new JLabel(detail.get(4));
			have.setBounds(214, 21, 54, 15);
			contentPanel.add(have);
		}
		{
			JLabel label = new JLabel("\u603B\u5B66\u5206\uFF1A");
			label.setBounds(10, 39, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel(detail.get(5));
			label.setBounds(56, 39, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5371\u9669!");
			label.setForeground(new Color(255, 0, 0));
			label.setFont(new Font("宋体", Font.BOLD, 25));
			label.setBounds(365, 4, 69, 40);
			contentPanel.add(label);
			if(Integer.valueOf(detail.get(8))>=25)label.setVisible(true);
			else label.setVisible(false);
		}
		{
			JLabel label = new JLabel("\u6210\u7EE9\u8BE6\u60C5:");
			label.setBounds(10, 56, 69, 15);
			contentPanel.add(label);
		}
		
		JLabel label = new JLabel("\u5FC5\u4FEE\u9650\u9009\u5B66\u5206:");
		label.setBounds(120, 39, 96, 15);
		contentPanel.add(label);
		
		JLabel bi = new JLabel(detail.get(6));
		bi.setBounds(201, 39, 54, 15);
		contentPanel.add(bi);
		
		JLabel lblNewLabel = new JLabel("\u672A\u5B8C\u6210\u5FC5\u9650\u5B66\u5206:");
		lblNewLabel.setBounds(260, 39, 120, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel none = new JLabel(detail.get(8));
		none.setBounds(354, 39, 54, 15);
		contentPanel.add(none);
		{
			table = new JTable(ans,names){
				public boolean isCellEditable(int row, int column){
                    return false;}
				
			};
			DefaultTableCellRenderer dtc = new DefaultTableCellRenderer(){
				public Color getForeground(){
					try{
					if(getText()!=""&&Integer.valueOf(getText())<60)
						return java.awt.Color.red;
					else return super.getForeground();
					}catch(Exception e){
						return super.getForeground();
					}
				}
			};
			table.getColumnModel().getColumn(6).setCellRenderer(dtc);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 70, 426, 172);
			contentPanel.add(scrollPane);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(365, 245, 69, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StudentDetial.this.dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
