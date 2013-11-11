package ase.example1;


import de.learnlib.drivers.reflect.AbstractMethodInput;
import de.learnlib.drivers.reflect.AbstractMethodOutput;
import de.learnlib.drivers.reflect.SimplePOJOTestDriver;
import de.learnlib.oracles.DefaultQuery;
import de.learnlib.statistics.SimpleProfiler;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Random;
import net.automatalib.commons.dotutil.DOT;
import net.automatalib.util.graphs.dot.GraphDOT;
import ase.learnlib.MealyModel;
import ase.learnlib.MealyExperiment;
import ase.learnlib.MealyRandomWalk;
import ase.learnlib.MealyLStar;
import ase.learnlib.MealyOracle;

/*
 * Copyright (C) 2013 falk.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

/**
 *
 * @author falk
 */
public class Example {
  
    public static void main(String[] ags) throws NoSuchMethodException, IOException {
        
        // create learning alphabet
        Method mOffer = BoundedStringQueue.class.getMethod(
                "offer", new Class<?>[]{String.class});
        Method mPoll = BoundedStringQueue.class.getMethod(
                "poll", new Class<?>[]{});
                
        // instantiate test driver
        SimplePOJOTestDriver driver = new SimplePOJOTestDriver(
                Queue1.class.getConstructor());

        // offer
        driver.addInput("offer_a", mOffer, "a");
        driver.addInput("offer_b", mOffer, "b");

        // poll
        driver.addInput("poll", mPoll);
        
        // create oracle for membership queries
        MealyOracle mqOracle = new MealyOracle(driver);
       
        // create lstar
        MealyLStar lstar = new MealyLStar(driver.getInputs(), mqOracle);
        
        // create eq test        
        MealyRandomWalk eqtest = new MealyRandomWalk( 
                0.05, // reset SUL w/ this probability before a step 
                10000, // max steps (overall)
                new Random(0), // random source
                driver // system under learning
                );          
        
        // create an experiment
        MealyExperiment experiment = 
                new MealyExperiment(lstar, eqtest, driver.getInputs());
        

        // turn on time profiling
        experiment.setProfile(true);

        // enable logging of models
        experiment.setLogModels(true);

        // run experiment
        experiment.run();

        // get learned model
        MealyModel result = experiment.getFinalHypothesis();

        // report results
        System.out.println("-------------------------------------------------------");

        // profiling
        System.out.println(SimpleProfiler.getResults());

        // learning statistics
        System.out.println(experiment.getRounds().getSummary());

        // model statistics
        System.out.println("States: " + result.size());
        System.out.println("Sigma: " + driver.getInputs().size());

        // show model
        System.out.println();
        System.out.println("Model: ");
        
        GraphDOT.write(result, driver.getInputs(), System.out); // may throw IOException!
        Writer w = DOT.createDotWriter(true);
        GraphDOT.write(result, driver.getInputs(), w);
        w.close();

        System.out.println("-------------------------------------------------------");
        
        
        SimplePOJOTestDriver driver2 = new SimplePOJOTestDriver(
                Queue2.class.getConstructor());
        
        MealyRandomWalk eqtest2 = new MealyRandomWalk( 
                0.4, // reset SUL w/ this probability before a step 
                10000, // max steps (overall)
                new Random(0), // random source
                driver2 // system under learning
                );
        
        DefaultQuery<AbstractMethodInput, AbstractMethodOutput> ce =
                eqtest2.findCounterExample(result, driver.getInputs());
        
        System.out.println("Diff: " + ce.getInput());
    }
    
}
