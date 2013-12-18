package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fire extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField bi_text;
	private JTextField xuan_text;
	private JLabel num;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sql_connetcton.login_s("admin","admin");
			Fire dialog = new Fire();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JTable table;
	private String[] name=new String[]{"学号","姓名","未完成必修学分","平均分"};
	private JTextField qi_text;
	private void refresh(){
		Vector data=new Vector<>();
		data.add(bi_text.getText());
		data.add(xuan_text.getText());
		data.add(qi_text.getText());
		Vector<Vector> ans=Sql_connetcton.kill(data);
		final Vector<Vector<Vector>> Res=new Vector<>(ans.size());
		data.add("");
		for(Vector s:ans){
			data.set(3, s.get(0));
			Res.add(Sql_connetcton.killDetail(data));
			
		}
		num.setText(String.valueOf(ans.size())+"人");
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		TableModel tableModle=new DefaultTableModel(ans, names){
			public boolean isCellEditable(int row, int column){
                return false;}//表格不允许被编辑
		};
		table.addMouseListener(new MouseListener() {
			Fire_Detail temp=null;
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(temp!=null){
					temp.dispose();
					temp=null;
				}
				else{
			//	System.out.println("press"+String.valueOf(e.getXOnScreen())+" "+String.valueOf(e.getYOnScreen()));
					int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());
					temp=new Fire_Detail(Res.get(row), e.getXOnScreen(), e.getYOnScreen()-100);
					temp.setVisible(true);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//temp.dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
					String num=String.valueOf(((JTable)e.getSource()).getValueAt(row, 0));
					StudentDetial now=new StudentDetial(num);
					now.setVisible(true);
				} 
				else return; 
			}
		});
		table.setModel(tableModle);
	}

	/**
	 * Create the dialog.
	 */
	public Fire() {
		setTitle("\u5C06\u88AB\u5F00\u9664\u5B66\u751F");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel label = new JLabel("\u5FC5\u4FEE\u603B\u5E95\u7EBF:");
		label.setBounds(6, 8, 72, 15);
		contentPanel.add(label);
		
		bi_text = new JTextField(){
			public String getText(){
				String s=super.getText();
				if(s.equals(""))return "0";
				else return s;
			}
		};
		bi_text.setText("28");
		bi_text.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		bi_text.setBounds(76, 6, 25, 21);
		contentPanel.add(bi_text);
		bi_text.setColumns(10);
		
		JLabel label_1 = new JLabel("\u9009\u4FEE\u603B\u5E95\u7EBF:");
		label_1.setBounds(110, 8, 79, 15);
		contentPanel.add(label_1);
		
		xuan_text = new JTextField(){
			public String getText(){
				String s=super.getText();
				if(s.equals(""))return "0";
				else return s;
			}
		};
		xuan_text.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		xuan_text.setText("18");
		xuan_text.setBounds(184, 6, 25, 21);
		contentPanel.add(xuan_text);
		xuan_text.setColumns(10);

		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(345, 4, 79, 23);
		contentPanel.add(button);
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 35, 414, 193);
		contentPanel.add(scrollPane);
		
		JLabel label_2 = new JLabel("\u5B66\u671F\u5FC5\u4FEE\u5E95\u7EBF:");
		label_2.setBounds(224, 8, 89, 15);
		contentPanel.add(label_2);
		
		qi_text = new JTextField() {
			public String getText(){
				String s=super.getText();
				if(s.equals(""))return "0";
				else return s;
			}
		};
		qi_text.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())){
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		qi_text.setText("8");
		qi_text.setColumns(10);
		qi_text.setBounds(311, 6, 25, 21);
		contentPanel.add(qi_text);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(367, 238, 67, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel label_3 = new JLabel("\u603B\u4EBA\u6570:");
		label_3.setBounds(20, 242, 54, 15);
		contentPanel.add(label_3);
		
		num = new JLabel("");
		num.setBounds(65, 242, 54, 15);
		contentPanel.add(num);
		refresh();
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
	}
}
