import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CopyWork extends SwingWorker<String, Integer> {
    @Override
    protected String doInBackground() {
        int size = FileListAction.fileList.size();
        Path destDir = Paths.get(MainPanel.destPath);
        int i = 1;
        for (Path entry : FileListAction.fileList) {
            ProgressDialog.copyLabel.setText("Copy files: " + i + " of " + size);
            i++;
            ProgressDialog.textField.append(entry.toString() + "\n");
            try {
                Files.copy(entry, destDir.resolve(entry.getFileName()));

            } catch (IOException e1) {
                System.err.println(e1);
            }

        }
        return null;
    }

}
