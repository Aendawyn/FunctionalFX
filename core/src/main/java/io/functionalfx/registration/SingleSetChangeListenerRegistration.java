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

import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

class SingleSetChangeListenerRegistration<T> implements ListenerRegistration {

    private ObservableSet<T> observableSet;
    private SetChangeListener<T> changeListener;
    private boolean isUnregistered;

    public SingleSetChangeListenerRegistration(ObservableSet<T> observableSet, SetChangeListener<T> listener) {
        this.observableSet = observableSet;
        this.changeListener = listener;
        this.isUnregistered = false;
        this.observableSet.addListener(listener);
    }

    @Override
    public boolean isUnregistered() {
        return isUnregistered;
    }

    @Override
    public void unregister() {
        if (!isUnregistered) {
            isUnregistered = true;
            observableSet.removeListener(changeListener);
            observableSet = null;
            changeListener = null;
        }
    }
}
