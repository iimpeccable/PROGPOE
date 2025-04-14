package progpoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogInSwing extends JFrame {
    private Logic userLogic;
    private MessageManager messageManager;
    private Font mainFont = new Font("Aptos", Font.PLAIN, 14);
    private Color textColor = new Color(50, 50, 50);
    private Color accentColor = new Color(0, 128, 128);
    private Color backgroundColor = new Color(245, 245, 245);
    private Color sectionBackgroundLogin = new Color(220, 240, 230);
    private Color sectionBackgroundRegister = new Color(245, 245, 245);

    /// Login Components
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JButton loginButton;

    /// Registration Components
    private JTextField registerNameField;
    private JTextField registerPhoneAreaCodeField;
    private JTextField registerPhoneNumberField;
    private JPasswordField registerPasswordField;
    private JButton registerButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserLogInSwing());
    }
    
    public UserLogInSwing() {
        super("QuickChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500); 
        setLocationRelativeTo(null);
        getContentPane().setBackground(backgroundColor);
        ///Links to other classes
        userLogic = new Logic();
        messageManager = new MessageManager();

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); 
        mainPanel.setBackground(backgroundColor);

        JPanel loginSection = createLoginSection();
        JPanel registerSection = createRegisterSection();

        mainPanel.add(loginSection);
        mainPanel.add(registerSection);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    ///Creates Log in section
    private JPanel createLoginSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(sectionBackgroundLogin);

        JLabel welcomeLabel = new JLabel("Welcome to QuickChat");
        welcomeLabel.setFont(new Font("Aptos", Font.BOLD, 24));
        welcomeLabel.setForeground(accentColor);

        JLabel infoLabel = new JLabel("Login with your credentials.");
        infoLabel.setForeground(Color.GRAY);

        loginUsernameField = createTextField("Username", 10);
        loginPasswordField = createPasswordField("Password", 10);

        loginButton = createStyledButton("Log In", accentColor);
        loginButton.addActionListener(e -> {
            String username = loginUsernameField.getText().trim();
            String password = new String(loginPasswordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                showMessage("Please fill in all fields.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Logic.Result result = userLogic.loginUser(username, password);
            if (result.isSuccess()) {
                showMessage(result.getMessage(), "Login Success", JOptionPane.INFORMATION_MESSAGE);
                // Transition to the new Chat Window UI here
                SwingUtilities.invokeLater(() -> new ChatWindowSwing(username));
                dispose(); // Close login window after successful login
            } else {
                showMessage(result.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(infoLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(loginUsernameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(loginPasswordField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(loginButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }
    ///Creates registration panel
    private JPanel createRegisterSection() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(sectionBackgroundRegister);

        JLabel createAccountLabel = new JLabel("Register");
        createAccountLabel.setFont(new Font("Aptos", Font.BOLD, 24));
        createAccountLabel.setForeground(textColor);
        createAccountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel orLabel = new JLabel("Register Below");
        orLabel.setForeground(Color.GRAY);
        orLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        registerNameField = createTextField("Username", 15);

        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        registerPhoneAreaCodeField = createTextField("Area Code", 5);
        registerPhoneNumberField = createTextField("Phone Number", 10);
        phonePanel.add(registerPhoneAreaCodeField);
        phonePanel.add(registerPhoneNumberField);
        phonePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        registerPasswordField = createPasswordField("Password", 15);

        registerButton = createStyledButton("Register", accentColor);
        registerButton.addActionListener(e -> {
            String username = registerNameField.getText().trim(); 
            String phone = registerPhoneAreaCodeField.getText() + registerPhoneNumberField.getText().trim();
            String password = new String(registerPasswordField.getPassword()).trim();

            Logic.Result result = userLogic.registerUser(username, phone, password);
            showMessage(result.getMessage(), result.isSuccess() ? "Success" : "Error",
                    result.isSuccess() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        });
        registerButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(createAccountLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(orLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(registerNameField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(phonePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(registerPasswordField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(registerButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JTextField createTextField(String placeholder, int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(mainFont);
        textField.setForeground(textColor);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(placeholder),
                BorderFactory.createEmptyBorder(2, 5, 2, 5)));
        return textField;
    }

    private JPasswordField createPasswordField(String placeholder, int columns) {
        JPasswordField passwordField = new JPasswordField(columns);
        passwordField.setFont(mainFont);
        passwordField.setForeground(textColor);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(placeholder),
                BorderFactory.createEmptyBorder(2, 5, 2, 5)));
        return passwordField;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30)); 
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}