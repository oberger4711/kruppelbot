package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.io.Serializable;
import java.util.List;

/**
 * A decorator that wraps an {@link IPolyFunction} onto a given period (starting
 * at x = 0). {@link #getValue(float) } will return getValue( {@link #wrapOntoPeriod(float)}(x + offsetX)).
 *
 * @author ole
 */
public class WrappedPolyFunction implements IPolyFunction, Serializable {

    private IPolyFunction decorated = null;
    private float period;
    private float offsetX;
    
    public WrappedPolyFunction(IPolyFunction decorated, float offsetX) {
	this(decorated, decorated.getPolygons().get(decorated.getPolygons().size() - 1).getX(), offsetX);
    }

    public WrappedPolyFunction(IPolyFunction decorated, float period, float offsetX) {
	if (decorated == null) {
	    throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
	}
	if (period <= 0) {
	    throw new IllegalArgumentException("Period must be higher than zero but was " + period);
	}
	this.decorated = decorated;
	this.period = period;
	this.offsetX = offsetX;
    }

    @Override
    public float getValue(float x) {
	float mappedX = wrapOntoPeriod(x);

	return decorated.getValue(mappedX);
    }

    protected float wrapOntoPeriod(float x) {
	float mappedX = x + offsetX;

	while (mappedX < 0) {
	    mappedX += period;
	}
	mappedX %= period;

	return mappedX;
    }

    @Override
    public List<IReadOnlyVector2> getPolygons() {
	return decorated.getPolygons();
    }

    public float getPeriod() {
	return period;
    }

    public float getOffsetX() {
	return offsetX;
    }

    @Override
    public Interpolator getInterpolator() {
	return decorated.getInterpolator();
    }

}
