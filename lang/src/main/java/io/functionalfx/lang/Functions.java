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
package io.functionalfx.lang;

import java.util.List;
import java.util.function.BiFunction;

@SuppressWarnings("unchecked")
public class Functions {

    private Functions() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    public static <T1, T2, R> FunctionN<R> from(BiFunction<T1, T2, R> function) {
        return parameters -> {
            checkParameters(parameters,2);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1));
        };
    }

    public static <T1, T2, T3, R> FunctionN<R> from(Function3<T1, T2, T3, R> function) {
        return parameters -> {
            checkParameters(parameters,3);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2));
        };
    }

    public static <T1, T2, T3, T4, R> FunctionN<R> from(Function4<T1, T2, T3, T4, R> function) {
        return parameters -> {
            checkParameters(parameters,4);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3));
        };
    }

    public static <T1, T2, T3, T4, T5, R> FunctionN<R> from(Function5<T1, T2, T3, T4, T5, R> function) {
        return parameters -> {
            checkParameters(parameters,5);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3), (T5) parameters.get(4));
        };
    }

    public static <T1, T2, T3, T4, T5, T6, R> FunctionN<R> from(Function6<T1, T2, T3, T4, T5, T6, R> function) {
        return parameters -> {
            checkParameters(parameters,6);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3), (T5) parameters.get(4), (T6) parameters.get(5));
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> FunctionN<R> from(Function7<T1, T2, T3, T4, T5, T6, T7, R> function) {
        return parameters -> {
            checkParameters(parameters,7);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3), (T5) parameters.get(4), (T6) parameters.get(5), (T7) parameters.get(6));
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> FunctionN<R> from(Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function) {
        return parameters -> {
            checkParameters(parameters,8);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3), (T5) parameters.get(4), (T6) parameters.get(5), (T7) parameters.get(6), (T8) parameters.get(7));
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> FunctionN<R> from(Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function) {
        return parameters -> {
            checkParameters(parameters,9);
            return function.apply((T1) parameters.get(0), (T2) parameters.get(1), (T3) parameters.get(2), (T4) parameters.get(3), (T5) parameters.get(4), (T6) parameters.get(5), (T7) parameters.get(6), (T8) parameters.get(7), (T9) parameters.get(8));
        };
    }

    private static void checkParameters(final List<Object> parameters, int expectedSize) {
        if (parameters.size() != expectedSize) {
            throw new IllegalArgumentException(String.format("Expecting %d parameters", expectedSize));
        }
    }
}
