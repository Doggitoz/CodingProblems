import java.io.*;
import java.util.*;

public class naq23_isyavowel {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String t = in.readLine();
        int y = 0;
        int v = 0;
        for (Character c : t.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') v++;
            if (c == 'y') y++;
        }
        System.out.printf("%d %d", v, v + y);
    }
}