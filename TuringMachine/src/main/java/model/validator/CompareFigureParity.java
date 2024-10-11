package model.validator;

public class CompareFigureParity extends Validator {
    private int id;
    private  int comparedFigure;
    private final int secretCode;
    public CompareFigureParity(int id, int secret) { // indice commence à 0
        switch (id) {
            case 5 -> this.comparedFigure = 0;
            case 6 -> this.comparedFigure = 1;
            case 7 -> this.comparedFigure = 2;
            default -> throw new IllegalArgumentException("L'id fourni n'est pas l'id d'un validateur " +
                    "qui vérifie la parité d’un chiffre !");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested = true;
        int codeFirstF = Character.getNumericValue((""+secretCode).charAt(comparedFigure));
        int userFirstF = Character.getNumericValue((""+userCode).charAt(comparedFigure));

        if(codeFirstF % 2 == 0){
            super.result = userFirstF % 2 == 0 ;
        }
        else {
            super.result = userFirstF % 2 != 0;
        }
        return super.result;
    }
}
