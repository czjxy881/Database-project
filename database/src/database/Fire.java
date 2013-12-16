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

public class Fire extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sql_connetcton.login_s("admin","admin");
			Fire dialog = new Fire();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JTable table;
	private String[] name=new String[]{"学号","姓名","未完成必修学分","平均分"};
	private void refresh(){
		
		Vector<Vector> ans=Sql_connetcton.kill(Integer.valueOf(textField.getText())-Integer.valueOf(textField_1.getText()));
		Vector<String> names=new Vector<String>();
		for(String s:name)names.add(s);
		TableModel tableModle=new DefaultTableModel(ans, names){
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
		
		JLabel label = new JLabel("\u5F00\u9664\u5B66\u5206:");
		label.setBounds(10, 10, 85, 15);
		contentPanel.add(label);
		
		textField = new JTextField(){
			public String getText(){
				String s=super.getText();
				if(s.equals(""))return "0";
				else return s;
			}
		};
		textField.setText("30");
		textField.addKeyListener(new KeyListener() {
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
		textField.setBounds(66, 8, 42, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8DDD\u79BB\u5206\u6570:");
		label_1.setBounds(142, 10, 66, 15);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField(){
			public String getText(){
				String s=super.getText();
				if(s.equals(""))return "0";
				else return s;
			}
		};
		textField_1.addKeyListener(new KeyListener() {
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
		textField_1.setText("2");
		textField_1.setBounds(199, 8, 31, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(304, 8, 79, 23);
		contentPanel.add(button);
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 35, 414, 185);
		contentPanel.add(scrollPane);
		refresh();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
	}
}
