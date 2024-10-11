package model.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class HowOftenFigureTest {
    private  int secretCode;
    private  int userCode;

    // v8
    @Test
    public void v8_sameCodesDontHave1() {
        secretCode = 352;
        userCode = 352;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_differentCodesBothDontHave1() {
        secretCode = 352;
        userCode = 234;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_secretDoesNotHaveFigure1UserHaveItOneTime() {
        secretCode = 352;
        userCode = 214;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretDoesNotHaveFigure1UserHaveItTwoTimes() {
        secretCode = 352;
        userCode = 131;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretDoesNotHaveFigure1UserHaveItThreeTimes() {
        secretCode = 345;
        userCode = 111;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    //
    @Test
    public void v8_sameCodesBothHaveOneTime1() {
        secretCode = 123;
        userCode = 123;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_differentCodesBothHaveOneTime1() {
        secretCode = 123;
        userCode = 231;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveOneTime1UserDoesNotHaveFigure1() {
        secretCode = 123;
        userCode = 234;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveOneTime1UserTwoTimes1() {
        secretCode = 123;
        userCode = 131;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveOneTime1UserThreeTimes1() {
        secretCode = 345;
        userCode = 111;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    //
    @Test
    public void v8_sameCodesBothHaveTwoTimes1() {
        secretCode = 121;
        userCode = 121;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_differentCodesBothHaveTwoTimes1() {
        secretCode = 121;
        userCode = 311;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveTwoTimes1UserDoesNotHaveFigure1() {
        secretCode = 121;
        userCode = 234;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveTwoTimes1UserOneItOnce() {
        secretCode = 121;
        userCode = 134;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveTwoTimes1UserThreeTimes1() {
        secretCode = 121;
        userCode = 111;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    //
    @Test
    public void v8_sameCodesBothHaveThreeTimes1() {
        secretCode = 111;
        userCode = 111;
        var val8 = new HowOftenFigure(8, secretCode);
        assertTrue(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveThreeTimes1UserDoesNotHaveFigure1() {
        secretCode = 111;
        userCode = 234;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveThreeTimes1UserUserOneItOnce() {
        secretCode = 111;
        userCode = 134;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    @Test
    public void v8_secretHaveThreeTimes1UserTwice() {
        secretCode = 111;
        userCode = 151;
        var val8 = new HowOftenFigure(8, secretCode);
        assertFalse(val8.test(userCode));
    }
    ///// v9
    @Test
    public void v9_sameCodesDontHave3() {
        secretCode = 152;
        userCode = 152;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_differentCodesBothDontHave3() {
        secretCode = 152;
        userCode = 214;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_secretDoesNotHaveFigure3UserHaveItOneTime() {
        secretCode = 152;
        userCode = 314;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretDoesNotHaveFigure3UserHaveItTwoTimes() {
        secretCode = 152;
        userCode = 331;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretDoesNotHaveFigure3UserHaveItThreeTimes() {
        secretCode = 145;
        userCode = 333;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    //
    @Test
    public void v9_sameCodesBothHaveOneTime3() {
        secretCode = 123;
        userCode = 123;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_differentCodesBothHaveOneTime3() {
        secretCode = 123;
        userCode = 231;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveOneTime3UserDoesNotHaveFigure3() {
        secretCode = 123;
        userCode = 214;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveOneTime3UserTwoTimes1() {
        secretCode = 123;
        userCode = 133;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveOneTime3UserThreeTimes1() {
        secretCode = 345;
        userCode = 333;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    //
    @Test
    public void v9_sameCodesBothHaveTwoTimes1() {
        secretCode = 133;
        userCode = 133;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_differentCodesBothHaveTwoTimes1() {
        secretCode = 133;
        userCode = 313;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveTwoTimes1UserDoesNotHaveFigure3() {
        secretCode = 133;
        userCode = 555;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveTwoTimes1UserOneItOnce() {
        secretCode = 133;
        userCode = 134;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveTwoTimes1UserThreeTimes1() {
        secretCode = 133;
        userCode = 333;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    //
    @Test
    public void v9_sameCodesBothHaveThreeTimes1() {
        secretCode = 333;
        userCode = 333;
        var val9 = new HowOftenFigure(9, secretCode);
        assertTrue(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveThreeTimes1UserUserOneItOnce() {
        secretCode = 333;
        userCode = 134;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    @Test
    public void v9_secretHaveThreeTimes1UserTwice() {
        secretCode = 333;
        userCode = 313;
        var val9 = new HowOftenFigure(9, secretCode);
        assertFalse(val9.test(userCode));
    }
    //// v10
    @Test
    public void v10_sameCodesDontHave4() {
        secretCode = 152;
        userCode = 152;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_differentCodesBothDontHave4() {
        secretCode = 152;
        userCode = 211;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_secretDoesNotHaveFigure4UserHaveItOnce() {
        secretCode = 152;
        userCode = 314;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretDoesNotHaveFigure4UserHaveItTwoTimes() {
        secretCode = 152;
        userCode = 441;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretDoesNotHaveFigure4UserHaveItThreeTimes() {
        secretCode = 152;
        userCode = 444;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    //
    @Test
    public void v10_sameCodesBothHaveOneTime4() {
        secretCode = 534;
        userCode = 534;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_differentCodesBothHaveOneTime4() {
        secretCode = 534;
        userCode = 241;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveOneTime4UserDoesNotHaveFigure4() {
        secretCode = 534;
        userCode = 222;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveOneTime4UserTwoTimes4() {
        secretCode = 534;
        userCode = 414;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveOneTime3UserThreeTimes1() {
        secretCode = 534;
        userCode = 444;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    //
    @Test
    public void v10_sameCodesBothHaveTwoTimes4() {
        secretCode = 144;
        userCode = 144;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_differentCodesBothHaveTwoTimes4() {
        secretCode = 144;
        userCode = 414;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveTwoTimes4UserDoesNotHaveFigure3() {
        secretCode = 144;
        userCode = 555;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveTwoTimes4UserOneItOnce() {
        secretCode = 144;
        userCode = 134;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveTwoTimes4UserThreeTimes4() {
        secretCode = 144;
        userCode = 444;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    //
    @Test
    public void v10_sameCodesBothHaveThreeTimes1() {
        secretCode = 444;
        userCode = 444;
        var val10 = new HowOftenFigure(10, secretCode);
        assertTrue(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveThreeTimes1UserUserOneItOnce() {
        secretCode = 444;
        userCode = 134;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }
    @Test
    public void v10_secretHaveThreeTimes1UserTwice() {
        secretCode = 444;
        userCode = 414;
        var val10 = new HowOftenFigure(10, secretCode);
        assertFalse(val10.test(userCode));
    }

}