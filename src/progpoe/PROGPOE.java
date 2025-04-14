package progpoe;

import javax.swing.SwingUtilities;

public class PROGPOE {

    public static void main(String[] args) {
        /// Launch the application using Swing's event dispatch thread
        SwingUtilities.invokeLater(() -> {
            /// Initialize the UserLogInSwing class which contains the UI logic
            new UserLogInSwing();
        });
    }
}