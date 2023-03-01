import java.util.*;
import java.io.*;

public class StringAnagrams {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String strOne = in.readLine().trim();
        String strTwo = in.readLine().trim();

        int similarities = 0;
        for (int i = 0; i < strOne.length(); i++) {
            if (strTwo.contains(Character.toString(strOne.charAt(i)))) {
                similarities++;
                strTwo = strTwo.replaceFirst(Character.toString(strOne.charAt(i)), "");
            }
        }
        System.out.println((strTwo.length() + strOne.length()) - similarities);
    }
}
