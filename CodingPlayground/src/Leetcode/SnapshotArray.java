package Leetcode;
import java.util.*;

@SuppressWarnings("unchecked")
class SnapshotArray {

    static HashMap<Integer, Integer>[] saves = new HashMap[50000];
    static HashMap<Integer, Integer> snap = new HashMap<>();
    static int snap_id = 0;

    public SnapshotArray(int length) {
        snap_id = 0;
        snap = new HashMap<>();
        saves = new HashMap[50000];
    }
    
    public void set(int index, int val) {
        snap.put(index, val);
    }
    
    public int snap() {
        if (!snap.isEmpty())
            saves[snap_id] = snap;
        snap = new HashMap<>();
        int val = snap_id;
        snap_id++;
        return val;
    }
    
    public int get(int index, int snap_id) {
        for (int i = snap_id; i >= 0; i--) {
            if (saves[i] == null) continue;
            if (saves[i].containsKey(index)) {
                return saves[i].get(index);
            }
        }
        return 0;
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */