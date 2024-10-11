package model.validator;

public class EvenFigures extends Validator {
    private int id;
    private final int secretCode;
    public EvenFigures(int id, int secret) {

        if(id != 17){
            throw new IllegalArgumentException("l'id fourni n'est pas l'id d'un validateur " +
                        "qui compte le nombre de chiffres pairs");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int usercode) {
        super.isTested =true;

        int userEvenNb = evenFiguresNumber(""+usercode);
        int secretEvenNb = evenFiguresNumber(""+secretCode);
        super.result = userEvenNb == secretEvenNb;
        return super.result;
    }
    private int evenFiguresNumber(String code){
        int cpt = 0;
        for (int i = 0; i < code.length(); i++) {
            int codeValue =  Character.getNumericValue(code.charAt(i));
            if(codeValue % 2 == 0){
                cpt++;
            }
        }
        return cpt;
    }
}
