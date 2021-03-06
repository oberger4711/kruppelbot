/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch;

import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.ExitCriterium;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 *
 * @author ole
 */
public class HillClimbingTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private HillClimbing createHillClimbing(ExitCriterium exitCriterium) {
	return new HillClimbing(exitCriterium);
    }

    private List<State> createStateList(State... states) {
	return Arrays.asList(states);
    }

    private State createFakeNeighbour(float score) {
	State fakeNeighbour = Mockito.mock(State.class);
	Mockito.doReturn(score).when(fakeNeighbour).getScore();

	return fakeNeighbour;
    }

    @Test
    public void getNextState_OnPassNull_ThrowsIllegalArgumentException() {
	HillClimbing hillClimbing = createHillClimbing(Mockito.mock(ExitCriterium.class));

	exception.expect(IllegalArgumentException.class);

	hillClimbing.getNextState(null);
    }

    @Test
    public void getNextState_OnPassEmptyList_ReturnsNull() {
	HillClimbing hillClimbing = createHillClimbing(Mockito.mock(ExitCriterium.class));

	State resultNextState = hillClimbing.getNextState(Collections.<State>emptyList());

	assertNull(resultNextState);
    }

    @Test
    public void getNextState_OnPassTwoStates_ReturnsStateWithHigherScore() {
	HillClimbing hillClimbing = createHillClimbing(Mockito.mock(ExitCriterium.class));
	State fakeNeighbourLowScore = createFakeNeighbour(0.3f);
	State fakeNeighbourHighScore = createFakeNeighbour(0.8f);
	List<State> fakeNeighbours = createStateList(fakeNeighbourLowScore, fakeNeighbourHighScore);

	State resultNextState = hillClimbing.getNextState(fakeNeighbours);

	assertEquals(fakeNeighbourHighScore, resultNextState);
    }

}
