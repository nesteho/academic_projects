package model;
/**
 *  Representation of a calorie calculator that calculates calorie needs
 *  based on Basal Metabolic Rate (BMR) and activity level.
 */
public class Calories {
    double bmr;
    private String activité;
    /**
     * Constructs a new Calories object with the given BMR and activity level.
     * @param bmr  The Basal Metabolic Rate (BMR) value.
     * @param activité The activity level of the individual.
     */
    public Calories(double bmr,String activité) {
        this.bmr = bmr;
        this.activité =activité;
    }
    /**
     * Calculates the calorie needs based on the BMR and activity level.
     * @return The calculated calorie needs based on the BMR and the activity level.
     */
    public double caloriesNeeds(){
        switch (this.activité){
            case "Sédentaire" : return bmr*NivAct.S.getFactor();
            case "Peu actif" : return bmr*NivAct.PA.getFactor();
            case "Actif" : return bmr*NivAct.A.getFactor();
            case "Fort actif" : return bmr*NivAct.FA.getFactor();
            case "Extrêmement actif" : return bmr*NivAct.EA.getFactor();
        }
        return 0;
    }
}
