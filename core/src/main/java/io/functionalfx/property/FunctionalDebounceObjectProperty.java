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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

class FunctionalDebounceObjectProperty<T> extends FunctionalObjectProperty<T> {

    private final Timeline timeline;
    private boolean isDebouncing = false;

    public FunctionalDebounceObjectProperty(ObservableValue<T> parent, long delayTime, TimeUnit timeUnit) {
        long delay = Math.max(0, timeUnit.toMillis(delayTime));
        timeline = new Timeline();
        timeline.setCycleCount(1);

        ObservableValues.addSafeValueListener(parent, newValue -> {
            if (isDebouncing) {
                timeline.stop();
            }

            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), event -> {
                timeline.stop();
                isDebouncing = false;
                set(newValue);
            }));
            isDebouncing = true;
            timeline.play();
        });
    }

}
