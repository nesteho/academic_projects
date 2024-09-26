package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class QuickSort extends AbstractSort{

    public QuickSort(SortType sortType, int size) {
        super(sortType, size);
    }
    @Override
    public void sort(int[] tab) {
        begin = LocalDateTime.now();
        quickSort(tab, 0, tab.length - 1);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end).toMillis();
    }
    private void quickSort(int[] tab, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(tab, low, high);
            quickSort(tab, low, partitionIndex - 1);
            quickSort(tab, partitionIndex + 1, high);
        }
    }
    private int partition(int[] tab, int low, int high) {

        int pivot = tab[high];
        int i = low - 1;
        operations += 3;

        for (int j = low; j < high; j++) {
            operations += 2; // comparaison for et if
            if (tab[j] < pivot) {
                i++;
                int temp = tab[i];
                tab[i] = tab[j];
                tab[j] = temp;
                operations += 4;
            }
            operations++; // j++
        }

        int temp = tab[i + 1];
        tab[i + 1] = tab[high];
        tab[high] = temp;
        operations += 3;

        return i + 1;
    }
}

