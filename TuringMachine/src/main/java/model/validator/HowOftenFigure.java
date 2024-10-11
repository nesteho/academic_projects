package model.validator;

public class HowOftenFigure extends Validator {
    private int id;
    private  int comparedValue;
    private final int secretCode;
    public HowOftenFigure(int id, int secret) {

        this.id = id;
        this.secretCode = secret;
        this.comparedValue = switch (id) {
            case 8 -> 1;
            case 9 -> 3;
            case 10 -> 4;
            default -> throw new IllegalArgumentException("L'id fourni n'est pas " +
                    "l'id d'un validateur qui compte une valeur de chiffre");
        };
    }
    public boolean test(int userCode) {
        super.isTested =true;

        int userCodeFreq = frequenceFigure(""+secretCode) ;
        int secretCodeFreq = frequenceFigure(""+userCode);
        super.result = userCodeFreq == secretCodeFreq;
        return super.result;
    }
    private int frequenceFigure (String code){
        int cpt = 0;
        for (int i = 0; i < code.length()  ; i++) {
            if ( Character.getNumericValue(code.charAt(i)) == comparedValue){
                cpt++;
            }
        }
        return cpt;
    }
}
