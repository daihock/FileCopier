import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PatternAction implements DocumentListener {

    JTextField patternField;

    public PatternAction (JTextField patternField) {
        this.patternField = patternField;

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        MainPanel.pattern = patternField.getText();

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        MainPanel.pattern = patternField.getText();

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
