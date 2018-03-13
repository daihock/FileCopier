import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseAction implements ActionListener {
    private JTextField textField;

    public BrowseAction(JTextField textField) {

        this.textField = textField;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(); //Init file chooser
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Directories only mode

        int choose = chooser.showDialog(null, "Choose"); //Show file chooser dialog
        if (choose == JFileChooser.APPROVE_OPTION) {

            String dirPath = chooser.getSelectedFile().getAbsolutePath(); //Set selected directory absolute path
            textField.setText(dirPath); //Set text to source text field

        }

    }
}
