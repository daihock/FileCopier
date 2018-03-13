
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
@Deprecated
public class CopyThread extends Thread {
    @Override
    public void run() {
        super.run();


        int size = FileListAction.fileList.size();
        String labelText = "files: 1 " + "of " + size;
        String fieldText = "Copy started at " + new Date() + "\n";



        synchronized (new ProgressDialog("Copy", labelText, fieldText)){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Path destDir = Paths.get(MainPanel.destPath);
        int i = 1;
        for (Path entry : FileListAction.fileList) {
            ProgressDialog.copyLabel.setText("Copy files: " + i + " of " + size);
            i++;
            ProgressDialog.textField.append(entry.toString() + "\n");
            System.out.println(ProgressDialog.textField.getText());
            try {
                Files.copy(entry, destDir.resolve(entry.getFileName()));

            }
            catch (IOException e1) {
                System.err.println(e1);
            }

        }
        FileListAction.fileList.clear();
    }
}
