/*
 *
 *    __              _   _               _      __
 *   / _|_  _ _ _  __| |_(_)___ _ _  __ _| |___ / _|_ __
 *  |  _| || | ' \/ _|  _| / _ \ ' \/ _` | |___|  _\ \ /
 *  |_|  \_,_|_||_\__|\__|_\___/_||_\__,_|_|   |_| /_\_\
 *  *
 *  * Copyright 2018 Functional-FX, https://github.com/Aendawyn/FunctionalFX
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.functionalfx.property;

import javafx.beans.value.ObservableValue;

import java.util.function.BiFunction;

class FunctionalScanObjectProperty<T, R> extends FunctionalObjectProperty<R> {

    private static final Object NO_VALUE = new Object();

    private R currentValue;

    @SuppressWarnings("unchecked")
    public FunctionalScanObjectProperty(ObservableValue<T> parent, BiFunction<R, T, R> accumulator) {
        this(parent, (R) NO_VALUE, accumulator);
    }

    @SuppressWarnings("unchecked")
    public FunctionalScanObjectProperty(ObservableValue<T> parent, R initialValue, BiFunction<R, T, R> accumulator) {
        super(parent);
        currentValue = initialValue;
        ObservableValues.addSafeValueListener(parent, newValue -> {
            if (NO_VALUE.equals(currentValue)) {
                currentValue = (R) newValue;
                set(currentValue);
            } else {
                currentValue = accumulator.apply(currentValue, newValue);
                set(currentValue);
            }
        });
    }
}
