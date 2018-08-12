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

import java.util.LinkedList;
import java.util.List;

class FunctionalBufferObjectProperty<T> extends FunctionalObjectProperty<List<T>> {

    private List<T> buffer;
    private List<List<T>> buffers;
    private long valueCount;

    public FunctionalBufferObjectProperty(ObservableValue<T> parent, int count) {
        super(parent);
        buffer = new LinkedList<>();
        ObservableValues.addSafeValueListener(parent, newValue -> {
            buffer.add(newValue);
            if (buffer.size() == count) {
                set(buffer);
                buffer = new LinkedList<>();
            }
        });
    }

    public FunctionalBufferObjectProperty(ObservableValue<T> parent, int count, int skip) {
        super(parent);
        buffers = new LinkedList<>();
        ObservableValues.addSafeValueListener(parent, newValue -> {
            if (valueCount % skip == 0) {
                buffers.add(new LinkedList<>());
            }
            valueCount++;

            if (!buffers.isEmpty()) {
                buffers.forEach(buffer -> buffer.add(newValue));
                if (buffers.get(0).size() == count) {
                    set(buffers.remove(0));
                }
            }
        });
    }
}
