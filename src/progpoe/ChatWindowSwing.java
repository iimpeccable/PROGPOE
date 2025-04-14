package progpoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindowSwing extends JFrame {

    private JTextArea messageArea;
    private JTextField messageInput;
    private JButton sendButton;
    private String loggedInUsername;

    public ChatWindowSwing(String username) {
        super("QuickChat - " + username);
        this.loggedInUsername = username;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        /// Message Display Area
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        add(messageScrollPane, BorderLayout.CENTER);

        /// Input Area and Send Button
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputPanel.add(messageInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);
    }
    ///To send messages
    private void sendMessage() {
        String message = messageInput.getText().trim();
        if (!message.isEmpty()) {
            String formattedMessage = loggedInUsername + ": " + message + "\n";
            messageArea.append(formattedMessage);
            messageInput.setText("");
            
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatWindowSwing("TestUser")); 
    }
}