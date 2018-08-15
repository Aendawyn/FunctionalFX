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
package io.functionalfx.scheduler;

import io.functionalfx.lang.concurrent.Cancellable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class JavaFXScheduler {

    private static final JavaFXScheduler INSTANCE = new JavaFXScheduler();
    private static final int ONCE = 1;
    private static final int INDEFINITE = Animation.INDEFINITE;

    private JavaFXScheduler() {
        // nothing to do
    }

    public static JavaFXScheduler getScheduler() {
        return INSTANCE;
    }

    public Cancellable schedule(Runnable action, long delayTime, TimeUnit timeUnit) {
        return schedule(action, delayTime, timeUnit, ONCE);
    }

    public Cancellable schedulePeriodically(Runnable action, long delayTime, TimeUnit timeUnit) {
        return schedule(action, delayTime, timeUnit, INDEFINITE);
    }

    private Cancellable schedule(Runnable action, long delayTime, TimeUnit timeUnit, int count) {
        final long delayMs = timeUnit.toMillis(delayTime);

        if (delayMs < 0) {
            throw new IllegalArgumentException("Cannot schedule an action using negative delay");
        }

        if (delayMs == 0) {
            final ImmediateAction immediateAction = new ImmediateAction(action);
            immediateAction.run();
            return immediateAction;
        } else {
            final Timeline timeline = new Timeline();
            final CancellableScheduledAction scheduledAction = new CancellableScheduledAction(action, timeline, count == INDEFINITE);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delayMs), e -> scheduledAction.run()));
            timeline.setCycleCount(count);
            timeline.play();
            return scheduledAction;
        }
    }

    private static class CancellableScheduledAction implements Cancellable, Runnable {

        private final boolean isPeriodic;
        private Runnable action;
        private boolean isDone = false;
        private boolean isCancelled = false;
        private Timeline timeline;

        public CancellableScheduledAction(Runnable action, final Timeline timeline, boolean isPeriodic) {
            this.action = action;
            this.timeline = timeline;
            this.isPeriodic = isPeriodic;
        }

        @Override
        public boolean isCancelled() {
            return isCancelled;
        }

        @Override
        public void cancel() {
            dispose();
            isCancelled = true;
        }

        @Override
        public void run() {
            if (!isDone) {
                Platform.runLater(action);
                if (!isPeriodic) {
                    dispose();
                }
            }
        }

        private void dispose() {
            if (!isDone) {
                if (timeline != null) {
                    timeline.stop();
                }
                action = null;
                timeline = null;
                isDone = true;
            }
        }
    }

    private static class ImmediateAction extends CancellableScheduledAction {

        public ImmediateAction(Runnable action) {
            super(action, null, false);
        }

    }
}
