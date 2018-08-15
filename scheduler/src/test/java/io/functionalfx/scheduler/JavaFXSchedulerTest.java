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
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class JavaFXSchedulerTest {

    @Before
    public void setUp() {
        // JavaFX Application Thread initialisation
        JFXPanel panel = new JFXPanel();
    }

    @Test
    public void runOnJavaFXApplicationThread() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean isJavaFXApplicationThread = new AtomicBoolean(false);
        JavaFXScheduler.getScheduler().schedule(() -> {
            isJavaFXApplicationThread.set(Platform.isFxApplicationThread());
            latch.countDown();
        }, 1, TimeUnit.MILLISECONDS);
        latch.await(5, TimeUnit.SECONDS);
        Assertions.assertThat(isJavaFXApplicationThread).isTrue();
    }

    @Test
    public void testSchedule() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicBoolean done = new AtomicBoolean(false);
        JavaFXScheduler.getScheduler().schedule(() -> {
            done.set(true);
            latch.countDown();
        }, 500, TimeUnit.MILLISECONDS);
        Assertions.assertThat(done).isFalse();

        Assertions.assertThat(latch.await(300, TimeUnit.MILLISECONDS)).isFalse();
        Assertions.assertThat(latch.await(550, TimeUnit.MILLISECONDS)).isTrue();
        Assertions.assertThat(done).isTrue();
    }

    @Test
    public void testScheduleOnlyOnce() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        JavaFXScheduler.getScheduler().schedule(latch::countDown, 200, TimeUnit.MILLISECONDS);

        Assertions.assertThat(latch.await(300, TimeUnit.MILLISECONDS)).isFalse();
        Assertions.assertThat(latch.getCount()).isEqualTo(1);
        Assertions.assertThat(latch.await(600, TimeUnit.MILLISECONDS)).isFalse();
        Assertions.assertThat(latch.getCount()).isEqualTo(1);
    }

    @Test
    public void testSchedulePeriodically() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        JavaFXScheduler.getScheduler().schedulePeriodically(latch::countDown, 200, TimeUnit.MILLISECONDS);
        Assertions.assertThat(latch.await(500, TimeUnit.MILLISECONDS)).isFalse();
        Assertions.assertThat(latch.await(1100, TimeUnit.MILLISECONDS)).isTrue();
    }

    @Test
    public void cancelScheduledAction() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Cancellable cancellable = JavaFXScheduler.getScheduler().schedule(latch::countDown, 500, TimeUnit.MILLISECONDS);
        cancellable.cancel();
        Assertions.assertThat(cancellable.isCancelled()).isTrue();
        Assertions.assertThat(latch.await(1, TimeUnit.SECONDS)).isFalse();
    }
}
