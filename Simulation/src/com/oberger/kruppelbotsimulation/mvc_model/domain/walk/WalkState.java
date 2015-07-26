/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.Model;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.ILegPolyFunctions;
import com.oberger.kruppelbotsimulation.mvc_model.domain.evaluators.simulationevaluator.legpolyfunctions.LegPolyFunctions;

/**
 *
 * @author ole
 */
public class WalkState {

    private ILegPolyFunctions legFunctions = null;
    private Model model = null;

    public WalkState(ILegPolyFunctions legFunctions, Model model) {
        if (legFunctions == null || model == null) {
            throw new IllegalArgumentException(new NullPointerException("Passing null is not allowed."));
        }
        this.legFunctions = legFunctions;
        this.model = model;
    }

    public ILegPolyFunctions getLegFunctions() {
        return legFunctions;
    }

    public Model getModel() {
        return model;
    }

}
