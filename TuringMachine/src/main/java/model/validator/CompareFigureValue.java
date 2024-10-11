package model.validator;

public class CompareFigureValue extends Validator {
        private int id;
        private  int comparedFigure;
        private  int comparedValue;
        private final int secretCode;
        // instancier les validateur via id : instancer comparateur en lui donner valeurs pour chiffre
        // un validateur instancié en début de partie . Validateurs 1-4 n'ont pas le mm code secret
        // -> faut passer code secret en param qd on construit le validateur

        public CompareFigureValue(int id, int secret) {
            switch (id) {
                case 1, 2 -> {
                    this.comparedFigure = 0;
                    this.comparedValue = (id == 1) ? 1 : 3;
                }
                case 3, 4 -> {
                    this.comparedFigure = 1;
                    this.comparedValue = (id == 3) ? 3 : 4;
                }
                default -> throw new IllegalArgumentException("L'id fourni n'est pas l'id d'un validateur " +
                        "qui compare un chiffre à un nombre !");
            }
            this.id = id;
            this.secretCode = secret;
        }
        public boolean test(int userCode) {
            super.isTested =true;

            int codeFirstF = Character.getNumericValue((""+secretCode).charAt(comparedFigure));
            int userFirstF = Character.getNumericValue((""+userCode).charAt(comparedFigure));
            if(codeFirstF < comparedValue){
                super.result = userFirstF < comparedValue;
            }
            else if(codeFirstF == comparedValue){
                super.result = userFirstF == comparedValue;
            }
            else {
                super.result = userFirstF > comparedValue;
            }
            return super.result;
        }
        public int getId() {
            return id;
        }
}
