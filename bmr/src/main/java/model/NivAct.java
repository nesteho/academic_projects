package model;

public enum NivAct {
    S(1.2), PA( 1.375), A( 1.55), FA( 1.725), EA( 1.9);
    private double factor;
     NivAct(double factor) {
        this.factor = factor;
    }
    public double getFactor() {
        return factor;
    }
}
