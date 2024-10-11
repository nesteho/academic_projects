package model.validator;

public abstract class   Validator {
    boolean isTested;  // 1 par objet et donc : un classe peut gerer plusieur validateur (1 classe =
    boolean  result ;
    abstract public boolean test( int userCode);
    public boolean isTested(){
        return  isTested;
    }
    public boolean ValidatorResult(){
        return  result;
    }
    public void setTested(){
        isTested = false;
    }
    public void setResult(){
        result = false;
    }
}
