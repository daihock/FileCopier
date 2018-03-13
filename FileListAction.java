import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileListAction implements ActionListener {

    private String sourcePath, destPath, pattern;
    public static ArrayList<Path> fileList = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        this.sourcePath = MainPanel.sourcePath;
        this.destPath = MainPanel.destPath;
        this.pattern = MainPanel.pattern;

        try {
            checkEmptyArgs(); //Check if any field contains empty string and throw exception to skip directory check

            Path dirPath = Paths.get(sourcePath); //Get Path type from String variable

            if (Files.isDirectory(dirPath)) { //Check is a current Path a directory
                try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath, pattern)) {
                    for (Path entry : directoryStream) {
                        if (Files.isRegularFile(entry)) {
                            fileList.add(entry);
                        }
                    }
                    System.out.println(fileList);

                } catch (IOException x) {
                    System.err.println(x);
                }
                if (!fileList.isEmpty()) {
                    new FileListDialog();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Can't find any files corresponding to pattern\n" +
                            "or directory is empty");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Can't find source folder");
            }
        }

        catch (FieldEmptyException e1) {
           JOptionPane.showMessageDialog(null, e1.getMessage(),
                   "Empty input", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**Check if any text field information not presented
     * Don't check <b>all</b> text field.
     * Instead - it have priority to fields check. It means that if all fields are empty you receive only one message prioritized by list:
     * <ol>
     *     <li>Source folder</li>
     *     <li>Destination folder</li>
     *     <li>Pattern</li>
     * </ol>
     *
     * @throws FieldEmptyException
     */

    private void checkEmptyArgs() throws FieldEmptyException {

            if (sourcePath.isEmpty()) {
                throw new FieldEmptyException("Source folder can't be empty");
            }

            if (destPath.isEmpty()) {
                throw new FieldEmptyException("Destination path can't be empty");
            }

            if (pattern.isEmpty()) {
                throw new FieldEmptyException("Pattern can't be empty (use * for 'all files')");
            }


    }
}
