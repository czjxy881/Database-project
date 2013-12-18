package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Import extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Import frame = new Import();
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
	public Import() {
		setTitle("\u6570\u636E\u5BFC\u5165");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button = new JButton("\u5B66\u751F\u4FE1\u606F\u5BFC\u5165");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_insert si=new Student_insert();
				si.setVisible(true);
				dispose();
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u8BFE\u7A0B\u4FE1\u606F\u5BFC\u5165");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course_insert ci=new Course_insert();
				ci.setVisible(true);
				dispose();
			}
		});
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u6210\u7EE9\u5BFC\u5165");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Score_insert si=new Score_insert();
				si.setVisible(true);
				dispose();
			}
		});
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u6559\u5B66\u8BA1\u5212\u5BFC\u5165");
		button_3.setEnabled(false);
		contentPane.add(button_3);
		this.setResizable(false);//æ”÷–
		this.setLocationRelativeTo(null);
	}
}
