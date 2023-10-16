package GameOfWar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

public class GameOfWarTest {

    public static void main(String[] args) throws IOException {
        GameOfWar g = new GameOfWar();
        List<String> lines = fileToLines("four.txt");
        while (true) {
            Collections.shuffle(lines);
            int t = g.solve(linesToArray(lines));
            System.out.println(t);
            System.out.println("------------");
            for (int i = 0; i < 51; i++) {
                System.out.println(lines.get(i));
            }
            System.out.print(lines.get(51));
            break;
            // if (t != -1)
            //     System.out.println(t);

            // if (t > 300) {
            //     System.out.println("---------");
            //     for (int i = 0; i < 51; i++) {
            //         System.out.println(lines.get(i));
            //     }
            //     System.out.print(lines.get(51));
            //     break;
            // }
        }
    }

    public static Path getPathToFile(String fileName) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "\\src\\GameOfWar\\input\\" + fileName;
        return Paths.get(path);
    }

    public static List<String> fileToLines(String fileName) {
        List<String> lines = new LinkedList<String>();
        try {
            lines = Files.readAllLines(getPathToFile(fileName));
        } catch (IOException e) {};
        return lines;
    }

    public static int[] linesToArray(List<String> lines) {
        int[] deck = new int[52];
        for (int i = 0; i < 52; i++) {
            deck[i] = Integer.parseInt(lines.get(i).trim());
        }
        return deck;
    }

    public static int[] fileToArray(String fileName) {
        int[] deck = new int[52];
        List<String> lines = new LinkedList<String>();
        try {
            lines = Files.readAllLines(getPathToFile(fileName));
            for (int i = 0; i < 52; i++) {
                deck[i] = Integer.parseInt(lines.get(i).trim());
            }
        } catch (IOException e) {};
        return deck;
    }

    // @Test
    // public void one() {
    //     String fileName = "one.txt";
    // }
}
