import javax.swing.*;

/**@author Vitaliy Smirnov
 * @version 0.0.1
 */

public class Window {
   // public static JFrame mainFrame;

    private Window() {

        ImageIcon icon  = new ImageIcon("resources/copier_logo.png");

        JFrame mainFrame = new JFrame("File Copier");
        JFrame.isDefaultLookAndFeelDecorated();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setResizable(false);
        mainFrame.add(new MainPanel());
        mainFrame.pack();
        mainFrame.setVisible(true);


    }


    public static void main (String[] args) {

        SwingUtilities.invokeLater(Window::new);

    }

    public static void disable() {

    }
}
