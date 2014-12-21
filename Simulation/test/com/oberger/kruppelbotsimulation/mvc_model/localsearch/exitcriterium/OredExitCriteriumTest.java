/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.localsearch.exitcriterium;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class OredExitCriteriumTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private OredExitCriterium createOredExitCriterium(ExitCriterium firstExitCriterium, ExitCriterium secondExitCriterium) {
        return new OredExitCriterium(firstExitCriterium, secondExitCriterium);
    }

    private ExitCriterium createFakeExitCriterium(State parameterState, boolean isFinishStateReturnValue) {
        ExitCriterium fakeExitCriterium = Mockito.mock(ExitCriterium.class);
        Mockito.doReturn(isFinishStateReturnValue).when(fakeExitCriterium).isFinishState(parameterState);

        return fakeExitCriterium;
    }

    private State createDummyState() {
        return Mockito.mock(State.class);
    }

    @Test
    public void constructor_OnPassFirstExitCriteriumNull_ThrowsIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);

        createOredExitCriterium(null, createFakeExitCriterium(null, true));
    }

    @Test
    public void constructor_OnPassSecondExitCriteriumNull_ThrowsIllegalParameterException() {
        exception.expect(IllegalArgumentException.class);

        createOredExitCriterium(createFakeExitCriterium(null, true), null);
    }

    @Test
    public void isFinishState_OnPassNull_ThrowsIllegalParameterException() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);
        ExitCriterium decoratorCriterium = createOredExitCriterium(fakeExitCriterium1, fakeExitCriterium2);
        
        exception.expect(IllegalArgumentException.class);

        decoratorCriterium.isFinishState(null);
    }

    @Test
    public void isFinishState_OnDecoratedReturnBothTrue_DecoratorReturnsTrue() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);

        ExitCriterium decoratorCriterium = createOredExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        assertTrue(decoratorCriterium.isFinishState(fakeState));
    }

    @Test
    public void isFinishState_OnOneDecoratedReturnsFalse_DecoratorReturnsFalse() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, false);

        ExitCriterium decoratorCriterium = createOredExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        assertFalse(decoratorCriterium.isFinishState(fakeState));
    }

    @Test
    public void isFinishState_OnCallResetOnDecorator_DecoratorCallsResetOnDecorated() {
        State fakeState = createDummyState();
        ExitCriterium fakeExitCriterium1 = createFakeExitCriterium(fakeState, false);
        ExitCriterium fakeExitCriterium2 = createFakeExitCriterium(fakeState, true);

        ExitCriterium decoratorCriterium = createOredExitCriterium(fakeExitCriterium1, fakeExitCriterium2);

        decoratorCriterium.reset();

        Mockito.verify(fakeExitCriterium1, Mockito.times(1)).reset();
        Mockito.verify(fakeExitCriterium2, Mockito.times(1)).reset();
    }

}