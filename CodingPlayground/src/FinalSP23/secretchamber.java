package FinalSP23;

import java.util.*;
import java.io.*;
 @SuppressWarnings("unchecked")
public class secretchamber {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] nm = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Character>[] map = new ArrayList[26];
        ArrayList<Character>[] group = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            map[i] = new ArrayList<>();
            group[i] = new ArrayList<>();
        }

        for (int i = 0; i < nm[0]; i++) {
            String[] chars = reader.readLine().split(" ");
            char c1 = chars[0].charAt(0);
            char c2 = chars[1].charAt(0);
            map[c1 - 'a'].add(c2);
        }

        for (int i = 0; i < 26; i ++) {
            Queue<Character> Q = new LinkedList<>();
            Q.add((char) (i + 'a'));
            boolean[] visited = new boolean[26];
            while (!Q.isEmpty()) {
                char c = Q.poll();
                if (visited[c - 'a']) continue;
                visited[c - 'a'] = true;
                group[i].add(c);
                for (Character ch : map[c - 'a']) {
                    Q.add(ch);
                }
            }
        }

        for (int i = 0; i < nm[1]; i++) {
            String[] words = reader.readLine().split(" ");
            if (words[0].length() != words[1].length()) {
                System.out.println("no");
                continue;
            }
            boolean validWord = true;
            for (int j = 0; j < words[0].length(); j++) {
                int c1 = words[0].charAt(j) - 'a';
                int c2 = words[1].charAt(j) - 'a';
                boolean valid = false;
                for (int k = 0; k < group[c1].size(); k++) {
                    if (group[c1].get(k) == (char) (c2 + 'a')) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    validWord = false;
                    break;
                }
            }
            if (!validWord) {
                System.out.println("no");
                continue;
            }
            System.out.println("yes");
        }
    }
}
