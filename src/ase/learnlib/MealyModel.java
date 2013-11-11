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
package ase.learnlib;

import de.learnlib.drivers.reflect.AbstractMethodInput;
import de.learnlib.drivers.reflect.AbstractMethodOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.automatalib.automata.concepts.StateIDs;
import net.automatalib.automata.transout.MealyMachine;
import net.automatalib.commons.util.mappings.MutableMapping;
import net.automatalib.ts.DeterministicTransitionSystem;
import net.automatalib.words.Word;

/**
 * Concrete Mealy version for the tutorial.
 * 
 * @author falk
 */
public class MealyModel implements MealyMachine<Object, AbstractMethodInput, Object, AbstractMethodOutput> {
    
    private final MealyMachine<Object, AbstractMethodInput, Object, AbstractMethodOutput> back;

    public MealyModel(MealyMachine<?, AbstractMethodInput, ?, AbstractMethodOutput> back) {
        this.back = (MealyMachine<Object, AbstractMethodInput, Object, AbstractMethodOutput>) back;
    }

    @Override
    public Collection<Object> getTransitions(Object s, AbstractMethodInput i) {
        return back.getTransitions(s, i);
    }

    @Override
    public Object getSuccessor(Object t) {
        return back.getSuccessor(t);
    }

    @Override
    public DeterministicTransitionSystem<? extends Set<Object>, AbstractMethodInput, ? extends Collection<Object>> powersetView() {
        return back.powersetView();
    }

    @Override
    public Set<Object> getInitialStates() {
        return back.getInitialStates();
    }

    @Override
    public Set<Object> getSuccessors(Object s, AbstractMethodInput i) {
        return back.getSuccessors(s, i);
    }

    @Override
    public Set<Object> getSuccessors(Object s, Iterable<AbstractMethodInput> itrbl) {
        return back.getSuccessors(s, itrbl);
    }

    @Override
    public Set<Object> getSuccessors(Collection<Object> clctn, Iterable<AbstractMethodInput> itrbl) {
        return back.getSuccessors(clctn, itrbl);
    }

    @Override
    public Set<Object> getStates(Iterable<AbstractMethodInput> itrbl) {
        return back.getStates(itrbl);
    }

    @Override
    public <V> MutableMapping<Object, V> createStaticStateMapping() {
        return back.createStaticStateMapping();
    }

    @Override
    public <V> MutableMapping<Object, V> createDynamicStateMapping() {
        return back.createDynamicStateMapping();
    }

    @Override
    public Collection<Object> getStates() {
        return back.getStates();
    }

    @Override
    public int size() {
        return back.size();
    }

    @Override
    public StateIDs<Object> stateIDs() {
        return back.stateIDs();
    }

    @Override
    public Iterator<Object> iterator() {
        return back.iterator();
    }

    @Override
    public Object getInitialState() {
        return back.getInitialState();
    }

    @Override
    public Object getSuccessor(Object s, AbstractMethodInput i) {
        return back.getSuccessor(s,i);
    }

    @Override
    public Object getSuccessor(Object s, Iterable<AbstractMethodInput> itrbl) {
        return back.getSuccessor(s, itrbl);
    }

    @Override
    public Object getState(Iterable<AbstractMethodInput> itrbl) {
        return back.getState(itrbl);
    }

    @Override
    public Object getTransition(Object s, AbstractMethodInput i) {
        return back.getTransition(s, i);
    }

    @Override
    public Void getStateProperty(Object s) {
        return back.getStateProperty(s);
    }

    @Override
    public AbstractMethodOutput getTransitionProperty(Object t) {
        return back.getTransitionProperty(t);
    }

    @Override
    public AbstractMethodOutput getOutput(Object s, AbstractMethodInput i) {
        return back.getOutput(s, i);
    }

    @Override
    public void trace(Iterable<AbstractMethodInput> itrbl, List<AbstractMethodOutput> list) {
        back.trace(itrbl, list);
    }

    @Override
    public void trace(Object s, Iterable<AbstractMethodInput> itrbl, List<AbstractMethodOutput> list) {
        back.trace(s, itrbl, list);
    }

    @Override
    public Word<AbstractMethodOutput> computeOutput(Iterable<AbstractMethodInput> itrbl) {
        return back.computeOutput(itrbl);
    }

    @Override
    public Word<AbstractMethodOutput> computeSuffixOutput(Iterable<AbstractMethodInput> itrbl, Iterable<AbstractMethodInput> itrbl1) {
        return back.computeSuffixOutput(itrbl, itrbl1);
    }

    @Override
    public AbstractMethodOutput getTransitionOutput(Object t) {
        return back.getTransitionOutput(t);
    }
    
    
}
