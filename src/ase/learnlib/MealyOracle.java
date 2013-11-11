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

import de.learnlib.api.MembershipOracle;
import de.learnlib.api.Query;
import de.learnlib.api.SUL;
import de.learnlib.drivers.reflect.AbstractMethodInput;
import de.learnlib.drivers.reflect.AbstractMethodOutput;
import de.learnlib.mealy.MealyUtil;
import de.learnlib.oracles.SULOracle;
import java.util.Collection;

/**
 * A customized oracle for the tutorial.
 * 
 * @author falk
 */
public class MealyOracle implements 
        MembershipOracle<AbstractMethodInput, AbstractMethodOutput> {
    
    /**
     * actual oracle wrapped in abstraction
     */
    private final MembershipOracle<AbstractMethodInput, AbstractMethodOutput> wrapper;
   
    /**
     * takes a SUL and wraps it into an oracle that produces one output symbol
     * for a sequence of inputs.
     * 
     * @param back 
     */
    public MealyOracle(SUL<AbstractMethodInput, AbstractMethodOutput> back) {
        this.wrapper = MealyUtil.wrapWordOracle(new SULOracle<>(back));
    }

    @Override
    public void processQueries(Collection<? extends Query<AbstractMethodInput, AbstractMethodOutput>> clctn) {
        wrapper.processQueries(clctn);
    }
}
