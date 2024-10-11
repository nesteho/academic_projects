package model.validator;

public class MostPresentParity extends Validator {
    private int id;
    private final int secretCode;
    public MostPresentParity(int id, int secret) {

        if(id != 16){
            throw new IllegalArgumentException("L'id fourni n'est pas l'id du " +
                    "validateur qui vérifie la parité la plus présente d'un code");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        int secretCodeParity = parityCpt(""+userCode);
        int userCodeParity = parityCpt(""+secretCode);
        super.result = secretCodeParity == userCodeParity;
        return super.result;
    }
    int parityCpt(String code){
        int pairCpt = 0;
        int impairCpt = 0;
        for (int i = 0; i < code.length(); i++) {
            int codeValue =  Character.getNumericValue(code.charAt(i));
            if(codeValue % 2 == 0){
               pairCpt++;
            }
            else {
                impairCpt++;
            }
        }
        if(pairCpt >impairCpt ){
            return 0;
        }
        return 1;
    }
}
