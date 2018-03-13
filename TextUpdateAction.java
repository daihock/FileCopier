import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextUpdateAction implements DocumentListener {

    JTextField textField;

    public TextUpdateAction(JTextField textField) {
        this.textField = textField;

    }


    @Override
    public void insertUpdate(DocumentEvent e) {
        String q = textField.getName();
        if (q == "sourceField") {
            MainPanel.sourcePath = textField.getText();

        } else {
            MainPanel.destPath = textField.getText();

        }

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (textField.getName() == "sourceField") {
            MainPanel.sourcePath = textField.getText();

        } else {
            MainPanel.destPath = textField.getText();

        }

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
