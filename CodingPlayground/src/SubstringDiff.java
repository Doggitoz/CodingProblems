import java.io.*;
//import java.util.*;

public class SubstringDiff {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    // bitwise and = 0011 & 0110 -> 0010 (it only keeps the lower bits we care about/masks away what we don't care about)
    // 
    static int LOWER_MASK = (1 << 16) - 1; //The upper 16 bits are flagged as one and represents length
    static int UPPER_MASK = (LOWER_MASK << 16); //The lower 16 bits are flagged as zero and represents misses
    
    public static int getEditDistance(int x) {
        return(x & UPPER_MASK) >> 16;
    }
    
    public static int addEditDistance(int x, int addDist) {
        return x + (addDist << 16);        
    }
    
    public static int addStrLength(int x, int strLength) {
        return x + strLength;
    }

    public static int subEditDistance(int x, int addDist) {
        return x - (addDist << 16);        
    }

    public static int subStrLength(int x, int strLength) {
        return x - strLength;
    }
    
    public static int getStrLength(int x) {
        return x & LOWER_MASK;
    }

    public static int extend(int x, int k, int n) {
        int delta = k - getEditDistance(x);
        if (getStrLength(x) + delta <= n) {
            delta = n - getStrLength(x);
        }
        return addEditDistance(addStrLength(x, delta), delta);
    }

    public static int shrink(int x, int k) {
        int delta = getEditDistance(x) - k;
        return addEditDistance(addStrLength(x, -delta), -delta);
    }
    
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(reader.readLine().trim());
        //bit masking = think of int not as integer but as two shorts
        //it is now a memory layout (upper 16 bits misses, lower 16 bits our length)
        int[][] mat = new int[1501][1501];
        for (int te = 1; te <= t; te++){
            String[] split = reader.readLine().trim().split(" ");
            int k = Integer.parseInt(split[0].trim());
            String s1 = split[1].trim(), s2 = split[2].trim();
            for (int i = 1; i <= s1.length(); i++) {
                for (int j = 1; j <= s2.length(); j++) {

                    int prev = mat[i - 1][j - 1];
                    int len = getStrLength(prev);

                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) { //our characters match at (i, j)
                        if (getEditDistance(prev) <= k) {
                            mat[i][j] = addStrLength(prev, 1);
                            continue;
                        }           
                        int off = 1;
                        while(s1.charAt((i - 1) - len + off) != s2.charAt((j - 1) - len + off)) {
                            off++;
                        }
                        mat[i][j] = 0;
                        mat[i][j] = addStrLength(mat[i][j], len - off + 1);
                        mat[i][j] = addEditDistance(mat[i][j], off - 1);
                        continue;       
                    }

                    if (len > 0) {
                        mat[i][j] = addStrLength(
                            addEditDistance(prev, 1),
                            1
                        );
                        continue;
                    }

                    mat[i][j] = 0;
                }
                if (getEditDistance(mat[i][s2.length()]) < k) {
                    mat[i][s2.length()] = extend(mat[i][s2.length()], k, s2.length());
                } else if (getEditDistance(mat[i][s2.length()]) > k) {
                    mat[i][s2.length()] = shrink(mat[1][s2.length()], k);
                }
                if (getStrLength(mat[i - 1][s2.length()]) > getStrLength(mat[i][s2.length()])) {
                    mat[i][s2.length()] = mat[i-1][s2.length()];
                }
            }
            int n = s1.length();
            for (int j = 1; j <= s2.length(); j++) {
                if (getEditDistance(mat[n][j]) < k) {
                    mat[n][j] = extend(mat[n][j], k, j);
                } else if (getEditDistance(mat[n][j]) > k) {
                    mat[n][j] = shrink(mat[1][j], k);
                }
                if (getStrLength(mat[n - 1][j - 1]) > getStrLength(mat[n][j])) {
                    mat[n][j] = mat[n][j - 1];
                }
            }
            System.out.println(getStrLength(mat[s1.length()][s2.length()]));
        }    
    }
}