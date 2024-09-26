package model;

import java.time.LocalDateTime;

public abstract class AbstractSort implements Sort{
    //pas repeter ces info dans tt les classe enfant (optimisation)
    protected SortType sortType;
    protected int size;
    protected long operations;
    protected double duration;
    protected LocalDateTime begin;
    protected LocalDateTime end;


    public AbstractSort(SortType sortType, int size) {
        this.sortType = sortType;
        this.size = size;
    }
    @Override
    public SortType getSortType() {
        return sortType;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public long getOperations() {
        return operations;
    }
    @Override
    public double getDuration() {
        return duration;
    }
}
