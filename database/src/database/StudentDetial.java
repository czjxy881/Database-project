package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	final String []name=new String[]{"课程号","课程名","学分","成绩"};
	public StudentDetial(String num) {
		Vector<String> detail=Sql_connetcton.getPersons(num);
		Vector<Vector<String>> ans=Sql_connetcton.getScoreDetail(num);
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		setTitle("\u5B66\u751F\u6210\u7EE9\u67E5\u770B");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u59D3\u540D:");
			label.setBounds(120, 8, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel name = new JLabel(detail.get(1));
			name.setBounds(158, 8, 70, 15);
			contentPanel.add(name);
			
		}
		{
			JLabel label = new JLabel("\u5B66\u53F7:");
			label.setBounds(10, 8, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel number = new JLabel(detail.get(0));
			number.setBounds(40, 8, 70, 15);
			contentPanel.add(number);
		}
		{
			JLabel label = new JLabel("\u6027\u522B:");
			label.setBounds(252, 8, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel sex = new JLabel(detail.get(2));
			sex.setBounds(287, 8, 54, 15);
			contentPanel.add(sex);
		}
		{
			JLabel label = new JLabel("\u5E73\u5747\u5206:");
			label.setBounds(10, 28, 69, 15);
			contentPanel.add(label);
		}
		{
			JLabel ave = new JLabel(detail.get(3));
			ave.setBounds(56, 28, 54, 15);
			contentPanel.add(ave);
		}
		{
			JLabel label = new JLabel("\u5DF2\u5B8C\u6210\u5B66\u5206:");
			label.setBounds(120, 28, 89, 15);
			contentPanel.add(label);
		}
		{
			JLabel have = new JLabel(detail.get(4));
			have.setBounds(188, 28, 54, 15);
			contentPanel.add(have);
		}
		{
			JLabel label = new JLabel("\u672A\u5B8C\u6210\u5B66\u5206\uFF1A");
			label.setBounds(252, 28, 89, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel(detail.get(5));
			label.setBounds(321, 28, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("\u5371\u9669!");
			label.setForeground(new Color(255, 0, 0));
			label.setFont(new Font("宋体", Font.BOLD, 25));
			label.setBounds(355, 8, 69, 40);
			contentPanel.add(label);
			if(Integer.valueOf(detail.get(5))>=28)label.setVisible(true);
			else label.setVisible(false);
		}
		{
			table = new JTable(ans,names){
				public boolean isCellEditable(int row, int column){
                    return false;}
			};
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 62, 414, 166);
			contentPanel.add(scrollPane);
		}
		{
			JLabel label = new JLabel("\u6210\u7EE9\u8BE6\u60C5:");
			label.setBounds(10, 45, 69, 15);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						StudentDetial.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
	

}
