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

import io.functionalfx.lang.concurrent.Cancellable;
import io.functionalfx.scheduler.JavaFXScheduler;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class FunctionalIntervalObjectProperty extends FunctionalObjectProperty<Long> {

    private long nextValue = 1;

    public FunctionalIntervalObjectProperty(long delayTime, TimeUnit timeUnit) {
        final WeakReference<FunctionalIntervalObjectProperty> reference = new WeakReference<>(this);
        final AtomicReference<Cancellable> cancellableReference = new AtomicReference<>();
        final Cancellable cancellable = JavaFXScheduler.getScheduler().schedulePeriodically(() -> {
            final FunctionalIntervalObjectProperty property = reference.get();
            if (property != null) {
                property.set(property.nextValue++);
            } else {
                cancellableReference.get().cancel();
                cancellableReference.set(null);
            }
        }, delayTime, timeUnit);
        cancellableReference.set(cancellable);
    }

}
