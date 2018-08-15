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
import javafx.beans.value.ObservableValue;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class FunctionalBufferObjectProperty<T> extends FunctionalObjectProperty<List<T>> {

    private BufferQueue<T> bufferQueue;
    private long valueCount;

    public FunctionalBufferObjectProperty(ObservableValue<T> parent, int count) {
        this(parent, count, count);
    }

    public FunctionalBufferObjectProperty(ObservableValue<T> parent, int count, int skip) {
        super(parent);
        bufferQueue = new BufferQueue<>();
        ObservableValues.addSafeValueListener(parent, newValue -> {
            if (valueCount % skip == 0) {
                bufferQueue.pushNewBuffer();
            }
            valueCount++;

            if (bufferQueue.hasBuffers()) {
                bufferQueue.addInAllBuffers(newValue);
                bufferQueue.getFirstBuffer().ifPresent(buffer -> {
                    if (buffer.size() == count) {
                        set(bufferQueue.popFirstBuffer());
                    }
                });
            }
        });
    }

    public FunctionalBufferObjectProperty(ObservableValue<T> parent, long delayTime, TimeUnit timeUnit) {
        super(parent);
        bufferQueue = new BufferQueue<>();
        bufferQueue.pushNewBuffer();

        ObservableValues.addSafeValueListener(parent, newValue -> {
            if (bufferQueue.hasBuffers()) {
                bufferQueue.addInFirstBuffer(newValue);
            }
        });

        final WeakReference<FunctionalBufferObjectProperty<T>> reference = new WeakReference<>(this);
        final AtomicReference<Cancellable> cancellableReference = new AtomicReference<>();
        cancellableReference.set(JavaFXScheduler.getScheduler().schedulePeriodically(() -> {
            final FunctionalBufferObjectProperty<T> property = reference.get();
            if (property != null) {
                if (bufferQueue.hasBuffers()) {
                    property.set(bufferQueue.popFirstBuffer());
                    bufferQueue.pushNewBuffer();
                }
            } else {
                cancellableReference.get().cancel();
                cancellableReference.set(null);
            }

        }, delayTime, timeUnit));
    }

    private static class BufferQueue<T> {

        private List<List<T>> buffers;

        public BufferQueue() {
            buffers = new LinkedList<>();
        }

        public void pushNewBuffer() {
            buffers.add(new LinkedList<>());
        }

        public boolean hasBuffers() {
            return !buffers.isEmpty();
        }

        public Optional<List<T>> getFirstBuffer() {
            if (buffers.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(buffers.get(0));
        }

        public List<T> popFirstBuffer() {
            if (buffers.isEmpty()) {
                throw new NoSuchElementException();
            }
            return buffers.remove(0);
        }

        public void addInFirstBuffer(T value) {
            if (hasBuffers()) {
                buffers.get(0).add(value);
            }
        }

        public void addInAllBuffers(T value) {
            buffers.forEach(buffer -> buffer.add(value));
        }
    }
}
