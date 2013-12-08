package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Class_insert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Class_insert dialog = new Class_insert();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Class_insert() {
		setBounds(100, 100, 340, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel(" \u73ED\u53F7:");
			label.setBounds(10, 25, 54, 15);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(52, 22, 66, 21);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4E13\u4E1A:");
			label.setBounds(150, 25, 54, 15);
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel = new JLabel("\u73ED\u4E3B\u4EFB:");
			lblNewLabel.setBounds(10, 76, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(52, 73, 66, 21);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
