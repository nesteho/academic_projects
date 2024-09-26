package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class BubbleSort extends AbstractSort{
    //ERROR QUI FAISAIT :  nb operation faites et affiché sont différent  attribut prive dans cet classe et pas attribut du parents!

    public BubbleSort(SortType sortType, int size) {
        super(sortType, size);
    }
    @Override
    public void sort(int[] tab) {
        begin = LocalDateTime.now();
        bubbleSort(tab);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end).toMillis();
    }

    private void bubbleSort(int[] arr) {
        int i = 0, n = arr.length;
        boolean swapNeeded = true;
        operations++;

        while (i < n - 1 && swapNeeded) {

            swapNeeded = false;
            operations+=2; // affectation swap et comparaison du while

            for (int j = 1; j < n - i; j++) {
                operations++;
                if (arr[j - 1] > arr[j]) {
                    operations++;

                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    operations+=3;
                    swapNeeded = true;
                }
                operations++;
            }
            if(!swapNeeded) {
                break;
            }
            i++;
        }
        operations++;
    }

}
