package DataStructures;

public class DynamicArray {
        
    int[] arr;
    int idx;

    public DynamicArray(int size) {
        arr = new int[size];
        idx = 0;
    }

    public void append(int x) {
        if (idx == arr.length) {
            this.grow();
        }
        this.arr[idx] = x;
        idx++;
    }

    public void add(int x) {
        if (idx == arr.length) {
            this.grow();
        }
        this.arr[idx] = x;
        idx++;
    }

    public void grow() {
        int[] temp = this.arr;
        this.arr = new int[temp.length * 2];
        System.arraycopy(temp, 0, this.arr, 0, temp.length);
    }

    public void removeAt(int index) {
        if (index > idx) throw new RuntimeException("Index " + index + " is out of bounds for DynamicArray of length " + idx);
        int[] temp = this.arr;
        System.arraycopy(temp, 0, this.arr, 0, index);
        System.arraycopy(temp, index + 1, this.arr, index, temp.length - index - 1);
        idx--;
    }

    public void addAt(int index, int data) {
        if (index > idx + 1) throw new RuntimeException("Index " + index + " is out of bounds for DynamicArray of length " + idx);
        if (idx == arr.length) { grow(); idx++; }
        int[] temp = this.arr;
        System.arraycopy(temp, 0, this.arr, 0, index);
        System.arraycopy(temp, index, this.arr, index + 1, temp.length - index - 1);
        this.arr[index] = data;
        idx++;
    }

    public int indexOf(int value) {
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == value) return i;
        }
        return -1;
    }

    public boolean contains(int value) {
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == value) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return idx < 0;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < idx - 1; i++) {
            System.out.printf("%d, ", this.arr[i]);
        }
        System.out.printf("%d]\n", this.arr[idx - 1]);
    }
}
