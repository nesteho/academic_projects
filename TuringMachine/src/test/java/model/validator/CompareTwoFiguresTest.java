package model.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CompareTwoFiguresTest {
    private  int secretCode;
    private  int userCode;

    // v11
    @Test
    public void v11_sameCodesBothFirstSmallerThanSecond() {
        secretCode = 345;
        userCode = 345;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_differentCodesBothFirstSmallerThanSecond() {
        secretCode = 345;
        userCode = 134;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstSmallerThanSecondUserFirstEqualsSecond() {
        secretCode = 345;
        userCode = 333;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstSmallerThanSecondUserFirstGreaterThanSecond() {
        secretCode = 345;
        userCode = 525;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    //
    @Test
    public void v11_sameCodesBothFirstEqualsSecond() {
        secretCode = 225;
        userCode = 225;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_differentCodesBothFirstEqualsSecond() {
        secretCode = 225;
        userCode = 114;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstEqualsSecondUserFirstSmallerThanSecond() {
        secretCode = 225;
        userCode = 131;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstEqualsSecondUserFirstGreaterThanSecond() {
        secretCode = 225;
        userCode = 255;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    //
    @Test
    public void v11_sameCodesBothFirstGreaterThanSecond() {
        secretCode = 423;
        userCode = 423;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_differentCodesBothFirstGreaterThanSecond() {
        secretCode = 423;
        userCode = 514;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertTrue(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstGreaterThanSecondUserFirstSmallerThanSecond() {
        secretCode = 423;
        userCode = 131;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    @Test
    public void v11_secretFirstGreaterThanSecondUserFirstEqualsSecond() {
        secretCode = 435;
        userCode = 555;
        var val11 = new CompareTwoFigures(11, secretCode);
        assertFalse(val11.test(userCode));
    }
    // v12
    @Test
    public void v12_sameCodesBothFirstSmallerThanThird() {
        secretCode = 123;
        userCode = 123;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_differentCodesBothFirstSmallerThanThird() {
        secretCode = 123;
        userCode = 134;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstSmallerThanThirdUserFirstEqualsThird() {
        secretCode = 123;
        userCode = 343;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstSmallerThanThirdUserFirstGreaterThanThird() {
        secretCode = 123;
        userCode = 523;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    //
    @Test
    public void v12_sameCodesBothFirstEqualsThird() {
        secretCode = 424;
        userCode = 424;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_differentCodesBothFirstEqualsThird() {
        secretCode = 424;
        userCode = 141;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstEqualsThirdUserFirstSmallerThanThird() {
        secretCode = 424;
        userCode = 134;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstEqualsThirdUserFirstGreaterThanThird() {
        secretCode = 424;
        userCode = 554;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    //
    @Test
    public void v12_sameCodesBothFirstGreaterThanThird() {
        secretCode = 423;
        userCode = 423;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_differentCodesBothFirstGreaterThanThird() {
        secretCode = 423;
        userCode = 514;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertTrue(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstGreaterThanThirdUserFirstSmallerThanThird() {
        secretCode = 423;
        userCode = 135;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    @Test
    public void v12_secretFirstGreaterThanThirdUserFirstEqualsThird() {
        secretCode = 423;
        userCode = 515;
        var val12 = new CompareTwoFigures(12, secretCode);
        assertFalse(val12.test(userCode));
    }
    // v13
    @Test
    public void v13_sameCodesBothSecondSmallerThanThird() {
        secretCode = 545;
        userCode = 545;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_differentCodesBothSecondSmallerThanThird() {
        secretCode = 545;
        userCode = 434;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondSmallerThanThirdUserSecondEqualsThird() {
        secretCode = 545;
        userCode = 533;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondSmallerThanThirdUserSecondGreaterThanThird() {
        secretCode = 545;
        userCode = 253;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }
    //
    @Test
    public void v13_sameCodesBothSecondEqualsThird() {
        secretCode = 144;
        userCode = 144;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_differentCodesBothSecondEqualsThird() {
        secretCode = 144;
        userCode = 122;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondEqualsThirdUserSecondSmallerThanThird() {
        secretCode = 144;
        userCode = 214;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondEqualsThirdUserSecondGreaterThanThird() {
        secretCode = 144;
        userCode = 154;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }
    //
    @Test
    public void v13_sameCodesBothSecondGreaterThanThird() {
        secretCode = 132;
        userCode = 132;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_differentCodesBothSecondGreaterThanThird() {
        secretCode = 132;
        userCode = 341;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertTrue(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondGreaterThanThirdUserSecondSmallerThanThird() {
        secretCode = 132;
        userCode = 535;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }
    @Test
    public void v13_secretSecondGreaterThanThirdUserSecondEqualsThird() {
        secretCode = 132;
        userCode = 555;
        var val13 = new CompareTwoFigures(13, secretCode);
        assertFalse(val13.test(userCode));
    }

}