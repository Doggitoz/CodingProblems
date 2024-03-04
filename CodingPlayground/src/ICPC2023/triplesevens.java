package ICPC2023;
import java.io.*;

public class triplesevens {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        System.out.println(solve());
    }
    
    public static int solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < 3; i++) {
            String s = in.readLine();
            if (!s.contains("7")) return 0;
        }
        return 777;
    }
}