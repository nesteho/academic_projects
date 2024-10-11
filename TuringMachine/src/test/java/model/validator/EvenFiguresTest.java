package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EvenFiguresTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v17_sameCodesHaveNoEvenFigure() {
        secretCode = 135;
        userCode = 135;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_differentCodesBothHaveNoEvenFigure() {
        secretCode = 135;
        userCode = 531;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveNoEvenFigure1UserHaveItOneTime() {
        secretCode = 135;
        userCode = 413;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveNoEvenFigureUserHaveItTwoTimes() {
        secretCode = 135;
        userCode = 412;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveNoEvenFigureUserHaveItThreeTimes() {
        secretCode = 135;
        userCode = 242;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    //
    @Test
    public void v17_sameCodesBothHaveOneEvenFigure() {
        secretCode = 145;
        userCode = 145;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_differentCodesBothHaveOneEvenFigure() {
        secretCode = 145;
        userCode = 231;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveOneEvenFigureUserZero() {
        secretCode = 145;
        userCode = 135;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveOneEvenFigureUserTwo() {
        secretCode = 145;
        userCode = 241;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveOneEvenFigureUserThree() {
        secretCode = 345;
        userCode = 442;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    //
    @Test
    public void v17_sameCodesBothHaveTwoEvenFigures() {
        secretCode = 243;
        userCode = 243;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_differentCodesBothHaveTwoEvenFigures() {
        secretCode = 243;
        userCode = 124;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveTwoEvenFiguresUserZero() {
        secretCode = 243;
        userCode = 131;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveTwoEvenFiguresUserOne() {
        secretCode = 243;
        userCode = 154;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveTwoEvenFiguresUserThree() {
        secretCode = 243;
        userCode = 444;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    //
    @Test
    public void v17_sameCodesBothHaveThreeEvenFigures() {
        secretCode = 242;
        userCode = 242;
        var val17 = new EvenFigures(17, secretCode);
        assertTrue(val17.test(userCode));
    }
    @Test
    public void v17_differentCodesBothHaveThreeEvenFigures() {
        secretCode = 242;
        userCode = 424;
        var val17 = new EvenFigures(17, secretCode);
        assert (val17.test(userCode));
    }
    @Test
    public void v17_secretHaveThreeEvenFiguresUserOne() {
        secretCode = 242;
        userCode = 134;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveThreeEvenFiguresUserTwo() {
        secretCode = 242;
        userCode = 225;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
    @Test
    public void v17_secretHaveThreeEvenFiguresUserZero() {
        secretCode = 242;
        userCode = 225;
        var val17 = new EvenFigures(17, secretCode);
        assertFalse(val17.test(userCode));
    }
}