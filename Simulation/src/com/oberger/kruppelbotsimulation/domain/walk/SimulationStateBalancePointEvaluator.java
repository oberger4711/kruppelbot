package com.oberger.kruppelbotsimulation.domain.walk;

import com.oberger.kruppelbotsimulation.domain.simulation.ISimulationState;
import com.oberger.kruppelbotsimulation.domain.simulation.LegPosition;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.EBalanceMode;
import com.oberger.kruppelbotsimulation.function.Interpolator;
import com.oberger.kruppelbotsimulation.function.LinearInterpolator;
import com.oberger.kruppelbotsimulation.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.model.BalancePoint;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;

public class SimulationStateBalancePointEvaluator implements IEvaluator<ISimulationState> {

    private LegPosition criticalLegPosition = null;
    private Interpolator scoreInterpolator = null;

    public SimulationStateBalancePointEvaluator(LegPosition criticalLegPosition) {
	if (criticalLegPosition == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	this.criticalLegPosition = criticalLegPosition;
	scoreInterpolator = new LinearInterpolator();
    }

    @Override
    public float getScore(ISimulationState simulationState) {
	if (simulationState == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	float score;

	if (EBalanceMode.CRITICAL.equals(simulationState.getBalanceMode(criticalLegPosition))) {
	    BalancePoint rootBalancePoint = simulationState.getModel().getRoot().getGlobalBalancePoint();
	    Vector2 bestBalancePointPosition = null;
	    switch (criticalLegPosition) {
		case BL:
		    bestBalancePointPosition = simulationState.getModel().getServoFR().getGlobalPosition();
		    break;
		case BR:
		    bestBalancePointPosition = simulationState.getModel().getServoFL().getGlobalPosition();
		    break;
		case FL:
		    bestBalancePointPosition = simulationState.getModel().getServoBR().getGlobalPosition();
		    break;
		case FR:
		    bestBalancePointPosition = simulationState.getModel().getServoBL().getGlobalPosition();
		    break;
	    }

	    if (!isInSameQuadrant(bestBalancePointPosition, rootBalancePoint.getPosition())) {
		score = 0f;
	    } else {
		score = interpolateScoreFromDistance(bestBalancePointPosition, rootBalancePoint.getPosition());
	    }
	} else {
	    score = 0;
	}

	return score;
    }

    private boolean isInSameQuadrant(Vector2 v1, Vector2 v2) {
	boolean sameXQuadrant = Math.signum(v1.getX()) == Math.signum(v2.getX());
	boolean sameYQuadrant = Math.signum(v1.getY()) == Math.signum(v2.getY());

	return sameXQuadrant && sameYQuadrant;
    }

    private float interpolateScoreFromDistance(Vector2 bestBalancePointPosition, Vector2 actualBalancePointPosition) {
	Vector2 secondPolygon = new Vector2(bestBalancePointPosition.getLength(), 0);
	Vector2 diffVector = new Vector2(bestBalancePointPosition).subtract(actualBalancePointPosition);

	return scoreInterpolator.getValue(Arrays.asList(new Vector2(0, 1), secondPolygon), diffVector.getLength());
    }

}
