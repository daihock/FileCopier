import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FileListDialog {

    public static JDialog listFrame;


    public FileListDialog () {
        buildFrame(buildPanel());

    }

    /**
     * Build dialog frame with file list checkboxes
     *
     * @param panel JPanel component
     */
    private void buildFrame(JPanel panel) {
        /*Dialog frame uses current active window as parent window.
        Dialog modality type set to Application. */
        listFrame = new JDialog(FocusManager.getCurrentManager().getActiveWindow(),
                                        "File List", Dialog.ModalityType.APPLICATION_MODAL);
        JDialog.isDefaultLookAndFeelDecorated();
        listFrame.addWindowListener(new DialogWindowListener());
        listFrame.setSize(new Dimension(700,500));
        listFrame.add(panel);
        //listFrame.pack();
        listFrame.setResizable(false);
        //TODO check window size method
        listFrame.setVisible(true);


    }

    /**
     * Build main panel with file list checkboxes and "ok"/"cancel" buttons
     * @return JPanel component
     */
    private JPanel buildPanel() {
        //Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Checkbox panel
        JPanel checkboxPanel = new JPanel();

        //Add Up Arrow and Down Arrow keys to focus traversal keys for checkboxes
        Set<AWTKeyStroke> oldBackKeys = checkboxPanel.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
        Set<AWTKeyStroke> oldForwardKeys = checkboxPanel.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        HashSet<AWTKeyStroke> newBackKeys = new HashSet<>(oldBackKeys);
        newBackKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0));
        HashSet<AWTKeyStroke> newForwardKeys = new HashSet<>(oldForwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
        checkboxPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newBackKeys);
        checkboxPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newForwardKeys);
        //End of key customizing

        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        //Add scroll pane to checkbox panel
        JScrollPane scrollPane = new JScrollPane(checkboxPanel);

        mainPanel.add(scrollPane);

        //Add checkboxes with file names
        for (Path entry : FileListAction.fileList) {
            JCheckBox checkbox = new JCheckBox(entry.getFileName().toString());
            checkbox.setSelected(true);
            checkbox.addActionListener(new CheckboxAction());
            checkboxPanel.add(checkbox);
        }

        //Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setAlignmentX(SwingConstants.RIGHT);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

        //Buttons
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new OkAction());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            listFrame.dispose();
            FileListAction.fileList.clear();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,10)));
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }
}
