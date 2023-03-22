package GoogleFoobar;
/* 
 * Commander Lambda has asked for your help to refine the automatic quantum antimatter fuel injection system for the LAMBCHOP doomsday device. 
 * It's a great chance for you to get a closer look at the LAMBCHOP -- and maybe sneak in a bit of sabotage while you're at it -- so you took the job gladly. 
 * 
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. 
 * However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time. 
 * 
 * The fuel control mechanisms have three operations: 
 * 
 * 1) Add one fuel pellet
 * 2) Remove one fuel pellet
 * 3) Divide the entire group of fuel pellets by 2 (due to the destructive energy released when a quantum antimatter pellet is cut in half, the 
 *      safety controls will only allow this to happen if there is an even number of pellets)
 * 
 * Write a function called solution(n) which takes a positive integer as a string and returns the minimum number of operations needed to transform the number of 
 * pellets to 1. The fuel intake control panel can only display a number up to 309 digits long, so there won't ever be more pellets than you can express in that many digits.
 * 
 * For example:
 * solution(4) returns 2: 4 -> 2 -> 1
 * solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 * Quantum antimatter fuel comes in small pellets, which is convenient since the many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a time. 
 * However, minions dump pellets in bulk into the fuel intake. You need to figure out the most efficient way to sort and shift the pellets down to a single pellet at a time. 
 * 
 * Completed in: 5 hrs, 34 mins, 59 secs
 */

import java.math.BigInteger;

public class FuelInjectionPerfection {
    public static int solution(String x) {
        // Your code here

        // Convert string to binary representation
        BigInteger big = new BigInteger(x);
        int[] bitRepresentation = new int[big.bitLength()];

        for (int i = 0; i < bitRepresentation.length; i++) {
            bitRepresentation[bitRepresentation.length - 1 - i] = big.mod(BigInteger.TWO).intValue();
            big = big.divide(BigInteger.TWO);
        }

        BinaryValue bv = new BinaryValue(bitRepresentation);
        int counter = 0;
        while (bv.msb != 0) {
            int[] lsbs = bv.GetLeastTwoSignificantBits();
            if (lsbs[0] == 0) {
                bv.Shift();
            } else {
                if (bv.msb < 2) {
                    bv.SubOne();
                } else if (lsbs[1] == 1) {
                    bv.AddOne();
                } else {
                    bv.SubOne();
                }
            }
            counter++;
        }

        return counter;
    }

    static class BinaryValue {
        int[] bits = new int[1028];
        public int msb;

        public BinaryValue(int[] bits) {
            for (int i = 0; i < bits.length; i++) {
                this.bits[i] = bits[bits.length - 1 - i];
            }
            SetMSB();
        }

        public int[] GetLeastTwoSignificantBits() {
            int[] result = new int[2];
            result[0] = bits[0];
            result[1] = bits[1];
            return result;
        }

        // Only applicable for when carryover not needed :)
        public void SubOne() {
            bits[0] = 0;
        }

        public void AddOne() {
            // Logic to add one
            int i = 0;
            while (bits[i] == 1) {
                bits[i] = 0;
                i++;
            }
            bits[i] = 1;
            if (i > msb)
                msb = i;
        }

        public void Shift() {
            // Logic to shift bit
            for (int i = 0; i < msb; i++) {
                bits[i] = bits[i + 1];
            }
            bits[msb] = 0;
            msb--;

        }

        public void SetMSB() {
            for (int i = bits.length - 1; i >= 0; i--) {
                if (bits[i] == 1) {
                    msb = i;
                    return;
                }
            }
        }

        public void PrintBinaryValue() {
            SetMSB();
            for (int i = bits.length - 1; i >= 0; i--) {
                if (i <= msb) {
                    System.out.print(bits[i]);
                }
            }
            System.out.printf(", Size: %d\n", msb);
        }
    }

    public static void main(String[] args) {
        // Test case one:
        String caseOne = "4";
        System.out.println(solution(caseOne));
        // Expected outcome: 2

        // Test case two:
        String caseTwo = "15";
        System.out.println(solution(caseTwo));
        // Expected outcome: 5

        // Test case three:
        String caseThree = "999999999999999999999999999999";
        System.out.println(solution(caseThree));
        // Expected outcome: iunno

        // Test case four:
        String caseFour = "";
        for (int i = 0; i < 309; i++) {
            caseFour += "9";
        }
        System.out.println(solution(caseFour));
        // Expected outcome: idk
        // To represent max number

        // Test case five:
        String caseFive = "64";
        System.out.println(solution(caseFive));
        // Expected outcome: 6
        // to test proper conversion

        // Test case six:
        String caseSix = "63";
        System.out.println(solution(caseSix));
        // Expected outcome: 7
        // to test bit flow up

        // Test case seven:
        String caseSeven = "65";
        System.out.println(solution(caseSeven));
        // Expected outcome: 7
        // to test bit flow down

        // Test case eight:
        String caseEight = "5021";
        System.out.println(solution(caseEight));
        // Expected outcome: 17
        // random problem i worked out

        // Test case nine:
        String caseNine = "127";
        System.out.println(solution(caseNine));
    }
}
