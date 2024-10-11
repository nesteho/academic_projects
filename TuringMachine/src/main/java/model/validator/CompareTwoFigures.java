package model.validator;

public class CompareTwoFigures extends Validator {
    private int id;
    private  int compare1;
    private  int compare2;
    private final int secretCode;
    public CompareTwoFigures(int id, int secret) {
        switch (id) {
            case 11 -> {
                this.compare1 = 0;
                this.compare2 = 1;
            }
            case 12 -> {
                this.compare1 = 0;
                this.compare2 = 2;
            }
            case 13 -> {
                this.compare1 = 1;
                this.compare2 = 2;
            }
            default -> throw new IllegalArgumentException("L'id fourni n'est pas l'id d'un " +
                    "validateur qui compare un chiffre à un nombre !");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        String sC = ""+secretCode;
        String uC = ""+userCode;

        int secretCompare1 = Character.getNumericValue( sC.charAt(compare1) );
        int secretCompare2 = Character.getNumericValue( (sC).charAt(compare2));
        int userCompare1 =Character.getNumericValue( uC.charAt(compare1) );
        int userCompare2 = Character.getNumericValue( uC.charAt(compare2) );

        if( secretCompare1 < secretCompare2){
            super.result =  userCompare1 < userCompare2;
        }
        else if( secretCompare1 == secretCompare2){
            super.result =  userCompare1 == userCompare2;
        }
        else {
            super.result = userCompare1 > userCompare2;
        }
        return  super.result;
    }
    public int getId() {
        return id;
    }
}
