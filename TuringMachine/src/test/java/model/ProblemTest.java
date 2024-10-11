package model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProblemTest {
    Problem problem;
    public ProblemTest() throws TuringGameException {
        this.problem = new Problem(9,1,3,123, new int[]{1, 7, 10, 12, 17});
    }
    @Test
    public void getValidatorNum() throws TuringGameException {
        assertEquals(1,problem.getValidatorNum(0));
        assertEquals(7, problem.getValidatorNum(1));
        assertEquals(10, problem.getValidatorNum(2));
        assertEquals(12, problem.getValidatorNum(3));
    }
    @Test
    public void nbValidators() {
        assertEquals(5, problem.nbValidators());
    }
    @Test
    public void getCode() {
        assertEquals(123, problem.getCode());
    }
    @Test
    public void checkValIndexLessThan0() {
        assertThrows(TuringGameException.class, () -> problem.getValidatorNum(-1));
    }
    @Test
    public void checkValIndexMoreThanNbVAlidators() {
        assertThrows(TuringGameException.class, () -> problem.getValidatorNum(6));
    }
    @Test
    public void checkValIndexEqualsNbVAlidators() {
        assertThrows(TuringGameException.class, () -> problem.getValidatorNum(5));
    }
    @Test
    public void checkValIndexCorrectIndex() throws TuringGameException {
        assertEquals(10, problem.getValidatorNum(2));
    }
    //    initializeValidators, getValidator(int valId)
    //    pas testées car font appel à factory qui est déja testé
}