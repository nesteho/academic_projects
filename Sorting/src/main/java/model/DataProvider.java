package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class DataProvider {
    //file et pas liste : FIFO
    LinkedList toSort;
    private int maxSize;
    public synchronized int[] getNext() {

        if(!toSort.isEmpty()){
            return (int[]) toSort.removeFirst();
        }
        return null;
    }
    public DataProvider(int maxSize) {
        this.toSort = new LinkedList<>();
        this.maxSize = maxSize;
        makeFile();
    }
    void makeFile(){
        int step = maxSize/10;
        for (int i = step; i <= maxSize; i+=step) {
           var tab =  generateTab(i);
            toSort.addLast(tab);
        }
    }
    int[] generateTab(int size){
        var randoms = new int[size];

        for (int i = 0; i <  size; i++) {
            int random = ThreadLocalRandom.current().nextInt();
            randoms[i] = random;
        }
        return randoms;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DataProvider(100).getNext()));
    }
}
