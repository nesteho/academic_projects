package model.validator;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiguresOrderTest {
    private  int secretCode;
    private  int userCode;
    @Test
    public void v22_sameCodesAsc() {
        secretCode = 123;
        userCode = 123;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void  v22_differentCodesAsc() {
        secretCode = 123;
        userCode = 345;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void v22_secretCodeAscUserDes() {
        secretCode = 123;
        userCode = 212;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    //Bridge And Plate are both in category "No order"
    @Test
    public void v22_secretCodeAscUserBridge() {
        secretCode = 123;
        userCode = 121;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretCodeAscUserPlate() {
        secretCode = 123;
        userCode = 111;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    //
    @Test
    public void v22_sameCodesDes() {
        secretCode = 543;
        userCode = 543;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void  v22_differentCodesDes() {
        secretCode = 543;
        userCode = 321;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void v22_secretCodeDesUserAsc() {
        secretCode = 543;
        userCode = 234;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretCodeDesUserBridge() {
        secretCode = 543;
        userCode = 121;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretCodeDesUserPlate() {
        secretCode = 543;
        userCode = 111;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    //////
    @Test
    public void v22_sameCodesNoOrderBridge() {
        secretCode = 312;
        userCode = 312;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void  v22_differentCodesNoOrderBridge() {
        secretCode = 312;
        userCode = 415;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderBridgeUserAsc() {
        secretCode = 312;
        userCode = 123;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderBridgeUserDesc() {
        secretCode = 312;
        userCode = 321;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderBridgeUserPlate() {
        secretCode = 312;
        userCode = 111;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }

    //////
    @Test
    public void v22_sameCodesNoOrderPlate() {
        secretCode = 222;
        userCode = 222;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void  v22_differentCodesNoOrderPlate() {
        secretCode = 222;
        userCode = 113;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderPlateUserAsc() {
        secretCode = 222;
        userCode = 123;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderPlateUserDesc() {
        secretCode = 222;
        userCode = 321;
        var val22 = new FiguresOrder(22, secretCode);
        assertFalse(val22.test(userCode));
    }
    @Test
    public void v22_secretNoOrderPlateUserBridge() {
        secretCode = 222;
        userCode = 415;
        var val22 = new FiguresOrder(22, secretCode);
        assertTrue(val22.test(userCode));
    }

}