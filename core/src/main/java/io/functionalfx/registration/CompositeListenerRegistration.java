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

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.collections.*;

import java.util.LinkedList;
import java.util.List;

class CompositeListenerRegistration implements ListenerRegister {

    private final List<ListenerRegistration> registrations = new LinkedList<>();

    @Override
    public <T> ListenerRegistration registerListener(ObservableValue<T> observableValue, ChangeListener<T> listener) {
        return register(ListenerRegistrations.registerListener(observableValue, listener));
    }

    @Override
    public <T> ListenerRegistration registerListener(ObservableList<T> observableList, ListChangeListener<T> listener) {
        return register(ListenerRegistrations.registerListener(observableList, listener));
    }

    @Override
    public <T> ListenerRegistration registerListener(ObservableSet<T> observableSet, SetChangeListener<T> listener) {
        return register(ListenerRegistrations.registerListener(observableSet, listener));
    }

    @Override
    public <K, V> ListenerRegistration registerListener(ObservableMap<K, V> observableMap, MapChangeListener<K, V> listener) {
        return register(ListenerRegistrations.registerListener(observableMap, listener));
    }
    @Override
    public <T> ListenerRegistration registerBinding(WritableValue<T> writableValue, ObservableValue<T> observableValue) {
        return register(ListenerRegistrations.registerBinding(writableValue, observableValue));
    }
    @Override
    public <T> ListenerRegistration registerBidirectionalBinding(final Property<T> property1, final Property<T> property2) {
        return register(ListenerRegistrations.registerBidirectionalBinding(property1, property2));
    }

    @Override
    public boolean isUnregistered() {
        return registrations.isEmpty();
    }

    @Override
    public void unregister() {
        registrations.forEach(ListenerRegistration::unregister);
        registrations.clear();
    }

    private ListenerRegistration register(final ListenerRegistration registration) {
        registrations.add(registration);
        return registration;
    }
}
