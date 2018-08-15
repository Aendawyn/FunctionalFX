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
package io.functionalfx.lang.function;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.BiFunction;

public class FunctionsTest {

    @Test
    public void testFromBiFunctionArgs() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatCode(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction3Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction4Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction5Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4, 5))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction6Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction7Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction8Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8))).doesNotThrowAnyException();
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void testFromFunction9Args() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Collections.singletonList(1)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4, 5)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
        Assertions.assertThatCode(() -> Functions.from((a, b, c, d, e, f, g, h, i) -> null).apply(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))).doesNotThrowAnyException();
    }

    @Test
    public void testFromBiFunction() {
        final BiFunction<String, String, String> z = (a, b) -> concat(a, b);
        Assertions.assertThat(z.apply("1", "2")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2")));
    }

    @Test
    public void testFromFunction3() {
        final Function3<String, String, String, String> z = (a, b, c) -> concat(a, b, c);
        Assertions.assertThat(z.apply("1", "2", "3")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3")));
    }

    @Test
    public void testFromFunction4() {
        final Function4<String, String, String, String, String> z = (a, b, c, d) -> concat(a, b, c, d);
        Assertions.assertThat(z.apply("1", "2", "3", "4")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4")));
    }

    @Test
    public void testFromFunction5() {
        final Function5<String, String, String, String, String, String> z = (a, b, c, d, e) -> concat(a, b, c, d, e);
        Assertions.assertThat(z.apply("1", "2", "3", "4", "5")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4", "5")));
    }

    @Test
    public void testFromFunction6() {
        final Function6<String, String, String, String, String, String, String> z = (a, b, c, d, e, f) -> concat(a, b, c, d, e, f);
        Assertions.assertThat(z.apply("1", "2", "3", "4", "5", "6")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4", "5", "6")));
    }

    @Test
    public void testFromFunction7() {
        final Function7<String, String, String, String, String, String, String, String> z = (a, b, c, d, e, f, g) -> concat(a, b, c, d, e, f, g);
        Assertions.assertThat(z.apply("1", "2", "3", "4", "5", "6", "7")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
    }

    @Test
    public void testFromFunction8() {
        final Function8<String, String, String, String, String, String, String, String, String> z = (a, b, c, d, e, f, g, h) -> concat(a, b, c, d, e, f, g, h);
        Assertions.assertThat(z.apply("1", "2", "3", "4", "5", "6", "7", "8")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8")));
    }

    @Test
    public void testFromFunction9() {
        final Function9<String, String, String, String, String, String, String, String, String, String> z = (a, b, c, d, e, f, g, h, i) -> concat(a, b, c, d, e, f, g, h, i);
        Assertions.assertThat(z.apply("1", "2", "3", "4", "5", "6", "7", "8", "9")).isEqualTo(Functions.from(z).apply(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
    }

    private String concat(String... strings) {
        return String.join("", strings);
    }
}
