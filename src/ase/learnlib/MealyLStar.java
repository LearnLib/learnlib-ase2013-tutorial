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

import de.learnlib.algorithms.lstargeneric.ce.ObservationTableCEXHandlers;
import de.learnlib.algorithms.lstargeneric.closing.ClosingStrategies;
import de.learnlib.algorithms.lstargeneric.mealy.ClassicLStarMealy;
import de.learnlib.api.LearningAlgorithm;
import de.learnlib.drivers.reflect.AbstractMethodInput;
import de.learnlib.drivers.reflect.AbstractMethodOutput;
import de.learnlib.oracles.DefaultQuery;
import java.util.ArrayList;
import java.util.List;
import net.automatalib.words.Alphabet;
import net.automatalib.words.Word;

/**
 * Concrete instance of generic mealy LStar for reflection alphabets. 
 * 
 * @author falk
 */
public class MealyLStar implements LearningAlgorithm<MealyModel, AbstractMethodInput, AbstractMethodOutput> {

    /** 
     * actual implementation
     */
    private final ClassicLStarMealy<AbstractMethodInput, AbstractMethodOutput> lstar; 
    
    /**
     * 
     * @param alphabet inputs 
     * @param oracle membership oracle
     */
    public MealyLStar(Alphabet<AbstractMethodInput> alphabet, MealyOracle oracle) {
        this.lstar = new ClassicLStarMealy<>(
              alphabet,
              oracle, 
              asSuffixes(alphabet),
              ObservationTableCEXHandlers.RIVEST_SCHAPIRE,
              ClosingStrategies.CLOSE_FIRST);
    }


    @Override
    public void startLearning() {
        this.lstar.startLearning();
    }

    @Override
    public boolean refineHypothesis(DefaultQuery<AbstractMethodInput, AbstractMethodOutput> dq) {
        return this.lstar.refineHypothesis(dq);
    }

    @Override
    public MealyModel getHypothesisModel() {
        return new MealyModel( this.lstar.getHypothesisModel());
    }
    
    /**
     * creates suffixes from alphabet symbols
     * 
     * @param sigma alphabet
     * @return list of words (suffixes)
     */
    private static List<Word<AbstractMethodInput>> asSuffixes(
            Alphabet<AbstractMethodInput> sigma) {

        List<Word<AbstractMethodInput>> suffixes = new ArrayList<>();
        for (AbstractMethodInput s : sigma) {
            suffixes.add(Word.fromLetter(s));
        }
        return suffixes;
    }

}
