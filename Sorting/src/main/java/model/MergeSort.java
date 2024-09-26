package model;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;

public class MergeSort extends AbstractSort{
    public MergeSort(SortType sortType, int size) {
        super(sortType, size);
        super.operations = 0;
    }
    @Override
    public void sort(int[] tab) {
        begin = LocalDateTime.now();
        mergeSort(tab, tab.length);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end).toMillis();
        System.out.println(duration);
    }

    private void mergeSort(int[] a, int n) {

        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            operations+=2;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            operations+=2;
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }
    private void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            operations+=2;
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
                operations+=2;
            }
            else {
                a[k++] = r[j++];
                operations++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            operations+=2;
        }
        while (j < right) {
            a[k++] = r[j++];
            operations+=2;
        }
    }
}
