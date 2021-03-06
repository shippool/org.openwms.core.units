/*
 * Copyright 2005-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openwms.core.units.converter;

import org.openwms.core.units.api.BaseUnit;
import org.openwms.core.units.api.Measurable;
import org.openwms.core.units.api.MeasurableString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.openwms.core.units.api.PieceUnit.PC;
import static org.openwms.core.units.api.WeightUnit.G;

/**
 * A Units.
 *
 * @author Heiko Scherrer
 */
public final class Units {

    public static Collection<BaseUnit<?>> getAllUnits() {
        List<BaseUnit<?>> result = new ArrayList<>(G.getAll());
        result.addAll(PC.getAll());
        return result;
    }

    public static Optional<BaseUnit<?>> getUnit(String name) {
        return Units.getAllUnits().stream().filter(f->f.name().equalsIgnoreCase(name)).findFirst();
    }

    public static Optional<Measurable> getMeasurable(String name) {
        MeasurableStringConverter conv = new MeasurableStringConverter();
        Measurable value = null;
        try {
            value = conv.convertFrom(new MeasurableString(name));
        } catch (Exception e) {
            // be fine here and omit MappingExceptions
        }
        return Optional.ofNullable(value);
    }
}
