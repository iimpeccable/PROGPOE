package progpoe;

import javax.swing.*;
import java.util.ArrayList;

public class MessageManager {

    private boolean isLoggedIn;
    private int maxMessages;
    private int currentMessageCount;
    private ArrayList<String> sentMessages;
    

    public MessageManager() {
        this.isLoggedIn = false;
        this.currentMessageCount = 0;
        this.sentMessages = new ArrayList<>();
    }

    /// Called after successful login
    public void showWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat", "Login Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /// Display the main menu (will be replaced by ChatWindow)
    public void showMainMenu() {
        if (!isLoggedIn) return;

        /// Prompt for number of messages to send
        String messageCountStr = JOptionPane.showInputDialog("Please define how many messages you'd like to send:");
        try {
            maxMessages = Integer.parseInt(messageCountStr); // User defines number of messages to send
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid number of messages.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /// Menu loop
        String[] options = {"Send Messages", "Show Recently Sent Messages", "Quit"};
        boolean running = true;
        while (running) {
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    sendMessages(); 
                    break;
                case 1:
                    showRecentlySentMessages();
                    break;
                case 2:
                    running = false;  
                    JOptionPane.showMessageDialog(null, "Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    /// Handles message sending
    private void sendMessages() {
        if (currentMessageCount >= maxMessages) {
            JOptionPane.showMessageDialog(null, "You have reached the maximum number of messages you can send.", "Max Messages Reached", JOptionPane.WARNING_MESSAGE);
            return;
        }

        while (currentMessageCount < maxMessages) {
            String message = JOptionPane.showInputDialog("Enter message " + (currentMessageCount + 1) + ":");
            if (message != null && !message.trim().isEmpty()) {
                sentMessages.add(message);
                currentMessageCount++;
                JOptionPane.showMessageDialog(null, "You sent: " + message, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Message cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /// Display recently sent messages
    private void showRecentlySentMessages() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven't sent any messages yet.", "No Messages", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder messageList = new StringBuilder("Recently Sent Messages:\n");
        for (String message : sentMessages) {
            messageList.append(message).append("\n");
        }

        JOptionPane.showMessageDialog(null, messageList.toString(), "Recently Sent Messages", JOptionPane.INFORMATION_MESSAGE);
    }

}