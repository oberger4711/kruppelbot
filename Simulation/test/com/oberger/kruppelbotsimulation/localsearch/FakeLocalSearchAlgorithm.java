/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.localsearch;

import com.oberger.kruppelbotsimulation.localsearch.exitcriterium.ExitCriterium;
import java.util.List;

/**
 * Fake {@link LocalSearchAlgorithm} that always returns the first neighbour of
 * the current state's neighbours or null if no.
 *
 * @author ole
 * @param <T>
 */
public class FakeLocalSearchAlgorithm<T> extends LocalSearchAlgorithm<T> {

    public FakeLocalSearchAlgorithm(ExitCriterium exitCriterium) {
	super(exitCriterium);
    }

    @Override
    State<T> getNextState(List<State<T>> neighbourStates) {
	State<T> nextState = null;
	if (neighbourStates != null && !neighbourStates.isEmpty()) {
	    nextState = neighbourStates.get(0);
	}

	return nextState;
    }

}
