package model.validator;

import java.util.HashSet;
import java.util.Set;

public class RepetitionNumber extends Validator {
    private final int id;
    private final int secretCode;
    public RepetitionNumber(int id, int secret) {
        if(id != 20  && id != 21){
            throw new IllegalArgumentException("L'id fourni n'est pas l'id du " +
                    "validateur qui détermine si un chiffre du code se répète, et si oui, " +
                    "combien de fois ou si un chiffre se repete exactement 2 fois !");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        int secretUnicity = unicity(""+userCode);
        int userUnicity = unicity(""+secretCode);
        if (id == 20) {
            super.result = secretUnicity == userUnicity;
        }
        else{
            super.result = (secretUnicity == 2) == (userUnicity == 2);
        }
        return super.result;
        //  si le secret a 1 doublon et que usercode aussi -> true
        // si le secret n'a pas exactement 1  doublon et que usercode aussi -> true
        // si secret a 1 doublon et usercode    NON -> false | inverse : false
    }

   private int unicity(String code){
        Set<Integer> uniqueFigures = new HashSet<>();
        for (int i = 0; i < code.length(); i++) {
            uniqueFigures.add(Character.getNumericValue(code.charAt(i) ));
        }
        return uniqueFigures.size();
       //  setsize = 3 -> pas doublons | setsize = 2 ->  doublons | setsize = 1 : triple
       // -> return taille du Set
    }
}
