
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: plamen
 * Date: 23/06/12
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class MakeFileDirectory {
    public static void main(String[] args) throws IOException {
        String root = "D:\\PDF\\Java\\JDK7\\TheCompleteReferenceJdk7\\code\\src";
        Path newdir;

        for(int i=2; i<35; i++) {
            newdir = FileSystems.getDefault().getPath(root  + "/" + "ch" + i);
            Files.createDirectory(newdir);
        }
    }
}
