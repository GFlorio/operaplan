package com.gabrielflorio.operaplan;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void solve() {
        SolverFactory<TaskAssigningSolution> solverFactory = SolverFactory.createFromXmlResource(
                "com/gabrielflorio/operaplan/solverConfig.xml");
        Solver<TaskAssigningSolution> solver = solverFactory.buildSolver();
    }
}