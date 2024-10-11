package model.validator;

public class FiguresOrder extends Validator {
    private int id;
    private final int secretCode;
    public FiguresOrder(int id, int secret) {
        if(id != 22){
            throw new IllegalArgumentException("L'id fourni n'est pas l'id du " +
                    "validateur qui détermine si les trois chiffres du code sont\n" +
                    "en ordre croissant ou décroissant");
        }
        this.id = id;
        this.secretCode = secret;
    }
    public boolean test(int userCode) {
        super.isTested =true;

        var userOrders = order(""+userCode);
        var secretOrders = order(""+secretCode);

        int cpt =  0 ;
        while ( cpt < userOrders.length && userOrders[cpt] == secretOrders[cpt]){
            cpt++;
        }
        super.result = !( cpt < userOrders.length) ;
        return super.result;
    }
    boolean[] order (String code){
        boolean ascCpt = true;
        boolean descCpt = true;

        for (int i = 1; i < code.length(); i++) {
            int currentFigure =  Character.getNumericValue(code.charAt(i));
            int previousFigure =  Character.getNumericValue(code.charAt(i-1));

            ascCpt = ascCpt && currentFigure > previousFigure;
            descCpt = descCpt && currentFigure < previousFigure;
        }
        boolean[] orders = { !ascCpt && !descCpt , ascCpt, descCpt };
        return orders;
    }
}
