package model.validator;

public class SumParity extends Validator {
    private int id;
    private final int secretCode;
    public SumParity(int id, int secret) {
        if(id != 18){
            throw new IllegalArgumentException("l'id fourni n'est pas l'id d'un validateur " +
                    "qui compte le nombre de chiffres pairs");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int usercode) {
        super.isTested =true;

        int secretSum = sumFigures(""+secretCode);
        int userSum =sumFigures(""+usercode);
        if (secretSum % 2 == 0){
             super.result = userSum % 2 == 0;
        }
        else {
            super.result = userSum % 2 != 0;
        }
        return super.result;
    }
    private int sumFigures(String code){
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            int codeValue =  Character.getNumericValue(code.charAt(i));
            sum+= codeValue;
        }
        return sum;
    }
}
