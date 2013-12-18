package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Color;

public class Score_insert_class extends JFrame {

	private JPanel contentPane;
	private JTextField classcode;
	private JTextField code;
	private JTable table;
	private JLabel classname;
	private String Classcode,cc;
	private JButton button;
	private Vector<Vector<String>> ans,ORG;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin", "admin");
					Score_insert_class frame = new Score_insert_class();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String[] name=new String[]{"学号","姓名","成绩","补考成绩"};
	public void refresh(){
		Classcode=classcode.getText();
		String na=Sql_connetcton.getCouresName(Classcode);
		if(na.equals("")){
			JOptionPane.showMessageDialog(getParent(), "课程号错误!", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		classname.setText(na);
		Vector<String> data=new Vector<String>();
		cc=code.getText();
		data.add(Classcode);
		data.add(cc);
		ans=Sql_connetcton.getScoreClass(data);
		ORG=new Vector<Vector<String>>();
		for(Vector<String> temp:ans){
			ORG.add((Vector<String>) temp.clone());
		}
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		TableModel tableModle=new DefaultTableModel(ans, names){
			public boolean isCellEditable(int row, int column){
				if(column>=2)return true;
                return false;}//表格不允许被编辑
		};
		table.setModel(tableModle);
	}
	/**
	 * Create the frame.
	 */
	public Score_insert_class() {
		setTitle("\u6210\u7EE9\u5F55\u5165(\u6309\u8BFE\u7A0B)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		JLabel label = new JLabel("*\u8BFE\u7A0B\u53F7:");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		classcode = new JTextField();
		classcode.setBounds(58, 7, 87, 21);
		contentPane.add(classcode);
		classcode.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u540D:");
		label_1.setBounds(15, 37, 54, 15);
		contentPane.add(label_1);
		
		 classname = new JLabel("");
		classname.setBounds(58, 37, 232, 15);
		contentPane.add(classname);
		
		JLabel label_2 = new JLabel("\u5B66\u53F7:");
		label_2.setBounds(148, 10, 54, 15);
		contentPane.add(label_2);
		
		code = new JTextField();
		code.setBounds(178, 7, 86, 21);
		contentPane.add(code);
		code.setColumns(10);
		
		button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(273, 6, 68, 32);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定修改？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
					Vector data=tableModel.getDataVector();
					int rows=tableModel.getRowCount();
					int suc=0,bad=0;
					for(int i=0;i<rows;i++){
						Vector s=(Vector) data.elementAt(i);
						Vector org=Score_insert_class.this.ORG.get(i);
						if(!org.get(2).equals("NULL")&&s.get(2).equals("-1")){
							s.remove(3);
							s.remove(1);
							s.remove(1);
							s.add(1,Classcode);
							if(Sql_connetcton.delScore(s))suc++;
							else bad++;
							
						}
						else if(!s.equals(org)){
							s.remove(1);
							s.add(1,Classcode);
							if(Sql_connetcton.update(s, "成绩"))suc++;
							else bad++;
						}

							
					}
					JOptionPane.showMessageDialog(getParent(), "修改完成,成功"+suc+"个,失败"+bad+"个！");
					refresh();
					
				}
			}
		});
		button_1.setBounds(349, 6, 68, 32);
		contentPane.add(button_1);
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 60, 414, 191);
		contentPane.add(scrollPane);
		
		JLabel label_3 = new JLabel("\u6210\u7EE9\u4E3A-1\u4EE3\u8868\u5220\u9664\u6B64\u6761\u8BB0\u5F55!");
		label_3.setForeground(Color.RED);
		label_3.setBounds(273, 42, 197, 15);
		contentPane.add(label_3);
		classcode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
				
			}
		});
		code.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button.doClick();
				
			}
		});
	}
}
