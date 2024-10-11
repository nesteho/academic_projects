package model.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class ExtremumFigureTest {
    private  int secretCode;
    private  int userCode;
    // v14
    @Test
    public void v14_smallerFigureTwoTimesIsNotAMinimum() {
        secretCode = 252;
        userCode = 222;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_sameCodesNoMinimum() {
        secretCode = 444;
        userCode = 444;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_differentCodesNoMinimum() {
        secretCode = 444;
        userCode = 111;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_secretNoMinimumUserHaveAMinimum() {
        secretCode = 444;
        userCode = 535;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    //
    @Test
    public void v14_sameCodesFirstFigureMinimum() {
        secretCode = 123;
        userCode = 123;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_differentCodesFirstFigureMinimum() {
        secretCode = 123;
        userCode = 234;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_secretFirstFigureMinimumUserNoMinimum() {
        secretCode = 123;
        userCode = 555;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretFirstFigureMinimumUserSecondFigureMinimum() {
        secretCode = 123;
        userCode = 523;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretFirstFigureMinimumUserThirdFigureMinimum() {
        secretCode = 123;
        userCode = 543;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    //
    @Test
    public void v14_sameCodesSecondFigureMinimum() {
        secretCode = 425;
        userCode = 425;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_differentCodesSecondFigureMinimum() {
        secretCode = 425;
        userCode = 534;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_secretSecondFigureMinimumUserNoMinimum() {
        secretCode = 425;
        userCode = 555;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretSecondFigureMinimumUserFirstFigureMinimum() {
        secretCode = 425;
        userCode = 124;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretSecondFigureMinimumUserThirdFigureMinimum() {
        secretCode = 425;
        userCode = 321;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    //////
    @Test
    public void v14_sameCodesThirdFigureMinimum() {
        secretCode = 321;
        userCode = 321;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_differentCodesThirdFigureMinimum() {
        secretCode = 321;
        userCode = 543;
        var val14 = new ExtremumFigure(14, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v14_secretThirdFigureMinimumUserNoMinimum() {
        secretCode = 321;
        userCode = 555;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretThirdFigureMinimumUserFirstFigureMinimum() {
        secretCode = 321;
        userCode = 124;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    @Test
    public void v14_secretThirdFigureMinimumUserSecondFigureMinimum() {
        secretCode = 321;
        userCode = 314;
        var val14 = new ExtremumFigure(14, secretCode);
        assertFalse(val14.test(userCode));
    }
    // v15
    @Test
    public void v15_greaterFigureTwoTimesIsNotAMaximum() {
        secretCode = 444;
        userCode = 424;
        var val14 = new ExtremumFigure(15, secretCode);
        assertTrue(val14.test(userCode));
    }
    @Test
    public void v15_sameCodesNoMaximum() {
        secretCode = 444;
        userCode = 444;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_differentCodesNoMaximum() {
        secretCode = 444;
        userCode = 111;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_secretNoMaximumUserHaveAMaximum() {
        secretCode = 444;
        userCode = 145;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    //
    @Test
    public void v15_sameCodesFirstFigureMaximum() {
        secretCode = 532;
        userCode = 532;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_differentCodesFirstFigureMaximum() {
        secretCode = 532;
        userCode = 321;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_secretFirstFigureMaximumUserNoMaximum() {
        secretCode = 532;
        userCode = 555;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretFirstFigureMaximumUserSecondFigureMaximum() {
        secretCode = 532;
        userCode = 143;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretFirstFigureMaximumUserThirdFigureMaximum() {
        secretCode = 532;
        userCode = 123;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    //
    @Test
    public void v15_sameCodesSecondFigureMaximum() {
        secretCode = 243;
        userCode = 243;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_differentCodesSecondFigureMaximum() {
        secretCode = 243;
        userCode = 154;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_secretSecondFigureMaximumUserNoMaximum() {
        secretCode = 243;
        userCode = 555;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretSecondFigureMaximumUserFirstFigureMaximum() {
        secretCode = 243;
        userCode = 321;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretSecondFigureMaximumUserThirdFigureMaximum() {
        secretCode = 243;
        userCode = 345;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    //////
    @Test
    public void v15_sameCodesThirdFigureMaximum() {
        secretCode = 123;
        userCode = 123;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_differentCodesThirdFigureMaximum() {
        secretCode = 123;
        userCode = 234;
        var val15 = new ExtremumFigure(15, secretCode);
        assertTrue(val15.test(userCode));
    }
    @Test
    public void v15_secretThirdFigureMaximumUserNoMaximum() {
        secretCode = 123;
        userCode = 555;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretThirdFigureMaximumUserFirstFigureMaximum() {
        secretCode = 123;
        userCode = 543;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }
    @Test
    public void v15_secretThirdFigureMaximumUserSecondFigureMaximum() {
        secretCode = 123;
        userCode = 354;
        var val15 = new ExtremumFigure(15, secretCode);
        assertFalse(val15.test(userCode));
    }

}