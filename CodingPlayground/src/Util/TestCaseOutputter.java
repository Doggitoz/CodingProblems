package Util;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class TestCaseOutputter {
    public static void main(String[] args) throws IOException {
        FileWriter myWriter = new FileWriter("output.txt");
        int n = 100;
        for (int i = 1; i <= n; i++) {
            myWriter.write(String.valueOf(1));
            myWriter.write(System.lineSeparator());
        }
        myWriter.close();
    }
}
