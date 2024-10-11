package model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoundTest {
    Round round;
    Problem problem;
    public RoundTest() throws TuringGameException {
        problem = new Problem(0,5,7,821, new int[]{2, 4, 3, 7});
        round = new Round(123);
    }

    @Test
    public void getUserCode() {
      assertEquals(123, round.getUserCode());
    }
    @Test
    public void setTestedValidatorMoreThan3(){
        assertThrows(TuringGameException.class, () -> round.setTestedValNb(10));
    }
    @Test
    public void setTestedValidatorCorrect() throws TuringGameException {
        round.validatorResult(problem.getValidator(0));
        round.setTestedValNb(-1);
        assertEquals(0, round.getTestedValNb());
    }
    @Test
    public void setTestedValidatorsToNegative(){
        assertThrows(TuringGameException.class, () -> round.setTestedValNb(-10));
    }
    @Test
    public void zeroTestedValidator() {
        assertEquals(0, round.getTestedValNb());
    }
    @Test
    public void oneTestedValidator() throws TuringGameException {
        round.validatorResult(problem.getValidator(0));
        assertEquals(1, round.getTestedValNb());
    }
    @Test
    public void twoTestedValidatorsDifferent() throws TuringGameException {
        round.validatorResult(problem.getValidator(0));
        round.validatorResult(problem.getValidator(1));
        assertEquals(2, round.getTestedValNb());
    }
    @Test
    public void threeTestedValidatorsDifferent() throws TuringGameException {
        round.validatorResult(problem.getValidator(0));
        round.validatorResult(problem.getValidator(1));
        round.validatorResult(problem.getValidator(2));
        assertEquals(3, round.getTestedValNb());
    }
    @Test
    public void moreThanThreeTestedValidators()  {
        assertThrows(TuringGameException.class, () -> round.validatorResult(
                problem.getValidator(4)));
    }
    @Test
    public void twoTestedValidatorsAreTheSame() throws TuringGameException {
        round.validatorResult(problem.getValidator(0));
        assertThrows(TuringGameException.class, () -> round.validatorResult(
                problem.getValidator(0)));
    }
}