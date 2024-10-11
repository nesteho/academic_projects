package model;

import model.dpoo.Observable;
import model.dpoo.Observer;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a model for calculating Basal Metabolic Rate (BMR).
 */
public class BmrModel implements Observable {
    Set<Observer> observers;
    private String sex;
    private double height;
    private double weight;
    private int age;
    private double bmrValue;
    /**
     * Constructs a`BmrModel
     */
    public BmrModel() {
        bmrValue = 0;
        observers = new HashSet<>();
    }
    /**
     * Calculates the BMR based on provided data and notifies observers.
     * @param sex user's sex
     * @param height  user's height
     * @param weight user's weight
     * @param age user's age
     * @param activity_level user's activity level
     */
    public void calculateBmr( String sex, double height, double weight, int age, String activity_level){
        if (sex == null){
            throw new NullPointerException("Aucun sexe n'a été fourni");
        }
        if (!sex.equals("Femme") && !sex.equals("Homme") ){
            System.out.println(sex);
            throw new NullPointerException("Donnée pour le sexe invalide !");
        }
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.age = age;

        if(sex.equals("Femme")){
            bmrValue = calculateBmrF();
        }
        else {
            bmrValue = calculateBmrM();
        }
        notifyObservers(activity_level);
    }
    private double calculateBmrF(){
        return ( ( BmrFactors_F.weightFactor.getFactor()*weight)+(BmrFactors_F.heightFactor.getFactor()*height)
                -(BmrFactors_F.ageFactor.getFactor()*age)+BmrFactors_F.generalFactor.getFactor() );
    }
    private double calculateBmrM(){
        return ( ( BmrFactors_M.weightFactor.getFactor()*weight)+(BmrFactors_M.heightFactor.getFactor()*height)
                -(BmrFactors_M.ageFactor.getFactor()*age)+BmrFactors_M.generalFactor.getFactor() );
    }
    /**
     * Registers a new observer
     * @param obs The observer to register
     */
    @Override
    public void registerObserver(Observer obs) {observers.add(obs);}
    /**
     * Removes a new observer
     * @param obs The observer to remove
     */
    @Override
    public void removeObserver(Observer obs) { observers.remove(obs); }

    /**
     * Gives to all the observers the updated values
     * @param activity_level the user's activity_level
     */
    @Override
    public void notifyObservers(String activity_level) {
       var c =  new Calories(bmrValue, activity_level );
       observers.forEach(o -> o.update(bmrValue, c.caloriesNeeds() ));
       // ->     appeler les observeurs + dire j'ai change voici les nvl val
    }
}
