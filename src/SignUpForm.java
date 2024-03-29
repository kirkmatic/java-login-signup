import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class SignUpForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtrepassword;
	private JTextField txtemail;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm frame = new SignUpForm();
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
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public SignUpForm() {
		
		conn = MySQLConnection.conn();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 689);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
	    setLocationRelativeTo(null);
		contentPane.setBackground(new Color(255, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 102));
		panel.setBounds(0, 0, 500, 650);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to");
		lblNewLabel.setBounds(113, 251, 282, 65);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BOK'S COMSHOP");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(128, 255, 0));
		lblNewLabel_1.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(77, 301, 345, 66);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sign Up");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(51, 51, 51));
		lblNewLabel_2.setFont(new Font("Sono", Font.BOLD, 50));
		lblNewLabel_2.setBounds(552, 89, 383, 79);
		contentPane.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBackground(Color.WHITE);
		txtusername.setBounds(562, 191, 373, 52);
		contentPane.add(txtusername);
		
		txtrepassword = new JPasswordField();
		txtrepassword.setBounds(562, 442, 373, 52);
		contentPane.add(txtrepassword);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpForm frame = new SignUpForm();
				frame.dispose();
				LogForm login = new LogForm();
				login = new LogForm();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setFont(new Font("Sono ExtraBold", Font.PLAIN, 20));
		btnLogIn.setBackground(new Color(128, 255, 0));
		btnLogIn.setBounds(800, 516, 135, 46);
		contentPane.add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = txtusername.getText();
		        String email = txtemail.getText();
		        char[] password = txtpassword.getPassword();
		        char[] repassword = txtrepassword.getPassword();

		        try {
		            // Check if passwords match before inserting into the database
		            if (Arrays.equals(password, repassword)) {
		                pst = conn.prepareStatement("INSERT INTO users(username, email, password) VALUES(?, ?, ?)");
		                pst.setString(1, username);
		                pst.setString(2, email);
		                pst.setString(3, String.valueOf(password));  // Assuming your password field in the database is a string

		                pst.executeUpdate();

		                JOptionPane.showMessageDialog(null, "Registration Successful!");
		                
		                txtusername.setText("");
		                txtemail.setText("");
		                txtpassword.setText("");
		                txtrepassword.setText("");
		                
		            } else {
		                JOptionPane.showMessageDialog(null, "Passwords do not match. Please re-enter.");
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error during registration. Please try again.");
		        }
		    }
		});

		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Sono ExtraBold", Font.PLAIN, 30));
		btnSignUp.setBackground(new Color(255, 153, 153));
		btnSignUp.setBounds(562, 516, 228, 46);
		contentPane.add(btnSignUp);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBackground(Color.WHITE);
		txtemail.setBounds(562, 266, 373, 52);
		contentPane.add(txtemail);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(562, 358, 373, 52);
		contentPane.add(txtpassword);
	}

}
