import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class OkAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO dialog with simple copy/move progress

        if (destDirectoryCheck()) {
            int size = FileListAction.fileList.size();
            String labelText = "files: 1 " + "of " + size;
            String fieldText = "Copy started at " + new Date() + "\n";

            if (MainPanel.copyType == MainPanel.COPY) {
                new ProgressDialog("Copy", labelText, fieldText);

            } else {

            }
        }
    }

    /**
     * Check directory path.
     * If directory path not valid - create dialog window with error message.
     * If directory not exist - show confirm dialog to create new folder.
     *
     * @return true - if directory created or false if path not valid or user press Cancel button
     */

    private boolean destDirectoryCheck() {
        boolean result = false;

        try {
            Path checkPath = Paths.get(MainPanel.destPath); //Throw InvalidPathException if path not valid

            if (!Files.exists(checkPath)) { //If directory not exist - show dialog
                int returnState = JOptionPane.showConfirmDialog(null, "Directory not exist\n " +
                        "Create new directory?", "Directory not exist",JOptionPane.OK_CANCEL_OPTION);
                if (returnState == JOptionPane.OK_OPTION) { //Create directory if Ok button is pressed
                    File newDir = new File(MainPanel.destPath);
                    result = newDir.mkdir();

                }

            } else {
                result = true;
            }

        } catch (InvalidPathException ipe) {
            JOptionPane.showMessageDialog(null, "Invalid destination path!",
                                          "Invalid path!", JOptionPane.ERROR_MESSAGE);
        }

        return result;

    }
}
