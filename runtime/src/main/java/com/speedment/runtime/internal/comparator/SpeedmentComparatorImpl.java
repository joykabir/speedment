/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.runtime.internal.comparator;

import com.speedment.runtime.field.trait.FieldTrait;
import com.speedment.runtime.field.trait.ReferenceFieldTrait;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 *
 * @author pemi
 * @param <ENTITY> entity type
 * @param <V> value type
 */
public class SpeedmentComparatorImpl<ENTITY, D, V extends Comparable<? super V>> implements SpeedmentComparator<ENTITY, V> {

    private final FieldTrait field;
    private final ReferenceFieldTrait<ENTITY, D, V> referenceField;
    private final NullOrder nullOrder;
    private boolean reversed;

    public SpeedmentComparatorImpl(FieldTrait field, ReferenceFieldTrait<ENTITY, D, V> referenceField, NullOrder nullOrder) {
        this.field = field;
        this.referenceField = referenceField;
        this.nullOrder = nullOrder;
    }

    @Override
    public FieldTrait getField() {
        return field;
    }

    @Override
    public boolean isReversed() {
        return reversed;
    }

    @Override
    public Comparator<ENTITY> reversed() {
        reversed = !reversed;
        return this;
    }

    @Override
    public int compare(ENTITY o1, ENTITY o2) {
        final V o1Value = referenceField.getter().apply(requireNonNull(o1));
        final V o2Value = referenceField.getter().apply(requireNonNull(o2));
        if (o1Value == null && o2Value == null) {
            if (NullOrder.NONE == nullOrder) {
                throw new NullPointerException("Both fields were null and null fields not allowed");
            }
            return 0;
        }
        if (o1Value == null) {
            return forNull(Parameter.FIRST);
        }
        if (o2Value == null) {
            return forNull(Parameter.SECOND);
        }
        return applyReversed(o1Value.compareTo(o2Value));
    }

    private enum Parameter {
        FIRST, SECOND;
    }

    private int forNull(Parameter parameter) {
        final int firstOutcome = Parameter.FIRST.equals(parameter) ? -1 : 1;
        final int lastOutcome = -firstOutcome;
        switch (nullOrder) {
            case NONE:
                throw new NullPointerException("A field was null and null fields not allowed");
            case FIRST:
                return applyReversed(firstOutcome);
            case LAST:
                return applyReversed(lastOutcome);
            default:
                throw new IllegalStateException("Illegal NullOrder");
        }
    }

    private int applyReversed(int compare) {
        if (!reversed) {
            return compare;
        }
        if (compare == 0) {
            return 0;
        } else if (compare > 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
