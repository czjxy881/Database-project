package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teach_plan extends JFrame {

	private JPanel contentPane;
	private Map<String, String> Major,Coures;
	private JComboBox courese_combo,Major_combo,grade_combo;
	private JButton query_button,clear_button,add_button,del_button;
	private JTable table;
	private JLabel label_3;
	private JLabel bi_label;
	private JLabel label_4;
	private JLabel xian_label;
	private JLabel label_5;
	private JLabel xuan_label;
	private Vector data;
	private Vector<Vector> ans;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin", "admin");
					Teach_plan frame = new Teach_plan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}
	private String[]name=new String[]{"全选","课程号","课程名","学分","课程类型"};
	private void refresh(){
		ans=Sql_connetcton.getPlanDetail(data);
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		
	}
	private void query(){
		data=new Vector<>();
		data.add(String.valueOf(grade_combo.getSelectedItem()));
		data.add(String.valueOf(Major.get(Major_combo.getSelectedItem())));
		Vector<String> score=Sql_connetcton.getPlanGen(data);
		bi_label.setText(score.get(0));
		xian_label.setText(score.get(1));
		xuan_label.setText(score.get(2));
		refresh();
		ArrayList<String> add=new ArrayList<String>();
		for(String s:Coures.keySet()){
			add.add(s);
		}
		Vector<Vector> temp=Sql_connetcton.getPlanDetail2(data);;
		for(Vector s:temp){
			if(add.contains(s.get(2)))
				add.remove(s.get(2));
		}
		courese_combo.removeAllItems();
		for(String s:add){
			courese_combo.addItem(s);
		}
		clear_button.setEnabled(true);
		add_button.setEnabled(true);
		del_button.setEnabled(true);
		courese_combo.setEnabled(true);
		Major_combo.setEnabled(false);
		grade_combo.setEnabled(false);
		query_button.setEnabled(false);
	}
	private void clear(){
		clear_button.setEnabled(false);
		add_button.setEnabled(false);
		del_button.setEnabled(false);
		courese_combo.setEnabled(false);
		Major_combo.setEnabled(true);
		grade_combo.setEnabled(true);
		query_button.setEnabled(true);
		CheckTableModle dtm=(CheckTableModle) table.getModel();
		dtm.setRowCount(0);
	}

	/**
	 * Create the frame.
	 */
	public Teach_plan() {
		setTitle("\u6559\u5B66\u8BA1\u5212");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u671F:");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		grade_combo = new JComboBox();
		grade_combo.setBounds(41, 8, 66, 21);
		contentPane.add(grade_combo);
		for(int i=1;i<=8;i++){
			grade_combo.addItem(i);
		}
		JLabel label_1 = new JLabel("\u4E13\u4E1A:");
		label_1.setBounds(112, 10, 37, 15);
		contentPane.add(label_1);
		
		Major_combo = new JComboBox();
		Major_combo.setBounds(146, 8, 79, 21);
		contentPane.add(Major_combo);
		Major=Sql_connetcton.getMajority();
		for(String s:Major.keySet()){
			Major_combo.addItem(s);
		}
		
		JLabel label_2 = new JLabel("\u8BFE\u7A0B:");
		label_2.setBounds(10, 35, 54, 15);
		contentPane.add(label_2);
		Coures=Sql_connetcton.getCouresByName();
		courese_combo = new JComboBox();
		courese_combo.setEnabled(false);
		courese_combo.setBounds(41, 32, 184, 21);
		contentPane.add(courese_combo);
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 80, 414, 179);
		contentPane.add(scrollPane);
		
		query_button = new JButton("\u67E5\u8BE2\u8BA1\u5212");
		query_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query();
			}
		});
		query_button.setBounds(232, 6, 105, 23);
		contentPane.add(query_button);
		
		clear_button = new JButton("\u6E05\u9664\u7ED3\u679C");
		clear_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		clear_button.setEnabled(false);
		clear_button.setBounds(341, 6, 90, 23);
		contentPane.add(clear_button);
		
		add_button = new JButton("\u589E\u52A0\u8BFE\u7A0B");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> temp=new Vector<String>();
				temp.addAll(data);
				temp.add(Coures.get(courese_combo.getSelectedItem()));
				if(Sql_connetcton.insert(temp,"教学计划")){
					JOptionPane.showMessageDialog(getParent(), "增加成功", "恭喜", JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(getParent(), "增加失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
				query();
			}
			
		});
		add_button.setEnabled(false);
		add_button.setBounds(232, 31, 105, 23);
		contentPane.add(add_button);
		
		del_button = new JButton("\u5220\u9664\u9009\u4E2D");
		del_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定删除？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector datas=tableModel.getDataVector();
						int rows=tableModel.getRowCount();int suc=0,bad=0;
						Vector<String> temp=new Vector<>();
						temp.addAll(data);
						temp.add("");
						for(int i=0;i<rows;i++){
							Vector s=(Vector) datas.elementAt(i);
							
							if((boolean)s.get(0)==true){
								s.remove(0);
								temp.set(2,(String) s.get(0));
								if(Sql_connetcton.delPlan(temp))suc++;
								else bad++;
							}
							
						}
						JOptionPane.showMessageDialog(getParent(), "删除完成,成功"+suc+"个,失败"+bad+"个！");
						query();
				}
			}
		});
		del_button.setEnabled(false);
		del_button.setBounds(341, 31, 90, 23);
		contentPane.add(del_button);
		
		label_3 = new JLabel("\u5FC5\u4FEE\u603B\u5B66\u5206:");
		label_3.setBounds(19, 61, 79, 15);
		contentPane.add(label_3);
		
		bi_label = new JLabel("");
		bi_label.setBounds(87, 62, 54, 15);
		contentPane.add(bi_label);
		
		label_4 = new JLabel("\u9650\u9009\u603B\u5B66\u5206:");
		label_4.setBounds(146, 61, 79, 15);
		contentPane.add(label_4);
		
		xian_label = new JLabel("");
		xian_label.setBounds(214, 61, 54, 15);
		contentPane.add(xian_label);
		
		label_5 = new JLabel("\u9009\u4FEE\u603B\u5B66\u5206:");
		label_5.setBounds(272, 61, 79, 15);
		contentPane.add(label_5);
		
		xuan_label = new JLabel("");
		xuan_label.setBounds(340, 61, 54, 15);
		contentPane.add(xuan_label);
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
	}
}
