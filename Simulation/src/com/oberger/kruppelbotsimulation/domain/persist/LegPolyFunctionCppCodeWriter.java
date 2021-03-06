/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.domain.persist;

import com.oberger.kruppelbotsimulation.domain.simulation.LegPosition;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.ConcatPolyFunction;
import com.oberger.kruppelbotsimulation.domain.simulation.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.util.IReadOnlyVector2;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author oberger
 */
public class LegPolyFunctionCppCodeWriter {

    public void write(ILegPolyFunctions legFunctions, String fileName) throws IOException {
	String fileNameBase = getFileNameBase(fileName);
	try (FileWriter writer = new FileWriter(fileName)) {
	    writer.append("#include \"" + fileNameBase + ".h\"\n");
	    writer.append("\n");
	    writeGetLegFunction(legFunctions, LegPosition.BR, writer);
	    writer.append("\n");
	    writeGetLegFunction(legFunctions, LegPosition.BL, writer);
	    writer.append("\n");
	    writeGetLegFunction(legFunctions, LegPosition.FR, writer);
	    writer.append("\n");
	    writeGetLegFunction(legFunctions, LegPosition.FL, writer);

	    writer.flush();
	}
    }
    
    public String getFileNameBase(String fileName) {
	int fileEndingIndex = fileName.lastIndexOf(".cpp");
	if (fileEndingIndex == -1) {
	    fileEndingIndex = fileName.length();
	}
	
	return fileName.substring(0, fileEndingIndex);
    }

    private void writeGetLegFunction(ILegPolyFunctions legFunctions, LegPosition position, FileWriter writer) throws IOException {
	writer.append("WrappedPolyFunction* LegPolyFunctionFactory::create" + position.toString().substring(0, 1).toUpperCase() + position.toString().substring(1, 2).toLowerCase() + "()\n");
	writer.append("{\n");
	writeGetLegFunctionBody(legFunctions, position, writer);
	writer.append("}\n");
    }

    private void writeGetLegFunctionBody(ILegPolyFunctions legFunctions, LegPosition position, FileWriter writer) throws IOException {
	ConcatPolyFunction function = legFunctions.getLegFunction(position);
	writer.append("\tVector2_t* polygons = new Vector2_t[" + function.getPolygons().size() + "];\n");
	List<IReadOnlyVector2> polygons = function.getPolygons();
	for (int i = 0; i < polygons.size(); i++) {
	    IReadOnlyVector2 polygon = polygons.get(i);
	    writer.append("\t\n");
	    writer.append("\tpolygons[" + i + "].x = " + (int) (polygon.getX() * 1000) + ";\n");
	    writer.append("\tpolygons[" + i + "].y = " + (int) polygon.getY() + ";\n");
	}
	writer.append("\t\n");
	writer.append("\treturn new WrappedPolyFunction(polygons, " + polygons.size() + ", " + (int) (function.getPeriod() * 1000) + ", " + (int) (function.getOffsetX() * 1000) + ");\n");
    }

}
