package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MostPresentParityTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v16_sameCodesHaveThreeFiguresOdd() {
        secretCode = 513;
        userCode = 513;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveThreeFiguresOdd() {
        secretCode = 513;
        userCode = 135;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresOddUserTwo() {
        secretCode = 513;
        userCode = 231;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresOddUserOne() {
        secretCode = 513;
        userCode = 245;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresOddUserZero() {
        secretCode = 513;
        userCode = 242;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    //
    @Test
    public void v16_sameCodesHaveTwoFiguresOdd() {
        secretCode = 431;
        userCode = 431;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveTwoFiguresOdd() {
        secretCode = 431;
        userCode = 125;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresOddUserThree() {
        secretCode = 431;
        userCode = 135;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresOddUserOne() {
        secretCode = 431;
        userCode = 245;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresOddUserZero() {
        secretCode = 431;
        userCode = 242;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }

    //
    @Test
    public void v16_sameCodesHaveOneFigureOdd() {
        secretCode = 241;
        userCode = 241;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveOneFigureOdd() {
        secretCode = 241;
        userCode = 254;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureOddUserThree() {
        secretCode = 241;
        userCode = 135;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureOddUserTwo() {
        secretCode = 241;
        userCode = 215;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureOddUserZero() {
        secretCode = 241;
        userCode = 242;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    //
    @Test
    public void v16_sameCodesHaveNoFigureOdd() {
        secretCode = 444;
        userCode = 444;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveNoFigureOdd() {
        secretCode = 444;
        userCode = 242;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureOddUserThree() {
        secretCode = 444;
        userCode = 135;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureOddUserTwo() {
        secretCode = 444;
        userCode = 215;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureOddUserOne() {
        secretCode = 444;
        userCode = 241;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    // POUR LES PAIR
    @Test
    public void v16_sameCodesHaveThreeFiguresEven() {
        secretCode = 222;
        userCode = 222;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveThreeFiguresEven() {
        secretCode = 222;
        userCode = 424;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresEvenUserTwo() {
        secretCode = 222;
        userCode = 234;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresEvenUserOne() {
        secretCode = 222;
        userCode = 415;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveThreeFiguresEvenUserZero() {
        secretCode = 222;
        userCode = 135;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    //
    @Test
    public void v16_sameCodesHaveTwoFiguresEven() {
        secretCode = 124;
        userCode = 124;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveTwoFiguresEven() {
        secretCode = 124;
        userCode = 122;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresEvenUserThree() {
        secretCode = 124;
        userCode = 222;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresEvenUserOne() {
        secretCode = 124;
        userCode = 132;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveTwoFiguresEvenUserZero() {
        secretCode = 124;
        userCode = 111;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }

    //
    @Test
    public void v16_sameCodesHaveOneFigureEven() {
        secretCode = 253;
        userCode = 253;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveOneFigureEven() {
        secretCode = 253;
        userCode = 215;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureEvenUserThree() {
        secretCode = 253;
        userCode = 424;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureEvenUserTwo() {
        secretCode = 253;
        userCode = 214;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveOneFigureEvenUserZero() {
        secretCode = 253;
        userCode = 111;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    //
    @Test
    public void v16_sameCodesHaveNoFigureEven() {
        secretCode = 153;
        userCode = 153;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void  v16_differentCodesBothHaveNoFigureEven() {
        secretCode = 153;
        userCode = 531;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureEvenUserThree() {
        secretCode = 153;
        userCode = 242;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureEvenUserTwo() {
        secretCode = 153;
        userCode = 421;
        var val16 = new MostPresentParity(16, secretCode);
        assertFalse(val16.test(userCode));
    }
    @Test
    public void v16_secretHaveNoFigureEvenUserOne() {
        secretCode = 153;
        userCode = 215;
        var val16 = new MostPresentParity(16, secretCode);
        assertTrue(val16.test(userCode));
    }
}