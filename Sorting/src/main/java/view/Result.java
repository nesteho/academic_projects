package view;

public class Result {
    private String sortType;
    private int size;
    private long operations;
    private double duration;

    public Result(String sortType, int size, long operations, double duration) {
        this.sortType = sortType;
        this.size = size;
        this.operations = operations;
        this.duration = duration;
    }

    public String getSortType() {
        return sortType;
    }

    public int getSize() {
        return size;
    }

    public long getOperations() {
        return operations;
    }

    public double getDuration() {
        return duration;
    }
}
