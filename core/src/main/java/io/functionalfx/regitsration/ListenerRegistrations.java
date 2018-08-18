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
package io.functionalfx.regitsration;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
import javafx.collections.*;

public class ListenerRegistrations {

    private ListenerRegistrations() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    public static <T> ListenerRegistration registerListener(final ObservableValue<T> observableValue, final ChangeListener<T> listener) {
        return registerListener(observableValue, listener, false);
    }

    public static <T> ListenerRegistration registerListener(final ObservableValue<T> observableValue, final ChangeListener<T> listener, boolean immediate) {
        if (immediate) {
            listener.changed(observableValue, observableValue.getValue(), observableValue.getValue());
        }
        return new SingleChangeListenerRegistration<>(observableValue, listener);
    }

    public static <T> ListenerRegistration registerListener(final ObservableList<T> observableList, final ListChangeListener<T> listener) {
        return new SingleListChangeListenerRegistration<>(observableList, listener);
    }

    public static <T> ListenerRegistration registerListener(final ObservableSet<T> observableSet, final SetChangeListener<T> listener) {
        return new SingleSetChangeListenerRegistration<>(observableSet, listener);
    }

    public static <K, V> ListenerRegistration registerListener(final ObservableMap<K, V> observableMap, final MapChangeListener<K, V> listener) {
        return new SingleMapChangeListenerRegistration<>(observableMap, listener);
    }

    public static <T> ListenerRegistration registerBinding(final WritableValue<T> writableValue, final ObservableValue<T> observableValue) {
        return new SingleBindingListenerRegistration<>(writableValue, observableValue);
    }

    public static <T> ListenerRegistration registerBidirectionalBinding(final Property<T> property1, final Property<T> property2) {
        return new SingleBidirectionalBindingListenerRegistration<>(property1, property2);
    }

    public static ListenerRegister newListenerRegister() {
        return new CompositeListenerRegistration();
    }
}
