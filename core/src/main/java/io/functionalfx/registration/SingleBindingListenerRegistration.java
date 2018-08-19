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
package io.functionalfx.registration;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

class SingleBindingListenerRegistration<T> implements ListenerRegistration {

    private WritableValue<T> writableValue;
    private ObservableValue<T> observableValue;
    private ChangeListener<T> changeListener;
    private boolean isUnregistered;

    public SingleBindingListenerRegistration(WritableValue<T> writableValue, ObservableValue<T> observableValue) {
        this.writableValue = writableValue;
        this.observableValue = observableValue;
        this.changeListener = (observable, oldValue, newValue) -> this.writableValue.setValue(newValue);
        this.isUnregistered = false;
        this.observableValue.addListener(this.changeListener);
    }

    @Override
    public boolean isUnregistered() {
        return isUnregistered;
    }

    @Override
    public void unregister() {
        if (!isUnregistered) {
            isUnregistered = true;
            observableValue.removeListener(changeListener);
            writableValue = null;
            observableValue = null;
            changeListener = null;
        }
    }
}
