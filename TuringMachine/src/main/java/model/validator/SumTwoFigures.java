package model.validator;

public class SumTwoFigures extends Validator {
    private int id;
    private final   int figure1;
    private final int figure2;
    private final int comparedValue;
    private final int secretCode;
    public SumTwoFigures(int id, int secret) {
        if(id != 19){
            throw new IllegalArgumentException("l'id fourni n'est pas l'id d'un validateur " +
                    "qui compare la somme de deux chiffres à une valeur");
        }
        this.id = id;
        comparedValue = 6;
        figure1 = 0;
        figure2 = 1;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        int secretSum = sumTwoFigures(""+secretCode);
        int userSum = sumTwoFigures(""+userCode);

        if(secretSum < comparedValue){
            super.result =  userSum < comparedValue;
        }
        else if(secretSum == comparedValue){
            super.result =  userSum == comparedValue;
        }
        else {
            super.result =  userSum > comparedValue;
        }
        return super.result;
    }
    private int sumTwoFigures(String code){
        return Character.getNumericValue(code.charAt(figure1)
                + Character.getNumericValue(code.charAt(figure2)));
    }
}
