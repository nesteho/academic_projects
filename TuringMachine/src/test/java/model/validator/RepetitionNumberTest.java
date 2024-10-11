package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RepetitionNumberTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v20_sameCodesNoDoublon() {
        secretCode = 123;
        userCode = 123;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void  v20_differentCodesNoDoublon() {
        secretCode = 123;
        userCode = 321;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void v20_secretCodesNoDoublonUserHave() {
        secretCode = 123;
        userCode = 212;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    @Test
    public void v20_secretCodesNoDoublonUserTriple() {
        secretCode = 123;
        userCode = 111;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    //
    @Test
    public void v20_sameCodesHaveADoublon() {
        secretCode = 331;
        userCode = 331;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void  v20_differentCodesHaveADoublon() {
        secretCode = 331;
        userCode = 121;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void v20_secretCodeHaveADoublonUserDoesNot() {
        secretCode = 331;
        userCode = 234;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    @Test
    public void v20_secretCodesHaveADoublonUserATriple() {
        secretCode = 331;
        userCode = 333;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    //
    @Test
    public void v20_sameCodesHaveATriple() {
        secretCode = 111;
        userCode = 111;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void  v20_differentCodesHaveATriple() {
        secretCode = 111;
        userCode = 555;
        var val20 = new RepetitionNumber(20, secretCode);
        assertTrue(val20.test(userCode));
    }
    @Test
    public void v20_secretCodeHaveATripleUserHaveNoDoublonNorTriple() {
        secretCode = 111;
        userCode = 234;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    @Test
    public void v20_secretCodesHaveATripleUserADoublon() {
        secretCode = 111;
        userCode = 343;
        var val20 = new RepetitionNumber(20, secretCode);
        assertFalse(val20.test(userCode));
    }
    // v21
    @Test
    public void v21_sameCodesNoDoublon() {
        secretCode = 123;
        userCode = 123;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void  v21_differentCodesNoDoublon() {
        secretCode = 123;
        userCode = 321;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void v21_secretCodesNoDoublonUserHave() {
        secretCode = 123;
        userCode = 212;
        var val21 = new RepetitionNumber(21, secretCode);
        assertFalse(val21.test(userCode));
    }
    @Test
    public void v21_secretCodesHaveNoDoublonUserHaveATriple() {
        secretCode = 232;
        userCode = 345;
        var val21 = new RepetitionNumber(21, secretCode);
        assertFalse(val21.test(userCode));
    }
    // A DOUBLON IS NOT A TRIPLE
    @Test
    public void v21_sameCodesNoDoublonButATriple() {
        secretCode = 111;
        userCode = 111;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void  v21_differentCodesNoDoublonButATriple() {
        secretCode = 111;
        userCode = 222;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void v21_secretCodeNoDoublonButATripleUserHaveADoublon() {
        secretCode = 111;
        userCode = 212;
        var val21 = new RepetitionNumber(21, secretCode);
        assertFalse(val21.test(userCode));
    }
    @Test
    public void v21_secretCodesHaveNoDoublonButATripleUserNoDoublonNorTriple() {
        secretCode = 111;
        userCode = 123;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    //
    @Test
    public void v21_sameCodesHaveADoublon() {
        secretCode = 232;
        userCode = 232;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void  v21_differentCodesHaveADoublon() {
        secretCode = 232;
        userCode = 112;
        var val21 = new RepetitionNumber(21, secretCode);
        assertTrue(val21.test(userCode));
    }
    @Test
    public void v21_secretCodesHaveADoublonUserDoesNot() {
        secretCode = 232;
        userCode = 345;
        var val21 = new RepetitionNumber(21, secretCode);
        assertFalse(val21.test(userCode));
    }
    @Test
    public void v21_secretCodesHaveADoublonUserHaveATriple() {
        secretCode = 232;
        userCode = 111;
        var val21 = new RepetitionNumber(21, secretCode);
        assertFalse(val21.test(userCode));
    }
}