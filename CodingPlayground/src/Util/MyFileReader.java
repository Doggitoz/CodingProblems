package Util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MyFileReader {
    public static List<String> ReadFile(String folder, String file) {
        List<String> lines;
        String path = "c:\\Users\\cpWhe\\Documents\\GitHub\\CodingProblems\\CodingPlayground\\test-cases\\" + folder + "\\" + file + ".txt";
        try {
            lines = Files.readAllLines(Paths.get(path));
        }
        catch (IOException e) {
            return null;
        }
        return lines;
    }
}
