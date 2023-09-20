import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRunner {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        NumberOfDigitOne.countDigitOne(n);
    }
}
