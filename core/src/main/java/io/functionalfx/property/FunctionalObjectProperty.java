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

import io.functionalfx.lang.function.*;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FunctionalObjectProperty<T> extends ObjectPropertyBase<T> {

    private final Collection<Object> parents;

    protected FunctionalObjectProperty() {
        this(Collections.emptyList());
    }

    protected FunctionalObjectProperty(ObservableValue<?> parent) {
        this(parent == null ? Collections.emptyList() : Collections.singletonList(parent));
    }

    protected FunctionalObjectProperty(Collection<? extends ObservableValue<?>> parents) {
        this.parents = new ArrayList<>(parents);
    }

    public static <T> FunctionalObjectProperty<T> fromProperty(ObservableValue<T> objectProperty) {
        return new FunctionalWrapperObjectProperty<>(objectProperty);
    }

    public static <T extends Event> FunctionalObjectProperty<T> fromEvent(Node node, EventType<T> eventType) {
        return new FunctionalFromEventObjectProperty<>(node, eventType);
    }

    public static <T, R> FunctionalObjectProperty<R> combineLatest(Collection<? extends ObservableValue<? extends T>> observables, FunctionN<R> combiner) {
        return new FunctionalCombineLatestObjectProperty<>(observables, combiner);
    }

    public static <T1, T2, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, BiFunction<T1, T2, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2), Functions.from(combiner));
    }

    public static <T1, T2, T3, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, Function3<T1, T2, T3, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, Function4<T1, T2, T3, T4, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, T5, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, Function5<T1, T2, T3, T4, T5, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4, o5), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, T5, T6, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, Function6<T1, T2, T3, T4, T5, T6, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4, o5, o6), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, ObservableValue<T7> o7, Function7<T1, T2, T3, T4, T5, T6, T7, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4, o5, o6, o7), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, ObservableValue<T7> o7, ObservableValue<T8> o8, Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8), Functions.from(combiner));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> FunctionalObjectProperty<R> combineLatest(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, ObservableValue<T7> o7, ObservableValue<T8> o8, ObservableValue<T9> o9, Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> combiner) {
        return combineLatest(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8, o9), Functions.from(combiner));
    }

    public static <T> FunctionalObjectProperty<T> merge(Collection<? extends ObservableValue<? extends T>> observables) {
        return new FunctionalMergeObjectProperty<>(observables);
    }
    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2) {
        return merge(Arrays.asList(o1, o2));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3) {
        return merge(Arrays.asList(o1, o2, o3));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4) {
        return merge(Arrays.asList(o1, o2, o3, o4));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4, ObservableValue<T> o5) {
        return merge(Arrays.asList(o1, o2, o3, o4, o5));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4, ObservableValue<T> o5, ObservableValue<T> o6) {
        return merge(Arrays.asList(o1, o2, o3, o4, o5, o6));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4, ObservableValue<T> o5, ObservableValue<T> o6, ObservableValue<T> o7) {
        return merge(Arrays.asList(o1, o2, o3, o4, o5, o6, o7));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4, ObservableValue<T> o5, ObservableValue<T> o6, ObservableValue<T> o7, ObservableValue<T> o8) {
        return merge(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8));
    }

    public static <T> FunctionalObjectProperty<T> merge(ObservableValue<T> o1, ObservableValue<T> o2, ObservableValue<T> o3, ObservableValue<T> o4, ObservableValue<T> o5, ObservableValue<T> o6, ObservableValue<T> o7, ObservableValue<T> o8, ObservableValue<T> o9) {
        return merge(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8, o9));
    }
    public static <T> FunctionalObjectProperty<T> just(T value) {
        return new FunctionalJustObjectProperty<>(value);
    }

    public static FunctionalObjectProperty<Long> interval(long delayTime, TimeUnit timeUnit) {
        return new FunctionalIntervalObjectProperty(delayTime, timeUnit);
    }

    public <T, R> FunctionalObjectProperty<R> withLatestFrom(Collection<? extends ObservableValue<? extends T>> observables, FunctionN<R> combiner) {
        return new FunctionalWithLatestFromObjectProperty<>(this, observables, combiner);
    }
    public <T1, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, BiFunction<T, T1, R> combiner) {
        return withLatestFrom(Collections.singletonList(o1), Functions.from(combiner));
    }
    public <T1, T2, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, Function3<T, T1, T2, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2), Functions.from(combiner));
    }
    public <T1, T2, T3, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, Function4<T, T1, T2, T3, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3), Functions.from(combiner));
    }
    public <T1, T2, T3, T4, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, Function5<T, T1, T2, T3, T4, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3, o4), Functions.from(combiner));
    }
    public <T1, T2, T3, T4, T5, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, Function6<T, T1, T2, T3, T4, T5, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3, o4, o5), Functions.from(combiner));
    }
    public <T1, T2, T3, T4, T5, T6, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, Function7<T, T1, T2, T3, T4, T5, T6, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3, o4, o5, o6), Functions.from(combiner));
    }
    public <T1, T2, T3, T4, T5, T6, T7, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, ObservableValue<T7> o7, Function8<T, T1, T2, T3, T4, T5, T6, T7, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3, o4, o5, o6, o7), Functions.from(combiner));
    }
    public <T1, T2, T3, T4, T5, T6, T7, T8, R> FunctionalObjectProperty<R> withLatestFrom(ObservableValue<T1> o1, ObservableValue<T2> o2, ObservableValue<T3> o3, ObservableValue<T4> o4, ObservableValue<T5> o5, ObservableValue<T6> o6, ObservableValue<T7> o7, ObservableValue<T8> o8, Function9<T, T1, T2, T3, T4, T5, T6, T7, T8, R> combiner) {
        return withLatestFrom(Arrays.asList(o1, o2, o3, o4, o5, o6, o7, o8), Functions.from(combiner));
    }
    public <R> FunctionalObjectProperty<R> map(Function<T, R> mapper) {
        return new FunctionalMapObjectProperty<>(this, mapper);
    }

    public FunctionalObjectProperty<T> onValueChanged(Consumer<T> consumer) {
        return new FunctionalOnValueChangedObjectProperty<>(this, consumer);
    }

    public FunctionalObjectProperty<T> filter(Predicate<T> predicate) {
        return new FunctionalFilterObjectProperty<>(this, predicate);
    }

    public FunctionalObjectProperty<T> startWith(T initialValue) {
        return new FunctionalStartWithObjectProperty<>(this, initialValue);
    }

    public <R> FunctionalObjectProperty<R> switchMap(Function<T, ObservableValue<R>> mapper) {
        return new FunctionalSwitchMapObjectProperty<>(this, mapper);
    }

    public FunctionalObjectProperty<T> distinct() {
        return new FunctionalDistinctObjectProperty<>(this);
    }

    public FunctionalObjectProperty<T> scan(BiFunction<T, T, T> accumulator) {
        return new FunctionalScanObjectProperty<>(this, accumulator);
    }

    public <R> FunctionalObjectProperty<R> scan(R initialValue, BiFunction<R, T, R> accumulator) {
        return new FunctionalScanObjectProperty<>(this, initialValue, accumulator);
    }

    @SuppressWarnings("unchecked")
    public <R extends T> FunctionalObjectProperty<R> ofType(Class<R> aClass) {
        return filter(aClass::isInstance).map(aClass::cast);
    }

    public FunctionalObjectProperty<T> debounce(long delayTime, TimeUnit timeUnit) {
        return new FunctionalDebounceObjectProperty<>(this, delayTime, timeUnit);
    }

    public FunctionalObjectProperty<List<T>> buffer(int count) {
        return new FunctionalBufferObjectProperty<>(this, count);
    }

    public FunctionalObjectProperty<List<T>> buffer(int count, int skip) {
        return new FunctionalBufferObjectProperty<>(this, count, skip);
    }

    public FunctionalObjectProperty<List<T>> buffer(long delayTime, TimeUnit timeUnit) {
        return new FunctionalBufferObjectProperty<>(this, delayTime, timeUnit);
    }

    public Object getBean() {
        return null;
    }

    public String getName() {
        return "";
    }

    public Collection<Object> getParents() {
        return Collections.unmodifiableCollection(parents);
    }

}