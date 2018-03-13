import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveWork extends SwingWorker<String, Integer> {
    @Override
    protected String doInBackground(){
        int size = FileListAction.fileList.size();
        Path destDir = Paths.get(MainPanel.destPath);
        int i = 1;
        for (Path entry : FileListAction.fileList) {
            ProgressDialog.copyLabel.setText("Move files: " + i + " of " + size);
            i++;
            ProgressDialog.textField.append(entry.toString() + "\n");
            try {
                Files.copy(entry, destDir.resolve(entry.getFileName()));

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return null;
    }

}
