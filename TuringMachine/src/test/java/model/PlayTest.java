package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayTest{
    // pas de test pour methodes : getValidatorId getValidator getValidatorsNb
    // car ne font qu'appeler methodes déja testées dans Problem
    Problem problem;
    Play play;
    public PlayTest() throws TuringGameException {
        problem = new Problem(0,5,7,821, new int[]{2, 4, 3, 7});
        this.play = new Play(problem);

    }
    @Test
    void nextRound_AfterOneValidatorTestedCorrect() throws TuringGameException {
        var round = new Round(236);
        play.curRound = round;
        play.testValidator(0);
        play.nextRound();
        assertFalse(problem.getValidator(0).isTested());
    }
    @Test
    void nextRound_AfterTwoValidatorsTestedCorrect() throws TuringGameException {
        var round = new Round(236);
        play.curRound = round;
        play.testValidator(0);
        play.testValidator(1);
        play.nextRound();
        assertFalse(problem.getValidator(0).isTested());
        assertFalse(problem.getValidator(1).isTested());
    }
    @Test
    void nextRound_AfterThreeValidatorsTestedCorrect() throws TuringGameException {
        var round = new Round(236);
        play.curRound = round;
        play.testValidator(0);
        play.testValidator(1);
        play.testValidator(2);
        play.nextRound();
        assertFalse(problem.getValidator(0).isTested());
        assertFalse(problem.getValidator(1).isTested());
        assertFalse(problem.getValidator(2).isTested());
    }
    @Test
    void previousRound_AfterTwoValidatorsTestedCorrect() throws TuringGameException {
        play.enterCode(236);
        play.testValidator(0);
        play.testValidator(1);
        play.nextRound();
        play.previousRound();
        play.testValidator(2);
        assertEquals(3,play.getScore());
        assertEquals(1,play.getNbRound());
    }
    @Test
    void newParty_noProblem() {
        assertThrows(TuringGameException.class, () -> new Play(null));
    }
    @Test
    void enterCode_noEnteredCode() throws TuringGameException {
        play.enterCode(0);
    }
    @Test
    void guessCode_noCurRound(){
        assertThrows(TuringGameException.class, () -> play.guessCode());
    }
    @Test
    void enterCode_noCurRound() throws TuringGameException {
        var beforeEnter = play.getNbRound();
        play.enterCode(132);
        assertEquals(beforeEnter+1, play.getNbRound());
    }
    @Test
    void enterCode_twoCodesSameRound(){
        var round = new Round(236);
        play.curRound = round;
        assertThrows(TuringGameException.class, () -> play.enterCode(123));
    }
    @Test
    public void getNbRound_AtBeginningOfAPlay() {
        assertEquals(0, play.getNbRound());
    }
    @Test
    public void getNbScore_AtBeginningOfAPlay() {
        assertEquals(0, play.getScore());
    }
    @Test
    public void getNbRoundTwoRounds() throws TuringGameException {
        play.enterCode(123);
        play.nextRound();
        play.enterCode(455);
        assertEquals(2, play.getNbRound());
    }
    @Test
    public void scoreAfterOneValidatorTested() throws TuringGameException {
        play.enterCode(123);
        play.testValidator(2);
        assertEquals(1, play.getScore());
    }
    @Test
    public void scoreAfterTwoValidatorTested() throws TuringGameException {
        play.enterCode(123);
        play.testValidator(0);
        play.testValidator(1);
        assertEquals(2, play.getScore());
    }
    @Test
    public void unTestAValidator() throws TuringGameException {
        play.enterCode(123);
        play.curRound.validatorResult(problem.getValidator(0));
        play.unTestValidator(0);
        assertFalse(problem.getValidator(0).isTested());
    }
}