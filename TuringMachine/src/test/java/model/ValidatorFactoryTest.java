package model;

import model.validator.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorFactoryTest {
    private ValidatorFactory factory = new ValidatorFactory();
    private Validator validator;

    @Test
    public void createV1() throws TuringGameException {
        validator = factory.createValidator(123,1);
        assertTrue( validator instanceof CompareFigureValue);
    }
    @Test
    public void createV2() throws TuringGameException {
        validator = factory.createValidator(123, 2);
        assertTrue(validator instanceof CompareFigureValue);
    }
    @Test
    public void createV3() throws TuringGameException {
        validator = factory.createValidator(123, 3);
        assertTrue(validator instanceof CompareFigureValue);
    }
    @Test
    public void createV4() throws TuringGameException {
        validator = factory.createValidator(123, 4);
        assertTrue(validator instanceof CompareFigureValue);
    }
    @Test
    public void createV5() throws TuringGameException {
        validator = factory.createValidator(123, 5);
        assertTrue(validator instanceof CompareFigureParity);
    }

    @Test
    public void createV6() throws TuringGameException {
        validator = factory.createValidator(123, 6);
        assertTrue(validator instanceof CompareFigureParity);
    }

    @Test
    public void createV7() throws TuringGameException {
        validator = factory.createValidator(123, 7);
        assertTrue(validator instanceof CompareFigureParity);
    }
    @Test
    public void createV8() throws TuringGameException {
        validator = factory.createValidator(123, 8);
        assertTrue(validator instanceof HowOftenFigure);
    }

    @Test
    public void createV9() throws TuringGameException {
        validator = factory.createValidator(123, 9);
        assertTrue(validator instanceof HowOftenFigure);
    }

    @Test
    public void createV10() throws TuringGameException {
        validator = factory.createValidator(123, 10);
        assertTrue(validator instanceof HowOftenFigure);
    }
    @Test
    public void createV11() throws TuringGameException {
        validator = factory.createValidator(123, 11);
        assertTrue(validator instanceof CompareTwoFigures);
    }
    @Test
    public void createV12() throws TuringGameException {
        validator = factory.createValidator(123, 12);
        assertTrue(validator instanceof CompareTwoFigures);
    }
    @Test
    public void createV13() throws TuringGameException {
        validator = factory.createValidator(123, 13);
        assertTrue(validator instanceof CompareTwoFigures);
    }
    @Test
    public void createV14() throws TuringGameException {
        validator = factory.createValidator(123, 14);
        assertTrue(validator instanceof ExtremumFigure);
    }
    @Test
    public void createV15() throws TuringGameException {
        validator = factory.createValidator(123, 15);
        assertTrue(validator instanceof ExtremumFigure);
    }
    @Test
    public void createV16() throws TuringGameException {
        validator = factory.createValidator(123, 16);
        assertTrue(validator instanceof MostPresentParity);
    }
    @Test
    public void createV17() throws TuringGameException {
        validator = factory.createValidator(123, 17);
        assertTrue(validator instanceof EvenFigures);
    }
    @Test
    public void createV18() throws TuringGameException {
        validator = factory.createValidator(123, 18);
        assertTrue(validator instanceof SumParity);
    }
    @Test
    public void createV19() throws TuringGameException {
        validator = factory.createValidator(123, 19);
        assertTrue(validator instanceof SumTwoFigures);
    }
    @Test
    public void createV20() throws TuringGameException {
        validator = factory.createValidator(123, 20);
        assertTrue(validator instanceof RepetitionNumber);
    }

    @Test
    public void createV21() throws TuringGameException {
        validator = factory.createValidator(123, 21);
        assertTrue(validator instanceof RepetitionNumber);
    }
    @Test
    public void createV22() throws TuringGameException {
        validator = factory.createValidator(123, 22);
        assertTrue(validator instanceof FiguresOrder);
    }
    @Test
    public void wrongValidatorId_LessThan0(){
        assertThrows(TuringGameException.class, () ->
                factory.createValidator(123, -1) );
    }
    @Test
    public void wrongValidatorId_LessEquals0(){
        assertThrows(TuringGameException.class, () ->
                factory.createValidator(123, 0) );
    }
    @Test
    public void wrongValidatorId_GreatherThan22(){
        assertThrows(TuringGameException.class, () ->
                factory.createValidator(123, 100) );
    }
    ///////////////////////////// NOT AN INSTANCEE
    @Test
    public void testCreateValidatorIsNotAnInstanceTheComparedTypeV1() throws TuringGameException {
        Validator validator = factory.createValidator(123, 1);
        assertFalse(validator instanceof CompareFigureParity);
    }

    @Test
    public void testCreateValidatorWrongTypeV2() throws TuringGameException {
        Validator validator = factory.createValidator(123, 2);
        assertFalse(validator instanceof HowOftenFigure);
    }

    @Test
    public void testCreateValidatorWrongTypeV3() throws TuringGameException {
        Validator validator = factory.createValidator(123, 3);
        assertFalse(validator instanceof CompareTwoFigures);
    }

    @Test
    public void testCreateValidatorWrongTypeV4() throws TuringGameException {
        Validator validator = factory.createValidator(123, 4);
        assertFalse(validator instanceof ExtremumFigure);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV5() throws TuringGameException {
        validator = factory.createValidator(123, 5);
        assertFalse(validator instanceof CompareFigureValue);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV6() throws TuringGameException {
        Validator validator = factory.createValidator(123, 6);
        assertFalse(validator instanceof CompareFigureValue);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV7() throws TuringGameException {
        Validator validator = factory.createValidator(123, 7);
        assertFalse(validator instanceof RepetitionNumber);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV8() throws TuringGameException {
        Validator validator = factory.createValidator(123, 8);
        assertFalse(validator instanceof CompareTwoFigures);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV9() throws TuringGameException {
        Validator validator = factory.createValidator(123, 9);
        assertFalse(validator instanceof ExtremumFigure);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV10() throws TuringGameException {
        Validator validator = factory.createValidator(123, 10);
        assertFalse(validator instanceof MostPresentParity);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV11() throws TuringGameException {
        Validator validator = factory.createValidator(123, 11);
        assertFalse(validator instanceof EvenFigures);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV12() throws TuringGameException {
        Validator validator = factory.createValidator(123, 12);
        assertFalse(validator instanceof CompareFigureValue);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV13() throws TuringGameException {
        Validator validator = factory.createValidator(123, 13);
        assertFalse(validator instanceof CompareFigureParity);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV14() throws TuringGameException {
        Validator validator = factory.createValidator(123, 14);
        assertFalse(validator instanceof HowOftenFigure);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV15() throws TuringGameException {
        Validator validator = factory.createValidator(123, 15);
        assertFalse(validator instanceof CompareTwoFigures);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV16() throws TuringGameException {
        Validator validator = factory.createValidator(123, 16);
        assertFalse(validator instanceof ExtremumFigure);
    }

    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV17() throws TuringGameException {
        Validator validator = factory.createValidator(123, 17);
        assertFalse(validator instanceof MostPresentParity);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV18() throws TuringGameException {
        Validator validator = factory.createValidator(123, 18);
        assertFalse(validator instanceof EvenFigures);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV19() throws TuringGameException {
        Validator validator = factory.createValidator(123, 19);
        assertFalse(validator instanceof SumParity);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV20() throws TuringGameException {
        Validator validator = factory.createValidator(123, 20);
        assertFalse(validator instanceof SumTwoFigures);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV21() throws TuringGameException {
        Validator validator = factory.createValidator(123, 21);
        assertFalse(validator instanceof CompareFigureValue);
    }
    @Test
    public void createdValidatorIsNotAnInstanceTheComparedTypeV22() throws TuringGameException {
        Validator validator = factory.createValidator(123, 22);
        assertFalse(validator instanceof RepetitionNumber);
    }





}
