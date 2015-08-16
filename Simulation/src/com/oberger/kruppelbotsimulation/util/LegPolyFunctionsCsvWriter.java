/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.util;

import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.LegPosition;
import com.oberger.kruppelbotsimulation.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionsCsvWriter {

    public void write(ILegPolyFunctions legFunctions, String filename) {
	try (FileWriter writer = new FileWriter(filename + ".csv")) {

	    for (LegPosition position : LegPosition.values()) {
		List<IReadOnlyVector2> polygons = legFunctions.getLegFunction(position).getPolygons();
		
		writer.append(position.toString() + "\n");
		writer.append("x,y\n");
		
		for (IReadOnlyVector2 polygon : polygons) {
		    writer.append(Float.toString(polygon.getX()));
		    writer.append(",");
		    writer.append(Float.toString(polygon.getY()));
		    writer.append('\n');
		}
	    }

	    writer.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
