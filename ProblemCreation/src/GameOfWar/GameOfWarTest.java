package GameOfWar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

public class GameOfWarTest {

    public int[] fileToArray(String fileName) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "\\src\\GameOfWar\\input\\" + fileName;
        int[] deck = new int[52];
        List<String> lines = new LinkedList<String>();
        try {
            lines = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < 52; i++) {
            deck[i] = Integer.parseInt(lines.get(i));
        }
        } catch (IOException e) {};
        return deck;
    }

    @Test
    public void one() {
        String fileName = "one.txt";
    }
}
