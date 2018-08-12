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

import java.util.function.Function;

class FunctionalMapObjectProperty<R> extends FunctionalObjectProperty<R> {

    public <T> FunctionalMapObjectProperty(ObservableValue<T> parent, Function<T, R> mapper) {
        super(parent);
        Runnable action = () -> {
            if (ObservableValues.hasValue(parent)) {
                set(mapper.apply(parent.getValue()));
            }
        };
        action.run();
        parent.addListener((observable, oldValue, newValue) -> action.run());
    }
}
