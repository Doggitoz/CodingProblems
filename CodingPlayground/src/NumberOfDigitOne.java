import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOfDigitOne {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        System.out.println(NumberOfDigitOne.countDigitOne(n));
    }

    public static int countDigitOne(int n) {

        String num = Integer.toString(n);

        int sum = 0;

        for (int i = 0; i < num.length(); i++) {
            int digit = CharInt(num.charAt(i));
            if (digit == 0) continue;
            sum += digit * (Math.pow(10, num.length() - i - 1) - Math.pow(9, num.length() - i - 1));
        }

        return sum;
    }

    public static int CharInt(char c) {
        return c - '0';
    }
}