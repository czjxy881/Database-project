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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class Student_update extends JFrame {

	private JPanel contentPane;
	private JTextField classcode;
	private JTextField num;
	private JTextField Name;
	private JTextField Brith;
	private JTextField textField_2;
	private JButton button,button_2;
	private JComboBox Sex;
	private String []name=new String[]{"全选","班号","学号","姓名","性别"};
	private JTable table;
	private void refresh(){
		Vector<Vector> ans=Sql_connetcton.getAllStudent();
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
	}
	private void clear() {
		button.setText("录入");
		num.setText("");
		Name.setText("");
		Sex.setSelectedIndex(0);
		num.setEditable(true);
		Brith.setText("yyyy.mm.dd");
		classcode.setText("");
		button_2.setVisible(false);
		
	}
	private boolean check_class(String s){
		if(s!=null&&!s.equals("")&&Sql_connetcton.getClassDetial(s).get(0).equals("")){
			int t=JOptionPane.showConfirmDialog(getParent(),"不存在班级"+s+",是否创建?","错误",JOptionPane.YES_NO_OPTION );
			if(t==0){
				
				Class_insert ci=new Class_insert(s);
				ci.setModal(true);
				ci.setVisible(true);

				return !Sql_connetcton.getClassDetial(s).get(0).equals("");
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
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
		
		JLabel label_1 = new JLabel("*\u5B66\u53F7:");
		label_1.setBounds(139, 10, 59, 15);
		contentPane.add(label_1);
		
		num = new JTextField();
		num.setBounds(180, 7, 66, 21);
		contentPane.add(num);
		num.setColumns(10);
		
		JLabel label_2 = new JLabel("*\u59D3\u540D:");
		label_2.setBounds(10, 38, 54, 15);
		contentPane.add(label_2);
		
		Name = new JTextField();
		Name.setBounds(56, 35, 66, 21);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel label_3 = new JLabel("\u6027\u522B:");
		label_3.setBounds(144, 38, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u51FA\u751F\u65E5\u671F:");
		label_4.setBounds(256, 38, 83, 15);
		contentPane.add(label_4);
		
		Brith = new JTextField(){
			@Override
			public String getText(){
				String s=super.getText();
				if(s.equals("yyyy.mm.dd"))
					s="";
				return s;
			}
		};
		Brith.setText("yyyy.mm.dd");
		Brith.setBounds(313, 35, 95, 21);
		contentPane.add(Brith);
		Brith.setColumns(10);
		button = new JButton("\u5F55\u5165");
		button.setBounds(272, 6, 93, 23);
		contentPane.add(button);
		Brith.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(Brith.getText().equals(""))Brith.setText("yyyy.mm.dd");
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(Brith.getText().equals(""))Brith.setText("");
				
			}
		});
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
		table = new JTable();
		JScrollPane span=new JScrollPane(table);
		span.setBounds(10, 35, 414, 147);
		panel.add(span);
		refresh();

		
		
		JButton button_1 = new JButton("\u4FEE\u6539\u9009\u4E2D");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定修改？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					if(check_class(textField_2.getText())==true){
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();int suc=0,bad=0;
						String classcString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							
							if((boolean)s.get(0)==true){
								s.remove(0);
								s.set(0,classcString);
								if(Sql_connetcton.update(s, "学生")==true)suc++;
								else bad++;
								
							}
							
							
						}
						JOptionPane.showMessageDialog(getParent(), "更新完成,成功"+suc+"个,失败"+bad+"个！");
						refresh();
					}else{
						textField_2.setText("");
					}
				}
			}
		});
		button_1.setBounds(224, 6, 93, 23);
		panel.add(button_1);
		
		JButton btnNewButton = new JButton("\u5220\u9664\u9009\u4E2D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定删除？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					{
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();int suc=0,bad=0;
						String classcString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							
							if((boolean)s.get(0)==true){
								if(Sql_connetcton.delStudent((String) s.get(2))==true)suc++;
								else bad++;
								
							}
							
						}
						JOptionPane.showMessageDialog(getParent(), "删除完成,成功"+suc+"个,失败"+bad+"个！");
						refresh();
					}
				}
			}
		});
		btnNewButton.setBounds(327, 6, 97, 23);
		panel.add(btnNewButton);
		button_2 = new JButton("\u653E\u5F03");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		button_2.setBounds(371, 6, 73, 23);
		contentPane.add(button_2);
		
		Sex = new JComboBox();
		Sex.setBounds(180, 35, 54, 21);
		Sex.addItem("男");
		Sex.addItem("女");
		
		contentPane.add(Sex);
		
		button_2.setVisible(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(num.getText().equals(""))JOptionPane.showMessageDialog(getParent(), "请输入学号！");
				else {
					Vector<String> data=new Vector<String>();
					data.add(classcode.getText());
					data.add(num.getText());
					data.add(Name.getText());
					data.add((String) Sex.getSelectedItem());
					data.add(Brith.getText());
					
					if(button.getText().equals("修改")){
						if(check_class(classcode.getText())==true){
							if(Sql_connetcton.update(data, "学生")==true){
								JOptionPane.showMessageDialog(getParent(), "修改完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								clear();
								refresh();
							}else{
								JOptionPane.showMessageDialog(getParent(), "修改失败！请检查格式。", "抱歉", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							classcode.setText("");
						}
					}
					else{
						String [][]ans=Sql_connetcton.find_student(1,num.getText());
						if(ans!=null&&ans.length!=0){
							int t=JOptionPane.showConfirmDialog(getParent(),"已存在学号"+num.getText()+"的学生,是否进入修改模式?","错误",JOptionPane.YES_NO_OPTION );
							if(t==0){
								classcode.setText(ans[0][0]);
								Name.setText(ans[0][2]);
								Sex.setSelectedItem(ans[0][3]);
								Brith.setText(ans[0][4]);
								button.setText("修改");
								num.setEditable(false);
								button_2.setVisible(true);
							}
						}
						else{
							if(check_class(classcode.getText())==true){
								if(Sql_connetcton.insert(data, "学生")==true){
									JOptionPane.showMessageDialog(getParent(), "录入完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
									clear();
									refresh();
								}else{
									JOptionPane.showMessageDialog(getParent(), "录入失败！格式错误或有重复学号", "抱歉", JOptionPane.ERROR_MESSAGE);
								}
							}else{
								classcode.setText("");
							}
							
							
						}
					}
					
				}
			}
		});
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
		//num.setn(Name);
			setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{ classcode,num,  Name, Sex,Brith,button,button_2,textField_2, button_1, btnNewButton,table}));
	}
}
