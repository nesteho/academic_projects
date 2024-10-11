package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SumTwoFiguresTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v19_sameCodesBothLessThan6() {
        secretCode = 123;
        userCode = 123;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_differentCodesBothLessThan6() {
        secretCode = 123;
        userCode = 134;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_secretLessThan6UserEquals6() {
        secretCode = 123;
        userCode = 331;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }
    @Test
    public void v19_secretLessThan6UserGreaterThan6() {
        secretCode = 123;
        userCode = 345;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }
    //
    @Test
    public void v19_sameCodesBothEquals6() {
        secretCode = 334;
        userCode = 334;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_differentCodesBothEquals6() {
        secretCode = 334;
        userCode = 331;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_secretEquals6UserLessThan6() {
        secretCode = 334;
        userCode = 321;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }
    @Test
    public void v19_secretEquals6UserGreaterThan6() {
        secretCode = 334;
        userCode = 345;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }
    //
    @Test
    public void v19_sameCodesBothGreaterThan6() {
        secretCode = 342;
        userCode = 342;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_differentCodesBothGreaterThan6() {
        secretCode = 342;
        userCode = 351;
        var val19 = new SumTwoFigures(19, secretCode);
        assertTrue(val19.test(userCode));
    }
    @Test
    public void v19_secretGreaterThan6UserLessThan6() {
        secretCode = 342;
        userCode = 313;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }
    @Test
    public void v19_secretGreaterThan6UserEquals6() {
        secretCode = 342;
        userCode = 335;
        var val19 = new SumTwoFigures(19, secretCode);
        assertFalse(val19.test(userCode));
    }

}