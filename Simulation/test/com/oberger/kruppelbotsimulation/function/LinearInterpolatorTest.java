/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.function;

import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import com.oberger.kruppelbotsimulation.util.Vector2;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinearInterpolatorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private LinearInterpolator createLinearInterpolator() {
	return new LinearInterpolator();
    }

    @Test
    public void getPolygons_OnPassNumberOfPolygonsThree_ReturnsStartAndInterpolatedAndEnd() {
	LinearInterpolator testee = createLinearInterpolator();
	Vector2 start = new Vector2(1, 1);
	Vector2 end = new Vector2(3, 3);

	List<Vector2> returnedPolygons = testee.getPolygons(start, end, 3);

	List<Vector2> expectedPolygons = Arrays.asList(start, new Vector2(2f, 2f), end);
	assertEquals(expectedPolygons, returnedPolygons);
    }
    
    @Test
    public void getPolygons_OnPassNumberOfPolygonsFour_ReturnsStartAndInterpolatedAndEnd() {
	LinearInterpolator testee = createLinearInterpolator();
	Vector2 start = new Vector2(4, 4);
	Vector2 end = new Vector2(1, 1);

	List<Vector2> returnedPolygons = testee.getPolygons(start, end, 4);

	List<Vector2> expectedPolygons = Arrays.asList(start, new Vector2(3f, 3f), new Vector2(2f, 2f), end);
	assertEquals(expectedPolygons, returnedPolygons);
    }

    @Test
    public void getValue_OnPassThreePointsWithXInsideInterval_ReturnsInterpolated() {
	LinearInterpolator testee = createLinearInterpolator();
	List<IReadOnlyVector2> polygons = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0));

	float returnedValue = testee.getValue(polygons, 0.5f);

	assertEquals(0.5f, returnedValue, 0.0001);
    }

    @Test
    public void getValue_OnPassTwoPointsWithXBeforeInterval_ReturnsInterpolated() {
	LinearInterpolator testee = createLinearInterpolator();
	List<IReadOnlyVector2> polygons = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0));

	float returnedValue = testee.getValue(polygons, -0.5f);

	assertEquals(-0.5f, returnedValue, 0.0001);
    }

    @Test
    public void getValue_OnPassTwoPointsWithXAfterInterval_ReturnsInterpolated() {
	LinearInterpolator testee = createLinearInterpolator();
	List<IReadOnlyVector2> polygons = Arrays.asList(new Vector2(0, 0), new Vector2(1, 1), new Vector2(2, 0));

	float returnedValue = testee.getValue(polygons, 2.5f);

	assertEquals(-0.5f, returnedValue, 0.0001);
    }

}
