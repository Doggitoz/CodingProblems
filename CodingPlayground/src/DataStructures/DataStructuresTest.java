package DataStructures;

public class DataStructuresTest {
    public static void main(String[] args) {
        DynamicArray da = new DynamicArray(10);
        for (int i = 0; i < 10; i++) {
            da.append(i);
        }
        da.print();
        da.removeAt(da.indexOf(7));
        da.print();
        da.addAt(2, 7);
        da.print();
    }
}
