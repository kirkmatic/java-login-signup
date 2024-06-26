import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LogForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtusername;
    private JPasswordField txtpassword;

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LogForm frame = new LogForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LogForm() {
        conn = MySQLConnection.conn();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1009, 722);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 102));
        panel.setBounds(0, 0, 1000, 689);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(new Color(51, 0, 102));
        panel_2.setBounds(0, 0, 500, 689);
        panel.add(panel_2);

        JLabel lblWelcome = new JLabel("Welcome to");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
        lblWelcome.setBounds(72, 235, 355, 132);
        panel_2.add(lblWelcome);

        JLabel lblShopName = new JLabel("BOK'S COMSHOP");
        lblShopName.setHorizontalAlignment(SwingConstants.CENTER);
        lblShopName.setForeground(new Color(128, 255, 0));
        lblShopName.setFont(new Font("Euphoria Party", Font.PLAIN, 50));
        lblShopName.setBounds(77, 315, 345, 66);
        panel_2.add(lblShopName);

        JLabel lblLogin = new JLabel("LOGIN");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(new Color(51, 51, 51));
        lblLogin.setFont(new Font("Sono", Font.BOLD, 50));
        lblLogin.setBounds(549, 176, 383, 79);
        panel.add(lblLogin);

        txtusername = new JTextField();
        txtusername.setColumns(10);
        txtusername.setBackground(Color.WHITE);
        txtusername.setBounds(559, 266, 373, 52);
        panel.add(txtusername);

        txtpassword = new JPasswordField();
        txtpassword.setBounds(559, 349, 373, 52);
        panel.add(txtpassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> login());
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Sono ExtraBold", Font.PLAIN, 30));
        btnLogin.setBackground(new Color(128, 255, 0));
        btnLogin.setBounds(559, 422, 373, 46);
        panel.add(btnLogin);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(e -> {
            dispose();
            new SignUpForm().setVisible(true);
        });
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("Sono ExtraBold", Font.PLAIN, 30));
        btnSignUp.setBackground(new Color(255, 102, 102));
        btnSignUp.setBounds(619, 502, 258, 46);
        panel.add(btnSignUp);
    }

    private void login() {
        String username = txtusername.getText();
        char[] password = txtpassword.getPassword();

        try {
            String sql = "SELECT * FROM users WHERE username = ? and password = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, String.valueOf(password));
            rs = pst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Invalid Username and Password");
            } else {
                JOptionPane.showMessageDialog(null, "Login Success!!");
                dispose();
                new HomePage().setVisible(true);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
⬤