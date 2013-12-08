package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Class_insert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField Classcode;
	private JTextField teacher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sql_connetcton.login_s("admin","admin");
			Class_insert dialog = new Class_insert("031111");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Class_insert(final String code) {
		setTitle("\u73ED\u7EA7\u4FE1\u606F\u4FEE\u6539");
		setBounds(100, 100, 340, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel(" \u73ED\u53F7:");
			label.setBounds(23, 25, 54, 15);
			contentPanel.add(label);
		}
		{
			Classcode = new JTextField();
			Classcode.setBounds(68, 22, 66, 21);
			contentPanel.add(Classcode);
			Classcode.setText(code);
			Classcode.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4E13\u4E1A:");
			label.setBounds(137, 25, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel = new JLabel("\u73ED\u4E3B\u4EFB:");
			lblNewLabel.setBounds(23, 76, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			teacher = new JTextField();
			teacher.setBounds(68, 73, 66, 21);
			contentPanel.add(teacher);
			teacher.setColumns(10);
		}
		
		final Map<String, String> ans=Sql_connetcton.getMajority();
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(174, 22, 140, 21);
		for(String s:ans.keySet()){
			comboBox.addItem(s);
		}
		contentPanel.add(comboBox);
		
		Vector detail =Sql_connetcton.getClassDetial(code);
		teacher.setText((String) detail.get(1));
		if((String)detail.get(0)!="")comboBox.setSelectedItem(detail.get(0));
		Classcode.setEnabled(false);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u4FEE\u6539");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vector<String> data=new Vector<String>();
						data.add(code);
						data.add(ans.get(comboBox.getSelectedItem()));
						data.add(teacher.getText());
						if(Sql_connetcton.update(data,"°à¼¶")==true){
							JOptionPane.showMessageDialog(getParent(), "ÐÞ¸ÄÍê³É£¡", "¹§Ï²", JOptionPane.INFORMATION_MESSAGE);
							Class_insert.this.dispose();
							
						}else{
							JOptionPane.showMessageDialog(getParent(), "ÐÞ¸ÄÊ§°Ü£¡", "±§Ç¸", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Class_insert.this.dispose();
						
					}
					
				});
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
			}
			this.setResizable(false);//¾ÓÖÐ
			this.setLocationRelativeTo(null);
		}
	}
}
