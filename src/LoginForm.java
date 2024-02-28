import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	private JFrame frame;
	private JTextField txtusername;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	public LoginForm() {
		initialize();
		conn = MySQLConnection.conn();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1011, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1000, 689);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 0, 102));
		panel_2.setBounds(0, 0, 500, 689);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(72, 235, 355, 132);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BOK'S COMSHOP");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
		lblNewLabel_1.setForeground(new Color(128, 255, 0));
		lblNewLabel_1.setBounds(77, 315, 345, 66);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN");
		lblNewLabel_2.setForeground(new Color(51, 51, 51));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Sono", Font.BOLD, 50));
		lblNewLabel_2.setBounds(552, 193, 383, 79);
		panel.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setBackground(new Color(255, 255, 255));
		txtusername.setBounds(562, 283, 373, 52);
		panel.add(txtusername);
		txtusername.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(562, 366, 373, 52);
		panel.add(txtpassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = txtusername.getText();
				char[] password = txtpassword.getPassword();
				
				try {
					
					String sql = "SELECT * FROM users WHERE username = ? and password = ?";
					pst = conn.prepareStatement(sql);
					pst.setString(1, username);
					pst.setString(2, String.valueOf(password));
					rs = pst.executeQuery();
					
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null, "Invalid Username and Password");
					}else {
						JOptionPane.showMessageDialog(null, "Login Success!!");
						frame.dispose();
						new HomePage().show();;
					}
					
				}catch(SQLException ex){
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Sono ExtraBold", Font.PLAIN, 30));
		btnNewButton.setBackground(new Color(128, 255, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(562, 439, 373, 46);
		panel.add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SignUpForm().show();
			}
		});
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Sono ExtraBold", Font.PLAIN, 30));
		btnSignUp.setBackground(new Color(255, 102, 102));
		btnSignUp.setBounds(622, 519, 258, 46);
		panel.add(btnSignUp);
	}
}
