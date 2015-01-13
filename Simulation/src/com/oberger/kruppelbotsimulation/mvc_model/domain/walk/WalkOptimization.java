package com.oberger.kruppelbotsimulation.mvc_model.domain.walk;

import com.oberger.kruppelbotsimulation.mvc_model.localsearch.HillClimbing;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.LocalSearchAlgorithm;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.State;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.evaluator.IEvaluator;
import com.oberger.kruppelbotsimulation.mvc_model.localsearch.manipulator.IManipulator;

public class WalkOptimization {
    
    public static void main(String[] args) {
        WalkState initInnerState = null; // TODO
        IEvaluator<WalkState> evaluator = null; // TODO
        IManipulator<WalkState> manipulator = null; // TODO
        State<WalkState> initState = new State<>(initInnerState, evaluator, manipulator);
        LocalSearchAlgorithm<WalkState> localSearchAlgo = new HillClimbing<>();
        
        System.out.println("Done.");
    }
    
}