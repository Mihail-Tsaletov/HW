import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(FileManager.saveFile("filesTest\\",
                "1222",
                "testFile"));

        FileManager.findFile("testFile", "filesTest\\");
    }
}