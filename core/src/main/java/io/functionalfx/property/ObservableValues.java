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

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class ObservableValues {

    private ObservableValues() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    public static <T> boolean isValidValue(T value) {
        return Objects.nonNull(value);
    }

    public static <T extends ObservableValue> boolean hasValue(T observableValue) {
        return isValidValue(observableValue.getValue());
    }

    public static <T extends ObservableValue> boolean haveValues(Collection<T> observableValues) {
        return observableValues.stream().allMatch(ObservableValues::hasValue);
    }

    public static <T> List<T> getValues(Collection<? extends ObservableValue<? extends T>> observableValue) {
        return observableValue.stream() //
                .map(ObservableValue::getValue) //
                .collect(Collectors.toList());
    }

    public static <T> void addSafeValueListener(final ObservableValue<T> observable, final Consumer<T> onValueChanged) {
        final Consumer<T> onSafeValueChanged = onSafeValueChanged(onValueChanged);
        onSafeValueChanged.accept(observable.getValue());
        observable.addListener((obs, oldValue, newValue) -> onSafeValueChanged.accept(newValue));
    }

    private static <T> Consumer<T> onSafeValueChanged(Consumer<T> onValueChanged) {
        return newValue -> {
            if (isValidValue(newValue)) {
                onValueChanged.accept(newValue);
            }
        };
    }
}
