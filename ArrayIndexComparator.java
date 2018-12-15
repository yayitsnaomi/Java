package project2;

import java.util.Comparator;
import java.util.List;

public class ArrayIndexComparator implements Comparator<Integer> {
    private List<Comparable> key;


    public ArrayIndexComparator(List<Comparable> key) {
        this.key = key;
    }


    public Integer[] createIndexArray() {
    	// create an array of index [0,1,2,...]
        Integer[] indexes = new Integer[key.size()];
        for (int i = 0; i < key.size(); i++) {
            indexes[i] = i; 
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2) {
    // sort the index based on key = the column selected in ascending order
            return key.get(index1).compareTo(key.get(index2));
    }

}
