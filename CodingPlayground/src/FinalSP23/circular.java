package FinalSP23;

import java.io.*;
import java.util.*;

public class circular {
    
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int numGenes = Integer.parseInt(reader.readLine().trim());

        HashMap<String, Integer> table = new HashMap<>();
        HashSet<Integer> contains = new HashSet<>();
        int[] indexes = new int[numGenes];

        String[] genes = reader.readLine().trim().split(" ");
        for (String gene : genes) {
            if (!contains.contains(Integer.parseInt(gene.substring(1)))) {
                contains.add(Integer.parseInt(gene.substring(1)));
            }
            if (table.containsKey(gene)) {
                table.put(gene, table.get(gene) + 1);
            }
            else {
                table.put(gene, 1);
            }
        }

        for (Integer in : contains) {
            String e = "e" + String.valueOf(in);
            String s = "s" + String.valueOf(in);
            if (!table.containsKey(e) || !table.containsKey(s)) continue;
            if (table.get(e) != table.get(s)) continue;

            boolean solved = false;
            int index = 0;
            int counter = 0;
            for (int i = 0; i < numGenes; i++) {
                if (!solved) {
                    index = i;
                    while (index < numGenes) {
                        if (genes[index].equals(s)) counter++;
                        if (genes[index].equals(e)) counter--;
                        index++;
                        if (counter < 0) {
                            i = index;
                            counter = 0;
                        }
                    }
                    indexes[i]++;
                    solved = true;
                }
                else {
                    
                }
            }
            // while (index < numGenes) {
            //     if (genes[index].equals(s)) counter++;
            //     if (genes[index].equals(e)) counter--;
            //     index++;
            //     if (counter < 0) {
            //         start = index;
            //         counter = 0;
            //     }
            // }
            // indexes[start]++;
            // if (start == 0) {
            //     for (int i = numGenes - 1; i > 0; i--) {
            //         if (genes[i].equals(e)) break;
            //         indexes[i]++;
            //     }
            // }
            // for (int i = start + 1; i < numGenes; i++) {
            //     if (genes[i - 1].equals(s)) break;
            //     indexes[i]++;
            // }
        }

        int max = 0;
        int index = -1;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] > max) {
                max = indexes[i];
                index = i;
            }
        }

        System.out.printf("%d %d", index + 1, max);
    }

    /**
     * Use dynamic programming to find how many different genes form a proper nesting in the sequence
     * @param genes
     * @return
     */
    //static int findNumGenesProperlyNested(String[] genes) {
    //     int numNested = 0;

    //     for (int gene : allGenes) {
    //         stack = new Stack<>();
    //         for (String geneID : genes) {
    //             int geneNum = Integer.parseInt(geneID.substring(1, geneID.length()));
    //             String role = geneID.substring(0, 1);
    //             if (geneNum == gene){
    //                 if (role.equals("s")){
    //                     stack.push(role);
    //                 } else if (stack.empty()){
    //                     // then we have an end - and this will not work because the stack is empty
    //                     stack.push(role);
    //                     break;
    //                 } else {
    //                     stack.pop();
    //                 }
    //             }
    //         }
    //         if (stack.empty()){
    //             numNested++;
    //         }
    //     }

    //     return numNested;
    // }

}
