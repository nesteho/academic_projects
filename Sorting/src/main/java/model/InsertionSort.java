package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class InsertionSort extends AbstractSort{
    public InsertionSort(SortType sortType, int size) {
        super(sortType, size);
    }
    @Override
    public void sort(int[] tab) {
        begin = LocalDateTime.now();
        insertionSort(tab);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end).toMillis();
    }
    private void insertionSort(int[] tab) {
        int n = tab.length;
        operations++; // assignation int i = 1
        for (int i = 1; i < n; i++) {
            int key = tab[i];
            int j = i - 1;
            operations += 4; // while et 2 assignation au dessus
            while (j >= 0 && tab[j] > key) {
                tab[j + 1] = tab[j];
                j = j - 1;
                operations += 2;
            }
            tab[j + 1] = key;
            operations += 2; // i++
        }
    }
}
