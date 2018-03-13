import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Deprecated
public class MoveThread extends Thread {
    @Override
    public void run() {
        super.run();

        Path destDir = Paths.get(MainPanel.destPath);
        for (Path entry : FileListAction.fileList) {
            try {
                Files.move(entry, destDir.resolve(entry.getFileName()));
            }
            catch (IOException e1) {
                System.err.println(e1);
            }

        }
        FileListAction.fileList.clear();
    }

}
