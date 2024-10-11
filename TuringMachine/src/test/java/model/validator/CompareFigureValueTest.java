package model.validator;

import org.junit.Test;
import static org.junit.Assert.*;

 public class CompareFigureValueTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v1_sameCodes() {
        secretCode = 123;
        userCode = 123;
        var val1 = new CompareFigureValue(1, secretCode);
        assertTrue(val1.test(userCode));
    }
    @Test
    public void v1_secretEquals1UserEquals1Too() {
        secretCode = 123;
        userCode = 134;
        var val1 = new CompareFigureValue(1, secretCode);
        assertTrue(val1.test(userCode));
    }
    @Test
    public void v1_secretEquals1UserGreaterThan1() {
        secretCode = 123;
        userCode = 323;
        var val1 = new CompareFigureValue(1, secretCode);
        assertFalse(val1.test(userCode));
    }
    @Test
    public void v1_sameCodesBothGreaterThan1() {
        secretCode = 345;
        userCode = 345;
        var val1 = new CompareFigureValue(1, secretCode);
        assertTrue(val1.test(userCode));
    }
     @Test
     public void v1_secretGreaterThan1UserGreaterThan1Too() {
         secretCode = 345;
         userCode = 543;
         var val1 = new CompareFigureValue(1, secretCode);
         assertTrue(val1.test(userCode));
     }
    @Test
    public void v1_secretGreaterThan1UserEquals1() {

        secretCode = 345;
        userCode = 134;
        var val1 = new CompareFigureValue(1, secretCode);
        assertFalse(val1.test(userCode));
    }
    // v2
    @Test
    public void v2_sameCodesBothLessThan3() {
        secretCode = 245;
        var val2 = new CompareFigureValue(2, secretCode);
        userCode = 245;
        assertTrue(val2.test(userCode));
    }
    @Test
    public void v2_secretLessThan3UserLessThan3Too() {
        secretCode = 245;
        var val2 = new CompareFigureValue(2, secretCode);
        userCode = 134;
        assertTrue(val2.test(userCode));
    }
    @Test
    public void v2_secretLessThan3UserEquals3() {
        secretCode = 245;
        var val2 = new CompareFigureValue(2, secretCode);
        userCode = 353;
        assertFalse(val2.test(userCode));
    }
    @Test
    public void v2_secretLessThan3UserGreaterThan3() {
        secretCode = 245;
        userCode = 555;
        var val2 = new CompareFigureValue(2, secretCode);
        assertFalse(val2.test(userCode));
    }
     //
     @Test
     public void v2_sameCodesBothEquals3() {
         secretCode = 321;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 321;
         assertTrue(val2.test(userCode));
     }
     @Test
     public void v2_secretEquals3UserLessThan3() {
         secretCode = 321;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 134;
         assertFalse(val2.test(userCode));
     }
     @Test
     public void v2_secretEquals3UserEquals3Too() {
         secretCode = 321;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 353;
         assertTrue(val2.test(userCode));
     }

     @Test
     public void v2_secretEquals3UserGreaterThan3() {
         secretCode = 321;
         userCode = 555;
         var val2 = new CompareFigureValue(2, secretCode);
         assertFalse(val2.test(userCode));
     }
     //
     @Test
     public void v2_sameCodesBothGreaterThan3() {
         secretCode = 523;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 523;
         assertTrue(val2.test(userCode));
     }
     @Test
     public void v2_secretGreaterThan3UserLessThan3() {
         secretCode = 523;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 134;
         assertFalse(val2.test(userCode));
     }
     @Test
     public void v2_secretGreaterThan3UserEquals3() {
         secretCode = 523;
         var val2 = new CompareFigureValue(2, secretCode);
         userCode = 353;
         assertFalse(val2.test(userCode));
     }

     @Test
     public void v2_secretGreaterThan3UserEquals3Too() {
         secretCode = 523;
         userCode = 555;
         var val2 = new CompareFigureValue(2, secretCode);
         assertTrue(val2.test(userCode));
     }
     // v3
     @Test
     public void v3_sameCodes_LessThan3() {
         secretCode = 123;
         userCode = 123;
         var val3 = new CompareFigureValue(3, secretCode);
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_secretLessThan3UserLessThan3Too() {
         secretCode = 123;
         userCode = 111;
         var val3 = new CompareFigureValue(3, secretCode);
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_secretLessThan3UserGreaterThan3() {
         secretCode = 123;
         userCode = 245;
         var val3 = new CompareFigureValue(3, secretCode);
         assertFalse(val3.test(userCode));
     }
     @Test
     public void v3_secretLessThan3UserEquals3() {
         secretCode = 123;
         userCode = 131;
         var val3 = new CompareFigureValue(3, secretCode);
         assertFalse(val3.test(userCode));
     }
     //
     @Test
     public void v3_sameCodesBothEquals3() {
         secretCode = 432;
         var val3 = new CompareFigureValue(3, secretCode);
         userCode =  432;
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_secretEquals3UserEquals3Too() {
         secretCode = 131;
         var val3 = new CompareFigureValue(3, secretCode);
         userCode = 533;
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_secretEquals3UserLessThan3() {
         secretCode = 231;
         var val3 = new CompareFigureValue(3, secretCode);
         userCode = 514;
         assertFalse(val3.test(userCode));
     }


     @Test
     public void v3_secretEquals3UserGreaterThan3() {
         secretCode = 131;
         userCode = 555;
         var val3 = new CompareFigureValue(3, secretCode);
         assertFalse(val3.test(userCode));
     }
     //
     @Test
     public void v3_sameCodesGreaterThan3() {
         secretCode = 555;
         userCode = 555;
         var val3 = new CompareFigureValue(3, secretCode);
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_sameCodesBothGreaterThan3() {
         secretCode = 555;
         userCode = 343;
         var val3 = new CompareFigureValue(3, secretCode);
         assertTrue(val3.test(userCode));
     }
     @Test
     public void v3_secretGreaterThan3UserLessThan3() {
         secretCode = 555;
         userCode = 513;
         var val3 = new CompareFigureValue(3, secretCode);
         assertFalse(val3.test(userCode));
     }
     @Test
     public void v3_secretGreaterThan1UserEquals3() {
         secretCode = 555;
         userCode = 134;
         var val3 = new CompareFigureValue(3, secretCode);
         assertFalse(val3.test(userCode));
     }
     // v4
     @Test
     public void v4_sameCodesBothEquals4() {
         secretCode = 345;
         var val4 = new CompareFigureValue(4, secretCode);
         userCode =  345;
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretEquals4UserEquals4Too() {
         secretCode = 345;
         var val4 = new CompareFigureValue(4, secretCode);
         userCode = 544;
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretEquals4UserLessThan4() {
         secretCode = 345;
         var val4 = new CompareFigureValue(4, secretCode);
         userCode = 514;
         assertFalse(val4.test(userCode));
     }
     @Test
     public void v4_secretEquals4UserGreaterThan4() {
         secretCode = 345;
         var val4 = new CompareFigureValue(4, secretCode);
         userCode = 555;
         assertFalse(val4.test(userCode));
     }
     //
     @Test
     public void v4_sameCodes_LessThan4() {
         secretCode = 321;
         userCode = 321;
         var val4 = new CompareFigureValue(4, secretCode);
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretLessThan4UserLessThan4Too() {
         secretCode = 321;
         userCode = 111;
         var val4 = new CompareFigureValue(4, secretCode);
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretLessThan4UserGreaterThan4() {
         secretCode = 321;
         userCode = 555;
         var val4 = new CompareFigureValue(4, secretCode);
         assertFalse(val4.test(userCode));
     }
     @Test
     public void v4_secretLessThan4UserEquals4() {
         secretCode = 321;
         userCode = 245;
         var val4 = new CompareFigureValue(4, secretCode);
         assertFalse(val4.test(userCode));
     }
     //
     @Test
     public void v4_sameCodesGreaterThan4() {
         secretCode = 555;
         userCode = 555;
         var val4 = new CompareFigureValue(4, secretCode);
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretGreaterThan4UserGreaterThan4Too() {
         secretCode = 555;
         userCode = 352;
         var val4 = new CompareFigureValue(4, secretCode);
         assertTrue(val4.test(userCode));
     }
     @Test
     public void v4_secretGreaterThan4UserLessThan4() {
         secretCode = 555;
         userCode = 513;
         var val4 = new CompareFigureValue(4, secretCode);
         assertFalse(val4.test(userCode));
     }
     @Test
     public void v4_secretGreaterThan4UserEquals4() {

         secretCode = 555;
         userCode = 142;
         var val4 = new CompareFigureValue(4, secretCode);
         assertFalse(val4.test(userCode));
     }
 }