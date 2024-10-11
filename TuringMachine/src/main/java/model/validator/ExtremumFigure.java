package model.validator;

public class ExtremumFigure extends Validator {
    public enum Extremum {
        MINIMUM, MAXIMUM;
    };
    private int id;
    private final Extremum EXTREMUM;
    private final int secretCode;
    public ExtremumFigure(int id, int secret) {
        switch (id) {
            case 14 -> this.EXTREMUM = Extremum.MINIMUM;
            case 15 -> this.EXTREMUM = Extremum.MAXIMUM;
            default -> throw new IllegalArgumentException("L'id fourni n'est pas l'id d'un " +
                    "validateur qui compte une valeur de chiffre");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        String sC = ""+secretCode;
        String uC = ""+userCode;
        if( EXTREMUM == Extremum.MINIMUM){
            super.result = findMinPos(sC) == findMinPos(uC);
        }
        else {
            super.result = findMaxPos(sC) == findMaxPos(uC);
        }
        return super.result;
    }
    private static int findMinPos(String code){
        if (code == null) {
            throw new IllegalArgumentException("La chaîne ne peut pas être vide!");
        }
        int minCpt = 0;
        int minValue = charToInt(code.charAt(minCpt) );

        for (int i = 1; i < code.length() ; i++) {
            int value = charToInt(code.charAt(i));
            if ( value == minValue){
                 return -1;
            }
            if ( value < minValue){
              minValue = value;
              minCpt = i;
            }
        }
        return minCpt;
    }
    private static int findMaxPos (String code){
        if (code == null) {
            throw new IllegalArgumentException("La chaîne ne peut pas être vide!");
        }
        int maxCpt = 0;
        int maxValue = charToInt(code.charAt(maxCpt) );

        for (int i = 1; i < code.length() ; i++) {
            int value = charToInt(code.charAt(i));
            if ( value == maxValue){
                return -1;
            }
            if ( value > maxValue){
                maxValue = value;
                maxCpt = i;
            }
        }
        return maxCpt;
    }
    private static int charToInt(char value){
        return Character.getNumericValue(value);
    }
}
