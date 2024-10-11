package model;
/**
 * Factors used for calculating Basal Metabolic Rate (BMR)
 * for the female sex.
 */
public enum BmrFactors_F {

    heightFactor(1.8),weightFactor(9.6), ageFactor(4.7),generalFactor(655) ;
    private double factor;
    /**
     * Creates a new instance of the BmrFactors_F enumeration with the given factor.
     * @param factor The factor associated with this enumeration element.
     */
    BmrFactors_F(double factor) {
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
