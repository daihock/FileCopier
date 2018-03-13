import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@Deprecated
public class FileListFrame {

    ArrayList<Path> fileList;

    public FileListFrame (ArrayList<Path> fileList) {
        this.fileList = fileList;
        buildFrame(buildPanel());

    }

    private void buildFrame(JPanel panel) {
        JFrame listFrame = new JFrame("File List");
        JFrame.isDefaultLookAndFeelDecorated();
        listFrame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listFrame.add(panel);
        listFrame.pack();
        listFrame.setVisible(true);

    }

    private JPanel buildPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel checkboxPanel = new JPanel();
        Set<AWTKeyStroke> oldBackKeys = checkboxPanel.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> oldForwardKeys = checkboxPanel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        HashSet<AWTKeyStroke> newBackKeys = new HashSet<>(oldBackKeys);
        newBackKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
        HashSet<AWTKeyStroke> newForwardKeys = new HashSet<>(oldForwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        checkboxPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackKeys);
        checkboxPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(checkboxPanel);

        mainPanel.add(scrollPane);

        for (Path entry : fileList) {
            JCheckBox checkbox = new JCheckBox(entry.getFileName().toString());
            checkbox.setSelected(true);
            checkboxPanel.add(checkbox);
        }

        return mainPanel;
    }
}
