import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    public static JLabel copyLabel;
    public static JTextArea textField;

    private String type, labelText, fieldText;

    public ProgressDialog(String type, String labelText, String fieldText) {
        super(FileListDialog.listFrame, "Copy", ModalityType.MODELESS);
        this.type = type;
        this.labelText = labelText;
        this.fieldText = fieldText;

        SwingUtilities.invokeLater(this::initDialog);
    }

    /**
     * Build dialog frame
     */

    private void initDialog() {
        this.setSize(800, 500);
        System.out.println(this.getSize());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.add(buildPanel());
        //this.pack();
        this.setVisible(true);
        actionStart();

    }

    private void actionStart() {
        if (MainPanel.copyType == MainPanel.COPY) {
            CopyWork copy = new CopyWork();
            copy.execute();
        } else {
            MoveWork move = new MoveWork();
            move.execute();
        }


    }

    /**
     * Build panel with copy progress
     *
     * @return JPanel component
     */

    private JPanel buildPanel() {
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setMaximumSize(new Dimension(800,20));
        copyLabel = new JLabel(type + labelText);
        //copyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        copyLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        labelPanel.add(copyLabel);
        textField = new JTextArea(fieldText);
        textField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setBorder(BorderFactory.createEmptyBorder( 1, 5, 5, 5));
        progressPanel.add(labelPanel);
        progressPanel.add(scrollPane);
        return progressPanel;

    }


}
