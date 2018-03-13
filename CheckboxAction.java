import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CheckboxAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton aBtn = (AbstractButton) e.getSource();
        boolean isChkd = aBtn.getModel().isSelected();
        //Set path to add or delete from file list
        Path buttonPath = Paths.get(MainPanel.sourcePath).resolve(aBtn.getText());
        if (isChkd) { //If checkbox is unchecked - remove entry from file list, if re-checked – add entry
            FileListAction.fileList.add(buttonPath);
        } else {
            FileListAction.fileList.remove(buttonPath);
        }
        System.out.println(isChkd + " " + aBtn.getText() + " " + FileListAction.fileList);

    }
}
