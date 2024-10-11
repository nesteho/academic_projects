package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuringTest {
    Turing turing;
    public TuringTest() throws TuringGameException {
        turing = new Turing();
        turing.startGame(1);
    }

    @Test
    void enterCode_ThreeDifferentFiguresAllBetweenOneAndFive() throws TuringGameException {
        turing.enterCode(123);
    }
    @Test
    void enterCode_ThreeDifferentFiguresTwoAre0(){
        assertThrows(TuringGameException.class, ()->  turing.enterCode(100));
    }
    @Test
    void enterCode_ThreeDifferentFiguresAllAre0() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(000));
    }
    @Test
    void enterCode_OneFigure0() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(0));
    }
    @Test
    void enterCode_ThreeDifferentFiguresContainsNegativeDigit() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(-123));
    }
    @Test
    void enterCode_ThreeDifferentFiguresContainsDigitGreaterThan5() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(678));
    }
    @Test
    void enterCode_TwoDifferentFiguresLengthLessThan3() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(12));
    }
    @Test
    void enterCode_TwoSameFiguresLengthLessThan3() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(33));
    }
    @Test
    void enterCode_ThreeDifferentFiguresLengthGreaterThan3() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(1234));
    }
    @Test
    void enterCode_ThreeDifferentFiguresValidButWithLeadingZeros() {
        assertThrows(TuringGameException.class, () -> turing.enterCode(001));
    }
    @Test
    void enterCode_ThreeDifferentFiguresValidWithDifferentOrder() throws TuringGameException {
        turing.enterCode(231);
    }
    @Test
    void enterCode_ThreeDifferentFiguresValidWithDifferentOrder2() throws TuringGameException {
        turing.enterCode(513);
    }

    @Test
    void startGame_RandomProblemWhenProblemIdIsMinusOne() throws TuringGameException {
        turing.startGame(-1);
        assertNotNull(turing.problemData());
    }

    @Test
    void startGame_SpecificProblemWhenProblemIdIsOne() throws TuringGameException {
        turing.startGame(1);
        assertEquals(turing.problemData(), turing.showProblem(1).toString());
    }

    @Test
    void startGame_SpecificProblemWhenProblemIdIsFive() throws TuringGameException {
        turing.startGame(5);
        assertEquals(turing.problemData(), turing.showProblem(5).toString());
    }

    @Test
    void startGame_SpecificProblemWhenProblemIdIsTen() throws TuringGameException {
        turing.startGame(10);
        assertEquals(turing.problemData(), turing.showProblem(10).toString());
    }

    @Test
    void startGame_SpecificProblemWhenProblemIdIsFifteen() throws TuringGameException {
        turing.startGame(15);
        assertEquals(turing.problemData(), turing.showProblem(15).toString());
    }

    @Test
    void startGame_RandomProblemWhenProblemIdIsZero() throws TuringGameException {
        assertThrows(TuringGameException.class, () ->        turing.startGame(0));
    }

    @Test
    void startGame_RandomProblemWhenProblemIdIsNegative() throws TuringGameException {
        assertThrows(TuringGameException.class, () -> turing.startGame(-5));
    }

    @Test
    void startGame_RandomProblemWhenProblemIdIsGreaterThanTotalProblems() throws TuringGameException {
        assertThrows(TuringGameException.class, () -> turing.startGame(20));
    }
    @Test
    void startGame_SpecificProblemAfterRandomProblem() throws TuringGameException {
        turing.startGame(-1);
        String randomProblem = turing.problemData();
        turing.startGame(3);
        assertNotEquals(randomProblem, turing.problemData());
    }
    @Test
    void startGame_RandomProblemCheckScoreAndRound() throws TuringGameException {
        turing.startGame(-1);
        assertTrue(turing.getScore() == 0);
        assertTrue(turing.getNbRound() == 0);
    }
    @Test
    void startGame_SpecificProblemCheckScoreAndRound() throws TuringGameException {
        turing.startGame(1);
        assertTrue(turing.getScore() == 0);
        assertTrue(turing.getNbRound() ==0);
    }

    @Test
    void startGame_RandomProblemCheckValidatorsRegistration() throws TuringGameException {
        turing.startGame(-1);
    }

    @Test
    void startGame_SpecificProblemCheckValidatorsRegistration() throws TuringGameException {
        turing.startGame(5);
    }


}