package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import java.awt.Color;
import javax.swing.UIManager;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("admin","admin");
					Main frame = new Main();
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
	public Main() {
		setTitle("\u5B66\u7C4D\u7BA1\u7406\u7CFB\u7EDF-jxy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton button = new JButton("\u5BFC\u5165\u6570\u636E");
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Import im=new Import();
				im.setVisible(true);
			}
		});
		
		JButton button_6 = new JButton("\u5F55\u5165\u5B66\u751F\u4FE1\u606F");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_update su=new Student_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_6);
		
		JButton button_8 = new JButton("\u73ED\u7EA7\u4FEE\u6539");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=JOptionPane.showInputDialog("°àºÅ:");
				if(s==null||s.equals(""))return;
				Class_insert ci=new Class_insert(s);
				ci.setVisible(true);
			}
		});
		
		JButton button_5 = new JButton("\u5F55\u5165\u6210\u7EE9(\u6309\u5B66\u53F7)");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Score_update su=new Score_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_5);
		
		JButton button_3 = new JButton("\u5F55\u5165\u6210\u7EE9(\u6309\u8BFE\u7A0B)");
		contentPane.add(button_3);
		contentPane.add(button_8);
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u5B66\u751F\u4FE1\u606F");
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u67E5\u8BE2\u4EFB\u8BFE\u6559\u5E08");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher_Find tf=new Teacher_Find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		JButton button_9 = new JButton("\u6CE8\u9500\u767B\u5F55");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sql_connetcton.close();
				Main.this.dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		
		JButton button_7 = new JButton("\u5C06\u88AB\u5F00\u9664\u5B66\u751F\u540D\u5355");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fire f=new Fire();
				f.setVisible(true);
			}
		});
		
		JButton button_4 = new JButton("\u6559\u5B66\u8BA1\u5212");
		contentPane.add(button_4);
		contentPane.add(button_7);
		contentPane.add(button_9);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("<html><body><center>\r\n\u8D3E\u65B0\u79B9<br>\r\n\u897F\u5B89\u7535\u5B50\u79D1\u6280\u5927\u5B66<br>\r\n03111002<br></center></body></html>");
		panel.add(lblNewLabel);

		
		JButton button_10 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(button_10);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_find sf=new Student_find();
				sf.setVisible(true);
			}
		});
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

}
