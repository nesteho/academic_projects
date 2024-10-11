package model;
/**
 * Factors used for calculating Basal Metabolic Rate (BMR)
 * for the male sex.
 */
public enum BmrFactors_M {
    heightFactor(5), weightFactor(13.7), ageFactor(6.8), generalFactor(66) ;
    private double factor;

    /**
     * Creates a new instance of the BmrFactors_M enumeration with the given factor.
     * @param factor The factor associated with this enumeration element.
     */
    BmrFactors_M(double factor) {
        this.factor = factor;
    }
    /**
     * Getter of the factor associated with this enumeration element.
     * @return The factor associated with this element.
     */
    public double getFactor() {
        return factor;
    }
}
