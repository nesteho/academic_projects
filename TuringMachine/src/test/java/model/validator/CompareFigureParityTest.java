package model.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class CompareFigureParityTest {
    private  int secretCode;
    private  int userCode;
    // v5
    @Test
    public void v5_sameCodesAreBothOdd() {
        secretCode = 523;
        userCode = 523;
        var val5 = new CompareFigureParity(5, secretCode);
        assertTrue(val5.test(userCode));
    }
    @Test
    public void  v5_differentCodesAreBothOdd() {
        secretCode = 523;
        userCode = 352;
        var val5 = new CompareFigureParity(5, secretCode);
        assertTrue(val5.test(userCode));
    }
    @Test
    public void v5_secretOddUserEven() {
        secretCode = 523;
        userCode = 413;
        var val5 = new CompareFigureParity(5, secretCode);
        assertFalse(val5.test(userCode));
    }
    //
    @Test
    public void v5_sameCodesAreBothEven() {
        secretCode = 415;
        userCode = 415;
        var val5 = new CompareFigureParity(5, secretCode);
        assertTrue(val5.test(userCode));
    }
    @Test
    public void  v5_differentCodesBothAreEven() {
        secretCode = 415;
        userCode = 252;
        var val5 = new CompareFigureParity(5, secretCode);
        assertTrue(val5.test(userCode));
    }
    @Test
    public void v5_secretEvenUserOdd() {
        secretCode = 415;
        userCode = 122;
        var val5 = new CompareFigureParity(5, secretCode);
        assertFalse(val5.test(userCode));
    }
    // v6
    @Test
    public void v6_sameCodesAreBothOdd() {
        secretCode = 432;
        userCode = 432;
        var val6 = new CompareFigureParity(6, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void  v6_differentCodesAreBothOdd() {
        secretCode = 432;
        userCode = 412;
        var val6 = new CompareFigureParity(6, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void v6_secretOddUserEven() {
        secretCode = 432;
        userCode = 123;
        var val6 = new CompareFigureParity(6, secretCode);
        assertFalse(val6.test(userCode));
    }
    //
    @Test
    public void v6_sameCodesAreBothEven() {
        secretCode = 145;
        userCode = 145;
        var val6 = new CompareFigureParity(6, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void  v6_differentCodesBothAreEven() {
        secretCode = 145;
        userCode = 121;
        var val6 = new CompareFigureParity(6, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void v6_secretEvenUserOdd() {
        secretCode = 145;
        userCode = 113;
        var val5 = new CompareFigureParity(6, secretCode);
        assertFalse(val5.test(userCode));
    }
    ///
// v7
    @Test
    public void v7_sameCodesAreBothOdd() {
        secretCode = 421;
        userCode = 421;
        var val6 = new CompareFigureParity(7, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void  v7_differentCodesAreBothOdd() {
        secretCode = 421;
        userCode = 245;
        var val6 = new CompareFigureParity(7, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void v7_secretOddUserEven() {
        secretCode = 421;
        userCode = 122;
        var val6 = new CompareFigureParity(7, secretCode);
        assertFalse(val6.test(userCode));
    }
    //
    @Test
    public void v7_sameCodesAreBothEven() {
        secretCode = 532;
        userCode = 532;
        var val6 = new CompareFigureParity(7, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void  v7_differentCodesBothAreEven() {
        secretCode = 532;
        userCode = 312;
        var val6 = new CompareFigureParity(7, secretCode);
        assertTrue(val6.test(userCode));
    }
    @Test
    public void v7_secretEvenUserOdd() {
        secretCode = 532;
        userCode = 123;
        var val5 = new CompareFigureParity(7, secretCode);
        assertFalse(val5.test(userCode));
    }

}