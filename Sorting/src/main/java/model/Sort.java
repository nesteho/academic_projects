package model;
public interface Sort {
     void sort(int[] tab);
     SortType getSortType();
     int getSize();
     long getOperations();
     double getDuration();
}
