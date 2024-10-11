package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SumParityTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v18_sameCodesSumParityIsOdd() {
        secretCode = 124;
        userCode = 124;
        var val18 = new SumParity(18, secretCode);
        assertTrue(val18.test(userCode));
    }
    @Test
    public void  v18_differentCodesSumParityIsOdd() {
        secretCode = 124;
        userCode = 355;
        var val18 = new SumParity(18, secretCode);
        assertTrue(val18.test(userCode));
    }
    @Test
    public void v18_secretSumParityIsOddUserEven() {
        secretCode = 124;
        userCode = 413;
        var val18 = new SumParity(18, secretCode);
        assertFalse(val18.test(userCode));
    }
    //Even
    @Test
    public void v18_sameCodesSumParityIsEven() {
        secretCode = 424;
        userCode = 125;
        var val18 = new SumParity(18, secretCode);
        assertTrue(val18.test(userCode));
    }
    @Test
    public void  v18_differentCodesSumParityIsEven() {
        secretCode = 424;
        userCode = 455;
        var val18 = new SumParity(18, secretCode);
        assertTrue(val18.test(userCode));
    }
    @Test
    public void v18_secretSumParityIsEvenUserOdd() {
        secretCode = 424;
        userCode = 313;
        var val18 = new SumParity(18, secretCode);
        assertFalse(val18.test(userCode));
    }
}