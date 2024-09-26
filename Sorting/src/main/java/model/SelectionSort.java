package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class SelectionSort extends AbstractSort {

    public SelectionSort(SortType sortType, int size) {
        super(sortType, size);
    }
    @Override
    public void sort(int[] tab) {
        begin = LocalDateTime.now();
        selectionSort(tab);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end).toMillis();
    }
    private void selectionSort(int[] tab) {
        int n = tab.length;
        operations++; // assignation int i = 1
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            operations += 3 ;// assignation minIndex,j et comparaison for;

            for (int j = i + 1; j < n; j++) {
                operations++;
                if (tab[j] < tab[minIndex]) {
                    minIndex = j;
                    operations++;
                }
                operations+=2; // j++ et if
            }
            int temp = tab[minIndex];
            tab[minIndex] = tab[i];
            tab[i] = temp;
            operations += 4; // 3 assignation + i++
        }
    }

}
